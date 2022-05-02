package com.example.ruleserver.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
public class RibbonConfiguration {

    final IClientConfig ribbonClientConfig;

    @Bean
    public IRule rule(IClientConfig ribbonClient) {
        return new AvailabilityFilteringRule();
    }
}
