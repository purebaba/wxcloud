package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.dto.CallRequest;
import com.tencent.wxcloudrun.model.BindUserInfo;
import com.tencent.wxcloudrun.model.Scene;
import com.tencent.wxcloudrun.service.BindUserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ezbim.shared.lang.util.IdUtils;
import net.ezbim.shared.wxpush.api.WxMessageApi;
import net.ezbim.shared.wxpush.api.WxQrCodeApi;
import net.ezbim.shared.wxpush.bean.TextDTO;
import net.ezbim.shared.wxpush.bean.WxMessageCreateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.service.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * counter控制器
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class CounterController {

    final SceneService sceneService;
    final BindUserInfoService bindUserInfoService;
    final WxMessageApi wxMessageApi;
    final Logger logger;


    @GetMapping({"/api/wx-data", "/api/count"})
    public ApiResponse getWxData(HttpServletRequest request, @RequestParam(required = false) String signature, @RequestParam(required = false) String timestamp, @RequestParam(required = false) String nonce, @RequestParam(required = false) String echostr) {
        // 详细打印request重的信息，包括请求路径，body等
        log.info("request:{}", request);
        log.info("request path:{}", request.getPathInfo());
        log.info("request query string:{}", request.getQueryString());
        log.info("signature:{}", signature);
        log.info("timestamp:{}", timestamp);
        log.info("nonce:{}", nonce);
        log.info("echostr:{}", echostr);
        return ApiResponse.ok();
    }


    /**
     * 更新计数，自增或者清零
     *
     * @param request {@link HttpServletRequest}
     * @return API response json
     */
    @PostMapping(value = "/api/count")
    ApiResponse create(@RequestBody CallRequest request) {
        logger.info("receive message: {}", request.toString());
        var msgType = request.getMsgType();
        switch (msgType) {
            case "event":
                var event = request.getEvent();
                var eventKey = request.getEventKey();
                var openId = request.getFromUserName();
                processEvent(event, eventKey, openId);
                break;
            case "subscribe":
        }
        return ApiResponse.ok();
    }

    private void processEvent(String event, String eventKey, String openId) {
        eventKey = eventKey.replace("qrscene_", "");
        var scene = sceneService.findById(eventKey);
        if (scene == null) {
            log.error("scene not found, sceneId:{}", eventKey);
            return;
        }
        switch (event) {
            case "SCAN":
            case "subscribe":
                bindUserInfo(openId, scene);
                return;
            case "unsubscribe":
                unbindUserInfo(openId);
                return;
            default:
                log.info("unknown event:{}", event);

        }
    }

    private void bindUserInfo(String openId, Scene scene) {
        var serverId = scene.getServerId();
        var userId = scene.getUserId();
        var bindInfo = bindUserInfoService.findBindInfo(userId, serverId);
        var isUpdateBind = true;
        if (bindInfo == null) {
            bindInfo = new BindUserInfo();
            bindInfo.setId(IdUtils.createObjectId());
            isUpdateBind = false;
        }

        bindInfo.setUserId(userId);
        bindInfo.setServerId(serverId);
        bindInfo.setOpenId(openId);
        bindUserInfoService.bind(bindInfo);

        if (isUpdateBind){
            sendTipMessage(bindInfo.getOpenId(), "更新绑定成功");
        } else {
            sendTipMessage(bindInfo.getOpenId(), "绑定成功");
        }
    }

    private void unbindUserInfo(String openId) {
        var bindInfos = bindUserInfoService.findBindInfosByOpenId(openId);
        if (bindInfos.isEmpty()) {
            log.info("unbindUserInfo, openId:{} not bind", openId);
            return;
        }
        bindUserInfoService.unbind(bindInfos);
    }

    private void sendTipMessage(String openId, String content) {
        WxMessageCreateRequest request = new WxMessageCreateRequest();
        request.setMsgType("text");
        var textDto = new TextDTO();
        textDto.setContent(content);
        request.setText(textDto);
        request.setToUser(openId);
        wxMessageApi.createQrCode(request);
    }

}