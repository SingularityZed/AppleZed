package com.zed.admin.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * Menu
 *
 * @author zed
 * @date 2020-01-16
 */
@Data
@Accessors(chain = true)
@TableName(value = "zed_admin_menu")
public class Menu implements Serializable {

    /**
     * 菜单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 是否外链
     */
    @Column(name = "is_frame")
    private Integer isFrame;

    /**
     * 菜单名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 组件
     */
    @Column(name = "component")
    private String component;

    /**
     * 上级菜单ID
     */
    @Column(name = "pid")
    private Long pid;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 链接地址
     */
    @Column(name = "path")
    private String path;

    /**
     * 是否缓存 1缓存 0不缓存
     */
    @Column(name = "is_cache")
    private Integer isCache;

    /**
     * 是否隐藏 1隐藏 0不隐藏
     */
    @Column(name = "is_hidden")
    private Integer isHidden;

    /**
     * 组件名称
     */
    @Column(name = "component_name")
    private String componentName;

    /**
     * 权限
     */
    @Column(name = "permission")
    private String permission;

    /**
     * 类型
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;


}