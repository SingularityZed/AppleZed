package com.zed.generator.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zed.generator.info.ColumnInfo;
import com.zed.generator.info.TableInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zed
 */
@Mapper
@Repository
public interface GeneratorMapper {

    /**
     * 查询数据库元数据
     *
     * @param page      分页参数
     * @param database
     * @param tableName 表名
     * @return /
     */
    List<TableInfo> getTables(@Param("page") Page page, @Param("database") String database, @Param("tableName") String tableName);

    /**
     * 得到数据表的元数据
     *
     * @param database
     * @param tableName
     * @return
     */
    List<ColumnInfo> getColumns(@Param("database") String database, @Param("tableName") String tableName);

}
