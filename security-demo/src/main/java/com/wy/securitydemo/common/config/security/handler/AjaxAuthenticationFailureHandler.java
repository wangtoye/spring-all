package com.wy.securitydemo.common.config.security.handler;

import com.google.gson.Gson;
import com.wy.securitydemo.common.constants.ResponseCode;
import com.wy.securitydemo.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : wangtoye
 * @date : 2019-09-29
 * Description: 登录失败
 */
@Component
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(new Gson().toJson(ResponseUtil.buildVoByResponseCode(ResponseCode.CODE_AUTH_FAILED)));
    }
}
