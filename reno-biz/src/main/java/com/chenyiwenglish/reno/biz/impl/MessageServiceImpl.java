package com.chenyiwenglish.reno.biz.impl;

import com.chenyiwenglish.reno.api.MessageService;
import com.chenyiwenglish.reno.biz.handle.MessageBizHandle;
import com.chenyiwenglish.reno.vo.message.GetMessageListRequest;
import com.chenyiwenglish.reno.vo.message.GetMessageListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageBizHandle messageBizHandle;

    @Override
    public GetMessageListResponse getMessageList(GetMessageListRequest request) {
        GetMessageListResponse response = null;
        try {
            response = messageBizHandle.getMessageList(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
