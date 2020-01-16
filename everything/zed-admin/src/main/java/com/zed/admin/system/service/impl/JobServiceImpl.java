package com.zed.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zed.admin.system.entity.Job;
import com.zed.admin.system.mapper.JobMapper;
import com.zed.admin.system.pojo.dto.JobDTO;
import com.zed.admin.system.pojo.dto.JobVerifyDTO;
import com.zed.admin.system.pojo.vo.JobVO;
import com.zed.admin.system.service.JobService;
import com.zed.common.base.PageParam;
import com.zed.common.utils.AutoMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * JobServiceImpl
 *
 * @author zed
 * @date 2020-01-16
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "job")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {


    /**
     * 分页查询
     *
     * @param pageParam
     * @param dto
     * @return
     */
    @Override
    @Cacheable
    public Page getPageList(PageParam pageParam, JobDTO dto) {
        Page<Job> page = pageParam.buildPage();
        Page<Job> jobPage = this.page(page, new LambdaQueryWrapper<Job>().eq(Job::getIsDeleted, false));
        return AutoMapperUtil.mappingPage(jobPage, JobVO.class);
    }

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(key = "#p0")
    public JobVO getJobById(Long id) {
        Job job = this.getOne(new LambdaQueryWrapper<Job>().eq(Job::getId, id).eq(Job::getIsDeleted, false));
        return AutoMapperUtil.toPOJO(job, JobVO.class);
    }

    /**
     * 新增
     *
     * @param dto
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void addJob(JobDTO dto) {
        Job job = AutoMapperUtil.toPOJO(dto, Job.class);
        this.saveOrUpdate(job);
    }

    /**
     * 更新
     *
     * @param dto
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void updateJob(JobDTO dto) {
        this.getJobById(dto.getId());
        Job job = AutoMapperUtil.toPOJO(dto, Job.class);
        this.saveOrUpdate(job);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void deleteJob(Long id) {
        this.getJobById(id);
        this.update(new LambdaUpdateWrapper<Job>().eq(Job::getId, id).set(Job::getIsDeleted, true));
    }

    /**
     * 校验
     *
     * @param dto
     * @return
     */
    @Override
    public JobVerifyDTO verifyRepeat(JobDTO dto) {
        return baseMapper.verifyRepeat(dto);
    }

}