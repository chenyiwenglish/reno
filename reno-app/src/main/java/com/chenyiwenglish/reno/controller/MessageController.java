package com.chenyiwenglish.reno.controller;

import com.chenyiwenglish.reno.api.MessageService;
import com.chenyiwenglish.reno.vo.CommonResponse;
import com.chenyiwenglish.reno.vo.message.GetMessageListRequest;
import com.chenyiwenglish.reno.vo.message.GetMessageListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/reno/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/getMessageList")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public CommonResponse<GetMessageListResponse> getMessageList(GetMessageListRequest request) {
        GetMessageListResponse response = messageService.getMessageList(request);
        return CommonResponse.success(response);
    }
}
