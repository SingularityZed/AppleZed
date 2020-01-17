package com.zed.admin.system.pojo.ao;

import com.zed.common.validate.GroupAdd;
import com.zed.common.validate.GroupEdit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * @author zed
 * @date 2020-01-16
 */
@Data
@ApiModel("岗位:新增")
public class JobAddAO implements Serializable {

    @ApiModelProperty("商户ID")
    @NotNull(groups = {GroupAdd.class}, message = "商户ID不能为空")
    private Long orgId;
    @ApiModelProperty("职位名称")
    @NotBlank(groups = {GroupAdd.class}, message = "职位名称不能为空")
    private String name;
    @ApiModelProperty("是否启用 1启用 0禁用")
    @NotNull(groups = {GroupAdd.class}, message = "是否启用不能为空")
    private Boolean enabled;
    @ApiModelProperty("排序")
    @NotNull(groups = {GroupAdd.class}, message = "排序不能为空")
    private Integer sort;

}