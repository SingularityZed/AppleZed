package com.zed.admin.system.service;

import com.zed.admin.system.entity.User;

/**
 * UserService
 *
 * @Author: wang_ycong(Tel : 16602526966)
 * @Date: 2019/12/15 14:02
 */
public interface UserService {

    User searchByName(String username);
}
