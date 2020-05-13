package com.zed.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Job
 *
 * @author zed
 * @date 2020-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "job")
public class Job extends BaseEntity<Job> {

    @Column(name = "id")
    private Long id;

    /**
     * 职位ID
     */
    @Column(name = "id")
    private Long id;

    @Column(name = "bean_name")
    private String beanName;

    /**
     * 商户ID
     */
    @Column(name = "org_id")
    private Long orgId;

    @Column(name = "create_time")
    private Timestamp createTime;

    /**
     * 职位名称
     */
    @Column(name = "name")
    private String name;

    @Column(name = "cron_expression")
    private String cronExpression;

    /**
     * 是否启用 1启用 0禁用
     */
    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "is_pause")
    private Boolean isPause;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

    @Column(name = "job_name")
    private String jobName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "method_name")
    private String methodName;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "params")
    private String params;

    /**
     * 创建者
     */
    @Column(name = "create_by")
    private String createBy;

    @Column(name = "remark")
    private String remark;

    /**
     * 更新者
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 删除标志 1已删除 0未删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;


}