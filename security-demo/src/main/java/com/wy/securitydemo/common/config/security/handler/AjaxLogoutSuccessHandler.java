package com.wy.securitydemo.common.config.security.handler;

import com.google.gson.Gson;
import com.wy.securitydemo.common.constants.ResponseCode;
import com.wy.securitydemo.dto.UserDto;
import com.wy.securitydemo.utils.RedisUtil;
import com.wy.securitydemo.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : wangtoye
 * @date : 2019-09-30
 * Description: 登出成功
 */
@Component
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(AjaxLogoutSuccessHandler.class);

    @Resource
    private RedisUtil redisUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Authentication authentication) throws IOException {

        String authHeader = httpServletRequest.getHeader(tokenHeader);
        if (authHeader != null && authHeader.startsWith(tokenPrefix)) {
            final String authToken = authHeader.substring(tokenPrefix.length());
            redisUtil.delete(authToken);
            //将token放入黑名单中
//            redisUtil.hset("blacklist", authToken, DateTimeUtil.getCurrDateTimeStr());
//            logger.info("token：{}已加入redis黑名单", authToken);
        }

        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(new Gson().toJson(ResponseUtil.buildVoByResponseCode(ResponseCode.CODE_SUCCESS)));

        logger.info("用户{}登出成功", ((UserDto) authentication.getPrincipal()).getUsername());
    }
}
