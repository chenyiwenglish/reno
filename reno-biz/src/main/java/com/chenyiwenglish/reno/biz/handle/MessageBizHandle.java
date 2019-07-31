package com.chenyiwenglish.reno.biz.handle;

import com.chenyiwenglish.reno.vo.message.GetMessageListRequest;
import com.chenyiwenglish.reno.vo.message.GetMessageListResponse;

public interface MessageBizHandle {
    GetMessageListResponse getMessageList(GetMessageListRequest request);
}
