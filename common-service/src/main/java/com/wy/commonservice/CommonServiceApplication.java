package com.wy.commonservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import zipkin2.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbine
//@EnableApolloConfig
@EnableZipkinServer
//@EnableAdminServer
public class CommonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonServiceApplication.class, args);
    }

}
