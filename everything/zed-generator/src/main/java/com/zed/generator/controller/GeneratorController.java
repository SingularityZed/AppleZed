package com.zed.generator.controller;

import com.zed.common.base.PageParam;
import com.zed.common.exception.ControllerException;
import com.zed.generator.info.ColumnInfo;
import com.zed.generator.service.GenConfigService;
import com.zed.generator.service.GeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zed
 * @date 2019-01-02
 */
@RestController
@RequestMapping("/api/generator")
@Api(tags = "系统:代码生成管理")
public class GeneratorController {

    /**
     * 是否启用代码生成
     */
    @Value("${generator.enabled}")
    private Boolean generatorEnabled;

    @Autowired
    private GeneratorService generatorService;
    @Autowired
    private GenConfigService genConfigService;


    @ApiOperation("查询数据库元数据")
    @GetMapping(value = "/tables")
    public ResponseEntity getTables(PageParam pageParam,
                                    @RequestParam(value = "database", required = false) String database,
                                    @RequestParam("tableName") String tableName) {
        return new ResponseEntity<>(generatorService.getTables(pageParam, database, tableName), HttpStatus.OK);
    }

    @ApiOperation("查询表内元数据")
    @GetMapping(value = "/columns/{database}/{tableName}")
    public ResponseEntity getColumns(PageParam pageParam,
                                    @PathVariable String database,
                                    @PathVariable String tableName) {
        return new ResponseEntity<>(generatorService.getColumns(pageParam,database, tableName), HttpStatus.OK);
    }

    @ApiOperation("生成代码")
    @PostMapping(value = "/make")
    public ResponseEntity generator(@RequestBody List<ColumnInfo> columnInfos,
                                    @RequestParam String tableName) {
        if (!generatorEnabled) {
            throw new ControllerException("此环境不允许生成代码！");
        }
        generatorService.generator(genConfigService.getConfig(), tableName, columnInfos);
        return new ResponseEntity(HttpStatus.OK);
    }


}
