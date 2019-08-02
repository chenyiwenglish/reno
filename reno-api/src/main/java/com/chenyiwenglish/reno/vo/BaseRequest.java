package com.chenyiwenglish.reno.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class BaseRequest {
    @JSONField(serialize=false)
    private String reqId;
}
