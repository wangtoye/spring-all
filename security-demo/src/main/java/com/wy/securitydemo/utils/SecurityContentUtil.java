package com.wy.securitydemo.utils;

import com.wy.securitydemo.dto.UserDto;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wangye
 * @date: 2019-10-10
 * Description:
 */
public class SecurityContentUtil {

    /**
     * 获取当前对象
     * @return
     */
    public static UserDto getCurrentUser(){
        return (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
