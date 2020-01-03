package com.zed.generator.service.impl;

import com.zed.generator.config.GenConfig;
import com.zed.generator.service.GenConfigService;
import com.zed.generator.utils.PropertiesUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * GenConfigServiceImpl
 *
 * @author zed
 */
@Service
@CacheConfig(cacheNames = "genConfig")
public class GenConfigServiceImpl implements GenConfigService {


    /**
     * 是否覆盖配置信息
     */
    @Value("${generator.isCover}")
    private Boolean isCover;


    /**
     * 查询代码生成配置
     *
     * @return
     */
    @Override
    public GenConfig getConfig() {
        GenConfig genConfig = new GenConfig();
        genConfig.setIsCover(isCover);
        genConfig.setPackagePath(PropertiesUtils.getConfigParam(genConfig.getPackagePath()));
        genConfig.setModuleName(PropertiesUtils.getConfigParam(genConfig.getModuleName()));
        genConfig.setTablePrefix(PropertiesUtils.getConfigParam(genConfig.getTablePrefix()));
        genConfig.setApiPath(PropertiesUtils.getConfigParam(genConfig.getApiPath()));
        genConfig.setFrontPath(PropertiesUtils.getConfigParam(genConfig.getFrontPath()));
        genConfig.setFrontApiPath(PropertiesUtils.getConfigParam(genConfig.getFrontApiPath()));
        genConfig.setAuthor(PropertiesUtils.getConfigParam(genConfig.getAuthor()));
        genConfig.setEmail(PropertiesUtils.getConfigParam(genConfig.getEmail()));
        return genConfig;
    }

    /**
     * 更新代码生成配置
     *
     * @param genConfig
     */
    @Override
    public void updateConfig(GenConfig genConfig) {
        PropertiesUtils.setConfigParam(genConfig.PACKAGE_PATH, genConfig.getPackagePath());
        PropertiesUtils.setConfigParam(genConfig.MODULE_NAME, genConfig.getModuleName());
        PropertiesUtils.setConfigParam(genConfig.TABLE_PREFIX, genConfig.getTablePrefix());
        PropertiesUtils.setConfigParam(genConfig.API_PATH, genConfig.getApiPath());
        PropertiesUtils.setConfigParam(genConfig.FRONT_PATH, genConfig.getFrontPath());
        PropertiesUtils.setConfigParam(genConfig.FRONT_API_PATH, genConfig.getFrontApiPath());
        PropertiesUtils.setConfigParam(genConfig.AUTHOR, genConfig.getAuthor());
        PropertiesUtils.setConfigParam(genConfig.EMAIL, genConfig.getEmail());
    }
}
