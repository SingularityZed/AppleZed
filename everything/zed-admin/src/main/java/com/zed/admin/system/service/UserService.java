package com.zed.admin.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zed.admin.system.entity.User;
import com.zed.admin.system.pojo.dto.UserDTO;
import com.zed.admin.system.pojo.dto.UserVerifyDTO;
import com.zed.admin.system.pojo.vo.UserVO;
import com.zed.common.base.PageParam;

/**
 * UserService
 *
 * @author zed
 * @date 2019/12/12 19:12
 */
public interface UserService extends IService<User> {

    /**
     * 分页查询
     *
     * @param pageParam
     * @param dto
     * @return
     */
    Page getPageList(PageParam pageParam, UserDTO dto);

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    UserVO getUserById(Long id);

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
     * @param id
     */
    void deleteUser(Long id);


    /**
     * 校验
     *
     * @param dto
     * @return
     */
    UserVerifyDTO verifyRepeat(UserDTO dto);
}
