package com.zed.admin.system.controller;

import com.zed.admin.system.pojo.ao.JobAddAO;
import com.zed.admin.system.pojo.ao.JobQueryAO;
import com.zed.admin.system.pojo.ao.JobUpdateAO;
import com.zed.admin.system.pojo.dto.JobDTO;
import com.zed.admin.system.pojo.vo.JobVO;
import com.zed.admin.system.service.JobService;
import com.zed.common.base.PageParam;
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
@Api(tags = "Job管理")
@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    private JobService jobService;


    @GetMapping("/pageList")
    @ApiOperation("分页查询Job")
    public ResponseEntity searchPage(PageParam pageParam, JobQueryAO queryAO) {
        JobDTO dto = AutoMapperUtil.toPOJO(queryAO, JobDTO.class);
        return new ResponseEntity<>(jobService.getPageList(pageParam, dto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("查询Job")
    public ResponseEntity getJob(@PathVariable Long id) {
        JobVO vo = jobService.getJobById(id);
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }


    @PostMapping("/add")
    @ApiOperation("新增Job")
    public ResponseEntity add(@Validated @RequestBody JobAddAO addAO) {
        // 转换
        JobDTO dto = AutoMapperUtil.toPOJO(addAO, JobDTO.class);
//        jobVerify.verifyRepeat(dto);
        jobService.addJob(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @ApiOperation("修改Job")
    public ResponseEntity update(@Validated @RequestBody JobUpdateAO updateAO) {
        // 转换
        JobDTO dto = AutoMapperUtil.toPOJO(updateAO, JobDTO.class);
//        jobVerify.verifyRepeat(dto);
        jobService.updateJob(dto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除Job")
    public ResponseEntity delete(@PathVariable Long id) {
        jobService.deleteJob(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}