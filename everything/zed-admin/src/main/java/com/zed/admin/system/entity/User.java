package com.zed.admin.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zed.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * User
 *
 * @Author: zed
 * @Date: 2019/12/13 17:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "user")
public class User extends BaseEntity<User> {

    private String username;
    private String password;
    private String telephone;
    private Boolean enabled;


    // TODO
    private Integer gender;
    private String email;


}
