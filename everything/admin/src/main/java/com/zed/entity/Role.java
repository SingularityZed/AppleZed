package com.zed.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Role
 *
 * @author zed
 * @date 2020-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "role")
public class Role extends BaseEntity<Role> {

    /**
     * 主键
     */
    @Column(name = "id")
    private Long id;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 角色名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 菜单ID
     */
    @Column(name = "menu_id")
    private Long menuId;

    /**
     * 商户ID
     */
    @Column(name = "org_id")
    private Long orgId;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;

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

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Timestamp createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Timestamp updateTime;


}