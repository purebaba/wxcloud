package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.CallRequest;
import com.tencent.wxcloudrun.dto.SceneCreateRequest;
import com.tencent.wxcloudrun.service.SceneService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * counter控制器
 */
@RestController
@Slf4j
public class SceneController {

    final SceneService sceneService;
    final Logger logger;

    public SceneController(@Autowired SceneService sceneService) {
        this.sceneService = sceneService;
        this.logger = LoggerFactory.getLogger(SceneController.class);
    }

    /**
     * 创建一个二维码
     *
     * @param request {@link HttpServletRequest}
     * @return API response json
     */
    @PostMapping(value = "/api/scenes")
    ApiResponse create(@RequestBody SceneCreateRequest request) {
        logger.info("receive message: {}", request.toString());
        return ApiResponse.ok();
    }

}