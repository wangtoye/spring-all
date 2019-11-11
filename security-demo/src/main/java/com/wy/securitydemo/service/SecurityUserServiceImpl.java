package com.wy.securitydemo.service;

import com.wy.securitydemo.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author wangye
 * @date 2019/09/30
 */
@Service
public class SecurityUserServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUserServiceImpl.class);


    /**
     * 自定义用户数据来源
     *
     * @param userName 用户名
     * @return 用户信息
     * @throws UsernameNotFoundException 用户名未找到异常
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.debug("查询用户{}开始", userName);
        UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setUsername("admin");
        //SMD5Utils.getPwd("123456")创建一个之后放入password，不能每次都创建，这样密码会不一致
        userDto.setPassword("{SMD5}NCjnLT2JmZm0/NYzSKkzehBXtdfGEzz1");
        userDto.setRealName("管理员");
        logger.debug("查询用户{}结束", userName);
        return userDto;
    }
}
