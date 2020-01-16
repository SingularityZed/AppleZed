package com.zed.rest;

import com.zed.aop.log.Log;
import com.zed.domain.Menu;
import com.zed.service.MenuService;
import com.zed.service.dto.MenuQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

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

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }


    @GetMapping("/pageList")
    @ApiOperation("分页查询Menu")
    @PreAuthorize("@el.check('menu:list')")
    public ResponseEntity searchPage(PageParam pageParam, MenuQueryAO queryAO){
        MenuDTO dto = AutoMapperUtil.toPOJO(queryAO, MenuDTO.class);
        return new ResponseEntity<>(menuService.getPageList(pageParam,dto),HttpStatus.OK);
    }

    @GetMapping("/{menuId}")
    @ApiOperation("查询Menu")
    @PreAuthorize("@el.check('menu:detail')")
    public ResponseEntity getMenu(@PathVariable Long menuId){
        MenuVO vo = menuService.getMenuById(menuId);
        return new ResponseEntity<>(vo,HttpStatus.OK);
    }


    @PostMapping("/add")
    @Log("新增Menu")
    @ApiOperation("新增Menu")
    @PreAuthorize("@el.check('menu:add')")
    public ResponseEntity add(@Validated @RequestBody MenuAddVO addAO){
        // 转换
        MenuDTO dto = AutoMapperUtil.toPOJO(addAO, MenuDTO.class);
        menuVerify.verifyRepeat(dto);
        menuService.addMenu(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @Log("修改Menu")
    @ApiOperation("修改Menu")
    @PreAuthorize("@el.check('menu:edit')")
    public ResponseEntity update(@Validated @RequestBody MenuUpdateAO updateAO){
        // 转换
        MenuDTO dto = AutoMapperUtil.toPOJO(updateAO, MenuDTO.class);
        menuVerify.verifyRepeat(dto);
        menuService.updateMenu(dto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{menuId}")
    @Log("删除Menu")
    @ApiOperation("删除Menu")
    @PreAuthorize("@el.check('menu:del')")
    public ResponseEntity delete(@PathVariable Long menuId){
        menuService.delete(menuId);
        return new ResponseEntity(HttpStatus.OK);
    }

}