package com.zed.generator.mapper;

import com.zed.common.base.PageParam;
import com.zed.generator.config.GenConfig;
import com.zed.generator.info.ColumnInfo;
import com.zed.generator.info.TableInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Zheng Jie
 */
@Mapper
public interface GeneratorMapper {

    /**
     * 查询数据库元数据
     *
     * @param pageParam 分页参数
     * @param database
     * @param tableName 表名
     * @return /
     */
    List<TableInfo> getTables(@Param("pageParam") PageParam pageParam, @Param("database") String database, @Param("tableName") String tableName);

    /**
     * 得到数据表的元数据
     *
     * @param database
     * @param tableName
     * @return
     */
    List<ColumnInfo> getColumns(@Param("database") String database, @Param("tableName") String tableName);

}
