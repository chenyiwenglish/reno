package com.chenyiwenglish.reno.dal.dao;

import com.chenyiwenglish.reno.dal.model.Message;
import com.chenyiwenglish.reno.dal.model.MessageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageDao {

    @Autowired
    private MessageMapper messageMapper;

    public List<Message> getMessageList() {
        MessageExample messageExample = new MessageExample();
        return messageMapper.selectByExampleWithBLOBs(messageExample);
    }
}
