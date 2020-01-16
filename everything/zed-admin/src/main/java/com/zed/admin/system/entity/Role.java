package com.zed.admin.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zed.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;

/**
 * Role
 *
 * @author zed
 * @date 2020-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "zed_admin_role")
public class Role extends BaseEntity<Role> {

    /**
     * 角色名称
     */
    @Column(name = "name")
    private String name;


    /**
     * 角色编码
     */
    @Column(name = "code")
    private String code;

    /**
     * 角色描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 数据范围(全部|本级|自定义)
     */
    @Column(name = "data_scope")
    private String dataScope;

    /**
     * 角色级别
     */
    @Column(name = "level")
    private Integer level;


}