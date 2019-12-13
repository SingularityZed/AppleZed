package com.zed.admin.system.controller.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @Author: wang_ycong(Tel : 16602526966)
 * @Date: 2019/12/12 19:21
 */
@Slf4j
@RestController
public class ZedController {


    @GetMapping
    public ResponseEntity zed(){
        log.info("测试启动类");
        return new ResponseEntity("How old are you?",HttpStatus.OK);
    }



}
