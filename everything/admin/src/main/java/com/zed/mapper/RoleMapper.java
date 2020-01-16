package com.zed.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zed.domain.Role;
import com.zed.service.dto.RoleDTO;

/**
* RoleMapper
* @author zed
* @date 2020-01-16
*/
@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {

}