package com.zed.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* User
* @author zed
* @date 2020-01-16
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "user")
public class User  extends BaseEntity<User>  {

    /**
     *   主键
     */
    @Column(name = "id")
    private Long id;

    /**
     *   用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     *   登录名称
     */
    @Column(name = "username")
    private String username;

    /**
     *   角色ID
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     *   登录密码
     */
    @Column(name = "password")
    private String password;

    /**
     *   是否启用 1启用 0禁用
     */
    @Column(name = "enabled")
    private Boolean enabled;

    /**
     *   商户ID
     */
    @Column(name = "org_id")
    private Long orgId;

    /**
     *   岗位ID
     */
    @Column(name = "job_id")
    private Long jobId;

    /**
     *   性别 1男 2女 3保密
     */
    @Column(name = "gender")
    private Integer gender;

    /**
     *   联系电话
     */
    @Column(name = "telephone")
    private String telephone;

    /**
     *   联系邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     *   头像
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     *   昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     *   描述信息
     */
    @Column(name = "description")
    private String description;

    /**
     *   最后登录时间
     */
    @Column(name = "last_login_time")
    private Timestamp lastLoginTime;

    /**
     *   最后修改密码时间
     */
    @Column(name = "last_password_reset_time")
    private Timestamp lastPasswordResetTime;

    /**
     *   创建时间
     */
    @Column(name = "create_time")
    private Timestamp createTime;

    /**
     *   更新时间
     */
    @Column(name = "update_time")
    private Timestamp updateTime;

    /**
     *   创建者
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     *   更新者
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     *   备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     *   删除标志 1删除 0未删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;


}