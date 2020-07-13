package com.example.exceptiondemo.exception.model;

/**
 * Created by dehua.lai on 2017/7/11. 这个类不支持new
 */
public class AjaxResult<T> {
    public static final int CODE_SUCCESS = 000000;
    public static final String MSG_SUCCESS = "成功";
    public static final int CODE_FAIL = 134000;
    public static final String MSG_FAIL = "失败";

    private int code;
    private String msg;
    private T data;

    private AjaxResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public AjaxResult(AbstractCode status, T data) {
        this.code = status.getCode();
        this.msg = status.getDesc();
        this.data = data;
    }

    /**** 一下是生成 ajaxResult的方法 *********/
    public static <T> AjaxResult<T> ok(T obj) {
        return new AjaxResult<>(CODE_SUCCESS, MSG_SUCCESS, obj);
    }

    public static <T> AjaxResult<T> ok() {
        return new AjaxResult<>(CODE_SUCCESS, MSG_SUCCESS, null);
    }

    public static <T> AjaxResult<T> fail(T obj) {
        return new AjaxResult<>(CODE_FAIL, MSG_FAIL, obj);
    }

    public static <T> AjaxResult<T> fail() {
        return new AjaxResult<>(CODE_FAIL, MSG_FAIL, null);
    }

    public static <T> AjaxResult<T> fail(String failMsg) {
        return new AjaxResult<>(CODE_FAIL, failMsg, null);
    }

    public static <T> AjaxResult<T> of(int status, String msg, T data) {
        return new AjaxResult<>(status, msg, data);
    }

    public static <T> AjaxResult<T> of(AbstractCode status, T data) {
        return new AjaxResult<>(status, data);
    }

    public static <T> AjaxResult<T> of(AbstractCode status) {
        return new AjaxResult<>(status, null);
    }

    /***** 到这里 *****/

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
