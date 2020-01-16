package com.zed.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zed.admin.system.entity.Job;
import com.zed.admin.system.pojo.dto.JobDTO;
import com.zed.admin.system.pojo.dto.JobVerifyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* JobMapper
* @author zed
* @date 2020-01-16
*/
@Mapper
@Repository
public interface JobMapper extends BaseMapper<Job> {

    JobVerifyDTO verifyRepeat(JobDTO dto);
}