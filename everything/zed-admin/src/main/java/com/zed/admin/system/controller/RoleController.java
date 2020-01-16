package com.zed.admin.system.controller;

import com.zed.admin.system.pojo.ao.RoleAddAO;
import com.zed.admin.system.pojo.ao.RoleQueryAO;
import com.zed.admin.system.pojo.ao.RoleUpdateAO;
import com.zed.admin.system.pojo.dto.RoleDTO;
import com.zed.admin.system.pojo.vo.RoleVO;
import com.zed.admin.system.service.RoleService;
import com.zed.admin.system.verify.RoleVerify;
import com.zed.admin.system.verify.UserVerify;
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
 * RoleController
 *
 * @author zed
 * @date 2020-01-16
 */
@Api(tags = "Role管理")
@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleVerify roleVerify;

    @GetMapping("/pageList")
    @ApiOperation("分页查询Role")
    public ResponseEntity searchPage(PageParam pageParam, RoleQueryAO queryAO) {
        RoleDTO dto = AutoMapperUtil.toPOJO(queryAO, RoleDTO.class);
        return new ResponseEntity<>(roleService.getPageList(pageParam, dto), HttpStatus.OK);
    }

    @GetMapping("/{roleId}")
    @ApiOperation("查询Role")
    public ResponseEntity getRole(@PathVariable Long roleId) {
        RoleVO vo = roleService.getRoleById(roleId);
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }


    @PostMapping("/add")
    @ApiOperation("新增Role")
    public ResponseEntity add(@Validated @RequestBody RoleAddAO addAO) {
        // 转换
        RoleDTO dto = AutoMapperUtil.toPOJO(addAO, RoleDTO.class);
//        roleVerify.verifyRepeat(dto);
        roleService.addRole(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @ApiOperation("修改Role")
    public ResponseEntity update(@Validated @RequestBody RoleUpdateAO updateAO) {
        // 转换
        RoleDTO dto = AutoMapperUtil.toPOJO(updateAO, RoleDTO.class);
//        roleVerify.verifyRepeat(dto);
        roleService.updateRole(dto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{roleId}")
    @ApiOperation("删除Role")
    public ResponseEntity delete(@PathVariable Long roleId) {
        roleService.deleteRole(roleId);
        return new ResponseEntity(HttpStatus.OK);
    }

}