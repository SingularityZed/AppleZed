package com.zed.admin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @Author: wang_ycong(Tel : 16602526966)
 * @Date: 2019/12/12 23:14
 */
@RestController
public class HelloController {

    @GetMapping("/hello/{name}")
    public String hollo(@PathVariable String name){
        return "Hello "+ name;
    }
}

