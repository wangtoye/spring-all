package com.wy.securitydemo.utils;

import com.wy.securitydemo.common.constants.ResponseCode;
import com.wy.securitydemo.dto.ResponseDto;

/**
 * @author wangtoye
 * @date 2017年8月7日
 */
public class ResponseUtil {

    /**
     * 依据code构造返回结构
     *
     * @param code
     * @param <T>
     * @return
     */
    public static <T> ResponseDto<T> buildVoByResponseCode(ResponseCode code) {
        return buildVoByResponseCode(code, null);
    }

    /**
     * 依据code和data构造返回结构
     *
     * @param code
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseDto<T> buildVoByResponseCode(ResponseCode code, T data) {
        return buildVo(code.isSuccess(), code.getCode(), code.getMsg(), data);
    }

    /**
     * 自定义参数返回结构
     *
     * @param result
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseDto<T> buildVo(boolean result, Integer code, String msg, T data) {
        ResponseDto<T> vo = new ResponseDto<>();
        vo.setSuccess(result);
        vo.setCode(code);
        vo.setMsg(msg);
        vo.setData(data);
        return vo;
    }
}
