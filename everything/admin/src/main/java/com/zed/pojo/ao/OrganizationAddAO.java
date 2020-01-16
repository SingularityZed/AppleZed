package com.zed.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author zed
* @date 2020-01-16
*/
@Data
public class OrganizationAddAO implements Serializable {

    // 商户ID
    private Long id;

    // 商户名称
    private String orgName;

    // 是否启用 1启用 0禁用
    private Boolean enabled;

    // 父ID
    private Long parentId;

    // 商户描述信息
    private String description;

    // 联系人
    private String contactPerson;

    // 联系电话
    private String contactTelephone;

    // 联系人邮箱
    private String contactEmail;

    // 身份证号码
    private String identityNumber;

    // 省（独立行政区）
    private String province;

    // 市（县）
    private String city;

    // 区域（村）
    private String area;

    // 联系地址
    private String address;

    // 企业名称
    private String enterpriseName;

    // 统一社会信用代码
    private String unifiedSocialCreditCode;

    // 营业执照照片（分辨率不得低于1000*1000）
    private String businessLicensePhoto;

    // 二维码
    private String promoteCode;

    // 创建时间
    private Timestamp createTime;

    // 更新时间
    private Timestamp updateTime;

    // 创建者
    private String createBy;

    // 更新者
    private String updateBy;

    // 备注
    private String remark;

    // 删除标志 1已删除 0未删除
    private Boolean isDeleted;
}