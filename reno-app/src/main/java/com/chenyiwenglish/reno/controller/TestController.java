package com.chenyiwenglish.reno.controller;

import com.chenyiwenglish.reno.sal.ip.impl.Ip360Api;
import com.chenyiwenglish.reno.sal.ip.vo.Ip360Request;
import com.chenyiwenglish.reno.sal.ip.vo.Ip360Response;
import com.chenyiwenglish.reno.vo.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/reno/test")
public class TestController {

    @Autowired
    private Ip360Api ip360Api;

    @RequestMapping("/getLocalIpInfo")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public CommonResponse<Ip360Response> getLocalIpInfo() {
        Ip360Response response = ip360Api.getLocalIpInfo(new Ip360Request());
        return CommonResponse.success(response);
    }
}
