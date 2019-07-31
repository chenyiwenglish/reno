package com.chenyiwenglish.reno.filter;

import com.alibaba.fastjson.JSON;
import com.chenyiwenglish.reno.vo.BaseRequest;
import com.chenyiwenglish.reno.vo.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
@Slf4j
public class LogFilter {

    @Around("execution(* com.chenyiwenglish.reno.controller..*Controller.*(..))")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String inputParams = getInputParams(args);
        String reqId = getReqId(args);
        log.info("Invoke service detail:[methodName:{}][input:{}][reqId:{}]",
                methodName, inputParams, reqId);
        long t = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long cost = System.currentTimeMillis() - t;
        String returnResult = getReturnResult(result);
        log.info("Invoke service detail:[methodName:{}][input:{}][reqId:{}][result:{}][cost:{}]",
                methodName, inputParams, reqId, returnResult, cost);
        return result;
    }

    private String getInputParams(Object[] args) {
        List<String> inputParams = new ArrayList<>();
        for (Object arg : args) {
            if (arg instanceof BaseRequest) {
                inputParams.add(JSON.toJSONString(arg));
            } else {
                inputParams.add(arg != null ? arg.toString() : "null");
            }
        }
        return String.join(",", inputParams);
    }

    private String getReqId(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof BaseRequest) {
                return ((BaseRequest) arg).getReqId();
            }
        }
        return null;
    }

    private String getReturnResult(Object result) {
        if (result instanceof BaseResponse) {
            return JSON.toJSONString(result);
        }
        return result != null ? result.toString() : "null";
    }
}
