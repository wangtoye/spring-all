package com.wy.consumerservice.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 渠道切面类 这个切面不能用在feignClient上，因为熔断的时候会再次被切，导致执行两次。
 *
 * @author wangtoye
 * @date 2019-12-16
 * Description:
 */
//@Aspect
//@Component
public class ChannelAspect {

    /**
     * 方法类型(可以省略) 方法返回值 包路径 方法名(参数)
     */
    @Pointcut("execution(* com..*.channel.ProducerChannel.getStr(..))")
    private void pointCut() {
    }

    /**
     * 执行顺序 around 1-》before-》around 2-》after-》afterReturning
     *
     * @param joinPoint
     */
    @Before(value = "pointCut()")
    private void doBefore(JoinPoint joinPoint) {
        System.out.println("before");
    }

    @After(value = "pointCut()")
    private void doAfter(JoinPoint joinPoint) {
        System.out.println("after");
    }

    @Around(value = "pointCut()")
    private Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around 1");
        Object object = joinPoint.proceed();
        System.out.println("around 2");
        return object;
    }

    @AfterReturning(value = "pointCut()")
    private void doAfterReturning(JoinPoint joinPoint) {
        System.out.println("afterReturning");
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    private void doAfterThrowing(Throwable exception) {
        System.out.println("afterThrowing");
    }
}
