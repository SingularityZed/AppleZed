package ${package}.service.impl;

import ${package}.domain.${className};
<#if columns??>
    <#list columns as column>
        <#if column.columnKey = 'UNI'>
            <#if column_index = 1>
                import com.zed.common.exception.DaoException;
            </#if>
        </#if>
    </#list>
</#if>
import ${package}.repository.${className}Repository;
import ${package}.service.${className}Service;
import ${package}.service.dto.${className}DTO;
import ${package}.service.dto.${className}QueryCriteria;
import ${package}.service.mapper.${className}Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
<#if !auto && pkColumnType = 'Long'>
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
</#if>
<#if !auto && pkColumnType = 'String'>
import cn.hutool.core.util.IdUtil;
</#if>
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* ${className}ServiceImpl
*
* @author ${author}
* @date ${date}
*/
@Slf4j
@Service
@CacheConfig(cacheNames = "${changeClassName}")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ${className}ServiceImpl extends ServiceImpl<${className}Mapper, ${className}> implements ${className}Service {



    /**
    * 分页查询
    *
    * @param pageParam
    * @param dto
    * @return
    */
    @Override
    @Cacheable
    public Page searchPage(PageParam pageParam, ${className}DTO dto){
        Page<${className}> page =pageParam.buildPage();
        Page<${className}> ${changeClassName}Page = this.page(page, new LambdaQueryWrapper<${className}>() .eq(${className}::getIsDeleted, false));
        return AutoMapperUtil.mappingPage(${changeClassName}Page, ${className}VO.class);
    }

        /**
        * 获取详情
        *
        * @param id
        * @return
        */
    @Override
    @Cacheable(key = "#p0")
    public ${className}VO get${className}ById(${pkColumnType} ${pkChangeColName}) {
        ${className} ${changeClassName} = this.getOne(new LambdaQueryWrapper< ${className}>().eq( ${className}::getId, id).eq(${className}::getIsDeleted, false));
        return AutoMapperUtil.toPOJO(${changeClassName}, ${className}VO.class);
    }

        /**
        * 新增
        *
        * @param dto
        */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void add${className}(${className}DTO dto) {
        ${className} ${changeClassName} = AutoMapperUtil.toPOJO(dto,  ${className}.class);
        this.saveOrUpdate(${changeClassName});
    }

        /**
        * 更新
        *
        * @param dto
        */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update${className}(${className}DTO dto) {
        this.get${className}ById(dto.getId());
        ${className} ${changeClassName} = AutoMapperUtil.toPOJO(dto, ${className}.class);
        this.saveOrUpdate(${changeClassName});
    }

        /**
        * 删除
        *
        * @param id
        */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete${className}(${pkColumnType} ${pkChangeColName}) {
        this.get${className}ById(id);
        this.update(new LambdaUpdateWrapper< ${className}>().eq( ${className}::getId, id).set( ${className}::getIsDeleted, true));
    }

        /**
        * 校验
        *
        * @param dto
        * @return
        */
        @Override
        public ${className}VerifyDTO verifyRepeat(${className}DTO dto) {
        return baseMapper.verifyRepeat(dto);
        }

}