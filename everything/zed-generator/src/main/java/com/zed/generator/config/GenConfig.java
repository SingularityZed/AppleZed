package com.zed.generator.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 代码生成配置
 *
 * @author zed
 * @date 2020-01-02
 */
@Data
@ApiModel("代码生成配置")
public class GenConfig {

    public final String PACKAGE_PATH = "packagePath";
    public final String MODULE_NAME = "moduleName";
    public final String TABLE_PREFIX = "tablePrefix";
    public final String API_PATH = "apiPath";
    public final String FRONT_PATH = "frontPath";
    public final String FRONT_API_PATH = "frontApiPath";
    public final String AUTHOR = "author";
    public final String EMAIL = "email";

    @ApiModelProperty("包路径")
    private String packagePath;
    @ApiModelProperty("模块名")
    private String moduleName;
    @ApiModelProperty("表前缀")
    private String tablePrefix;
    @ApiModelProperty("api路径")
    private String apiPath;
    @ApiModelProperty("前端文件路径")
    private String frontPath;
    @ApiModelProperty("前端文件地址路径")
    private String frontApiPath;
    @ApiModelProperty("作者")
    private String author;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("是否覆盖")
    private Boolean isCover;
}
