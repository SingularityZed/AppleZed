package com.zed.admin.system.controller;

import com.zed.admin.system.pojo.ao.MenuAddAO;
import com.zed.admin.system.pojo.ao.MenuQueryAO;
import com.zed.admin.system.pojo.ao.MenuUpdateAO;
import com.zed.admin.system.pojo.dto.MenuDTO;
import com.zed.admin.system.service.MenuService;
import com.zed.admin.system.verify.MenuVerify;
import com.zed.common.exception.handler.ResponseResult;
import com.zed.common.utils.AutoMapperUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private MenuVerify menuVerify;

    @ApiOperation("获取前端所需菜单")
    @GetMapping("/build")
    public ResponseResult buildMenus() {
        List<MenuDTO> dealList = new ArrayList<>();
        List<MenuDTO> menuDTOList = (List<MenuDTO>) menuService.buildTree(dealList).get("content");
        return ResponseResult.succeed(menuService.buildMenus(menuDTOList));
    }


    @ApiOperation("返回全部菜单")
    @GetMapping("list")
    public ResponseResult search() {
        return ResponseResult.succeed(menuService.getMenuTree(menuService.findByPid(0L)));
    }


    @ApiOperation("查询菜单")
    @GetMapping
    public ResponseResult getMenu(MenuQueryAO queryAO) {
        List<MenuDTO> menuDTOList = menuService.getMenuList(queryAO);
        return ResponseResult.succeed(menuService.buildTree(menuDTOList));
    }


    @ApiOperation("新增菜单")
    @PostMapping("/add")
    public ResponseResult add(@Validated @RequestBody MenuAddAO addAO) {
        // 转换
        MenuDTO dto = AutoMapperUtil.toPOJO(addAO, MenuDTO.class);
//        menuVerify.verifyRepeat(dto);
        menuService.addMenu(dto);
        return ResponseResult.succeed();
    }


    @ApiOperation("修改菜单")
    @PutMapping("/update")
    public ResponseResult update(@Validated @RequestBody MenuUpdateAO updateAO) {
        // 转换
        MenuDTO dto = AutoMapperUtil.toPOJO(updateAO, MenuDTO.class);
//        menuVerify.verifyRepeat(dto);
        menuService.updateMenu(dto);
        return ResponseResult.succeed();
    }

    @ApiOperation("删除菜单")
    @DeleteMapping("/{menuId}")
    public ResponseResult delete(@PathVariable Long menuId) {
        menuService.deleteMenu(menuId);
        return ResponseResult.succeed();
    }

}