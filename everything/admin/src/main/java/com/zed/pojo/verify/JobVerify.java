package com.zed.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * @author zed
 * @date 2020-01-16
 */
@Data
public class JobVerify implements Serializable {

    private Long id;

    // 职位ID
    private Long id;

    private String beanName;

    // 商户ID
    private Long orgId;

    private Timestamp createTime;

    // 职位名称
    private String name;

    private String cronExpression;

    // 是否启用 1启用 0禁用
    private Boolean enabled;

    private Boolean isPause;

    // 排序
    private Integer sort;

    private String jobName;

    // 创建时间
    private Timestamp createTime;

    private String methodName;

    // 更新时间
    private Timestamp updateTime;

    private String params;

    // 创建者
    private String createBy;

    private String remark;

    // 更新者
    private String updateBy;

    // 备注
    private String remark;

    // 删除标志 1已删除 0未删除
    private Boolean isDeleted;
}