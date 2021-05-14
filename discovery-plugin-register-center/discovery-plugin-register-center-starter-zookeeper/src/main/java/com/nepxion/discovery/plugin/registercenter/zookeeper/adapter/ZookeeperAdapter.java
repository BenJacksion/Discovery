package com.nepxion.discovery.plugin.registercenter.zookeeper.adapter;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.Map;

import org.springframework.cloud.zookeeper.discovery.ZookeeperServer;

import com.nepxion.discovery.plugin.framework.adapter.AbstractPluginAdapter;
import com.netflix.loadbalancer.Server;

public class ZookeeperAdapter extends AbstractPluginAdapter {
    @Override
    public Map<String, String> getServerMetadata(Server server) {
        if (server instanceof ZookeeperServer) {
            ZookeeperServer zookeeperServer = (ZookeeperServer) server;

            return zookeeperServer.getInstance().getPayload().getMetadata();
        }

        return emptyMetadata;

        // throw new DiscoveryException("Server instance isn't the type of ZookeeperServer");
    }
}