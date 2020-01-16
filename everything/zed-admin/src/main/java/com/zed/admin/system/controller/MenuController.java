package com.zed.admin.system.controller;

import com.zed.admin.system.pojo.ao.MenuAddAO;
import com.zed.admin.system.pojo.ao.MenuQueryAO;
import com.zed.admin.system.pojo.ao.MenuUpdateAO;
import com.zed.admin.system.pojo.dto.MenuDTO;
import com.zed.admin.system.pojo.vo.MenuVO;
import com.zed.admin.system.service.MenuService;
import com.zed.common.base.PageParam;
import com.zed.common.utils.AutoMapperUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * MenuController
 *
 * @author zed
 * @date 2020-01-16
 */
@Api(tags = "Menu管理")
@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    @GetMapping("/pageList")
    @ApiOperation("分页查询Menu")
    public ResponseEntity searchPage(PageParam pageParam, MenuQueryAO queryAO) {
        MenuDTO dto = AutoMapperUtil.toPOJO(queryAO, MenuDTO.class);
        return new ResponseEntity<>(menuService.getPageList(pageParam, dto), HttpStatus.OK);
    }

    @GetMapping("/{menuId}")
    @ApiOperation("查询Menu")
    public ResponseEntity getMenu(@PathVariable Long menuId) {
        MenuVO vo = menuService.getMenuById(menuId);
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }


    @PostMapping("/add")
    @ApiOperation("新增Menu")
    public ResponseEntity add(@Validated @RequestBody MenuAddAO addAO) {
        // 转换
        MenuDTO dto = AutoMapperUtil.toPOJO(addAO, MenuDTO.class);
//        menuVerify.verifyRepeat(dto);
        menuService.addMenu(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @ApiOperation("修改Menu")
    public ResponseEntity update(@Validated @RequestBody MenuUpdateAO updateAO) {
        // 转换
        MenuDTO dto = AutoMapperUtil.toPOJO(updateAO, MenuDTO.class);
//        menuVerify.verifyRepeat(dto);
        menuService.updateMenu(dto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{menuId}")
    @ApiOperation("删除Menu")
    public ResponseEntity delete(@PathVariable Long menuId) {
        menuService.deleteMenu(menuId);
        return new ResponseEntity(HttpStatus.OK);
    }

}