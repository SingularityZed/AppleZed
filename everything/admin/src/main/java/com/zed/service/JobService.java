package com.zed.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zed.common.base.PageParam;
import com.zed.domain.Job;
import com.zed.service.dto.JobDTO;
import com.zed.service.dto.JobQueryCriteria;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* JobService
*
* @author zed
* @date 2020-01-16
*/
public interface JobService extends IService<Job>{

    /**
    * 分页查询
    *
    * @param pageParam 条件参数
    * @param dto 分页参数
    * @return
    */
    Page getPageList(PageParam pageParam, JobDTO dto);

    /**
     * 获取详情
     *
     * @param id ID
     * @return JobVO
     */
    JobVO getJobById(Long id);

    /**
    * 新增
    *
    * @param dto
    */
    void addJob(JobDTO dto);

    /**
    * 更新
    *
    * @param dto
    */
    void updateJob(JobDTO dto);

    /**
    * 删除
    *
    * @param id
    */
    void deleteJob(Long id);

    /**
    * 校验
    *
    * @param dto
    * @return
    */
    JobVerifyDTO verifyRepeat(JobDTO dto);

}