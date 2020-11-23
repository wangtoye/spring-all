package com.wy.securitydemo.common.config.security.detailssource;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wangtoye
 * @date: 2019-10-28
 * Description:
 */
@Component
public class UserAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest,
        WebAuthenticationDetails> {

    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new UserWebAuthenticationDetails(context);
    }
}
