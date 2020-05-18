package com.zed.admin.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zed.admin.system.entity.Role;
import com.zed.admin.system.pojo.dto.RoleDTO;
import com.zed.admin.system.pojo.dto.RoleVerifyDTO;
import com.zed.admin.system.pojo.vo.RoleVO;
import com.zed.common.base.PageParam;

/**
 * RoleService
 *
 * @author zed
 * @date 2020-01-16
 */
public interface RoleService extends IService<Role> {

    /**
     * 分页查询
     *
     * @param pageParam 条件参数
     * @param dto       分页参数
     * @return
     */
    Page<RoleVO> getPageList(PageParam pageParam, RoleDTO dto);

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