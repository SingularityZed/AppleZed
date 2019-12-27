package com.zed.generator.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zed.common.base.PageParam;
import com.zed.generator.config.GenConfig;
import com.zed.generator.info.ColumnInfo;
import com.zed.generator.info.TableInfo;
import com.zed.generator.mapper.GeneratorMapper;
import com.zed.generator.service.GeneratorService;
import me.zhengjie.domain.GenConfig;
import me.zhengjie.domain.vo.ColumnInfo;
import me.zhengjie.domain.vo.TableInfo;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.service.GeneratorService;
import me.zhengjie.utils.GenUtil;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zheng Jie
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {

    private GeneratorMapper generatorMapper;
    @Autowired
    private void setGeneratorMapper(GeneratorMapper generatorMapper){
        this.generatorMapper=generatorMapper;
    }


//
//    @PersistenceContext
//    private EntityManager em;

    @Override
    public Page getTables(PageParam pageParam, String database,String tableName) {
        List<TableInfo> tableInfoList=generatorMapper.getTables(pageParam,database,tableName);

        return null;
    }

    @Override
    public Page getColumns(String database,String tableName) {

        List<ColumnInfo> columnInfos = generatorMapper.getColumns(database,tableName);

        return null;
    }



    /**
     * @param columnInfos
     * @param genConfig
     * @param tableName
     */
    @Override
    public void generator(List<ColumnInfo> columnInfos, GenConfig genConfig, String tableName) {
        if(genConfig.getId() == null){
            throw new BadRequestException("请先配置生成器");
        }
        try {
            GenUtil.generatorCode(columnInfos,genConfig,tableName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
