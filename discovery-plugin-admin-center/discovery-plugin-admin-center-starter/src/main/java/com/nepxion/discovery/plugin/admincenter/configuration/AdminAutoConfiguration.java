package com.nepxion.discovery.plugin.admincenter.configuration;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @author Ning Zhang
 * @version 1.0
 */

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.nepxion.discovery.plugin.admincenter.endpoint.ConfigEndpoint;
import com.nepxion.discovery.plugin.admincenter.endpoint.GatewayStrategyRouteEndpoint;
import com.nepxion.discovery.plugin.admincenter.endpoint.GitEndpoint;
import com.nepxion.discovery.plugin.admincenter.endpoint.InspectorEndpoint;
import com.nepxion.discovery.plugin.admincenter.endpoint.RouterEndpoint;
import com.nepxion.discovery.plugin.admincenter.endpoint.SentinelCoreEndpoint;
import com.nepxion.discovery.plugin.admincenter.endpoint.SentinelParamEndpoint;
import com.nepxion.discovery.plugin.admincenter.endpoint.StrategyEndpoint;
import com.nepxion.discovery.plugin.admincenter.endpoint.VersionEndpoint;
import com.nepxion.discovery.plugin.admincenter.endpoint.ZuulStrategyRouteEndpoint;
import com.nepxion.discovery.plugin.strategy.gateway.route.GatewayStrategyRoute;
import com.nepxion.discovery.plugin.strategy.wrapper.StrategyWrapper;
import com.nepxion.discovery.plugin.strategy.zuul.route.ZuulStrategyRoute;

@Configuration
@Import(SwaggerConfiguration.class)
@AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE)
public class AdminAutoConfiguration {
    protected static class AdminEndpointConfiguration {
        @Bean
        public ConfigEndpoint configEndpoint() {
            return new ConfigEndpoint();
        }

        @Bean
        public VersionEndpoint versionEndpoint() {
            return new VersionEndpoint();
        }

        @Bean
        public InspectorEndpoint inspectorEndpoint() {
            return new InspectorEndpoint();
        }

        @Bean
        public RouterEndpoint routerEndpoint() {
            return new RouterEndpoint();
        }

        @Bean
        public GitEndpoint gitEndpoint() {
            return new GitEndpoint();
        }
    }

    @ConditionalOnClass(StrategyWrapper.class)
    protected static class StrategyEndpointConfiguration {
        @Bean
        public StrategyEndpoint strategyEndpoint() {
            return new StrategyEndpoint();
        }
    }

    @ConditionalOnClass(FlowRule.class)
    protected static class SentinelCoreEndpointConfiguration {
        @Bean
        public SentinelCoreEndpoint sentinelCoreEndpoint() {
            return new SentinelCoreEndpoint();
        }
    }

    @ConditionalOnClass(ParamFlowRule.class)
    protected static class SentinelParamEndpointConfiguration {
        @Bean
        public SentinelParamEndpoint sentinelParamEndpoint() {
            return new SentinelParamEndpoint();
        }
    }

    @ConditionalOnBean(GatewayStrategyRoute.class)
    protected static class GatewayStrategyRouteEndpointConfiguration {
        @Bean
        @ConditionalOnProperty(value = "spring.cloud.gateway.discovery.locator.enabled", havingValue = "false", matchIfMissing = true)
        public GatewayStrategyRouteEndpoint gatewayStrategyRouteEndpoint() {
            return new GatewayStrategyRouteEndpoint();
        }
    }

    @ConditionalOnBean(ZuulStrategyRoute.class)
    protected static class ZuulStrategyRouteEndpointConfiguration {
        @Bean
        public ZuulStrategyRouteEndpoint zuulStrategyRouteEndpoint() {
            return new ZuulStrategyRouteEndpoint();
        }
    }

    @ConditionalOnClass(WebMvcConfigurer.class)
    protected static class WebMvcActivationConfiguration {
        @Bean
        @ConditionalOnProperty(value = "cors.registry.enabled", matchIfMissing = false)
        public CorsRegistryConfiguration corsRegistryConfiguration() {
            return new CorsRegistryConfiguration();
        }
    }
}