package com.example.correction_spring_aspect.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {
    private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(com.example.correction_spring_aspect.annotation.Performance)")
    public void performancePointCut() {

    }

    @Around("performancePointCut()")
    public Object performanceAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object object = joinPoint.proceed();
        long time = System.currentTimeMillis() - startTime;
        logger.warn("Times : "+time);
        return object;
    }
}
