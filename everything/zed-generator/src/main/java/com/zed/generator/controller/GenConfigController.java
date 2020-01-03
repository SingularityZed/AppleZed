package com.zed.generator.controller;

import com.zed.generator.config.GenConfig;
import com.zed.generator.service.GenConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zheng Jie
 * @date 2019-01-14
 */
@RestController
@RequestMapping("/api/genConfig")
@Api(tags = "系统:代码生成器配置管理")
public class GenConfigController {


    private final GenConfigService genConfigService;

    public GenConfigController(GenConfigService genConfigService) {
        this.genConfigService = genConfigService;
    }

    @ApiOperation("查询配置")
    @GetMapping
    public ResponseEntity getConfig() {
        return new ResponseEntity<>(genConfigService.getConfig(), HttpStatus.OK);
    }

    @ApiOperation("修改配置")
    @PutMapping
    public ResponseEntity updateConfig(@Validated @RequestBody GenConfig genConfig) {
        genConfigService.updateConfig(genConfig);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
