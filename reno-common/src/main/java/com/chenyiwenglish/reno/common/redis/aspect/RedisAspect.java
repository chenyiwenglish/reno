package com.chenyiwenglish.reno.common.redis.aspect;

import com.chenyiwenglish.reno.common.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RedisAspect {

    @Around("execution(* com.chenyiwenglish.reno.common.redis.impl..*(..))")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String inputParams = CommonUtils.getInputParams(args);
        log.info("Request cache detail:[methodName:{}][input:{}]", methodName, inputParams);
        StopWatch stopWatch = StopWatch.createStarted();
        Object result = joinPoint.proceed();
        long cost = stopWatch.getTime();
        String returnResult = CommonUtils.getReturnResult(result);
        log.info("Request cache detail:[methodName:{}][input:{}][result:{}][cost:{}]",
                methodName, inputParams, returnResult, cost);
        return result;
    }
}
