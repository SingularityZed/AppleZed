package com.zed.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zed.domain.Organization;
import com.zed.service.dto.OrganizationDTO;

/**
* OrganizationMapper
* @author zed
* @date 2020-01-16
*/
@Mapper
@Repository
public interface OrganizationMapper extends BaseMapper<Organization> {

}