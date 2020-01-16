package com.zed.admin.info;

import lombok.Data;

import java.io.Serializable;


/**
 * @author zed
 * @date 2020-01-16
 */
@Data
public class JobInfo implements Serializable {

    // 职位ID
    private Long id;
    // 商户ID
    private Long orgId;
    // 职位名称
    private String name;
    // 是否启用 1启用 0禁用
    private Boolean enabled;
    // 排序
    private Integer sort;
    // 创建者
    private String createBy;
    // 更新者
    private String updateBy;
    // 备注
    private String remark;
    // 删除标志 1已删除 0未删除
    private Boolean isDeleted;
}