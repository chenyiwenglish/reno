package com.chenyiwenglish.reno.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResponse<T> extends BaseResponse implements Response {
    private Integer errno;
    private String message;
    private T data;

    public static CommonResponse success() {
        return CommonResponse.builder().errno(Response.SUCCESS_ERRNO).message(SUCCESS_MESSAGE).data(null).build();
    }

    public static CommonResponse success(Object data) {
        return CommonResponse.builder().errno(Response.SUCCESS_ERRNO).message(SUCCESS_MESSAGE).data(data).build();
    }
}
