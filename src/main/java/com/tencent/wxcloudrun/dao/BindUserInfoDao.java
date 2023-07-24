package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.BindUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BindUserInfoDao extends JpaRepository<BindUserInfo, String> {
    BindUserInfo findFirstByUserIdAndServerId(String userId, String serverId);

    List<BindUserInfo> findByOpenId(String openId);
}
