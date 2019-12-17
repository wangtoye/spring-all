package com.wy.consumerservice.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangtoye
 * @date 2019-12-16
 * Description:
 */
public class ThreadLocalUtil {
    private static Map<String, ThreadLocal<Object>> THREAD_LOCAL_MAP = new ConcurrentHashMap<>();

    public static Object get(String threadLocalName) {
        return THREAD_LOCAL_MAP.get(threadLocalName).get();
    }

    public static void set(String threadLocalName, Object value) {
        if (THREAD_LOCAL_MAP.get(threadLocalName) == null) {
            ThreadLocal<Object> threadLocal = new ThreadLocal<>();
            threadLocal.set(value);
            THREAD_LOCAL_MAP.put(threadLocalName, threadLocal);
        } else {
            THREAD_LOCAL_MAP.get(threadLocalName).set(value);
        }
    }

    public static void remove(String threadLocalHostName) {
        THREAD_LOCAL_MAP.get(threadLocalHostName).remove();
        THREAD_LOCAL_MAP.remove(threadLocalHostName);
    }
}
