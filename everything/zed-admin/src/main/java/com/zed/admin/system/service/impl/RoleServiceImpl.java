package com.zed.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zed.admin.system.entity.Role;
import com.zed.admin.system.mapper.RoleMapper;
import com.zed.admin.system.pojo.dto.RoleDTO;
import com.zed.admin.system.pojo.dto.RoleVerifyDTO;
import com.zed.admin.system.pojo.vo.RoleVO;
import com.zed.admin.system.service.RoleService;
import com.zed.common.base.PageParam;
import com.zed.common.utils.AutoMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * RoleServiceImpl
 *
 * @author zed
 * @date 2020-01-16
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "role")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


    /**
     * 分页查询
     *
     * @param pageParam
     * @param dto
     * @return
     */
    @Override
    @Cacheable
    public Page<RoleVO> getPageList(PageParam pageParam, RoleDTO dto) {
        Page<Role> page = pageParam.buildPage();
        Page<Role> rolePage = this.page(page, new LambdaQueryWrapper<Role>().eq(Role::getIsDeleted, false));
        return AutoMapperUtil.mappingPage(rolePage, RoleVO.class);
    }

    /**
     * 获取详情
     *
     * @param roleId
     * @return
     */
    @Override
    @Cacheable(key = "#p0")
    public RoleVO getRoleById(Long roleId) {
        Role role = this.getOne(new LambdaQueryWrapper<Role>().eq(Role::getId, roleId).eq(Role::getIsDeleted, false));
        return AutoMapperUtil.toPOJO(role, RoleVO.class);
    }

    /**
     * 新增
     *
     * @param dto
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void addRole(RoleDTO dto) {
        Role role = AutoMapperUtil.toPOJO(dto, Role.class);
        this.saveOrUpdate(role);
    }

    /**
     * 更新
     *
     * @param dto
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(RoleDTO dto) {
        this.getRoleById(dto.getId());
        Role role = AutoMapperUtil.toPOJO(dto, Role.class);
        this.saveOrUpdate(role);
    }

    /**
     * 删除
     *
     * @param roleId
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Long roleId) {
        this.getRoleById(roleId);
        this.update(new LambdaUpdateWrapper<Role>().eq(Role::getId, roleId).set(Role::getIsDeleted, true));
    }

    /**
     * 校验
     *
     * @param dto
     * @return
     */
    @Override
    public RoleVerifyDTO verifyRepeat(RoleDTO dto) {
        return baseMapper.verifyRepeat(dto);
    }

}