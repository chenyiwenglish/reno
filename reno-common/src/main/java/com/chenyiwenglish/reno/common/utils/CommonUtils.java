package com.chenyiwenglish.reno.common.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {

    public static String getInputParams(Object[] args) {
        List<String> inputParams = new ArrayList<>();
        for (Object arg : args) {
            try {
                inputParams.add(JSON.toJSONString(arg));
            } catch (Exception e) {
                inputParams.add(arg != null ? arg.toString() : "null");
            }
        }
        return String.join(",", inputParams);
    }

    public static String getReturnResult(Object result) {
        try {
            return JSON.toJSONString(result);
        } catch (Exception e) {
            return result != null ? result.toString() : "null";
        }
    }
}
