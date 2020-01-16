package com.zed.admin.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zed.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.util.Date;


/**
 * User
 *
 * @Author: zed
 * @Date: 2019/12/13 17:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "zed_admin_user")
public class User extends BaseEntity<User> {

    /**
     * 登录名称
     */
    @Column(name = "username")
    private String username;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 登录密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 是否启用 1启用 0禁用
     */
    @Column(name = "enabled")
    private Boolean enabled;

    /**
     * 商户ID
     */
    @Column(name = "org_id")
    private Long orgId;

    /**
     * 岗位ID
     */
    @Column(name = "job_id")
    private Long jobId;

    /**
     * 性别 1男 2女 3保密
     */
    @Column(name = "gender")
    private Integer gender;

    /**
     * 联系电话
     */
    @Column(name = "telephone")
    private String telephone;

    /**
     * 联系邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 头像
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 描述信息
     */
    @Column(name = "description")
    private String description;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 最后修改密码时间
     */
    @Column(name = "last_password_reset_time")
    private Date lastPasswordResetTime;


}
