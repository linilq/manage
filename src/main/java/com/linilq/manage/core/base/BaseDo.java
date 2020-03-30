package com.linilq.manage.core.base;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @author lizhijian
 * @description
 * @date 2020/2/20
 */
@Data
public class BaseDo {
    private Long id;
    private String createBy;
    private Date createTime;
    private Date updateTime;
    private String updateBy;
    private Integer delFlag;

    @TableField(exist = false)
    protected boolean enablePage = Boolean.TRUE;
    @TableField(exist = false)
    protected int page;
    @TableField(exist = false)
    protected int limit;
}
