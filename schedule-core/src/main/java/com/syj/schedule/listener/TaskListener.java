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
 * 任务管理监控
 * Created by lancey on 16/3/5.
 */
public class TaskListener implements Closeable {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskListener.class);
    private String path;
    private PathChildrenCache cache;

    public TaskListener(String path) {
        this.path = path;
    }

    public void exec(final ScheduleSpringFactroy factroy) {
        cache = new PathChildrenCache(factroy.getClient(), path, true);
        cache.getListenable().addListener((CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) -> {
            if (PathChildrenCacheEvent.Type.CHILD_REMOVED.equals(pathChildrenCacheEvent.getType())) {
                //需要处理是否需要更新主机
                Thread.sleep(2000);
                factroy.handleHostRemove(new String(pathChildrenCacheEvent.getData().getData()));
            } else {
                LOGGER.debug("skip event:{}", pathChildrenCacheEvent);
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
