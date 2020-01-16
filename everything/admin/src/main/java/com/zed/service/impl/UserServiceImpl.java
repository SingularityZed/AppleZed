package com.zed.service.impl;

import com.zed.domain.User;
import com.zed.repository.UserRepository;
import com.zed.service.UserService;
import com.zed.service.dto.UserDTO;
import com.zed.service.dto.UserQueryCriteria;
import com.zed.service.mapper.UserMapper;
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
* UserServiceImpl
*
* @author zed
* @date 2020-01-16
*/
@Slf4j
@Service
@CacheConfig(cacheNames = "user")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {



    /**
    * 分页查询
    *
    * @param pageParam
    * @param dto
    * @return
    */
    @Override
    @Cacheable
    public Page getPageList(PageParam pageParam, UserDTO dto){
        Page<User> page =pageParam.buildPage();
        Page<User> userPage = this.page(page, new LambdaQueryWrapper<User>() .eq(User::getIsDeleted, false));
        return AutoMapperUtil.mappingPage(userPage, UserVO.class);
    }

        /**
        * 获取详情
        *
        * @param id
        * @return
        */
    @Override
    @Cacheable(key = "#p0")
    public UserVO getUserById(Long roleId) {
        User user = this.getOne(new LambdaQueryWrapper< User>().eq( User::getId, id).eq(User::getIsDeleted, false));
        return AutoMapperUtil.toPOJO(user, UserVO.class);
    }

        /**
        * 新增
        *
        * @param dto
        */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserDTO dto) {
        User user = AutoMapperUtil.toPOJO(dto,  User.class);
        this.saveOrUpdate(user);
    }

        /**
        * 更新
        *
        * @param dto
        */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserDTO dto) {
        this.getUserById(dto.getId());
        User user = AutoMapperUtil.toPOJO(dto, User.class);
        this.saveOrUpdate(user);
    }

        /**
        * 删除
        *
        * @param id
        */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Long roleId) {
        this.getUserById(id);
        this.update(new LambdaUpdateWrapper< User>().eq( User::getId, id).set( User::getIsDeleted, true));
    }

        /**
        * 校验
        *
        * @param dto
        * @return
        */
        @Override
        public UserVerifyDTO verifyRepeat(UserDTO dto) {
        return baseMapper.verifyRepeat(dto);
        }

}