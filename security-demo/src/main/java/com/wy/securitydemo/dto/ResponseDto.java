package com.wy.securitydemo.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wangtoye
 * @date: 2017/11/14
 * Description:
 */
@Data
public class ResponseDto<T> {
    private boolean success;
    private Integer code;
    private String msg;
    private T data;
}
