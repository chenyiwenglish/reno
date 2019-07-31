package com.chenyiwenglish.reno.api;

import com.chenyiwenglish.reno.vo.message.GetMessageListRequest;
import com.chenyiwenglish.reno.vo.message.GetMessageListResponse;

public interface MessageService {
    GetMessageListResponse getMessageList(GetMessageListRequest request);
}
