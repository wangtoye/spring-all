package com.wy.oauthservice.config;

///*
// * 资源配置
// *
// * @author wangtoye
// */
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//
//    /**
//     * 用于配置对受保护的资源的访问规则
//     * 默认情况下所有不在/oauth/**下的资源都是受保护的资源
//     *
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
////        http.requestMatchers().antMatchers("/api/**")
////                .and()
////                .authorizeRequests()
////                .anyRequest().authenticated();
//        http
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint((request, response, authException) -> response.sendError
//                (HttpServletResponse.SC_UNAUTHORIZED))
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//
//
//    }
//}