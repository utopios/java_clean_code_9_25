package com.example.correction_spring_aspect.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class LoggingAspect {

    private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(com.example.correction_spring_aspect.annotation.Log)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object logAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Object object = null;
        try
        {
            logger.warn("Start Method");
            logger.warn("Method name : " + joinPoint.getSignature().getName());
            logger.warn("Method name : " + joinPoint.getArgs());
            object = joinPoint.proceed();
            logger.warn("End Method");

        }catch (Exception ex) {

        }finally {
            return object;
        }

    }

}
