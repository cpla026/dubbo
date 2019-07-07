package com.coolron.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/**
 *
 * 拦截顺序 ： Filter >>> Interceptor >>> Aspect  >>> controller
 * 若果有异常返回结果：  controller >>> Aspect >>> ControllerAdvice >>> Interceptor >>> Filter
 *
 * @Auther: xf
 * @Date: 2018/11/19 17:49
 * @Description:  可以拿到请求的具体controller 对应的方法的具体参数值  但是拿不到 原始的request 和 response
 *
 * 切入点（注解）：
 * 1、 在哪些方法上起作用
 * 2、 什么时候起作用
 *
 * 增强（方法）
 * 1、起作用是执行的业务逻辑
 */
// 声明切片类

@Slf4j
//@Aspect
//@Component
public class CustomAspect {

    // 在什么时候起作用
    // @Before() 相当于拦截器的 PreHandle() 方法
    // @After()  拦截的方法响应之后执行
    // @AfterThrowing 方法抛出某些异常的时候调用
    // @Around 环绕 覆盖前面三种
    // 环绕的方式调用下面的方法
    @Around("execution(* com.coolron.*.controller..*.*(..))")
    // ProceedingJoinPoint 类 包含了当前拦截的方法的一些信息
    public Object method(ProceedingJoinPoint pjp) throws Throwable {
        log.info("=====Aspect处理=======");
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            log.info("参数为:" + arg);
        }
        long start = System.currentTimeMillis();
        // 相当于Filter 的 chain.doFilter()  调用被拦截的那个方法  返回值 object 与 controller中方法的返回值相同
        Object object = pjp.proceed();
        log.info("Aspect 耗时:" + (System.currentTimeMillis() - start));
        return object;
    }
}
