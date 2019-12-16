package com.zed.admin.common.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zed.admin.common.base.entity.BaseEntity;
import com.zed.admin.common.constant.CommonConstant;
import com.zed.admin.common.utils.Tools;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;

/**
 * QueryParam
 *
 * @Author: zed
 * @Date: 2019/12/16 9:53
 */
@Data
public class PageParam implements Serializable {

    private String sortField;
    private String sortOrder;

    private String createTimeFrom;
    private String createTimeTo;
    private String updateTimeFrom;
    private String updateTimeTo;


    /**
     * Page 分页时间比较
     *
     * @param <T>
     * @return
     */
    public <T extends BaseEntity> QueryWrapper<T> buildTimePage() {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge(StringUtils.isNotBlank(createTimeFrom), BaseEntity.CREATE_TIME, createTimeFrom);
        queryWrapper.le(StringUtils.isNotBlank(createTimeFrom), BaseEntity.CREATE_TIME, createTimeTo);
        queryWrapper.ge(StringUtils.isNotBlank(updateTimeFrom), BaseEntity.UPDATE_TIME, updateTimeFrom);
        queryWrapper.le(StringUtils.isNotBlank(updateTimeTo), BaseEntity.UPDATE_TIME, updateTimeTo);
        queryWrapper.eq(BaseEntity.IS_DELETED, false);
        return queryWrapper;
    }

    /**
     * Page 分页排序
     *
     * @param <T>
     * @return
     */
    public <T> Page<T> buildPage() {
        Page<T> page = new Page<>();
        sortField = Tools.humpToUnderline(sortField);
        boolean desc = StringUtils.equals(sortOrder, CommonConstant.ORDER_DESC);
        boolean asc = StringUtils.equals(sortOrder, CommonConstant.ORDER_ASC);
        if (StringUtils.isNotBlank(sortField)) {
            String[] fields = sortField.split(StringPool.COMMA);
            if (desc) {
                page.addOrder(Arrays.stream(fields).map(OrderItem::desc).toArray(OrderItem[]::new));
            } else if (asc) {
                page.addOrder(Arrays.stream(fields).map(OrderItem::asc).toArray(OrderItem[]::new));
            }
        }
        return page;
    }

}
