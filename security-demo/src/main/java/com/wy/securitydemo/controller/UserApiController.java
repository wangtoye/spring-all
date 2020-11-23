package com.wy.securitydemo.controller;

import com.wy.securitydemo.common.constants.ResponseCode;
import com.wy.securitydemo.dto.ResponseDto;
import com.wy.securitydemo.dto.UserDto;
import com.wy.securitydemo.utils.ResponseUtil;
import com.wy.securitydemo.utils.SecurityContentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wangtoye
 * @date: 2018/4/11
 * Description: 用户控制器
 */
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private static final Logger logger = LoggerFactory.getLogger(UserApiController.class);

    /**
     * 查询当前用户
     *
     * @return
     */
    @RequestMapping("/queryCurrentUser")
    public ResponseDto<UserDto> queryCurrentUser() {
        UserDto currentUser = SecurityContentUtil.getCurrentUser();
        return ResponseUtil.buildVoByResponseCode(ResponseCode.CODE_SUCCESS, currentUser);
    }
}
