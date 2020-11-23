package com.wy.securitydemo.common.config.security.provider;

import com.wy.securitydemo.common.config.security.detailssource.UserWebAuthenticationDetails;
import com.wy.securitydemo.common.config.security.encoder.Smd5PasswordEncoder;
import com.wy.securitydemo.service.SecurityUserServiceImpl;
import com.wy.securitydemo.utils.AesUtils;
import com.wy.securitydemo.utils.RedisUtil;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author : wangtoye
 * @date : 2019-10-09
 * Description:
 */
@Component
public class AjaxAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Resource
    private SecurityUserServiceImpl securityUserService;

    @Resource
    private Smd5PasswordEncoder passwordEncoder;

    @Resource
    private RedisUtil redisUtil;

    /**
     * @param userDetails    数据库需要校验使用的字段
     * @param authentication 用户登录传的字段
     * @throws AuthenticationException 认证失败异常
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        //1.校验密码
        String presentedPassword = authentication.getCredentials().toString();
        if (StringUtils.isBlank(presentedPassword)) {
            logger.error("认证失败： 密码校验失败");
            throw new BadCredentialsException("认证失败：密码校验失败");
        }
        String username = authentication.getPrincipal().toString();
        Object value = redisUtil.get(username);
        if (Objects.nonNull(value)) {
            redisUtil.delete(username);
            presentedPassword = AesUtils.decrypt(presentedPassword, String.valueOf(value));
            if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
                logger.error("认证失败：密码校验失败");
                throw new BadCredentialsException("认证失败：密码校验失败");
            }

            //2.校验验证码的，可以写自己的逻辑
            String validateCode = "123456";
            UserWebAuthenticationDetails detail = (UserWebAuthenticationDetails) authentication.getDetails();
            if (!detail.getValidateCode().equals(validateCode)) {
                logger.error("认证失败：验证码校验失败");
                throw new BadCredentialsException("认证失败：验证码校验失败");
            }
        } else {
            logger.error("认证失败：获取用户名失败");
            throw new BadCredentialsException("认证失败：获取用户名失败");
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        UserDetails loadedUser = securityUserService.loadUserByUsername(username);
        if (loadedUser == null) {
            throw new InternalAuthenticationServiceException(
                    "UserDetailsService returned null, which is an interface contract violation");
        }
        return loadedUser;
    }
}
