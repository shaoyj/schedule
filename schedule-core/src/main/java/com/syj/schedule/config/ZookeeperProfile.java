package com.syj.schedule.config;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.ZKPaths;
import org.springframework.util.Assert;

/**
 * Created by lancey on 15/11/19.
 */
public class ZookeeperProfile {

    private String connectStr;
    private String rootNode;

    private RetryPolicy retryPolicy;

    private volatile CuratorFramework client;


    public ZookeeperProfile(String connectStr, String rootNode) {
        this.connectStr = connectStr;
        this.rootNode = rootNode;
        this.retryPolicy = new RetryNTimes(1000, 3);
    }


    public String getConnectStr() {
        return connectStr;
    }

    public String getRootNode() {
        return rootNode;
    }

    public RetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

    public CuratorFramework createClient() {
        if (client == null) {
            CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder().connectString(this.getConnectStr()).retryPolicy(this.getRetryPolicy());
            client = builder.build();
            client.start();
        }
        return client;
    }

    public String makePath(String firstPath, String... path) {
        Assert.hasLength(firstPath, "firstPath no length");
        if (ArrayUtils.isEmpty(path)) {
            return ZKPaths.makePath(rootNode, firstPath);
        }
        return ZKPaths.makePath(rootNode, firstPath, path);
    }
}
