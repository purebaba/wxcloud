package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.BindUserInfo;

import java.util.List;

/**
 * Created on 7/24/23 14:57
 *
 * @author hdk
 **/
public interface BindUserInfoService {
    BindUserInfo bind(BindUserInfo bindUserInfo);
    BindUserInfo findBindInfo(String userId, String serverId);
    List<BindUserInfo> findBindInfosByOpenId(String openId);

    void unbind(List<BindUserInfo> bindInfos);

}
