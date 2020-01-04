package com.zed.generator.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zed.common.base.PageParam;
import com.zed.generator.config.GenConfig;
import com.zed.generator.info.ColumnInfo;

import java.util.List;

/**
 * @author zed
 * @date 2019-01-02
 */
public interface GeneratorService {

    /**
     * 查询数据库元数据
     *
     * @param pageParam 分页参数
     * @param database  数据库
     * @param tableName 表名
     * @return /
     */
    Page getTables(PageParam pageParam, String database, String tableName);

    /**
     * 得到数据表的元数据
     *
     * @param database  数据库
     * @param tableName 表名
     * @return /
     */
    Page getColumns(String database, String tableName);

    /**
     * 生成代码
     *
     * @param columnInfos 表字段数据
     * @param genConfig   代码生成配置
     * @param tableName   表名
     */
    void generator( GenConfig genConfig,  String tableName,List<ColumnInfo> columnInfos);
}
