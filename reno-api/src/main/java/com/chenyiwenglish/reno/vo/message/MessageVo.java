package com.chenyiwenglish.reno.vo.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MessageVo {
    private Long id;
    private String name;
    private String content;
}
