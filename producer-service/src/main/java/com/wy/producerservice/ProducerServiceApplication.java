package com.wy.producerservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author : wangtoye
 * @date : 2019-11-04
 * Description:
 */
@SpringBootApplication
@EnableHystrix
@MapperScan("com.wy.producerservice.mapper")
@EnableTransactionManagement
public class ProducerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerServiceApplication.class, args);
    }
}
