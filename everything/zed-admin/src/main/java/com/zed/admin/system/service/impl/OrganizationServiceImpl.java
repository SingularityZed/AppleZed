package com.zed.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zed.admin.system.entity.Organization;
import com.zed.admin.system.mapper.OrganizationMapper;
import com.zed.admin.system.pojo.dto.OrganizationDTO;
import com.zed.admin.system.pojo.dto.OrganizationVerifyDTO;
import com.zed.admin.system.pojo.vo.OrganizationVO;
import com.zed.admin.system.service.OrganizationService;
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
 * OrganizationServiceImpl
 *
 * @author zed
 * @date 2020-01-16
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "organization")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {


    /**
     * 分页查询
     *
     * @param pageParam
     * @param dto
     * @return
     */
    @Override
    @Cacheable
    public Page getPageList(PageParam pageParam, OrganizationDTO dto) {
        Page<Organization> page = pageParam.buildPage();
        Page<Organization> organizationPage = this.page(page, new LambdaQueryWrapper<Organization>().eq(Organization::getIsDeleted, false));
        return AutoMapperUtil.mappingPage(organizationPage, OrganizationVO.class);
    }

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(key = "#p0")
    public OrganizationVO getOrganizationById(Long id) {
        Organization organization = this.getOne(new LambdaQueryWrapper<Organization>().eq(Organization::getId, id).eq(Organization::getIsDeleted, false));
        return AutoMapperUtil.toPOJO(organization, OrganizationVO.class);
    }

    /**
     * 新增
     *
     * @param dto
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void addOrganization(OrganizationDTO dto) {
        Organization organization = AutoMapperUtil.toPOJO(dto, Organization.class);
        this.saveOrUpdate(organization);
    }

    /**
     * 更新
     *
     * @param dto
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void updateOrganization(OrganizationDTO dto) {
        this.getOrganizationById(dto.getId());
        Organization organization = AutoMapperUtil.toPOJO(dto, Organization.class);
        this.saveOrUpdate(organization);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrganization(Long id) {
        this.getOrganizationById(id);
        this.update(new LambdaUpdateWrapper<Organization>().eq(Organization::getId, id).set(Organization::getIsDeleted, true));
    }

    /**
     * 校验
     *
     * @param dto
     * @return
     */
    @Override
    public OrganizationVerifyDTO verifyRepeat(OrganizationDTO dto) {
        return baseMapper.verifyRepeat(dto);
    }

}