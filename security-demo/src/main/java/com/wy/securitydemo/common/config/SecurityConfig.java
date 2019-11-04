package com.wy.securitydemo.common.config;

import com.wy.securitydemo.common.config.security.detailssource.UserAuthenticationDetailsSource;
import com.wy.securitydemo.common.config.security.entrypoint.AjaxAuthenticationEntryPoint;
import com.wy.securitydemo.common.config.security.handler.AjaxAccessDeniedHandler;
import com.wy.securitydemo.common.config.security.handler.AjaxAuthenticationFailureHandler;
import com.wy.securitydemo.common.config.security.handler.AjaxAuthenticationSuccessHandler;
import com.wy.securitydemo.common.config.security.handler.AjaxLogoutSuccessHandler;
import com.wy.securitydemo.common.config.security.provider.AjaxAuthenticationProvider;
import com.wy.securitydemo.common.filter.JwtAuthenticationTokenFilter;
import com.wy.securitydemo.service.SecurityUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wangye
 * @date: 2019-09-30
 * Description:
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 未登陆时返回 JSON 格式的数据给前端（否则为 html）
     */
    @Resource
    private AjaxAuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 登录成功返回的 JSON 格式数据给前端（否则为 html）
     */
    @Resource
    private AjaxAuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * 登录失败返回的 JSON 格式数据给前端（否则为 html）
     */
    @Resource
    private AjaxAuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）
     */
    @Resource
    private AjaxLogoutSuccessHandler logoutSuccessHandler;

    /**
     * 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）
     */
    @Resource
    private AjaxAccessDeniedHandler accessDeniedHandler;

    /**
     * JWT 拦截器
     */
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private SecurityUserServiceImpl securityUserServiceImpl;

    @Resource
    private AjaxAuthenticationProvider ajaxAuthenticationProvider;

    @Resource
    private UserAuthenticationDetailsSource userAuthenticationDetailsSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(ajaxAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 去掉 CSRF 加上CORS
        http
                .cors().and()
                //CSRF跨站点请求伪造,为测试所以关闭。正常生产需要对Referer认证
                .csrf().disable()
                // 使用 JWT，关闭session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                //定义哪些URL需要被保护、哪些不需要被保护
                .authorizeRequests()
                //不需要认证
                .antMatchers("/api/getSecret").permitAll()
                //任何请求,登录后可以访问
                .anyRequest().authenticated()
                .and()

                .formLogin()
                // 登录成功
                .successHandler(authenticationSuccessHandler)
                // 登录失败
                .failureHandler(authenticationFailureHandler)
                .permitAll()
                .authenticationDetailsSource(userAuthenticationDetailsSource)
                .and()

                //默认注销行为 logout
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();

        // 记住我
        http.rememberMe().rememberMeParameter("remember-me")
                .userDetailsService(securityUserServiceImpl).tokenValiditySeconds(3600);

        // 无权访问 JSON 格式的数据
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint);

        // JWT Filter
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 定义全局，可能某些操作会直接跳过security
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/static/**", "/favicon.ico")
                .antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
