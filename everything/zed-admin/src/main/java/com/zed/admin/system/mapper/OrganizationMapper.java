package com.zed.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zed.admin.system.entity.Organization;
import com.zed.admin.system.pojo.dto.OrganizationDTO;
import com.zed.admin.system.pojo.dto.OrganizationVerifyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * OrganizationMapper
 *
 * @author zed
 * @date 2020-01-16
 */
@Mapper
@Repository
public interface OrganizationMapper extends BaseMapper<Organization> {

    OrganizationVerifyDTO verifyRepeat(OrganizationDTO dto);
}