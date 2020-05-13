package com.zed.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zed.admin.system.entity.Role;
import com.zed.admin.system.pojo.dto.RoleDTO;
import com.zed.admin.system.pojo.dto.RoleVerifyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * RoleMapper
 *
 * @author zed
 * @date 2020-01-16
 */
@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    RoleVerifyDTO verifyRepeat(RoleDTO dto);
}