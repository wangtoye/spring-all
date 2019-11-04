package com.wy.gatewayservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 默认降级处理
 */
@RestController
public class DefaultHystrixController {

    @RequestMapping("/defaultFallback")
    public String defaultfallback() {
        return "降级操作...";
    }
}