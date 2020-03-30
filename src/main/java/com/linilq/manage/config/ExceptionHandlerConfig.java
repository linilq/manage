package com.linilq.manage.config;

import com.linilq.manage.core.utils.HttpRequestUtil;
import com.linilq.manage.sys.entity.dto.BaseResultDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lizhijian
 * @description
 * @date 2020/3/30
 */
@ControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResultDto<String> handleEexception(HttpServletRequest request, Exception e) {
        HttpRequestUtil.isAjaxRequest(request);
        BaseResultDto<String> resultDto = new BaseResultDto<>();
        resultDto.setMsg(e.getMessage());
        return resultDto;
    }
}
