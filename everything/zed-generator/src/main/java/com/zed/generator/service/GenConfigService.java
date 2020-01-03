package com.zed.generator.service;


import com.zed.generator.config.GenConfig;

/**
 * GenConfigService
 *
 * @author zed
 * @date 2019-01-14
 */
public interface GenConfigService {

    /**
     * 查询代码生成配置
     *
     * @return
     */
    GenConfig getConfig();

    /**
     * 更新代码生成配置
     *
     * @param genConfig
     * @return
     */
    void updateConfig(GenConfig genConfig);
}
