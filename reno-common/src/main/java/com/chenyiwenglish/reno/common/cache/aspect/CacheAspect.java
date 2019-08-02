package com.chenyiwenglish.reno.common.cache.aspect;

import com.chenyiwenglish.reno.common.cache.annotation.CacheKey;
import com.chenyiwenglish.reno.common.cache.annotation.EnableCache;
import com.chenyiwenglish.reno.common.redis.impl.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Aspect
@Component
@Slf4j
public class CacheAspect {

    @Autowired
    private RedisClient redisClient;

    @Around("@annotation(com.chenyiwenglish.reno.common.cache.annotation.EnableCache)")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        EnableCache enableCache = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(EnableCache.class);
        if (enableCache.enableCache()) {
            Annotation[][] parameterAnnotations = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterAnnotations();
            Object[] arguments = joinPoint.getArgs();
            String key = getKeyFromArguments(arguments, parameterAnnotations);
            if (StringUtils.isBlank(key)) {
                return joinPoint.proceed();
            }
            if (enableCache.isHash()) {
                key = md5Key(key);
            }
            if (StringUtils.isNoneBlank(enableCache.keyPrefix())) {
                key = enableCache.keyPrefix() + "_" + key;
            }
            Object result = null;
            try {
                if (!redisClient.exist(key)) {
                    result = joinPoint.proceed();
                    redisClient.setObject(key, result, enableCache.expire());
                } else {
                    return redisClient.getObject(key, ((MethodSignature) joinPoint.getSignature()).getMethod().getGenericReturnType());
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (result != null) {
                    return result;
                }
            }
        }
        return joinPoint.proceed();
    }

    private String getKeyFromArguments(Object[] arguments, Annotation[][] parameterAnnotations) {
        String key = "";
        if (arguments == null) {
            return key;
        }
        for (int i = 0; i < parameterAnnotations.length; i ++) {
            Annotation[] annotations = parameterAnnotations[i];
            if (annotations == null) {
                continue;
            }
            if (isKey(annotations)) {
                if (arguments[i] == null) {
                    continue;
                }
                if (StringUtils.isBlank(key)) {
                    key = arguments[i].toString();
                } else {
                    key = key + "_" + arguments[i].toString();
                }
            }
        }
        return key;
    }

    private boolean isKey(Annotation[] annotations) {
        boolean isKey = false;
        for (Annotation annotation: annotations) {
            if (annotation instanceof CacheKey) {
                isKey = true;
                break;
            }
        }
        return isKey;
    }

    private String md5Key(String key) {
        try {
            return DigestUtils.md5Hex(key);
        } catch (Exception e) {
            e.printStackTrace();
            return key;
        }
    }
}
