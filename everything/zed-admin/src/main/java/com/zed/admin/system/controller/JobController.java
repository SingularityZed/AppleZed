package com.zed.admin.system.controller;

import com.zed.admin.system.pojo.ao.JobAddAO;
import com.zed.admin.system.pojo.ao.JobQueryAO;
import com.zed.admin.system.pojo.ao.JobUpdateAO;
import com.zed.admin.system.pojo.dto.JobDTO;
import com.zed.admin.system.pojo.vo.JobVO;
import com.zed.admin.system.service.JobService;
import com.zed.admin.system.verify.JobVerify;
import com.zed.common.base.PageParam;
import com.zed.common.exception.handler.ResponseResult;
import com.zed.common.utils.AutoMapperUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * JobController
 *
 * @author zed
 * @date 2020-01-16
 */
@Api(tags = "岗位管理")
@RestController
@RequestMapping("/api/zed-admin/job")
public class JobController {

    @Autowired
    private JobService jobService;
    @Autowired
    private JobVerify jobVerify;

    @ApiOperation("分页查询岗位")
    @GetMapping("/pageList")
    public ResponseResult searchPage(PageParam pageParam, JobQueryAO queryAO) {
        JobDTO dto = AutoMapperUtil.toPOJO(queryAO, JobDTO.class);
        return ResponseResult.succeed(jobService.searchPage(pageParam, dto));
    }

    @ApiOperation("查询岗位详情")
    @GetMapping("/{id}")
    public ResponseResult getJob(@PathVariable Long id) {
        JobVO vo = jobService.getJobById(id);
        return ResponseResult.succeed(vo);
    }

    @ApiOperation("新增Job")
    @PostMapping("/add")
    public ResponseResult add(@Validated @RequestBody JobAddAO addAO) {
        // 转换
        JobDTO dto = AutoMapperUtil.toPOJO(addAO, JobDTO.class);
        jobVerify.verifyRepeat(dto);
        jobService.addJob(dto);
        return ResponseResult.succeed();
    }

    @ApiOperation("修改Job")
    @PutMapping("/update")
    public ResponseResult update(@Validated @RequestBody JobUpdateAO updateAO) {
        // 转换
        JobDTO dto = AutoMapperUtil.toPOJO(updateAO, JobDTO.class);
//        jobVerify.verifyRepeat(dto);
        jobService.updateJob(dto);
        return ResponseResult.succeed();
    }

    @ApiOperation("删除Job")
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseResult.succeed();
    }

}