package com.zed.admin.common.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 公共字段数据填充
 *
 * @author zed
 * @date 2019/12/13 17:44
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public abstract class BaseEntity<T extends Model<?>> extends Model<T> {

    public final static String UPDATE_TIME = "update_time";
    public final static String CREATE_TIME = "create_time";
    public final static String UPDATE_BY = "update_by";
    public final static String CREATE_BY = "create_by";
    public final static String REMARK = "remark";
    public final static String IS_DELETED = "is_deleted";

    /**
     * 实体编号（唯一标识）
     */
    protected Long id;
    /**
     * 创建者
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    protected Long createBy;

    /**
     * 创建日期
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    protected Long updateBy;

    /**
     * 更新日期
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    protected Date updateTime;

    /**
     * 备注
     */
    protected String remark;

    /**
     * 删除标记
     */
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    protected Boolean isDeleted;

    /**
     * 默认未删除
     */
    public BaseEntity() {
        this.isDeleted = false;
    }

    @Override
    protected Serializable pkVal() {
        /**
         * AR 模式这个必须有，否则 xxById 的方法都将失效！
         * 另外 UserMapper 也必须 AR 依赖该层注入，有可无 XML
         */
        return this.id;
    }
}
