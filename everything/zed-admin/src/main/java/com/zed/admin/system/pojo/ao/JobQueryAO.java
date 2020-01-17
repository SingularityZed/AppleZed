package com.zed.admin.system.pojo.ao;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * @author zed
 * @date 2020-01-16
 */
@Data
@Api(tags = "岗位查询")
public class JobQueryAO implements Serializable {

    @ApiModelProperty("商户ID")
    private Long orgId;
    @ApiModelProperty("职位名称")
    private String name;
    @ApiModelProperty("是否启用 1启用 0禁用")
    private Boolean enabled;
    @ApiModelProperty("排序")
    private Integer sort;

}