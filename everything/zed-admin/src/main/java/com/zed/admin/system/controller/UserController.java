package com.zed.admin.system.controller;

import com.zed.admin.system.pojo.ao.UserAddAO;
import com.zed.admin.system.pojo.ao.UserQueryAO;
import com.zed.admin.system.pojo.ao.UserUpdateAO;
import com.zed.admin.system.pojo.dto.UserDTO;
import com.zed.admin.system.pojo.verify.UserVerify;
import com.zed.admin.system.pojo.vo.UserVO;
import com.zed.admin.system.service.UserService;
import com.zed.common.base.PageParam;
import com.zed.common.utils.AutoMapperUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * UserController
 *
 * @author zed
 * @date 2019/12/13 10:09
 */
@Slf4j
@Api(tags = "用户管理")
@RestController
@RequestMapping("/api/zed-admin/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserVerify userVerify;

    @ApiOperation("分页查询用户列表")
    @GetMapping("/pageList")
    public ResponseEntity searchPage(PageParam pageParam, UserQueryAO queryAO) {
        UserDTO dto = AutoMapperUtil.toPOJO(queryAO, UserDTO.class);
        return new ResponseEntity<>(userService.getPageList(pageParam, dto), HttpStatus.OK);
    }

    @ApiOperation("获取用户详情")
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        UserVO vo = userService.getUserById(id);
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }

    @ApiOperation("新增用户")
    @PostMapping("/add")
    public ResponseEntity add(@Validated @RequestBody UserAddAO addAO) {
        // 转换
        UserDTO dto = AutoMapperUtil.toPOJO(addAO, UserDTO.class);
        userVerify.verifyRepeat(dto);
        userService.addUser(dto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ApiOperation("修改用户")
    @PutMapping("/update")
    public ResponseEntity update(@Validated @RequestBody UserUpdateAO updateAO) {
        UserDTO dto = AutoMapperUtil.toPOJO(updateAO, UserDTO.class);
        userService.updateUser(dto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
