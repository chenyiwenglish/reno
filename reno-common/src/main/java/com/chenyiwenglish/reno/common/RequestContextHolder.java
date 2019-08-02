package com.chenyiwenglish.reno.common;

import java.util.HashMap;
import java.util.Map;

public class RequestContextHolder {
    private static final ThreadLocal<String> REQUEST_ID = new ThreadLocal<>();

    private static final ThreadLocal<Map<String, Object>> CONTEXT_PARAM = new ThreadLocal<>();

    public static void setRequestId(String requestId) {
        REQUEST_ID.set(requestId);
    }

    public static String getRequestId() {
        return REQUEST_ID.get();
    }

    public static void put(String key, Object value) {
        if (CONTEXT_PARAM.get() == null) {
            CONTEXT_PARAM.set(new HashMap<>());
        }
        CONTEXT_PARAM.get().put(key, value);
    }

    public static Object get(String key) {
        if (CONTEXT_PARAM.get() == null) {
            return null;
        } else {
            return CONTEXT_PARAM.get().get(key);
        }
    }

    public static void clean() {
        CONTEXT_PARAM.remove();
        REQUEST_ID.remove();
    }
}
