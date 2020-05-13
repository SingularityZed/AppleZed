package com.zed.admin.system.controller;

import com.zed.admin.system.pojo.ao.OrganizationAddAO;
import com.zed.admin.system.pojo.ao.OrganizationQueryAO;
import com.zed.admin.system.pojo.ao.OrganizationUpdateAO;
import com.zed.admin.system.pojo.dto.OrganizationDTO;
import com.zed.admin.system.pojo.vo.OrganizationVO;
import com.zed.admin.system.service.OrganizationService;
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
 * OrganizationController
 *
 * @author zed
 * @date 2020-01-16
 */
@Api(tags = "Organization管理")
@RestController
@RequestMapping("/api/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;


    @GetMapping("/pageList")
    @ApiOperation("分页查询Organization")
    public ResponseResult searchPage(PageParam pageParam, OrganizationQueryAO queryAO) {
        OrganizationDTO dto = AutoMapperUtil.toPOJO(queryAO, OrganizationDTO.class);
        return ResponseResult.succeed(organizationService.getPageList(pageParam, dto));
    }

    @GetMapping("/{id}")
    @ApiOperation("查询Organization")
    public ResponseResult getOrganization(@PathVariable Long id) {
        OrganizationVO vo = organizationService.getOrganizationById(id);
        return ResponseResult.succeed(vo);
    }


    @PostMapping("/add")
    @ApiOperation("新增Organization")
    public ResponseResult add(@Validated @RequestBody OrganizationAddAO addAO) {
        // 转换
        OrganizationDTO dto = AutoMapperUtil.toPOJO(addAO, OrganizationDTO.class);
//        organizationVerify.verifyRepeat(dto);
        organizationService.addOrganization(dto);
        return ResponseResult.succeed();
    }

    @PutMapping("/update")
    @ApiOperation("修改Organization")
    public ResponseResult update(@Validated @RequestBody OrganizationUpdateAO updateAO) {
        // 转换
        OrganizationDTO dto = AutoMapperUtil.toPOJO(updateAO, OrganizationDTO.class);
//        organizationVerify.verifyRepeat(dto);
        organizationService.updateOrganization(dto);
        return ResponseResult.succeed();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除Organization")
    public ResponseResult delete(@PathVariable Long id) {
        organizationService.deleteOrganization(id);
        return ResponseResult.succeed();
    }

}