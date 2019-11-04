package com.wy.gatewayservice.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wangye
 * @date: 2019-07-02
 * Description:
 */
@Configuration
public class FilterConfiguration {
    @Bean
    public ComputingTimeGatewayFilterFactory computingTimeGatewayFilterFactory() {
        return new ComputingTimeGatewayFilterFactory();
    }
}
