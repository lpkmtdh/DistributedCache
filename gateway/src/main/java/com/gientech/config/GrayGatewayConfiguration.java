//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gientech.config;


import com.gientech.filter.GrayLoadBalancerClientFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.gateway.config.LoadBalancerProperties;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrayGatewayConfiguration {
    public GrayGatewayConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean({GrayLoadBalancerClientFilter.class})
    public GrayLoadBalancerClientFilter getGrayLoadBalancerClientFilter(LoadBalancerClientFactory clientFactory, LoadBalancerProperties properties) {
        return new GrayLoadBalancerClientFilter(clientFactory, properties);
    }




}
