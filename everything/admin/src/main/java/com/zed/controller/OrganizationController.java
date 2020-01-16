package com.zed.rest;

import com.zed.aop.log.Log;
import com.zed.domain.Organization;
import com.zed.service.OrganizationService;
import com.zed.service.dto.OrganizationQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

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

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }


    @GetMapping("/pageList")
    @ApiOperation("分页查询Organization")
    @PreAuthorize("@el.check('organization:list')")
    public ResponseEntity searchPage(PageParam pageParam, OrganizationQueryAO queryAO){
        OrganizationDTO dto = AutoMapperUtil.toPOJO(queryAO, OrganizationDTO.class);
        return new ResponseEntity<>(organizationService.getPageList(pageParam,dto),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("查询Organization")
    @PreAuthorize("@el.check('organization:detail')")
    public ResponseEntity getOrganization(@PathVariable Long id){
        OrganizationVO vo = organizationService.getOrganizationById(id);
        return new ResponseEntity<>(vo,HttpStatus.OK);
    }


    @PostMapping("/add")
    @Log("新增Organization")
    @ApiOperation("新增Organization")
    @PreAuthorize("@el.check('organization:add')")
    public ResponseEntity add(@Validated @RequestBody OrganizationAddVO addAO){
        // 转换
        OrganizationDTO dto = AutoMapperUtil.toPOJO(addAO, OrganizationDTO.class);
        organizationVerify.verifyRepeat(dto);
        organizationService.addOrganization(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @Log("修改Organization")
    @ApiOperation("修改Organization")
    @PreAuthorize("@el.check('organization:edit')")
    public ResponseEntity update(@Validated @RequestBody OrganizationUpdateAO updateAO){
        // 转换
        OrganizationDTO dto = AutoMapperUtil.toPOJO(updateAO, OrganizationDTO.class);
        organizationVerify.verifyRepeat(dto);
        organizationService.updateOrganization(dto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @Log("删除Organization")
    @ApiOperation("删除Organization")
    @PreAuthorize("@el.check('organization:del')")
    public ResponseEntity delete(@PathVariable Long id){
        organizationService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}