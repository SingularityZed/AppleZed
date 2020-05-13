package com.zed.admin.system.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zed
 * @date 2018-12-20
 */
@Data
@AllArgsConstructor
public class MenuMetaVO implements Serializable {

    private String title;

    private String icon;

    private Integer isCache;
}
