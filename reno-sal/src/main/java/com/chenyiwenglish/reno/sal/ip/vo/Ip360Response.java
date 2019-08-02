package com.chenyiwenglish.reno.sal.ip.vo;

import com.chenyiwenglish.reno.vo.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ip360Response extends BaseResponse {
    private String greetheader;
    private String nickname;
    private String ip;
    private String location;
    private String loc_client;
}
