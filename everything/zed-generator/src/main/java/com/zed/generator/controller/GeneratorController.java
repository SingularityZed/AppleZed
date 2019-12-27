package com.zed.generator.controller;

import com.zed.common.base.PageParam;
import com.zed.generator.service.GeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.zhengjie.domain.vo.ColumnInfo;
import me.zhengjie.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Zheng Jie
 * @date 2019-01-02
 */
@RestController
@RequestMapping("/api/generator")
@Api(tags = "系统：代码生成管理")
public class GeneratorController {

    @Value("${generator.enabled}")
    private Boolean generatorEnabled;

    /**
     * 通过set方法注入
     */
    private GeneratorService generatorService;

    @Autowired
    public void setGeneratorService(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }


    @ApiOperation("查询数据库元数据")
    @GetMapping(value = "/tables")
    public ResponseEntity getTables(PageParam pageParam,
                                    @RequestParam(value = "database", required = false) String database,
                                    @RequestParam("tableName") String tableName) {
        return new ResponseEntity<>(generatorService.getTables(pageParam, database, tableName), HttpStatus.OK);
    }

    @ApiOperation("查询表内元数据")
    @GetMapping(value = "/columns/{schema}/{tableName}")
    public ResponseEntity getTables(@PathVariable String schema,
                                    @PathVariable String tableName) {
        return new ResponseEntity<>(generatorService.getColumns(schema,tableName), HttpStatus.OK);
    }

    @ApiOperation("生成代码")
    @PostMapping
    public ResponseEntity generator(@RequestBody List<ColumnInfo> columnInfos, @RequestParam String tableName) {
        if (!generatorEnabled) {
            throw new BadRequestException("此环境不允许生成代码！");
        }
        generatorService.generator(columnInfos, genConfigService.find(), tableName);
        return new ResponseEntity(HttpStatus.OK);
    }


}
