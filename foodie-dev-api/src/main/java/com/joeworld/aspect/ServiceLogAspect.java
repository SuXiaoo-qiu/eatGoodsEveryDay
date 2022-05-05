package com.joeworld.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLogAspect {
    public  static final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);


    /**
     * 切面表达式
     * execution 代表所有要执行的表达式主体
     * 第一处 * 代表方法返回的类型 * 代表所有类型
     * 第二处 报名代表aop 监控的类所在的包
     * 第三处 ..带包该包下以及子包下的所有方法
     * 第四处 * 代表类名 *代表所有类
     * 第五处 *（..） * 代表类型的方法名 （..）表示任何方法中的参数
     *
     * @param joinPoint
     * @return
     */
    @Around("execution(* com.joeworld.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("========== 开始执行 进入接口 {}.{} ========",joinPoint.getTarget().getClass(),joinPoint.getSignature().getName());
        //记录开始时间
        long begin = System.currentTimeMillis();
        //执行目标
        Object rest = joinPoint.proceed();
        //结束时间
        long end = System.currentTimeMillis();

        long takeTime = end - begin;

        if (takeTime>3000) {
            logger.warn("====== 执行结束 耗时: {} 毫秒========" ,takeTime);
        }else if (takeTime > 2000) {
            logger.warn("====== 执行结束 耗时: {} 毫秒========" ,takeTime);
        }else {
            logger.warn("====== 执行结束 耗时: {} 毫秒========" ,takeTime);
        }
        return rest;
    }
}
