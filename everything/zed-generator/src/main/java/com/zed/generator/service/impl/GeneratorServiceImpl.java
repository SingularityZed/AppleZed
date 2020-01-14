package com.zed.generator.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zed.common.base.PageParam;
import com.zed.common.exception.ServiceException;
import com.zed.generator.config.GenConfig;
import com.zed.generator.info.ColumnInfo;
import com.zed.generator.info.TableInfo;
import com.zed.generator.mapper.GeneratorMapper;
import com.zed.generator.service.GeneratorService;
import com.zed.generator.utils.GenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author zed
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Autowired
    private GeneratorMapper generatorMapper;


    /**
     * 查询数据库元数据
     *
     * @param pageParam 分页参数
     * @param database  数据库
     * @param tableName 表名
     * @return
     */
    @Override
    public Page getTables(PageParam pageParam, String database, String tableName) {
        Page<TableInfo> page = pageParam.buildPage();
        List<TableInfo> tableInfoList = generatorMapper.getTables(page, database, tableName);
        page.setRecords(tableInfoList);
        return page;
    }


    /**
     * 得到数据表的元数据
     *
     * @param pageParam 分页参数
     * @param database  数据库
     * @param tableName 表名
     * @return
     */
    @Override
    public Page getColumns(PageParam pageParam, String database, String tableName) {
        Page<ColumnInfo> page = pageParam.buildPage();
        List<ColumnInfo> columnInfoList = generatorMapper.getColumns(database, tableName);
        page.setRecords(columnInfoList);
        return page;
    }


    /**
     * 生成代码
     *
     * @param columnInfos
     * @param genConfig
     * @param tableName
     */
    @Override
    public void generator(GenConfig genConfig, String tableName, List<ColumnInfo> columnInfos) {
        if (genConfig == null) {
            throw new ServiceException("请先配置生成器");
        }
        try {
            GenUtils.generatorCode(genConfig, tableName, columnInfos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
