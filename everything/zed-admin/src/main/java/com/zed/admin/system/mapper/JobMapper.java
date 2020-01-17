package com.zed.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zed.admin.system.entity.Job;
import com.zed.admin.system.pojo.dto.JobDTO;
import com.zed.admin.system.pojo.dto.JobVerifyDTO;
import com.zed.admin.system.pojo.vo.JobVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JobMapper
 *
 * @author zed
 * @date 2020-01-16
 */
@Mapper
@Repository
public interface JobMapper extends BaseMapper<Job> {

    /**
     * 分页
     *
     * @param page
     * @param dto
     * @return
     */
    List<JobVO> searchPage(Page page, @Param("dto") JobDTO dto);

    /**
     * 校验
     *
     * @param dto
     * @return
     */
    JobVerifyDTO verifyRepeat(JobDTO dto);
}