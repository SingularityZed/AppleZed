package com.zed.admin.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zed.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;

/**
 * Job
 *
 * @author zed
 * @date 2020-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "zed_admin_job")
public class Job extends BaseEntity<Job> {

    /**
     * 商户ID
     */
    @Column(name = "org_id")
    private Long orgId;

    /**
     * 职位名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 是否启用 1启用 0禁用
     */
    @Column(name = "enabled")
    private Boolean enabled;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

}