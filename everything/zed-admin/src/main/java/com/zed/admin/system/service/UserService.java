package com.zed.admin.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zed.admin.common.base.PageParam;
import com.zed.admin.system.entity.User;
import com.zed.admin.system.pojo.ao.UserAddAO;
import com.zed.admin.system.pojo.ao.UserQueryAO;
import com.zed.admin.system.pojo.ao.UserUpdateAO;
import com.zed.admin.system.pojo.dto.UserDTO;
import com.zed.admin.system.pojo.dto.UserVerifyDTO;
import com.zed.admin.system.pojo.vo.UserVO;

/**
 * UserService
 *
 * @author zed
 * @date 2019/12/12 19:12
 */
public interface UserService {

    Page<UserVO> getPageList(PageParam pageParam, UserQueryAO queryAO);

    void getById(Long id);

    void add(UserAddAO addAO);

    void update(UserUpdateAO updateAO);

    void delete(Long id);


    UserVerifyDTO verifyRepeat(UserDTO dto);
}
