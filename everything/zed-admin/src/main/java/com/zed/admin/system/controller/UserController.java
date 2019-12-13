package com.zed.admin.system.controller;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/api/zed/admin/user")
public class UserController {

    @GetMapping
    public ResponseEntity search(){
       log.info("获取用户信息!");

       return new ResponseEntity<>("userList", HttpStatus.OK);
    }
}
