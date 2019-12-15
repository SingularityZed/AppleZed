package com.zed.admin.system.service.impl;

import com.zed.admin.system.entity.User;
import com.zed.admin.system.mapper.UserMapper;
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
    public User searchByName(String username) {
        return userMapper.selectByName(username);
    }
}
