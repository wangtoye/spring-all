package com.wy.securitydemo.controller;

import com.google.gson.Gson;
import com.wy.securitydemo.common.constants.ResponseCode;
import com.wy.securitydemo.utils.RedisUtil;
import com.wy.securitydemo.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wangye
 * @date: 2019-10-08
 * Description:
 */
@RestController
@RequestMapping("/api")
public class LoginModelController {
    private static final Logger logger = LoggerFactory.getLogger(LoginModelController.class);

    @Value("${jwt.secret.expire}")
    private Integer expire;

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping(value = "/getSecret", method = RequestMethod.GET)
    public String getSecret(@RequestParam("userName") String userName) {
        logger.info("生成动态秘钥开始");
        String value = UUID.randomUUID().toString().replace("-", "");
        redisUtil.set(userName, value, expire);
        logger.info("生成动态秘钥完成");
        return new Gson().toJson(ResponseUtil.buildVoByResponseCode(ResponseCode.CODE_SUCCESS, value));
    }


}
