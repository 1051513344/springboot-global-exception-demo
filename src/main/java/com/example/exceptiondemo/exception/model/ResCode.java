
package com.example.exceptiondemo.exception.model;

/**
 * @Author: YLBG-YCY-1325
 * @Description: 返回枚举
 * @Date: 2018/5/8 11:54
 */
public enum ResCode implements AbstractCode {

   /*
   规范文档地址：https://docs.qq.com/doc/DYkFZRHdBSVB4Rld3
   编码标准定义：
            00 0000
            1,2位表示产品线（内部、外部全部铺开）
            3,4,5,6位表示具体的错误代码
    产品线：01-AZ       智能医嘱产品编码为 13
    具体错误代码：0000-9999
    抛错同时，同步到日志平台；每个季度更新一个版本；乐享上持续更新、维护规范。

    用户侧展现：编码-具体提示语，如010001-请稍后再试！（定位为掌医U的具体问题）。

    主要解决的问题：
    通过产品线编码，定位公司、外部问题，以及公司内部（例如掌医、render、nuts）问题；
    通过具体错误代码，业务线定位具体问题。
    1.具体错误代码 0000-9999
    产品专用：0000-1999
    接口平台专用：2000-2999
    业务系统专用：3000-9999
    */

    SUCCESS(AjaxResult.CODE_SUCCESS, AjaxResult.MSG_SUCCESS, AjaxResult.MSG_SUCCESS), FAIL(AjaxResult.CODE_FAIL,
            AjaxResult.MSG_FAIL, AjaxResult.MSG_FAIL),


    SYS_SERVER_ERROR(133001, "服务器开了个小差，请稍后重试", "服务器开了个小差，请稍后重试"),
    BIZ_SERVER_ERROR(133002, "服务挂掉啦，程序员哥哥在拼命抢救中", "服务挂掉啦，程序员哥哥在拼命抢救中"),


    /**
     * 4000-5999 为通用的提示 4,5位表示错误分类
     */
    EXCEPTION_134000(134000, "失败", "失败"),
    EXCEPTION_134001(134001, "缺少必传参数", "缺少必传参数"),
    EXCEPTION_134002(134002, "接口入参有误", "接口入参有误"),
    EXCEPTION_134003(134003, "查无数据", "查无数据"),
    EXCEPTION_134004(134004, "权限不足", "权限不足"),

    /**
     * 6000-9999  6,7,8,9位表示具体的错误代码
     */
    EXCEPTION_136001(136001, "机构已存在", "机构已存在"),

    ;

    ResCode(int code, String desc, String insideDesc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;// 对外用户提示
    private String insideDesc;// 研发内部提示

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public String getInsideDesc() {
        return insideDesc;
    }
}
