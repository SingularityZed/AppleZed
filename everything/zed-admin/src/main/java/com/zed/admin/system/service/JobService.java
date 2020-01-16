package com.zed.admin.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zed.admin.system.entity.Job;
import com.zed.admin.system.pojo.dto.JobDTO;
import com.zed.admin.system.pojo.dto.JobVerifyDTO;
import com.zed.admin.system.pojo.vo.JobVO;
import com.zed.common.base.PageParam;

/**
 * JobService
 *
 * @author zed
 * @date 2020-01-16
 */
public interface JobService extends IService<Job> {

    /**
     * 分页查询
     *
     * @param pageParam 条件参数
     * @param dto       分页参数
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