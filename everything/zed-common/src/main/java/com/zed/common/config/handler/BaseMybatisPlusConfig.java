package com.zed.common.config.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * BaseMybatisPlusConfig
 *
 * @Author: zed
 * @Date: 2019/12/13 17:28
 */
@Slf4j
@Component
public class BaseMybatisPlusConfig implements MetaObjectHandler {

    private final static String CREATE_TIME = "createTime";
    private final static String UPDATE_TIME = "updateTime";
    private final static String CREATE_BY = "createBy";
    private final static String UPDATE_BY = "updateBy";
    private final static String REMARK = "remark";
    private final static String IS_DELETED = "isDeleted";

    /**
     * 插入填充,字段为空自动填充
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName(CREATE_TIME, metaObject);
        Object updateTime = getFieldValByName(UPDATE_TIME, metaObject);
        Object createBy = getFieldValByName(CREATE_BY, metaObject);
        Object updateBy = getFieldValByName(UPDATE_BY, metaObject);
        Object remark = getFieldValByName(REMARK, metaObject);
        Object isDeleted = getFieldValByName(IS_DELETED, metaObject);
        if (createTime == null) {
            this.setFieldValByName(CREATE_TIME, new Date(), metaObject);
        }
        if (updateTime == null) {
            this.setFieldValByName(UPDATE_TIME, new Date(), metaObject);
        }
        if (isDeleted == null) {
            this.setFieldValByName(IS_DELETED, false, metaObject);
        }
        // 创建者、更新者、备注暂无


    }

    /**
     * 更新填充
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(UPDATE_TIME,new Date(),metaObject);
    }
}
