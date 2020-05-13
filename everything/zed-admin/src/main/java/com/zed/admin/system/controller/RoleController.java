package com.zed.admin.system.controller;

import com.zed.admin.system.pojo.ao.RoleAddAO;
import com.zed.admin.system.pojo.ao.RoleQueryAO;
import com.zed.admin.system.pojo.ao.RoleUpdateAO;
import com.zed.admin.system.pojo.dto.RoleDTO;
import com.zed.admin.system.pojo.vo.RoleVO;
import com.zed.admin.system.service.RoleService;
import com.zed.admin.system.verify.RoleVerify;
import com.zed.common.base.PageParam;
import com.zed.common.exception.handler.ResponseResult;
import com.zed.common.utils.AutoMapperUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseResult searchPage(PageParam pageParam, RoleQueryAO queryAO) {
        RoleDTO dto = AutoMapperUtil.toPOJO(queryAO, RoleDTO.class);
        return ResponseResult.succeed(roleService.getPageList(pageParam, dto));
    }

    @GetMapping("/{roleId}")
    @ApiOperation("查询Role")
    public ResponseResult getRole(@PathVariable Long roleId) {
        RoleVO vo = roleService.getRoleById(roleId);
        return ResponseResult.succeed(vo);
    }


    @PostMapping("/add")
    @ApiOperation("新增Role")
    public ResponseResult add(@Validated @RequestBody RoleAddAO addAO) {
        // 转换
        RoleDTO dto = AutoMapperUtil.toPOJO(addAO, RoleDTO.class);
//        roleVerify.verifyRepeat(dto);
        roleService.addRole(dto);
        return ResponseResult.succeed();
    }

    @PutMapping("/update")
    @ApiOperation("修改Role")
    public ResponseResult update(@Validated @RequestBody RoleUpdateAO updateAO) {
        // 转换
        RoleDTO dto = AutoMapperUtil.toPOJO(updateAO, RoleDTO.class);
//        roleVerify.verifyRepeat(dto);
        roleService.updateRole(dto);
        return ResponseResult.succeed();
    }

    @DeleteMapping("/{roleId}")
    @ApiOperation("删除Role")
    public ResponseResult delete(@PathVariable Long roleId) {
        roleService.deleteRole(roleId);
        return ResponseResult.succeed();
    }

}