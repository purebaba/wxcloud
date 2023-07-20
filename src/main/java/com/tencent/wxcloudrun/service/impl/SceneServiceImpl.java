package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.SceneDao;
import com.tencent.wxcloudrun.model.Scene;
import com.tencent.wxcloudrun.service.SceneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SceneServiceImpl implements SceneService {

    final SceneDao sceneDao;

    @Override
    public void save(Scene scene) {
        sceneDao.save(scene);
    }
}
