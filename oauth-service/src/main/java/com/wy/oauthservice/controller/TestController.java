package com.wy.oauthservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wangtoye
 * @date: 2019-04-25
 * Description:
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("/{name}")
    public String sayHello(@PathVariable String name) {
        return "Hello, " + name;
    }

}
