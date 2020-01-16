package com.zed.service.impl;

import com.zed.domain.Role;
import com.zed.repository.RoleRepository;
import com.zed.service.RoleService;
import com.zed.service.dto.RoleDTO;
import com.zed.service.dto.RoleQueryCriteria;
import com.zed.service.mapper.RoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
    public Page getPageList(PageParam pageParam, RoleDTO dto){
        Page<Role> page =pageParam.buildPage();
        Page<Role> rolePage = this.page(page, new LambdaQueryWrapper<Role>() .eq(Role::getIsDeleted, false));
        return AutoMapperUtil.mappingPage(rolePage, RoleVO.class);
    }

        /**
        * 获取详情
        *
        * @param id
        * @return
        */
    @Override
    @Cacheable(key = "#p0")
    public RoleVO getRoleById(Long roleId) {
        Role role = this.getOne(new LambdaQueryWrapper< Role>().eq( Role::getId, id).eq(Role::getIsDeleted, false));
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
        Role role = AutoMapperUtil.toPOJO(dto,  Role.class);
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
        * @param id
        */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Long roleId) {
        this.getRoleById(id);
        this.update(new LambdaUpdateWrapper< Role>().eq( Role::getId, id).set( Role::getIsDeleted, true));
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