package com.zed.admin.system.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * QueryAO
 *
 * @Author: zed
 * @Date: 2019/12/12 19:12
 */
@Data
@ApiModel("用户返回信息")
public class UserVO implements Serializable {

    @ApiModelProperty("用户名称")
    private String username;
    @ApiModelProperty("用户联系电话")
    private String telephone;

    @ApiModelProperty("用户性别")
    private Integer gender;
}
