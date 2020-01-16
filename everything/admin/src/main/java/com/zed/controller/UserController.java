package com.zed.rest;

import com.zed.aop.log.Log;
import com.zed.domain.User;
import com.zed.service.UserService;
import com.zed.service.dto.UserQueryCriteria;
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
* UserController
*
* @author zed
* @date 2020-01-16
*/
@Api(tags = "User管理")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/pageList")
    @ApiOperation("分页查询User")
    @PreAuthorize("@el.check('user:list')")
    public ResponseEntity searchPage(PageParam pageParam, UserQueryAO queryAO){
        UserDTO dto = AutoMapperUtil.toPOJO(queryAO, UserDTO.class);
        return new ResponseEntity<>(userService.getPageList(pageParam,dto),HttpStatus.OK);
    }

    @GetMapping("/{roleId}")
    @ApiOperation("查询User")
    @PreAuthorize("@el.check('user:detail')")
    public ResponseEntity getUser(@PathVariable Long roleId){
        UserVO vo = userService.getUserById(roleId);
        return new ResponseEntity<>(vo,HttpStatus.OK);
    }


    @PostMapping("/add")
    @Log("新增User")
    @ApiOperation("新增User")
    @PreAuthorize("@el.check('user:add')")
    public ResponseEntity add(@Validated @RequestBody UserAddVO addAO){
        // 转换
        UserDTO dto = AutoMapperUtil.toPOJO(addAO, UserDTO.class);
        userVerify.verifyRepeat(dto);
        userService.addUser(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @Log("修改User")
    @ApiOperation("修改User")
    @PreAuthorize("@el.check('user:edit')")
    public ResponseEntity update(@Validated @RequestBody UserUpdateAO updateAO){
        // 转换
        UserDTO dto = AutoMapperUtil.toPOJO(updateAO, UserDTO.class);
        userVerify.verifyRepeat(dto);
        userService.updateUser(dto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{roleId}")
    @Log("删除User")
    @ApiOperation("删除User")
    @PreAuthorize("@el.check('user:del')")
    public ResponseEntity delete(@PathVariable Long roleId){
        userService.delete(roleId);
        return new ResponseEntity(HttpStatus.OK);
    }

}