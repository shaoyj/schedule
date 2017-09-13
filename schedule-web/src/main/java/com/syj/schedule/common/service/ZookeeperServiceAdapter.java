package com.syj.schedule.common.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syj.schedule.back.task.domain.ChildCompator;
import com.syj.schedule.common.domain.BaseDomainZK;
import com.syj.schedule.common.domain.PageView;
import com.syj.schedule.common.domain.ResultHandler;
import com.syj.schedule.common.util.PageUtil;
import com.syj.schedule.common.zk.ZookeeperClient;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * dataBaseService适配器
 * Created by syj on 2016/10/11 0011.
 */
public abstract class ZookeeperServiceAdapter<T extends BaseDomainZK> {


    /**
     * 获取class
     *
     * @return
     */
    public Class<T> getClassType() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<T>) params[0];
    }


    public ResultHandler saveData(T data, ZookeeperClient zookeeperClient, String path) {
        ResultHandler result = new ResultHandler();
        String path2 = ZKPaths.makePath(path, data.getFlagName());
        try {
            zookeeperClient.getClient().create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path2, data.toString().getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(-1);
            result.setMessage("添加失败");
        }
        return result;
    }

    public ResultHandler deleteData(String flagName, ZookeeperClient zookeeperClient, String path) {
        ResultHandler result = new ResultHandler();
        String path2 = ZKPaths.makePath(path, flagName);
        try {
            zookeeperClient.getClient().delete().guaranteed().forPath(path2);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(-1);
            result.setMessage("删除失败");
        }
        return result;
    }

    public ResultHandler updateData(T data, String oldFlagName, ZookeeperClient zookeeperClient, String path) {
        ResultHandler result = new ResultHandler();

        String path2 = ZKPaths.makePath(path, oldFlagName);
        if (oldFlagName.equals(data.getFlagName())) {
            //更新
            try {
                zookeeperClient.getClient().setData().forPath(path2, data.toString().getBytes("utf-8"));
            } catch (Exception e) {
                e.printStackTrace();
                result.setCode(-1);
                result.setMessage("更新失败");
            }
        } else {
            try {
                zookeeperClient.getClient().delete().guaranteed().forPath(path2);
                //保存
                path2 = ZKPaths.makePath(path, data.getFlagName());
                zookeeperClient.getClient().create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path2, data.toString().getBytes("utf-8"));
            } catch (Exception e) {
                e.printStackTrace();
                result.raise(e);
            }
        }
        return result;
    }

    public T findData(String flagName, ZookeeperClient zookeeperClient, String path) {

        String path2 = ZKPaths.makePath(path, flagName);

        T result = null;

        try {
            byte[] data = zookeeperClient.getClient().getData().forPath(path2);
            result = JSONObject.toJavaObject((JSON) JSONObject.parse(new String(data, "utf-8")), getClassType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public PageView<T> queryData(Integer page, Integer pageSize, String flagName, ZookeeperClient zookeeperClient, String path) {
        //分页
        PageView<T> pageView = PageView.newPage(PageUtil.curPageDeal(page), PageUtil.pageSizeDeal(pageSize));
        List<T> result = new ArrayList<>();
        try {
            //地址不存在则新建
            if (zookeeperClient.exists(path) == false) {
                try {
                    zookeeperClient.getClient().create().creatingParentsIfNeeded().forPath(path, new byte[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //装填
            List<String> dataSourcesList = zookeeperClient.getClient().getChildren().forPath(path);
            Collections.sort(dataSourcesList, new ChildCompator());

            if (CollectionUtils.isNotEmpty(dataSourcesList)) {
                pageView.setTotalSize(dataSourcesList.size());
                Iterator<String> var2 = dataSourcesList.iterator();
                int countPage = 0; //计数
                int countPageSize = 0; //计数
                Integer limitNum = (PageUtil.curPageDeal(page) - 1) * PageUtil.pageSizeDeal(pageSize);
                while (var2.hasNext()) {
                    //处理page
                    countPage++;
                    if (countPage <= limitNum) {
                        var2.next();
                        continue;
                    }
                    //装填数据 有无nodeName参数分两种情况处理
                    String next = var2.next();
                    if (StringUtils.isNotBlank(flagName)) {
                        if (flagName.equals(next)) {
                            result.add(findData(next, zookeeperClient, path));
                        }
                    } else {
                        result.add(findData(next, zookeeperClient, path));
                    }
                    //处理pageSize
                    countPageSize++;
                    if (countPageSize == PageUtil.pageSizeDeal(pageSize)) {
                        break;
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        pageView.setResult(result);
        return pageView;
    }
}
