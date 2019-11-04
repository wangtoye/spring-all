package com.wy.securitydemo.common.config.security.detailssource;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wangye
 * @date: 2019-10-28
 * Description:
 */
public class UserWebAuthenticationDetails extends WebAuthenticationDetails {

    private String validateCode;

    public UserWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        validateCode = request.getParameter("validateCode");
    }


    public String getValidateCode() {
        return validateCode;
    }
}
