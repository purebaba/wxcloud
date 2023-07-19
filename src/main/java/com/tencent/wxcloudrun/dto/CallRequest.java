package com.tencent.wxcloudrun.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created on 7/19/23 18:54
 *
 * @author hdk
 **/
@NoArgsConstructor
@Data
@ToString
public class CallRequest {

    @JsonProperty("ToUserName")
    private String toUserName;
    @JsonProperty("FromUserName")
    private String fromUserName;
    @JsonProperty("CreateTime")
    private Integer createTime;

    // text event
    @JsonProperty("MsgType")
    private String msgType;

    // 当msgType为event时才有实际意义，否则无该字段
    @JsonProperty("Event")
    private String event;
    @JsonProperty("EventKey")
    private String eventKey;
    @JsonProperty("Ticket")
    private String ticket;

    @JsonProperty("Content")
    private String content;

    @JsonProperty("MsgId")
    private String msgId;
}
