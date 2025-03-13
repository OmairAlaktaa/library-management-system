package com.maidscc.library_management_system.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.maidscc.library_management_system.service.*.*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        logger.info("Method {} called with arguments: {}", signature.toShortString(), Arrays.toString(args));
    }

    @Around("execution(* com.maidscc.library_management_system.service.BookService.addBook(..)) || " +
            "execution(* com.maidscc.library_management_system.service.BookService.updateBook(..)) || " +
            "execution(* com.maidscc.library_management_system.service.TransactionService.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - start;
            logger.info("Executed {} in {} ms", joinPoint.getSignature(), executionTime);
            return result;
        } catch (Exception e) {
            logger.error("Exception in {} with cause = {}", joinPoint.getSignature(), e.getMessage());
            throw e;
        }
    }

    @AfterReturning(value = "execution(* com.maidscc.library_management_system.service.*.*(..))", returning = "result")
    public void logMethodReturn(JoinPoint joinPoint, Object result) {
        logger.info("Method {} returned: {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "execution(* com.maidscc.library_management_system.service.*.*(..))", throwing = "exception")
    public void logMethodException(JoinPoint joinPoint, Throwable exception) {
        logger.error("Exception in method {}: {}", joinPoint.getSignature(), exception.getMessage());
    }
}
