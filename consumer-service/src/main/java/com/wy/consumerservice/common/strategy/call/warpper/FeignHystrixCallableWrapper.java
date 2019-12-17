package com.wy.consumerservice.common.strategy.call.warpper;

import java.util.concurrent.Callable;

/**
 * Hystrix CallBack 装饰器定义
 * 主线程本地线程变量传入hystrix子线程
 *
 * @author wangye
 * @date 2019-12-17
 * Description:
 */
public interface FeignHystrixCallableWrapper {

    /**
     * 装饰 Callable实例
     *
     * @param callable 待装饰实例
     * @param <T>      返回类型
     * @return 装饰后的实例
     */
    <T> Callable<T> wrap(Callable<T> callable);
}
