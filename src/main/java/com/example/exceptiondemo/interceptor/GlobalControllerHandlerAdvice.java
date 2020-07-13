package com.example.exceptiondemo.interceptor;


import com.example.exceptiondemo.exception.BizRuntimeException;
import com.example.exceptiondemo.exception.model.AjaxResult;
import com.example.exceptiondemo.exception.model.ResCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


/**
 * @Author: YLBG-YCY-1325
 * @Description: 异常捕获, 如果有自己的异常，后面写自己的异常捕获
 * @Date: 2018/5/4 9:29
 */
@Slf4j
@RestControllerAdvice
public class GlobalControllerHandlerAdvice {

/*    @ExceptionHandler(BizArgumentException.class)
    @ResponseBody
    public AjaxResult bizArgError(BizArgumentException e) {
        return resAjaxResult(e, ResCode.BIZ_SERVER_ERROR);
    }*/

    @ExceptionHandler(BizRuntimeException.class)
    @ResponseBody
    public AjaxResult bizRuntimeError(BizRuntimeException e) {
        log.error("出错,信息如下:", e);
        return resAjaxResult(e, ResCode.BIZ_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public AjaxResult resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if(!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                msgBuilder.append(objectError.getDefaultMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            return AjaxResult.fail(errorMessage);
        }
        return AjaxResult.ok();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public AjaxResult bizError(Exception e) {
        log.error("出错,信息如下:", e);
        return AjaxResult.of(ResCode.BIZ_SERVER_ERROR.getCode(),
                ResCode.BIZ_SERVER_ERROR.getDesc(), null);
    }



   /* @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void userError(UserException e, HttpServletResponse response)
            throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value());
        return;
    }*/

    private AjaxResult resAjaxResult(BizRuntimeException e,
            ResCode resCode) {
        if(null != e.getCode() || StringUtils.isNotBlank(e.getMessage())) {
            return AjaxResult.of(e.getCode(), e.getMessage(), null);
        }
        return AjaxResult.of(resCode.getCode(), resCode.getDesc(), null);
    }
}
