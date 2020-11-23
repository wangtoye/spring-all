package com.wy.gatewayservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : wangtoye
 * @date : 2019-11-04
 * Description: 默认降级处理
 */
@RestController
public class DefaultHystrixController {

    @RequestMapping("/defaultFallback")
    public String defaultfallback() {
        return "降级操作...";
    }
}