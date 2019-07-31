package com.chenyiwenglish.reno.vo.message;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageVo {
    private Long id;
    private String name;
    private String content;
}
