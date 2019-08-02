package com.chenyiwenglish.reno.sal.ip.impl;

import com.chenyiwenglish.reno.common.http.impl.HttpTool;
import com.chenyiwenglish.reno.sal.ExternalApi;
import com.chenyiwenglish.reno.sal.ip.vo.Ip360Request;
import com.chenyiwenglish.reno.sal.ip.vo.Ip360Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Ip360Api implements ExternalApi {

    @Autowired
    private HttpTool httpTool;

    private static final Map<String, String> apiMap = new HashMap<>();

    static {
        apiMap.put("LOCAL_IP_INFO", "http://ip.360.cn/IPShare/info");
    }

    public Ip360Response getLocalIpInfo(Ip360Request request) {
        return httpTool.post(apiMap.get("LOCAL_IP_INFO"), request, Ip360Response.class);
    }
}
