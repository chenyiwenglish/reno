package com.chenyiwenglish.reno.filter;

import com.chenyiwenglish.reno.common.RequestContextHolder;
import com.chenyiwenglish.reno.common.utils.CommonUtils;
import com.chenyiwenglish.reno.vo.BaseRequest;
import com.chenyiwenglish.reno.vo.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
@Slf4j
public class LogFilter {

    @Around("execution(* com.chenyiwenglish.reno.controller..*Controller.*(..))")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String inputParams = CommonUtils.getInputParams(args);
        String reqId = getReqId(args);
        setUpContext();
        log.info("Invoke service detail:[methodName:{}][input:{}][reqId:{}]",
                methodName, inputParams, reqId);
        StopWatch stopWatch = StopWatch.createStarted();
        Object result = joinPoint.proceed();
        long cost = stopWatch.getTime();
        setReqId(result);
        String returnResult = CommonUtils.getReturnResult(result);
        log.info("Invoke service detail:[methodName:{}][input:{}][reqId:{}][result:{}][cost:{}]",
                methodName, inputParams, reqId, returnResult, cost);
        cleanUpContext();
        return result;
    }

    private String getReqId(Object[] args) {
        String reqId = null;
        for (Object arg : args) {
            if (arg instanceof BaseRequest) {
                reqId = ((BaseRequest) arg).getReqId();
            }
        }
        if (StringUtils.isBlank(reqId)) {
            reqId = UUID.randomUUID().toString();
        }
        RequestContextHolder.setRequestId(reqId);
        return reqId;
    }

    private void setReqId(Object result) {
        if (result instanceof BaseResponse) {
            ((BaseResponse) result).setReqId(RequestContextHolder.getRequestId());
        }
    }

    private void setUpContext() {
        MDC.put("reqId", RequestContextHolder.getRequestId());
    }

    private void cleanUpContext() {
        RequestContextHolder.clean();
        MDC.clear();
    }
}
