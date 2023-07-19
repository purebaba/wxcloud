package com.tencent.wxcloudrun.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.CounterRequest;
import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

/**
 * counter控制器
 */
@RestController
@Slf4j
public class CounterController {

    final CounterService counterService;
    final Logger logger;

    public CounterController(@Autowired CounterService counterService) {
        this.counterService = counterService;
        this.logger = LoggerFactory.getLogger(CounterController.class);
    }


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
    ApiResponse create(HttpServletRequest request) {
        logger.info("/api/count post request, action: {}", request.toString());
        log.info("request:{}", request);
        log.info("request path:{}", request.getPathInfo());
        log.info("request query string:{}", request.getQueryString());
        log.info("request query string:{}", handleRequest(request));
        return ApiResponse.ok();
    }


    private String handleRequest(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 现在sb中包含了请求的body数据，您可以进行相应的处理。
        return "Received data: " + sb.toString();
    }

}