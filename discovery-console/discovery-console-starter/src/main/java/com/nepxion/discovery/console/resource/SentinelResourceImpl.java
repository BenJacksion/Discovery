package com.nepxion.discovery.console.resource;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.nepxion.discovery.common.entity.ResultEntity;
import com.nepxion.discovery.console.rest.SentinelClearRestInvoker;
import com.nepxion.discovery.console.rest.SentinelUpdateRestInvoker;

public class SentinelResourceImpl implements SentinelResource {
    @Autowired
    private ServiceResource serviceResource;

    @Autowired
    private RestTemplate consoleRestTemplate;

    @Override
    public List<ResultEntity> updateSentinel(String ruleType, String serviceId, String rule) {
        SentinelUpdateRestInvoker sentinelUpdateRestInvoker = new SentinelUpdateRestInvoker(serviceResource, serviceId, consoleRestTemplate, ruleType, rule);

        return sentinelUpdateRestInvoker.invoke();
    }

    @Override
    public List<ResultEntity> clearSentinel(String ruleType, String serviceId) {
        SentinelClearRestInvoker sentinelClearRestInvoker = new SentinelClearRestInvoker(serviceResource, serviceId, consoleRestTemplate, ruleType);

        return sentinelClearRestInvoker.invoke();
    }
}