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
@TableName("sys_role")
public class RoleDo extends BaseDo {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String name;

}
