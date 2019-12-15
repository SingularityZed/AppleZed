package com.zed.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zed.admin.system.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * UserMapper
 *
 * @Author: wang_ycong(Tel : 16602526966)
 * @Date: 2019/12/13 20:13
 */


@Mapper
public interface UserMapper extends BaseMapper<User> {


  User selectByName (@Param("username") String username);


}
