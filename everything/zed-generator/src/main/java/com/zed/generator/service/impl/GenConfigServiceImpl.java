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
        genConfig.setPackagePath(PropertiesUtils.getConfigParam(GenConfig.PACKAGE_PATH));
        genConfig.setModuleName(PropertiesUtils.getConfigParam(GenConfig.MODULE_NAME));
        genConfig.setTablePrefix(PropertiesUtils.getConfigParam(GenConfig.TABLE_PREFIX));
        genConfig.setApiPath(PropertiesUtils.getConfigParam(GenConfig.API_PATH));
        genConfig.setFrontPath(PropertiesUtils.getConfigParam(GenConfig.FRONT_PATH));
        genConfig.setFrontApiPath(PropertiesUtils.getConfigParam(GenConfig.FRONT_API_PATH));
        genConfig.setAuthor(PropertiesUtils.getConfigParam(GenConfig.AUTHOR));
        genConfig.setEmail(PropertiesUtils.getConfigParam(GenConfig.EMAIL));
        return genConfig;
    }

    /**
     * 更新代码生成配置
     *
     * @param genConfig
     */
    @Override
    public void updateConfig(GenConfig genConfig) {
        PropertiesUtils.setConfigParam(GenConfig.PACKAGE_PATH, genConfig.getPackagePath());
        PropertiesUtils.setConfigParam(GenConfig.MODULE_NAME, genConfig.getModuleName());
        PropertiesUtils.setConfigParam(GenConfig.TABLE_PREFIX, genConfig.getTablePrefix());
        PropertiesUtils.setConfigParam(GenConfig.API_PATH, genConfig.getApiPath());
        PropertiesUtils.setConfigParam(GenConfig.FRONT_PATH, genConfig.getFrontPath());
        PropertiesUtils.setConfigParam(GenConfig.FRONT_API_PATH, genConfig.getFrontApiPath());
        PropertiesUtils.setConfigParam(GenConfig.AUTHOR, genConfig.getAuthor());
        PropertiesUtils.setConfigParam(GenConfig.EMAIL, genConfig.getEmail());
    }
}
