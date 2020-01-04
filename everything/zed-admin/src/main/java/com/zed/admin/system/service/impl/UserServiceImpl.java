package com.zed.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zed.admin.system.entity.User;
import com.zed.admin.system.mapper.UserMapper;
import com.zed.admin.system.pojo.dto.UserDTO;
import com.zed.admin.system.pojo.dto.UserVerifyDTO;
import com.zed.admin.system.pojo.vo.UserVO;
import com.zed.admin.system.service.UserService;
import com.zed.common.base.PageParam;
import com.zed.common.utils.AutoMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 *
 * @author zed
 * @date 2019/12/15 14:02
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    /**
     * 分页查询
     *
     * @param pageParam
     * @param dto
     * @return
     */
    @Override
    public Page getPageList(PageParam pageParam, UserDTO dto) {
        Page<User> page = pageParam.buildPage();
        Page<User> userPage = this.page(page,
                new LambdaQueryWrapper<User>()
                        .eq(User::getIsDeleted, false)
                        .like(StringUtils.isNotBlank(dto.getUsername()), User::getUsername, dto.getUsername())
                        .like(StringUtils.isNotBlank(dto.getUsername()), User::getTelephone, dto.getTelephone())
                        .select(User::getEnabled, User::getEmail, User::getGender)
                        .orderByDesc(User::getUpdateTime)
                        .orderByDesc(User::getId));
        return AutoMapperUtil.mappingPage(userPage, UserVO.class);
    }

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    @Override
    public UserVO getUserById(Long id) {
        User user = this.getOne(new LambdaQueryWrapper<User>().eq(User::getId, id).eq(User::getIsDeleted, false));
        return AutoMapperUtil.toPOJO(user, UserVO.class);
    }

    /**
     * 新增
     *
     * @param dto
     */
    @Override
    public void addUser(UserDTO dto) {
        User user = AutoMapperUtil.toPOJO(dto, User.class);
        this.saveOrUpdate(user);
    }

    /**
     * 更新
     *
     * @param dto
     */
    @Override
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
    public void deleteUser(Long id) {
        this.getUserById(id);
        this.update(new LambdaUpdateWrapper<User>().eq(User::getId, id).set(User::getIsDeleted, true));
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
