package com.tencent.wxcloudrun.dto;

import lombok.Data;
import lombok.ToString;

/**
 * Created on 7/20/23 14:35
 *
 * @author hdk
 **/
@Data
@ToString
public class SceneCreateRequest {
    private String userId;
    private String serverId;
}
