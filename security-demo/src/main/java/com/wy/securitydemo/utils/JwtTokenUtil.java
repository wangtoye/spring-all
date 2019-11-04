package com.wy.securitydemo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * jwt生成token
 *
 * @author wangye
 */
public class JwtTokenUtil {

    private final static String SALT = "salt";

    /**
     * 生成token
     *
     * @param subject 主体信息
     * @param claims  自定义属性
     * @return
     */
    public static String generateToken(String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, SALT)
                .setIssuedAt(new Date())
                .compact();
    }

    /**
     * 解析token, 获得subject中的信息
     *
     * @param token
     * @return
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SALT)
                .parseClaimsJws(token).getBody();
    }

    /**
     * 是否已过期
     *
     * @param expirationTime
     * @return
     */
    public static boolean isExpiration(String expirationTime) {
        Long expirationDate = DateTimeUtil.getFormatDate(expirationTime, DateTimeUtil.TIME_FORMAT).getTime();
        //通过redis中的失效时间进行判断 当前时间比过期时间大，失效
        return expirationDate < System.currentTimeMillis();
    }
}