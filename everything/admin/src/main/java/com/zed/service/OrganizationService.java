package com.zed.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zed.common.base.PageParam;
import com.zed.domain.Organization;
import com.zed.service.dto.OrganizationDTO;
import com.zed.service.dto.OrganizationQueryCriteria;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* OrganizationService
*
* @author zed
* @date 2020-01-16
*/
public interface OrganizationService extends IService<Organization>{

    /**
    * 分页查询
    *
    * @param pageParam 条件参数
    * @param dto 分页参数
    * @return
    */
    Page getPageList(PageParam pageParam, OrganizationDTO dto);

    /**
     * 获取详情
     *
     * @param id ID
     * @return OrganizationVO
     */
    OrganizationVO getOrganizationById(Long id);

    /**
    * 新增
    *
    * @param dto
    */
    void addOrganization(OrganizationDTO dto);

    /**
    * 更新
    *
    * @param dto
    */
    void updateOrganization(OrganizationDTO dto);

    /**
    * 删除
    *
    * @param id
    */
    void deleteOrganization(Long id);

    /**
    * 校验
    *
    * @param dto
    * @return
    */
    OrganizationVerifyDTO verifyRepeat(OrganizationDTO dto);

}