package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.dto.SceneCreateRequest;
import com.tencent.wxcloudrun.dto.SceneResponse;
import com.tencent.wxcloudrun.model.Scene;
import com.tencent.wxcloudrun.service.SceneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ezbim.shared.lang.util.IdUtils;
import net.ezbim.shared.wxpush.api.WxQrCodeApi;
import net.ezbim.shared.wxpush.bean.ActionInfoDTO;
import net.ezbim.shared.wxpush.bean.ActionNameEnum;
import net.ezbim.shared.wxpush.bean.SceneDTO;
import net.ezbim.shared.wxpush.bean.WxQrCodeCreateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * counter控制器
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class SceneController {

    final SceneService sceneService;
    final WxQrCodeApi wxQrCodeApi;


    /**
     * 创建一个二维码
     *
     * @param request {@link HttpServletRequest}
     * @return API response json
     */
    @PostMapping(value = "/api/scenes")
    SceneResponse create(@RequestBody SceneCreateRequest request) {
        log.info("receive message: {}", request.toString());
        String sceneId = IdUtils.createObjectId();
        Scene scene = new Scene();
        scene.setId(sceneId);
        scene.setServerId(request.getServerId());
        scene.setUserId(request.getUserId());
        var qrCode = wxQrCodeApi.createQrCodeStrResponse(genQrCodeRes(scene));
        log.info(qrCode);
//        scene.setUrl(qrCode.getUrl());
        sceneService.save(scene);
        SceneResponse response = new SceneResponse();
        response.setUrl(scene.getUrl());
        response.setUserId(scene.getUserId());
        response.setServerId(scene.getServerId());
        return response;
    }


    private WxQrCodeCreateRequest genQrCodeRes(Scene scene) {
        WxQrCodeCreateRequest request = new WxQrCodeCreateRequest();
        request.setActionName(ActionNameEnum.QR_STR_SCENE);
        ActionInfoDTO actionInfoDTO = new ActionInfoDTO();
        SceneDTO sceneDTO = new SceneDTO();
        sceneDTO.setSceneStr(scene.getId());
        actionInfoDTO.setScene(sceneDTO);
        request.setActionInfo(actionInfoDTO);
        request.setExpireSeconds(8000);
        return request;
    }

}