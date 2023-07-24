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

    // 一般为公众号的id
    @JsonProperty("ToUserName")
    private String toUserName;

    // 用户的openId
    @JsonProperty("FromUserName")
    private String fromUserName;
    @JsonProperty("CreateTime")
    private Integer createTime;

    // text event
    @JsonProperty("MsgType")
    private String msgType;

    // 当msgType为event时才有实际意义，否则无该字段
    // 现在可能的类型：SCAN，unsubscribe，subscribe
    @JsonProperty("Event")
    private String event;

    // 现在抓到的数据来看，当是subscribe时，eventKey为qrscene_64be1f021c52ad13faf42475，其中64be1f021c52ad13faf42475为sceneId
    // 当是别的类型的事件时，eventKey都为64be1f021c52ad13faf42475，即为sceneId
    @JsonProperty("EventKey")
    private String eventKey;
    @JsonProperty("Ticket")
    private String ticket;

    // context当msgType为text时才有实际意义，否则无该字段
    @JsonProperty("Content")
    private String content;

    @JsonProperty("MsgId")
    private String msgId;
}
