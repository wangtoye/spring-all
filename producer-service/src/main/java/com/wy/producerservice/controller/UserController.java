package com.wy.producerservice.controller;


import com.wy.producerservice.entity.User;
import com.wy.producerservice.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangye
 * @since 2019-09-16
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/get")
    @ResponseBody
    public void getUser() {
        List<User> list = userService.list();
        list.forEach(user -> System.out.println(user.toString()));

    }
}

