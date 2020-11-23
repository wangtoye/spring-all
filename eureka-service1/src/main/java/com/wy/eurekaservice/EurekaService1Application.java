package com.wy.eurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author : wangtoye
 * @date : 2019-11-04
 * Description:
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaService1Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaService1Application.class, args);
    }

}
