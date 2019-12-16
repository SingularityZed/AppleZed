package com.zed.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zed.admin.system.entity.User;
import com.zed.admin.system.pojo.dto.UserDTO;
import com.zed.admin.system.pojo.dto.UserVerifyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * UserMapper
 *
 * @author zed
 * @date 2019/12/12 19:12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


    User selectByName(@Param("username") String username);

    UserVerifyDTO verifyRepeat(@Param("dto") UserDTO dto);
}
