package com.zed.generator.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.util.List;

/**
 * properties配置类
 *
 * <p>
 * //    简化配置信息，但是不方便详细配置
 * //    Configurations configs = new Configurations();
 * //    // Read data from this file
 * //    File propertiesFile = new File("config.properties");
 * //    PropertiesConfiguration config = configs.properties(propertiesFile);
 * </p>
 *
 * @author zed
 * @description 详见：https://commons.apache.org
 */
@Slf4j
public class PropertiesUtils {

    /**
     * 读取generator.properties,并静态资源加载一次
     */
    private static Configuration CONFIG;
    private static FileBasedConfigurationBuilder<FileBasedConfiguration> builder;

    static {
        Parameters params = new Parameters();
        builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                .configure(params.properties()
                        // 默认情况下，禁用列表定界符处的字符串值拆分,配置后可取得“,”后内容
                        .setListDelimiterHandler(new DefaultListDelimiterHandler(','))
                        // 默认的编码格式是ISO-8859-1，所以才在读取文件之前先设置了编码格式
                        .setFileName("generator.properties").setEncoding("UTF-8"));
        try {
            // 通过构建器得到配置，解耦
            CONFIG = builder.getConfiguration();
            // 启用|禁用 自动保存模式，可以自动保存配置不用每次调用save()方法，但是在有很多配置更新时，请谨慎使用此模式。这也将导致许多I / O操作
            // 原理：在后台，通过适用于所有配置对象的事件通知机制实现自动保存 。专用事件侦听器在构建器的托管配置对象中注册，该对象每次接收到更新事件时都会触发save（）方法
            builder.setAutoSave(false);
            //    ...
        } catch (ConfigurationException cex) {
            // loading of the configuration file failed
            log.error(cex.getMessage());
        }
    }


    /**
     * 获取properties单个字段
     * String backColor = config.getString("colors.background");
     * Integer width=config.getInt("window.width")
     * 根据关键字获取String类型参数propsConfig的默认分割符是','，换句话说，如果使用','分割，使用getString去取的话只能取到','前面的内容。
     *
     * @param key
     * @return
     */
    public static String getConfigParam(String key) {
        return CONFIG.getString(key, "");
    }

    /**
     * 获取properties数组
     * String[] colors = config.getStringArray("colors.pie");
     *
     * @param key
     * @return
     */
    public static String[] getConfigArray(String key) {
        return CONFIG.getStringArray(key);
    }

    /**
     * 获取properties列表
     * List<Object> colorList = config.getList("colors.pie");
     *
     * @param key
     * @return
     */
    public static List<Object> getConfigList(String key) {
        return CONFIG.getList(key);
    }

    /**
     * 新增配置字段
     *
     * @param key
     * @param value
     */
    public static void addConfigParam(String key, String value) {
        CONFIG.addProperty(key, value);
        try {
            builder.save();
        } catch (ConfigurationException cex) {
            log.error(cex.getMessage());
        }
    }

    /**
     * 更新配置字段
     *
     * @param key
     * @param value
     */
    public static void setConfigParam(String key, String value) {
        CONFIG.setProperty(key, value);
        try {
            builder.save();
        } catch (ConfigurationException cex) {
            log.error(cex.getMessage());
        }
    }


}
