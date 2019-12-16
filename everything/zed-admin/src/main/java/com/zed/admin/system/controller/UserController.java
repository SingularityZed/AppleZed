package com.zed.admin.system.controller;

import com.zed.admin.common.base.PageParam;
import com.zed.admin.system.pojo.ao.UserAddAO;
import com.zed.admin.system.pojo.ao.UserQueryAO;
import com.zed.admin.system.pojo.ao.UserUpdateAO;
import com.zed.admin.system.service.UserService;
import com.zed.admin.system.verify.UserVerify;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * UserController
 *
 * @author zed
 * @date 2019/12/13 10:09
 */
@Slf4j
@Api(tags = "用户管理")
@RestController
@RequestMapping("/api/zed-admin/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserVerify userVerify;

    @ApiOperation("分页查询用户列表")
    @GetMapping("/pageList")
    public ResponseEntity searchPage(PageParam pageParam, UserQueryAO queryAO) {
        return new ResponseEntity<>(userService.getPageList(pageParam, queryAO), HttpStatus.OK);
    }

    @ApiOperation("获取用户详情")
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        userService.getById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation("新增用户")
    @PostMapping("/add")
    public ResponseEntity add(@Validated @RequestBody UserAddAO addAO) {
        userVerify.verifyRepeat(addAO);
        userService.add(addAO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation("修改用户")
    @PutMapping("/update")
    public ResponseEntity update(@Validated @RequestBody UserUpdateAO updateAO) {
        userService.update(updateAO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
