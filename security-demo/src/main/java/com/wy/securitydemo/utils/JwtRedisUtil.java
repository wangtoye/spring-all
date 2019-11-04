package com.wy.securitydemo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;

/**
 * @author : wangye
 * @date : 2019-11-04
 * Description: 操作jwt的redis api
 */
@Component
public class JwtRedisUtil {

    @Value("${jwt.expirationSeconds}")
    private int expirationSeconds;

    @Value("${jwt.validTime}")
    private int validTime;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 判断此token是否在黑名单中
     *
     * @param token 查询的key
     * @return true false
     */
    public Boolean isBlackList(String token) {
        return redisUtil.hasKey("blacklist", token);
    }

    /**
     * 将token加入到redis黑名单中
     *
     * @param token 查询的key
     */
    public void addBlackList(String token) {
        redisUtil.hset("blacklist", token, "true");
    }


    /**
     * 查询token下的刷新时间
     *
     * @param token 查询的key
     * @return HV
     */
    public Object getTokenValidTimeByToken(String token) {
        return redisUtil.getHashValue(token, "tokenValidTime");
    }

    /**
     * 查询token下的刷新时间
     *
     * @param token 查询的key
     * @return HV
     */
    public Object getUsernameByToken(String token) {
        return redisUtil.getHashValue(token, "username");
    }

    /**
     * 查询token下的刷新时间
     *
     * @param token 查询的key
     * @return HV
     */
    public Object getIpByToken(String token) {
        return redisUtil.getHashValue(token, "ip");
    }

    /**
     * 查询token下的过期时间
     *
     * @param token 查询的key
     * @return HV
     */
    public Object getExpirationTimeByToken(String token) {
        return redisUtil.getHashValue(token, "expirationTime");
    }

    public void setTokenRefresh(String token, String username, String ip) {
        //刷新时间
        Integer expire = validTime;

        redisUtil.hset(token, "tokenValidTime", DateTimeUtil.getFormatDate(DateTimeUtil.getDateBeforeOrAfter(validTime,
                Calendar.SECOND), DateTimeUtil.TIME_FORMAT),
                expire);
        redisUtil.hset(token, "expirationTime",
                DateTimeUtil.getFormatDate(DateTimeUtil.getDateBeforeOrAfter(expirationSeconds,
                        Calendar.SECOND), DateTimeUtil.TIME_FORMAT), expire);
        redisUtil.hset(token, "username", username, expire);
        redisUtil.hset(token, "ip", ip, expire);
    }
}