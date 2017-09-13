package com.syj.schedule.common.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.data.Stat;

public class ZookeeperClient {

    private String connectString;
    private CuratorFramework client;

    public ZookeeperClient(String connectString) {
        this.connectString = connectString;
    }

    public void init() {
        if (this.client == null) {
            this.client = CuratorFrameworkFactory.newClient(this.connectString, new RetryNTimes(3000, 6));
            this.client.start();
        }

    }

    public void close() {
        if (this.client != null) {
            this.client.close();
        }

    }

    public CuratorFramework getClient() {
        return this.client;
    }

    public boolean exists(String node) {
        try {
            Stat stat = (Stat) this.getClient().checkExists().forPath(node);
            return stat != null;
        } catch (Exception var3) {
            var3.printStackTrace();
            return false;
        }
    }

    public boolean create(String node) {
        try {
            this.getClient().create().forPath(node, new byte[0]);
            return true;
        } catch (Exception var3) {
            var3.printStackTrace();
            return false;
        }
    }

    public boolean createParentsIfNeeded(String node) {
        try {
            this.getClient().create().creatingParentsIfNeeded().forPath(node, new byte[0]);
            return true;
        } catch (Exception var3) {
            var3.printStackTrace();
            return false;
        }
    }

    public boolean delete(String node) {
        try {
            this.getClient().delete().deletingChildrenIfNeeded().forPath(node);
            return true;
        } catch (Exception var3) {
            var3.printStackTrace();
            return false;
        }
    }
}
