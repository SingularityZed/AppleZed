package com.zed.admin.system.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 岗位:返回
 *
 * @author zed
 * @date 2020-01-16
 */
@Data
@ApiModel("岗位:返回")
public class JobVO implements Serializable {

    @ApiModelProperty("职位ID")
    private Long id;
    @ApiModelProperty("商户ID")
    private Long orgId;
    @ApiModelProperty("职位名称")
    private String name;
    @ApiModelProperty("是否启用 1启用 0禁用")
    private Boolean enabled;
    @ApiModelProperty("排序")
    private Integer sort;

}