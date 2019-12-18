package com.wy.consumerservice.common.strategy.warpper;

import com.wangtoye.feignhystrixthreadlocalspringbootstarter.strategy.call.warpper.FeignHystrixCallableWrapper;
import com.wy.consumerservice.common.strategy.callable.RandomNumCallable;
import com.wy.consumerservice.utils.ThreadLocalUtil;

import java.util.concurrent.Callable;

/**
 * 随机数 回调包装实现类
 *
 * @author wangtoye
 * @date 2019-12-17
 * Description:
 */
public class RandomNumCallableWrapperImpl implements FeignHystrixCallableWrapper {

    @Override
    public <T> Callable<T> wrap(Callable<T> callable) {
        return new RandomNumCallable<>(callable, ThreadLocalUtil.get("randomNum"));
    }
}
