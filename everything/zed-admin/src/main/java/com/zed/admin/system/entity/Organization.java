package com.zed.admin.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zed.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;

/**
 * Organization
 *
 * @author zed
 * @date 2020-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "zed_admin_organization")
public class Organization extends BaseEntity<Organization> {

    /**
     * 商户名称
     */
    @Column(name = "org_name")
    private String orgName;

    /**
     * 是否启用 1启用 0禁用
     */
    @Column(name = "enabled")
    private Boolean enabled;

    /**
     * 父ID
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 商户描述信息
     */
    @Column(name = "description")
    private String description;

    /**
     * 联系人
     */
    @Column(name = "contact_person")
    private String contactPerson;

    /**
     * 联系电话
     */
    @Column(name = "contact_telephone")
    private String contactTelephone;

    /**
     * 联系人邮箱
     */
    @Column(name = "contact_email")
    private String contactEmail;

    /**
     * 身份证号码
     */
    @Column(name = "identity_number")
    private String identityNumber;

    /**
     * 省（独立行政区）
     */
    @Column(name = "province")
    private String province;

    /**
     * 市（县）
     */
    @Column(name = "city")
    private String city;

    /**
     * 区域（村）
     */
    @Column(name = "area")
    private String area;

    /**
     * 联系地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 企业名称
     */
    @Column(name = "enterprise_name")
    private String enterpriseName;

    /**
     * 统一社会信用代码
     */
    @Column(name = "unified_social_credit_code")
    private String unifiedSocialCreditCode;

    /**
     * 营业执照照片（分辨率不得低于1000*1000）
     */
    @Column(name = "business_license_photo")
    private String businessLicensePhoto;

    /**
     * 二维码
     */
    @Column(name = "promote_code")
    private String promoteCode;


}