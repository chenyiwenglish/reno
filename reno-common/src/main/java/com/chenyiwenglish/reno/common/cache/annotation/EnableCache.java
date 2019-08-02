package com.chenyiwenglish.reno.common.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface EnableCache {
    int expire() default 300;

    boolean enableCache() default true;

    String keyPrefix() default "";

    boolean isHash() default false;
}
