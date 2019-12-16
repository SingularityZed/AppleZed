package com.zed.admin.system.pojo.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * UserAddAO
 *
 * @author zed
 * @date 2019/12/12 19:12
 */
@Data
@ApiModel("用户新增请求实体")
public class UserAddAO implements Serializable {

    @ApiModelProperty("用户名称")
    private String username;
    @ApiModelProperty("用户联系电话")
    private String telephone;
}
