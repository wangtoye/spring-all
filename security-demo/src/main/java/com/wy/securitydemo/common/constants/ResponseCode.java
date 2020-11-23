package com.wy.securitydemo.common.constants;

/**
 * @author wangtoye
 */
public enum ResponseCode {
    //默认success为false
    CODE_SUCCESS(100000, "处理成功", true),
    CODE_ACCESS_DENIED(100001, "无权访问"),
    CODE_NEED_AUTH(100002, "未登录"),
    CODE_AUTH_FAILED(100003, "登录失败"),
    CODE_AUTH_TOKEN_INVALID(100004, "无效的TOKEN"),
    CODE_AUTH_LOGIN_OVERDUE(100005, "登录失效"),

    CODE_ERROR(111111, "处理失败"),
    CODE_SYSTEM_ERROR(999999, "系统错误");

    private Integer code;
    private String msg;
    private boolean success;

    ResponseCode(int code, String msg, boolean success) {
        this.setCode(code);
        this.setMsg(msg);
        this.setSuccess(success);
    }

    ResponseCode(int code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
        this.setSuccess(false);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ResponseCode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", success=" + success +
                '}';
    }
}
