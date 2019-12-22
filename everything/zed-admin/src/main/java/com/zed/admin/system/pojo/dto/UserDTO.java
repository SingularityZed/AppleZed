package com.zed.admin.system.pojo.dto;

import com.zed.admin.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * UserDTO
 *
 * @author zed
 * @date 2019/12/12 19:12
 */
@Data
@ApiModel("用户DTO")
public class UserDTO extends BaseEntity implements Serializable {

    @ApiModelProperty("用户名称")
    private String username;
    @ApiModelProperty("用户联系电话")
    private String telephone;
}
