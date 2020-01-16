package com.zed.admin.system.pojo.ao;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author zed
* @date 2020-01-16
*/
@Data
public class RoleQueryAO implements Serializable {

    // 主键
    private Long id;

    // 用户ID
    private Long userId;

    // 角色名称
    private String name;

    // 菜单ID
    private Long menuId;

    // 商户ID
    private Long orgId;

    // 角色ID
    private Long roleId;

    // 角色编码
    private String code;

    // 角色描述
    private String description;

    // 数据范围(全部|本级|自定义)
    private String dataScope;

    // 角色级别
    private Integer level;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;
}