package com.wy.securitydemo.common.config.security.handler;

import com.google.gson.Gson;
import com.wy.securitydemo.common.constants.ResponseCode;
import com.wy.securitydemo.utils.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : wangye
 * @date : 2019-09-29
 * Description: 无权访问
 */
@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(new Gson().toJson(ResponseUtil.buildVoByResponseCode(ResponseCode.CODE_ACCESS_DENIED)));
    }
}
