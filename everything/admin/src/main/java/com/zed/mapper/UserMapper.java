package com.zed.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zed.domain.User;
import com.zed.service.dto.UserDTO;

/**
* UserMapper
* @author zed
* @date 2020-01-16
*/
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}