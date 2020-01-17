package ${package}.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zed.common.base.PageParam;
import ${package}.domain.${className};
import ${package}.service.dto.${className}DTO;
import ${package}.service.dto.${className}QueryCriteria;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* ${className}Service
*
* @author ${author}
* @date ${date}
*/
public interface ${className}Service extends IService<${className}>{

    /**
    * 分页查询
    *
    * @param pageParam 条件参数
    * @param dto 分页参数
    * @return
    */
    Page searchPage(PageParam pageParam, ${className}DTO dto);

    /**
     * 获取详情
     *
     * @param ${pkChangeColName} ID
     * @return ${className}VO
     */
    ${className}VO get${className}ById(${pkColumnType} ${pkChangeColName});

    /**
    * 新增
    *
    * @param dto
    */
    void add${className}(${className}DTO dto);

    /**
    * 更新
    *
    * @param dto
    */
    void update${className}(${className}DTO dto);

    /**
    * 删除
    *
    * @param ${pkChangeColName}
    */
    void delete${className}(${pkColumnType} ${pkChangeColName});

    /**
    * 校验
    *
    * @param dto
    * @return
    */
    ${className}VerifyDTO verifyRepeat(${className}DTO dto);

}