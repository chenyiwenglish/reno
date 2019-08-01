package com.chenyiwenglish.reno.biz.handle.impl;

import com.alibaba.fastjson.JSON;
import com.chenyiwenglish.reno.biz.handle.MessageBizHandle;
import com.chenyiwenglish.reno.dal.dao.MessageDao;
import com.chenyiwenglish.reno.dal.model.Message;
import com.chenyiwenglish.reno.vo.message.GetMessageListRequest;
import com.chenyiwenglish.reno.vo.message.GetMessageListResponse;
import com.chenyiwenglish.reno.vo.message.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class MessageBizHandleImpl implements MessageBizHandle {

    private final String MESSAGE_LIST_CACHE_KEY = "msg:list";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MessageDao messageDao;

    @Override
    @SuppressWarnings("unchecked")
    public GetMessageListResponse getMessageList(GetMessageListRequest request) {
        if (redisTemplate.hasKey(MESSAGE_LIST_CACHE_KEY)) {
            return JSON.parseObject((String) redisTemplate.opsForValue().get(MESSAGE_LIST_CACHE_KEY), GetMessageListResponse.class);
        }
        GetMessageListResponse response = new GetMessageListResponse();
        List<Message> messages = messageDao.getMessageList();
        response.setMessages(messages.stream()
                .map(message -> MessageVo.builder()
                        .id(message.getId())
                        .name(message.getName())
                        .content(message.getContent()).build())
                .collect(Collectors.toList()));
        redisTemplate.opsForValue().set(MESSAGE_LIST_CACHE_KEY, JSON.toJSONString(response), 60, TimeUnit.SECONDS);
        return response;
    }
}
