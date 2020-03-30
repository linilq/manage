package com.linilq.manage.sys.entity.dto;

import com.linilq.manage.core.Constants;
import lombok.Data;

/**
 * @author lizhijian
 * @description
 * @date 2020/2/21
 */
@Data
public class BaseResultDto<T> {
    private Integer code;
    private String msg;
    private T data;

    public BaseResultDto() {
        this(404, "处理异常");
    }

    public BaseResultDto(T data) {
        this(Constants.ResposeCodeSuc, "处理成功", data);
    }

    public BaseResultDto(String msg, T data) {
        this(Constants.ResposeCodeSuc, msg, data);
    }

    public BaseResultDto(Integer code, String msg) {
        this(code, msg, null);
    }

    public BaseResultDto(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
