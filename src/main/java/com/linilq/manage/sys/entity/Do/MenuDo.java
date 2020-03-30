package com.linilq.manage.sys.entity.Do;

import com.baomidou.mybatisplus.annotation.TableName;
import com.linilq.manage.core.base.BaseDo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author linilq
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
public class MenuDo extends BaseDo {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单
     */
    private Long parentId;

    /**
     * 菜单层级
     */
    private String level;

    /**
     * 父菜单联集
     */
    private String parentIds;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 链接地址
     */
    private String href;

    /**
     * 打开方式
     */
    private String target;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 显示背景色
     */
    private String bgColor;

    /**
     * 是否显示
     */
    private Integer isShow;

    /**
     * 权限标识
     */
    private String permission;

}
