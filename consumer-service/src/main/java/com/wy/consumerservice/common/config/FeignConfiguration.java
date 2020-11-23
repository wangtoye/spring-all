package com.wy.consumerservice.common.config;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.wangtoye.feignhystrixthreadlocalspringbootstarter.strategy.FeignHystrixConcurrencyStrategy;
import com.wangtoye.feignhystrixthreadlocalspringbootstarter.strategy.call.warpper.FeignHystrixCallableWrapper;
import com.wy.consumerservice.common.strategy.warpper.RandomNumCallableWrapperImpl;
import com.wy.consumerservice.common.strategy.warpper.ThreadIdCallableWrapperImpl;
import com.wy.consumerservice.utils.ThreadLocalUtil;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * feignClient的配置类
 * 日志级别 契约配置 拦截器配置
 *
 * @author wangtoye
 */
@Configuration
public class FeignConfiguration {

    /**
     * 日志配置
     *
     * @return 日志级别
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

//    /**
//     *  契约配置
//     * @return 契约类型
//     */
//    @Bean
//    Contract feignContract(){
//        //默认使用SpringMvcContract，也可以选择feign自身的或者hystrix的
//        return new Contract.Default();
//    }

    /**
     * 拦截器
     *
     * @return 拦截器 发送请求
     */
    @Bean
    RequestInterceptor threadLocalInterceptor() {
        return template -> {
            System.out.println("子线程，线程id：" + Thread.currentThread().getId() + ",主线程id：" + ThreadLocalUtil.get(
                    "threadId"));
            System.out.println("子线程，线程id：" + Thread.currentThread().getId() + ",随机数：" + ThreadLocalUtil.get(
                    "randomNum"));
        };
    }

//    /**
//     * 超时配置
//     * @return 超时配置
//     */
//    @Bean
//    Request.Options options(){
//        //此处配置有限ribbon
//        return new Request.Options(1000,500);
//    }

    @Bean
    HystrixConcurrencyStrategy hystrixConcurrencyStrategy() {
        List<FeignHystrixCallableWrapper> wrappers = new ArrayList<>();
        wrappers.add(new ThreadIdCallableWrapperImpl());
        wrappers.add(new RandomNumCallableWrapperImpl());
        return new FeignHystrixConcurrencyStrategy(wrappers);
    }
}