package com.chenyiwenglish.reno.common.http.aspect;

import com.chenyiwenglish.reno.common.RequestContextHolder;
import com.chenyiwenglish.reno.common.utils.CommonUtils;
import com.chenyiwenglish.reno.vo.BaseRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class HttpAspect {

    @Around("execution(* com.chenyiwenglish.reno.common.http.impl..*(..))")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        setReqId(args);
        String inputParams = CommonUtils.getInputParams(args);
        log.info("Http request detail:[methodName:{}][input:{}]", methodName, inputParams);
        StopWatch stopWatch = StopWatch.createStarted();
        Object result = joinPoint.proceed();
        long cost = stopWatch.getTime();
        String returnResult = CommonUtils.getReturnResult(result);
        log.info("Http request detail:[methodName:{}][input:{}][result:{}][cost:{}]",
                methodName, inputParams, returnResult, cost);
        return result;
    }

    private void setReqId(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof BaseRequest) {
                ((BaseRequest) arg).setReqId(RequestContextHolder.getRequestId());
            }
        }
    }
}
