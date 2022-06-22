package com.sandbox.bdd.stepDefs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = { "com.*" })
@EnableConfigurationProperties(value = { EndPointsInfo.class })
@PropertySource(value = "/Users/khanabdulshariff/Downloads/BraintreeSandBox/src/test/resources/static.properties")
public class TestManagerConfig {
    @Bean
    @ConfigurationProperties
    public EndPointsInfo braintreeEndpoint() {
        return new EndPointsInfo();
    }
    @Bean
    public RestTemplate braintreeRestTemplate() { return new RestTemplate(); }
}
