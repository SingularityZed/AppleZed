package com.zed.rest;

import com.zed.aop.log.Log;
import com.zed.domain.Role;
import com.zed.service.RoleService;
import com.zed.service.dto.RoleQueryCriteria;
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
* RoleController
*
* @author zed
* @date 2020-01-16
*/
@Api(tags = "Role管理")
@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping("/pageList")
    @ApiOperation("分页查询Role")
    @PreAuthorize("@el.check('role:list')")
    public ResponseEntity searchPage(PageParam pageParam, RoleQueryAO queryAO){
        RoleDTO dto = AutoMapperUtil.toPOJO(queryAO, RoleDTO.class);
        return new ResponseEntity<>(roleService.getPageList(pageParam,dto),HttpStatus.OK);
    }

    @GetMapping("/{roleId}")
    @ApiOperation("查询Role")
    @PreAuthorize("@el.check('role:detail')")
    public ResponseEntity getRole(@PathVariable Long roleId){
        RoleVO vo = roleService.getRoleById(roleId);
        return new ResponseEntity<>(vo,HttpStatus.OK);
    }


    @PostMapping("/add")
    @Log("新增Role")
    @ApiOperation("新增Role")
    @PreAuthorize("@el.check('role:add')")
    public ResponseEntity add(@Validated @RequestBody RoleAddVO addAO){
        // 转换
        RoleDTO dto = AutoMapperUtil.toPOJO(addAO, RoleDTO.class);
        roleVerify.verifyRepeat(dto);
        roleService.addRole(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @Log("修改Role")
    @ApiOperation("修改Role")
    @PreAuthorize("@el.check('role:edit')")
    public ResponseEntity update(@Validated @RequestBody RoleUpdateAO updateAO){
        // 转换
        RoleDTO dto = AutoMapperUtil.toPOJO(updateAO, RoleDTO.class);
        roleVerify.verifyRepeat(dto);
        roleService.updateRole(dto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{roleId}")
    @Log("删除Role")
    @ApiOperation("删除Role")
    @PreAuthorize("@el.check('role:del')")
    public ResponseEntity delete(@PathVariable Long roleId){
        roleService.delete(roleId);
        return new ResponseEntity(HttpStatus.OK);
    }

}