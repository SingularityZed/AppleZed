package com.zed.admin.system.pojo.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * UserUpdateAO
 *
 * @author zed
 * @date 2019/12/12 19:12
 */
@Data
@ApiModel("用户编辑请求实体")
public class UserUpdateAO implements Serializable {

    @ApiModelProperty("用户名称")
    private String username;
    @ApiModelProperty("用户联系电话")
    private String telephone;
}
