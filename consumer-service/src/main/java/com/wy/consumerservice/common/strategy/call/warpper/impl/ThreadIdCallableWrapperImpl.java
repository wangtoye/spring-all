package com.wy.consumerservice.common.strategy.call.warpper.impl;

import com.wy.consumerservice.common.strategy.call.callable.ThreadIdCallable;
import com.wy.consumerservice.common.strategy.call.warpper.FeignHystrixCallableWrapper;
import com.wy.consumerservice.utils.ThreadLocalUtil;

import java.util.concurrent.Callable;

/**
 * 线程id 回调包装实现类
 *
 * @author wangtoye
 * @date 2019-12-17
 * Description:
 */
public class ThreadIdCallableWrapperImpl implements FeignHystrixCallableWrapper {
    @Override
    public <T> Callable<T> wrap(Callable<T> callable) {
        return new ThreadIdCallable<>(callable, ThreadLocalUtil.get("threadId"));
    }
}
