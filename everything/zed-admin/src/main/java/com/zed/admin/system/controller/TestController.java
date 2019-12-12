package com.zed.admin.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * TestController
 *
 * @Author: wang_ycong(Tel : 16602526966)
 * @Date: 2019/12/12 19:21
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping
    public ResponseEntity test(){
        log.info("可不可以？");
        return new ResponseEntity(HttpStatus.OK);
    }



}
