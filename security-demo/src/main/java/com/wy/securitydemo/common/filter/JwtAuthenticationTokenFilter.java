package com.wy.securitydemo.common.filter;

import com.google.gson.Gson;
import com.wy.securitydemo.common.constants.ResponseCode;
import com.wy.securitydemo.dto.UserDto;
import com.wy.securitydemo.utils.*;
import io.jsonwebtoken.Claims;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wangye
 * @date: 2019-09-30
 * Description:
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Value("${jwt.validTime}")
    private int validTime;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;

    @Autowired
    private JwtRedisUtil jwtRedisUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException {
        try {
            String authHeader = request.getHeader(tokenHeader);

            if (StringUtils.isNotBlank(authHeader) && authHeader.startsWith(tokenPrefix)) {
                String authToken = authHeader.substring(tokenPrefix.length());

                if (StringUtils.isBlank(authToken)) {
                    filterChain.doFilter(request, response);
                    return;
                }

                Claims claims = JwtTokenUtil.parseToken(authToken);

                String userName;
//            String ip;
                if (claims != null) {
                    userName = claims.getSubject();
//                ip = (String) claims.get("ip");
                } else {
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().write(new Gson().toJson(ResponseUtil.buildVoByResponseCode(ResponseCode.CODE_AUTH_TOKEN_INVALID)));
                    return;
                }

//            //进入黑名单验证
//            if (jwtRedisUtil.isBlackList(authToken)) {
//                logger.info("用户：{}的token：{}在黑名单之中，拒绝访问", userName, authToken);
//                response.setCharacterEncoding("UTF-8");
//                response.getWriter().write(new Gson().toJson(ResponseUtil.buildVoByResponseCode(ResponseCode
//                .CODE_AUTH_TOKEN_INVALID)));
//                return;
//            }

                //判断token是否过期
                if (!redisUtil.hasKey(authToken)) {
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().write(new Gson().toJson(ResponseUtil.buildVoByResponseCode(ResponseCode.CODE_AUTH_LOGIN_OVERDUE)));
                    return;


                    //过期的话，从redis中读取有效时间（比如七天登录有效），再refreshToken（根据以后业务加入，现在直接refresh）
                    //同时，已过期的token加入黑名单
//                //判断redis是否有保存
//                String expirationTime = (String) redisUtil.hget(authToken, "expirationTime");
//                if (JwtTokenUtil.isExpiration(expirationTime)) {
//                    //获得redis中用户的token刷新时效
//                    String tokenValidTime = (String) jwtRedisUtil.getTokenValidTimeByToken(authToken);
//                    String currentTime = DateTimeUtil.getCurrDateTimeStr();
//                    //这个token已作废，加入黑名单
//                    logger.info("{}已作废，加入黑名单", authToken);
//                    redisUtil.hset("blacklist", authToken, currentTime);
//
//                    if (currentTime.compareTo(tokenValidTime) > 0) {
//                        //超过有效期，不予刷新
//                        logger.info("{}已超过有效期，不予刷新", authToken);
//                        response.setCharacterEncoding("UTF-8");
//                        response.getWriter().write(new Gson().toJson(ResponseUtil.buildVoByResponseCode
//                        (ResponseCode.CODE_AUTH_LOGIN_OVERDUE)));
//                        return;
//                    } else {
//                        //仍在刷新时间内，则刷新token，放入请求头中
//                        claims.put("ip", ip);
//                        String jwt = JwtTokenUtil.generateToken(userName, claims);
//
//                        //新的token保存到redis中
//                        jwtRedisUtil.setTokenRefresh(jwt, userName, ip);
//                        //删除旧的token保存的redis
//                        redisUtil.deleteKey(authToken);
//
//                        logger.info("redis已删除旧token：{},新token：{}已更新redis", authToken, jwt);
//                        response.setHeader("Authorization", "Bearer " + jwt);
//                    }
//                }
                }

                if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    // 加入对ip的验证
                    // 如果ip不正确，进入黑名单验证
//                if (!Objects.equals(ip,AccessAddressUtil.getIpAddress(request))) {
//                    logger.info("用户：{}的ip地址变动，进入黑名单校验",userName);
//                    //进入黑名单验证
//                    if (jwtRedisUtil.isBlackList(authToken)) {
//                        logger.info("用户：{}的token：{}在黑名单之中，拒绝访问",userName,authToken);
//                        response.setCharacterEncoding("UTF-8");
//                        response.getWriter().write(new Gson().toJson(ResponseUtil.buildVoByResponseCode
//                        (ResponseCode.CODE_AUTH_TOKEN_INVALID)));
//                        return;
//                    }
//                }


//                UserDetails userDto = securityUserServiceImpl.loadUserByUsername(userName);
                    UserDetails userDto = buildUserDto(claims);
                    if (userDto != null) {
                        //这边应该实现WebAuthenticationDetailsSource，把需要使用的用户信息全部放到details中去，而不是直接放到principal
                        //偷个懒操作一下
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDto, null, null);
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            logger.error("系统异常-" + e);
            e.printStackTrace();
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(new Gson().toJson(ResponseUtil.buildVoByResponseCode(ResponseCode.CODE_SYSTEM_ERROR)));
        }
    }


    private UserDetails buildUserDto(Map<String, Object> claims) {
        UserDto userDto = new UserDto();
        userDto.setUserId(Integer.parseInt(claims.get("userId").toString()));
        userDto.setUsername(claims.get("username").toString());
        userDto.setRealName(claims.get("realName").toString());

        if (claims.get("jobPosition") != null) {
            userDto.setJobPosition(claims.get("jobPosition").toString());
        }

        if (claims.get("addTime") != null) {
            userDto.setAddTime(DateTimeUtil.getFormatDate(claims.get("addTime").toString(), DateTimeUtil.DATE_FORMAT));
        }

        return userDto;
    }
}
