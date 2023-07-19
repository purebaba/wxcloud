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


  /**
   * 获取当前计数
   * @return API response json
   */
  @GetMapping(value = "/api/count")
  ApiResponse get(@RequestParam(required = false) String signature, @RequestParam(required = false) String timestamp, @RequestParam(required = false) String nonce, @RequestParam(required = false) String echostr) {
    logger.info("/api/count get request");
    log.info("signature:{}", signature);
    log.info("timestamp:{}", timestamp);
    log.info("nonce:{}", nonce);
    log.info("echostr:{}", echostr);
    return ApiResponse.ok();
  }

  @GetMapping("/api/wx-data")
  public ApiResponse getWxData(@RequestParam(required = false) String signature, @RequestParam(required = false) String timestamp, @RequestParam(required = false) String nonce, @RequestParam(required = false) String echostr) {
    log.info("signature:{}", signature);
    log.info("timestamp:{}", timestamp);
    log.info("nonce:{}", nonce);
    log.info("echostr:{}", echostr);
    return ApiResponse.ok();
  }



  /**
   * 更新计数，自增或者清零
   * @param request {@link CounterRequest}
   * @return API response json
   */
  @PostMapping(value = "/api/count")
  ApiResponse create(@RequestBody Object request) {
    logger.info("/api/count post request, action: {}", request.toString());
    return ApiResponse.ok();
  }
  
}