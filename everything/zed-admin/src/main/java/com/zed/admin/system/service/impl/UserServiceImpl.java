package com.zed.admin.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zed.admin.common.base.PageParam;
import com.zed.admin.system.entity.User;
import com.zed.admin.system.mapper.UserMapper;
import com.zed.admin.system.pojo.ao.UserAddAO;
import com.zed.admin.system.pojo.ao.UserQueryAO;
import com.zed.admin.system.pojo.ao.UserUpdateAO;
import com.zed.admin.system.pojo.dto.UserDTO;
import com.zed.admin.system.pojo.dto.UserVerifyDTO;
import com.zed.admin.system.pojo.vo.UserVO;
import com.zed.admin.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService
 *
 * @Author: wang_ycong(Tel : 16602526966)
 * @Date: 2019/12/15 14:02
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public Page<UserVO> getPageList(PageParam pageParam, UserQueryAO queryAO) {
        return null;
    }

    @Override
    public void getById(Long id) {

    }

    @Override
    public void add(UserAddAO addAO) {

    }

    @Override
    public void update(UserUpdateAO updateAO) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserVerifyDTO verifyRepeat(UserDTO dto) {
        return  userMapper.verifyRepeat(dto);
    }
}
