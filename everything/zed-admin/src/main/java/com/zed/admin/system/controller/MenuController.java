package com.zed.admin.system.controller;

import com.zed.admin.system.pojo.ao.MenuAddAO;
import com.zed.admin.system.pojo.ao.MenuQueryAO;
import com.zed.admin.system.pojo.ao.MenuUpdateAO;
import com.zed.admin.system.pojo.dto.MenuDTO;
import com.zed.admin.system.pojo.vo.MenuVO;
import com.zed.admin.system.service.MenuService;
import com.zed.common.base.PageParam;
import com.zed.common.exception.handler.ResponseResult;
import com.zed.common.utils.AutoMapperUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @GetMapping("/build")
    @ApiOperation("获取前端所需菜单")
    public ResponseResult buildMenus() {
        List<MenuDTO> menuDTOList=new ArrayList<>();
        List<MenuVO> menuVOList=menuService.buildTree(menuDTOList);
        return ResponseResult.succeed(menuService.buildMenus(menuVOList));
    }


    //返回全部菜单
    @GetMapping("list")
    @ApiOperation("查询菜单")
    public ResponseResult searchPage(PageParam pageParam ,MenuQueryAO queryAO) {
        MenuDTO dto = AutoMapperUtil.toPOJO(queryAO, MenuDTO.class);
        return ResponseResult.succeed(menuService.getPageList(pageParam,dto));
    }





//    @GetMapping("list")
//    @ApiOperation("查询菜单")
//    public ResponseResult searchPage( MenuQueryAO queryAO) {
//        MenuDTO dto = AutoMapperUtil.toPOJO(queryAO, MenuDTO.class);
//        return ResponseResult.succeed(menuService.getPageList( dto));
//    }
//
//
//
//    @GetMapping("/{menuId}")
//    @ApiOperation("查询Menu")
//    public ResponseResult getMenu(@PathVariable Long menuId) {
//        MenuVO vo = menuService.getMenuById(menuId);
//        return ResponseResult.succeed(vo);
//    }


    @PostMapping("/add")
    @ApiOperation("新增Menu")
    public ResponseResult add(@Validated @RequestBody MenuAddAO addAO) {
        // 转换
        MenuDTO dto = AutoMapperUtil.toPOJO(addAO, MenuDTO.class);
//        menuVerify.verifyRepeat(dto);
        menuService.addMenu(dto);
        return ResponseResult.succeed();
    }

    @PutMapping("/update")
    @ApiOperation("修改Menu")
    public ResponseResult update(@Validated @RequestBody MenuUpdateAO updateAO) {
        // 转换
        MenuDTO dto = AutoMapperUtil.toPOJO(updateAO, MenuDTO.class);
//        menuVerify.verifyRepeat(dto);
        menuService.updateMenu(dto);
        return ResponseResult.succeed();
    }

    @DeleteMapping("/{menuId}")
    @ApiOperation("删除Menu")
    public ResponseResult delete(@PathVariable Long menuId) {
        menuService.deleteMenu(menuId);
        return ResponseResult.succeed();
    }

}