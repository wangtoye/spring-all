package com.wy.consumerservice.common.strategy.call.callable;

import com.wy.consumerservice.utils.ThreadLocalUtil;

import java.util.concurrent.Callable;

/**
 * 线程id 回调类
 *
 * @author wangtoye
 * @date 2019-12-17
 * Description:
 */
public class ThreadIdCallable<T> extends AbstractCallable<T> {

    public ThreadIdCallable(Callable<T> delegate, Object value) {
        super(delegate, value);
    }

    @Override
    public T call() throws Exception {
        try {
            ThreadLocalUtil.set("threadId", getValue());
            return getDelegate().call();
        } finally {
            //必须做finally处理，方式异常之后子线程本地变量未清空
            ThreadLocalUtil.remove("threadId");
        }
    }
}