package com.example.ruleserver;

import com.example.ruleserver.config.RibbonConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@RibbonClient(name = "customer-service", configuration = RibbonConfiguration.class)
@RequiredArgsConstructor
public class RuleServerApplication {

    @LoadBalanced // 로드밸런싱 사용
    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(RuleServerApplication.class, args);
    }
}
