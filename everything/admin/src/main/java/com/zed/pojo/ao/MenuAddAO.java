package com.zed.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author zed
* @date 2020-01-16
*/
@Data
public class MenuAddAO implements Serializable {

    // 菜单ID
    private Long id;

    // 角色ID
    private Long roleId;

    // 是否外链
    private Integer isFrame;

    // 菜单ID
    private Long menuId;

    // 菜单名称
    private String name;

    // 组件
    private String component;

    // 上级菜单ID
    private Long pid;

    // 排序
    private Integer sort;

    // 图标
    private String icon;

    // 链接地址
    private String path;

    // 是否缓存 1缓存 0不缓存
    private Integer isCache;

    // 是否隐藏 1隐藏 0不隐藏
    private Integer isHidden;

    // 组件名称
    private String componentName;

    // 权限
    private String permission;

    // 类型
    private Integer type;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;
}