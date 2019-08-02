package com.chenyiwenglish.reno.biz.handle.impl;

import com.chenyiwenglish.reno.biz.handle.MessageBizHandle;
import com.chenyiwenglish.reno.common.cache.annotation.CacheKey;
import com.chenyiwenglish.reno.common.cache.annotation.EnableCache;
import com.chenyiwenglish.reno.dal.dao.MessageDao;
import com.chenyiwenglish.reno.dal.model.Message;
import com.chenyiwenglish.reno.vo.message.GetMessageListRequest;
import com.chenyiwenglish.reno.vo.message.GetMessageListResponse;
import com.chenyiwenglish.reno.vo.message.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageBizHandleImpl implements MessageBizHandle {

    private final String MESSAGE_LIST_CACHE_KEY = "msg:list";

    @Autowired
    private MessageDao messageDao;

    @Override
    @EnableCache(keyPrefix = MESSAGE_LIST_CACHE_KEY, isHash = true)
    public GetMessageListResponse getMessageList(@CacheKey GetMessageListRequest request) {
        GetMessageListResponse response = new GetMessageListResponse();
        List<Message> messages = messageDao.getMessageList();
        response.setMessages(messages.stream()
                .map(message -> MessageVo.builder()
                        .id(message.getId())
                        .name(message.getName())
                        .content(message.getContent()).build())
                .collect(Collectors.toList()));
        return response;
    }
}
