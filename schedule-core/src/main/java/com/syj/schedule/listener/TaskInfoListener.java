package com.syj.schedule.listener;

import com.syj.schedule.core.ScheduleSpringFactroy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * 负责监听taskInfo的列表数据修改
 * 如果发生时间、是否动作的修改需要改变当前的列表的执行
 * Created by lancey on 16/2/28.
 */
public class TaskInfoListener implements Closeable {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskInfoListener.class);

    private String path;
    private PathChildrenCache cache;

    public TaskInfoListener(String path) {
        this.path = path;
    }


    public void exec(final ScheduleSpringFactroy factroy) {
        cache = new PathChildrenCache(factroy.getClient(), path, false);
        cache.getListenable().addListener((CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) -> {
            switch (pathChildrenCacheEvent.getType()) {
                case CHILD_ADDED:
                    factroy.handleTaskAdd(pathChildrenCacheEvent.getData().getPath());
                    break;
                case CHILD_UPDATED:
                    factroy.handleTaskUpdate(pathChildrenCacheEvent.getData().getPath());
                    break;
                case CHILD_REMOVED:
                    factroy.handleTaskRemove(pathChildrenCacheEvent.getData().getPath());
                    break;
                default:
                    LOGGER.debug("TASK INFO data:{}, event:{}", pathChildrenCacheEvent.getData(), pathChildrenCacheEvent.getType());
            }
        });
        try {
            cache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Closes this stream and releases any system resources associated
     * with it. If the stream is already closed then invoking this
     * method has no effect.
     *
     * @throws java.io.IOException if an I/O error occurs
     */
    @Override
    public void close() throws IOException {
        if (cache != null) {
            cache.close();
        }
    }
}
