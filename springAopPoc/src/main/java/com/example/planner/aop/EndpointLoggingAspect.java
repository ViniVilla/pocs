package com.example.planner.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Slf4j
@Aspect
@Component
public class EndpointLoggingAspect {

    @Pointcut("@annotation(LogEndpoint)")
    protected void annotation() {}

    @Pointcut("execution(!void com.example.planner.resource.*.*(..))")
    protected void notVoidReturnType() {}

    @Before("annotation()")
    public void logEndpoint(JoinPoint joinPoint){
        Map<String, Object> parameters = new HashMap<>();
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();

        IntStream.range(0, codeSignature.getParameterNames().length)
                .forEach(i -> parameters.put(codeSignature.getParameterNames()[i], joinPoint.getArgs()[i]));

        log.info("class={}, method={}, parameters={}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                parameters
        );
    }

    @AfterReturning(pointcut = "notVoidReturnType()", returning = "result")
    public void logEndpointReturn(JoinPoint joinPoint, Object result) {
        log.info("class={}, method={}, endpointReturn={}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                result
        );
    }



}
