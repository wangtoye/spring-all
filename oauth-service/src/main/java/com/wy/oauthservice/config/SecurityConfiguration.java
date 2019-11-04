package com.wy.oauthservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 安全配置，用户，认证，授权等等
 *
 * @author wangye
 */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfiguration {

//    /**
//     * 把web资源忽略掉
//     *
//     * @param web
//     * @throws Exception
//     */
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/css/**", "/js/**", "/plugins/**", "/favicon.ico");
//    }


//    /**
//     * 验证
//     *
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

//    /**
//     * httpSecurity中配置所有请求的安全验证
//     *
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .requestMatchers()
//                    .anyRequest()
//                    .and()
//                .authorizeRequests()
//                    .antMatchers("/oauth/**").permitAll();
//    }
}