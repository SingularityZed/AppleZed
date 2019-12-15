package com.zed.admin.system.controller;

import com.zed.admin.system.entity.User;
import com.zed.admin.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @Author: wang_ycong(Tel : 16602526966)
 * @Date: 2019/12/13 10:09
 */
@Api(tags = "角色管理")
@Slf4j
@RestController
@RequestMapping("/api/zed/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("分页查询用户列表")
    @GetMapping
    public ResponseEntity search() {
        log.info("获取用户信息!");
        String username = "zed";
        User user = userService.searchByName(username);

        log.info("这个用户是："+user.toString());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
