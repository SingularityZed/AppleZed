package com.zed.rest;

import com.zed.aop.log.Log;
import com.zed.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;

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

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @GetMapping("/pageList")
    @ApiOperation("分页查询Job")
    @PreAuthorize("@el.check('job:list')")
    public ResponseEntity searchPage(PageParam pageParam, JobQueryAO queryAO) {
        JobDTO dto = AutoMapperUtil.toPOJO(queryAO, JobDTO.class);
        return new ResponseEntity<>(jobService.getPageList(pageParam, dto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("查询Job")
    @PreAuthorize("@el.check('job:detail')")
    public ResponseEntity getJob(@PathVariable Long id) {
        JobVO vo = jobService.getJobById(id);
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }


    @PostMapping("/add")
    @Log("新增Job")
    @ApiOperation("新增Job")
    @PreAuthorize("@el.check('job:add')")
    public ResponseEntity add(@Validated @RequestBody JobAddVO addAO) {
        // 转换
        JobDTO dto = AutoMapperUtil.toPOJO(addAO, JobDTO.class);
        jobVerify.verifyRepeat(dto);
        jobService.addJob(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @Log("修改Job")
    @ApiOperation("修改Job")
    @PreAuthorize("@el.check('job:edit')")
    public ResponseEntity update(@Validated @RequestBody JobUpdateAO updateAO) {
        // 转换
        JobDTO dto = AutoMapperUtil.toPOJO(updateAO, JobDTO.class);
        jobVerify.verifyRepeat(dto);
        jobService.updateJob(dto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @Log("删除Job")
    @ApiOperation("删除Job")
    @PreAuthorize("@el.check('job:del')")
    public ResponseEntity delete(@PathVariable Long id) {
        jobService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}