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

    private GeneratorMapper generatorMapper;

    @Autowired
    private void setGeneratorMapper(GeneratorMapper generatorMapper) {
        this.generatorMapper = generatorMapper;
    }


//
//    @PersistenceContext
//    private EntityManager em;

    @Override
    public Page getTables(PageParam pageParam, String database, String tableName) {
        List<TableInfo> tableInfoList = generatorMapper.getTables(pageParam, database, tableName);

        return null;
    }

    @Override
    public Page getColumns(String database, String tableName) {

        List<ColumnInfo> columnInfos = generatorMapper.getColumns(database, tableName);

        return null;
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
