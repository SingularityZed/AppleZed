package com.zed.admin.system.pojo.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @ApiModelProperty("用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long id;

    @ApiModelProperty("用户名称")
    @NotBlank(message = "用户名称不能为空")
    private String username;

    @ApiModelProperty("用户联系电话")
    @NotBlank(message = "用户联系电话不能为空")
    private String telephone;
}
