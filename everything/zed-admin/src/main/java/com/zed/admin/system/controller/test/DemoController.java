package com.zed.admin.system.controller.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoController
 *
 * @Author: wang_ycong(Tel : 16602526966)
 * @Date: 2019/12/13 9:43
 */

@RestController
public class DemoController {
    @Value("${server.port}")
    private String msg;

    @RequestMapping("/index1")
    public String index1() {
        return "方式一:" + msg;
    }

}