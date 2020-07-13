package com.example.exceptiondemo.exception;


import com.example.exceptiondemo.exception.model.AbstractCode;

/**
 * 系统运行时异常父类.
 * @author plz
 */
public class BizException extends Exception {
    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_CODE = -1;

    private Integer code = DEFAULT_CODE;
    private String message;

    public BizException() {
    }

    public BizException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BizException(String message) {
        super(message);
        this.message = message;
    }

    public BizException(Throwable cause) {
        super(cause);
        this.message = cause.getMessage();
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public BizException(AbstractCode abstractCode) {
        this.code = abstractCode.getCode();
        this.message = abstractCode.getDesc();
    }

    public Integer getCode() {
        return code;
    }

    protected void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    protected void setMessage(String message) {
        this.message = message;
    }

    public boolean isDefault() {
        return code == DEFAULT_CODE;
    }

}
