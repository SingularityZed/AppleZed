package com.zed.admin.system.controller;

import com.zed.admin.system.pojo.ao.UserAddAO;
import com.zed.admin.system.pojo.ao.UserQueryAO;
import com.zed.admin.system.pojo.ao.UserUpdateAO;
import com.zed.admin.system.pojo.dto.UserDTO;
import com.zed.admin.system.pojo.vo.UserVO;
import com.zed.admin.system.service.UserService;
import com.zed.admin.system.verify.UserVerify;
import com.zed.common.base.PageParam;
import com.zed.common.exception.handler.ResponseResult;
import com.zed.common.utils.AutoMapperUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseResult searchPage(PageParam pageParam, UserQueryAO queryAO) {
        UserDTO dto = AutoMapperUtil.toPOJO(queryAO, UserDTO.class);
        return ResponseResult.succeed(userService.getPageList(pageParam, dto));
    }

    @ApiOperation("获取用户详情")
    @GetMapping("/{id}")
    public ResponseResult get(@PathVariable Long id) {
        UserVO vo = userService.getUserById(id);
        return ResponseResult.succeed(vo);
    }

    @ApiOperation("新增用户")
    @PostMapping("/add")
    public ResponseResult add(@Validated @RequestBody UserAddAO addAO) {
        // 转换
        UserDTO dto = AutoMapperUtil.toPOJO(addAO, UserDTO.class);
        userVerify.verifyRepeat(dto);
        userService.addUser(dto);
        return ResponseResult.succeed();
    }

    @ApiOperation("修改用户")
    @PutMapping("/update")
    public ResponseResult update(@Validated @RequestBody UserUpdateAO updateAO) {
        UserDTO dto = AutoMapperUtil.toPOJO(updateAO, UserDTO.class);
        userService.updateUser(dto);
        return ResponseResult.succeed();
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseResult.succeed();
    }
}
