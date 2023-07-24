package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.BindUserInfoDao;
import com.tencent.wxcloudrun.model.BindUserInfo;
import com.tencent.wxcloudrun.service.BindUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 7/24/23 14:57
 *
 * @author hdk
 **/
@Service
@RequiredArgsConstructor
public class BindUserInfoServiceImpl implements BindUserInfoService {

    final BindUserInfoDao bindUserInfoDao;

    @Override
    public BindUserInfo bind(BindUserInfo bindUserInfo) {
        bindUserInfoDao.save(bindUserInfo);
        return  bindUserInfo;
    }

    @Override
    public BindUserInfo findBindInfo(String userId, String serverId) {
        return bindUserInfoDao.findFirstByUserIdAndServerId(userId, serverId);
    }

    @Override
    public List<BindUserInfo> findBindInfosByOpenId(String openId) {
        return bindUserInfoDao.findByOpenId(openId);
    }

    @Override
    public void unbind(List<BindUserInfo> bindInfos) {
        bindUserInfoDao.deleteAll(bindInfos);
    }
}
