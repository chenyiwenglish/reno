package com.chenyiwenglish.reno.vo.message;

import lombok.Data;

import java.util.List;

@Data
public class GetMessageListResponse {
    private List<MessageVo> messages;
}
