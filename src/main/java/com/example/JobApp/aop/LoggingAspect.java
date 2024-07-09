package com.example.JobApp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

//import java.util.logging.Logger;
@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    //returnType, className.methodName(args)

    @Before("execution(* com.example.JobApp.service.JobService.getJobById(..))")
    public void logMethodCall(JoinPoint jp) {
        LOGGER.info("Method Called " + jp.getSignature().getName());
    }

    @After("execution(* com.example.JobApp.service.JobService.getJobById(..))")
    public void logMethodExecuted(JoinPoint jp) {
        LOGGER.info("Method Executed " + jp.getSignature().getName());
    }

    @AfterThrowing("execution(* com.example.JobApp.service.JobService.getJobById(..))")
    public void logMethodCrash(JoinPoint jp) {
        LOGGER.info("Method has exception " + jp.getSignature().getName());
    }

    @AfterReturning("execution(* com.example.JobApp.service.JobService.getJobById(..))")
    public void logMethodSuccess(JoinPoint jp) {
        LOGGER.info("Method has no exception " + jp.getSignature().getName());
    }

}
