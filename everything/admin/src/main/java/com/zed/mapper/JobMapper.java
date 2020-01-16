package com.zed.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zed.domain.Job;
import com.zed.service.dto.JobDTO;

/**
* JobMapper
* @author zed
* @date 2020-01-16
*/
@Mapper
@Repository
public interface JobMapper extends BaseMapper<Job> {

}