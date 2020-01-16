package com.zed.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zed.common.base.PageParam;
import com.zed.domain.Role;
import com.zed.service.dto.RoleDTO;
import com.zed.service.dto.RoleQueryCriteria;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* RoleService
*
* @author zed
* @date 2020-01-16
*/
public interface RoleService extends IService<Role>{

    /**
    * 分页查询
    *
    * @param pageParam 条件参数
    * @param dto 分页参数
    * @return
    */
    Page getPageList(PageParam pageParam, RoleDTO dto);

    /**
     * 获取详情
     *
     * @param roleId ID
     * @return RoleVO
     */
    RoleVO getRoleById(Long roleId);

    /**
    * 新增
    *
    * @param dto
    */
    void addRole(RoleDTO dto);

    /**
    * 更新
    *
    * @param dto
    */
    void updateRole(RoleDTO dto);

    /**
    * 删除
    *
    * @param roleId
    */
    void deleteRole(Long roleId);

    /**
    * 校验
    *
    * @param dto
    * @return
    */
    RoleVerifyDTO verifyRepeat(RoleDTO dto);

}