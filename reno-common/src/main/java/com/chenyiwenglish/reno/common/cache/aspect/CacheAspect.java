package com.chenyiwenglish.reno.common.cache.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CacheAspect {

    @Around("@annotation(com.chenyiwenglish.reno.common.cache.annotation.EnableCache)")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
}
