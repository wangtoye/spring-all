package com.wy.commonservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * @author : wangtoye
 * @date : 2019-11-04
 * Description:
 */
@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbine
@EnableZipkinServer
public class CommonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonServiceApplication.class, args);
    }

}
