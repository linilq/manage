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
@TableName("sys_user")
public class UserDo extends BaseDo {

    private static final long serialVersionUID = 1L;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 昵称
     */
    private String nickName;

    private String icon;

    /**
     * 密码
     */
    private String password;

    /**
     * shiro加密盐
     */
    private String salt;

    /**
     * 手机号码
     */
    private String tel;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 是否锁定
     */
    private Integer locked;

}
