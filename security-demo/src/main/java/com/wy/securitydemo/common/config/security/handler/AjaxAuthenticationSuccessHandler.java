package com.wy.securitydemo.common.config.security.handler;

import com.google.gson.Gson;
import com.wy.securitydemo.common.constants.ResponseCode;
import com.wy.securitydemo.dto.UserDto;
import com.wy.securitydemo.utils.DateTimeUtil;
import com.wy.securitydemo.utils.JwtRedisUtil;
import com.wy.securitydemo.utils.JwtTokenUtil;
import com.wy.securitydemo.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : wangtoye
 * @date : 2019-09-29
 * Description: 登录成功，返回jwt
 */
@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(AjaxAuthenticationSuccessHandler.class);

    @Resource
    private JwtRedisUtil jwtRedisUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        UserDto userDto = (UserDto) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<>(3);
        claims.put("userId", userDto.getUserId());
        claims.put("username", userDto.getUsername());
        claims.put("realName", userDto.getRealName());
        claims.put("jobPosition", userDto.getJobPosition());
        claims.put("addTime", DateTimeUtil.getFormatDate(userDto.getAddTime(), DateTimeUtil.DATE_FORMAT));

        String ip = httpServletRequest.getRemoteAddr();
        claims.put("ip", ip);
        String userName = userDto.getUsername();
        String jwt = JwtTokenUtil.generateToken(userName, claims);

        jwtRedisUtil.setTokenRefresh(jwt, userName, ip);
        logger.info("用户{}登录成功，信息已保存至redis", userName);

        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(new Gson().toJson(ResponseUtil.buildVoByResponseCode(ResponseCode.CODE_SUCCESS, jwt)));
    }
}
