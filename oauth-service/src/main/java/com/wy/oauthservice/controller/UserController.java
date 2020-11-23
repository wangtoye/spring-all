package com.wy.oauthservice.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wangtoye
 * @date: 2019-06-04
 * Description:
 */
@RestController
public class UserController {


    /**
     * 返回用户信息
     *
     * @param authentication
     * @return
     */
    @GetMapping("/user")
    public Authentication getUser(Authentication authentication) {
        return authentication;
    }
}
