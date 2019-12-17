package com.wy.consumerservice.common.strategy.call.callable;

import lombok.Data;

import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangtoye
 * @date 2019-12-17
 * Description:
 */
@Data
public abstract class AbstractCallable<T> implements Callable<T> {

    private final Callable<T> delegate;

    private final Object value;

    public AbstractCallable(Callable<T> delegate, Object value) {
        this.delegate = delegate;
        this.value = value;
    }
}
