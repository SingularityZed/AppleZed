package com.zed.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author zed
* @date 2020-01-16
*/
@Data
public class UserDTO implements Serializable {

    // 主键
    private Long id;

    // 用户ID
    private Long userId;

    // 登录名称
    private String username;

    // 角色ID
    private Long roleId;

    // 登录密码
    private String password;

    // 是否启用 1启用 0禁用
    private Boolean enabled;

    // 商户ID
    private Long orgId;

    // 岗位ID
    private Long jobId;

    // 性别 1男 2女 3保密
    private Integer gender;

    // 联系电话
    private String telephone;

    // 联系邮箱
    private String email;

    // 头像
    private String avatar;

    // 昵称
    private String nickname;

    // 描述信息
    private String description;

    // 最后登录时间
    private Timestamp lastLoginTime;

    // 最后修改密码时间
    private Timestamp lastPasswordResetTime;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;

    // 创建者
    private String createBy;

    // 更新者
    private String updateBy;

    // 备注
    private String remark;

    // 删除标志 1删除 0未删除
    private Boolean isDeleted;
}