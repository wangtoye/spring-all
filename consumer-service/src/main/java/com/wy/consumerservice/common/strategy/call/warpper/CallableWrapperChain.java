package com.wy.consumerservice.common.strategy.call.warpper;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangtoye
 * @date 2019-12-17
 * Description:
 */
public class CallableWrapperChain<T> {
    private Callable<T> callable;
    private List<FeignHystrixCallableWrapper> wrappers;

    public CallableWrapperChain(Callable<T> callable, List<FeignHystrixCallableWrapper> wrappers) {
        this.callable = callable;
        this.wrappers = wrappers;
    }

    public Callable<T> wrapCallable() {
        wrappers.forEach(w -> callable = w.wrap(callable));
        return callable;
    }
}
