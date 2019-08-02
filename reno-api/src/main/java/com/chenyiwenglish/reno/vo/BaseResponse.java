package com.chenyiwenglish.reno.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class BaseResponse {
    @JSONField(serialize = false)
    private String reqId;
}
