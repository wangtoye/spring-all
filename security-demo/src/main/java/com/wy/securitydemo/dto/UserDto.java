package com.wy.securitydemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * @author wangtoye
 * @date 2019/10/18
 * description 用户信息
 */
@Data
public class UserDto implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 用户名称（拼音）
     */
    private String username;

    /**
     * 用户名称（中文）
     */
    private String realName;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 在职岗位
     */
    private String jobPosition;

    /**
     * 备注说明
     */
    private String description;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    /**
     * 权限
     */
    private Set<String> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 账户未过期
     *
     * @return true false
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户未锁定
     *
     * @return true false
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码未过期
     *
     * @return true false
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户是否有效
     *
     * @return true false
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
