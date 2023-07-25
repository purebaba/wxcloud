package com.tencent.wxcloudrun.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import net.ezbim.shared.wxpush.bean.TextDTO;

/**
 * Created on 7/25/23 13:55
 *
 * @author hdk
 **/
@Data
public class MessageCreateRequest {
    private String userId;
    private String serverId;
    @JsonProperty("msgtype")
    private String msgType;
    @JsonProperty("text")
    private TextDTO text;
}
