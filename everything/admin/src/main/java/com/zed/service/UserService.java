package com.zed.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zed.common.base.PageParam;
import com.zed.domain.User;
import com.zed.service.dto.UserDTO;

/**
 * UserService
 *
 * @author zed
 * @date 2020-01-16
 */
public interface UserService extends IService<User> {

    /**
     * 分页查询
     *
     * @param pageParam 条件参数
     * @param dto       分页参数
     * @return
     */
    Page getPageList(PageParam pageParam, UserDTO dto);

    /**
     * 获取详情
     *
     * @param roleId ID
     * @return UserVO
     */
    UserVO getUserById(Long roleId);

    /**
     * 新增
     *
     * @param dto
     */
    void addUser(UserDTO dto);

    /**
     * 更新
     *
     * @param dto
     */
    void updateUser(UserDTO dto);

    /**
     * 删除
     *
     * @param roleId
     */
    void deleteUser(Long roleId);

    /**
     * 校验
     *
     * @param dto
     * @return
     */
    UserVerifyDTO verifyRepeat(UserDTO dto);

}