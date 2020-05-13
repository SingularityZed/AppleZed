package com.zed.generator.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.template.*;
import com.zed.common.utils.StringTools;
import com.zed.generator.config.GenConfig;
import com.zed.generator.info.ColumnInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器工具类
 *
 * @author zed
 */
@Slf4j
public class GenUtils {

    private static final String TIMESTAMP = "Timestamp";

    private static final String BIG_DECIMAL = "BigDecimal";

    private static final String PK = "PRI";

    private static final String EXTRA = "auto_increment";


    private static final String FILE_CONTROLLER = "Controller.java";
    private static final String FILE_MANAGER = "Manager.java";
    private static final String FILE_SERVICE = "Service.java";
    private static final String FILE_SERVICE_IMPL = "ServiceImpl.java";
    private static final String FILE_ENTITY = ".java";
    private static final String FILE_MAPPER = "Mapper.java";
    private static final String FILE_MAPPER_XML = "Mapper.xml";
    private static final String FILE_INFO = "Info.java";
    private static final String FILE_DTO = "DTO.java";
    private static final String FILE_VO = "VO.java";
    private static final String FILE_DETAIL_VO = "DetailVO.java";
    private static final String FILE_QUERY_AO = "QueryAO.java";
    private static final String FILE_ADD_AO = "AddAO.java";
    private static final String FILE_UPDATE_AO = "UpdateAO.java";
    private static final String FILE_DELETE_AO = "DeleteAO.java";
    private static final String FILE_VERIFY = "Verify.java";
    private static final String FILE_ENUM = "Enum.java";

    private static final String FILE_NAME_CONTROLLER = "Controller";
    private static final String FILE_NAME_MANAGER = "Manager";
    private static final String FILE_NAME_SERVICE = "Service";
    private static final String FILE_NAME_SERVICE_IMPL = "ServiceImpl";
    private static final String FILE_NAME_ENTITY = "Entity";
    private static final String FILE_NAME_MAPPER = "Mapper";
    private static final String FILE_NAME_MAPPER_XML = "MapperXML";
    private static final String FILE_NAME_INFO = "Info";
    private static final String FILE_NAME_DTO = "DTO";
    private static final String FILE_NAME_VO = "VO";
    private static final String FILE_NAME_DETAIL_VO = "DetailVO";
    private static final String FILE_NAME_QUERY_AO = "QueryAO";
    private static final String FILE_NAME_ADD_AO = "AddAO";
    private static final String FILE_NAME_UPDATE_AO = "UpdateAO";
    private static final String FILE_NAME_DELETE_AO = "DeleteAO";
    private static final String FILE_NAME_VERIFY = "Verify";
    private static final String FILE_NAME_ENUM = "Enum";

    /**
     * 获取后端代码模板名称
     *
     * @return List
     */
    private static List<String> getAdminTemplateNames() {
        List<String> adminTemplateNames = new ArrayList<>();
        adminTemplateNames.add(FILE_NAME_CONTROLLER);
        adminTemplateNames.add(FILE_NAME_MANAGER);
        adminTemplateNames.add(FILE_NAME_SERVICE);
        adminTemplateNames.add(FILE_NAME_SERVICE_IMPL);
        adminTemplateNames.add(FILE_NAME_ENTITY);
        adminTemplateNames.add(FILE_NAME_MAPPER);
        adminTemplateNames.add(FILE_NAME_MAPPER_XML);
        adminTemplateNames.add(FILE_NAME_INFO);
        adminTemplateNames.add(FILE_NAME_DTO);
        adminTemplateNames.add(FILE_NAME_VO);
        adminTemplateNames.add(FILE_NAME_QUERY_AO);
        adminTemplateNames.add(FILE_NAME_ADD_AO);
        adminTemplateNames.add(FILE_NAME_UPDATE_AO);
        adminTemplateNames.add(FILE_NAME_DELETE_AO);
        adminTemplateNames.add(FILE_NAME_VERIFY);
        adminTemplateNames.add(FILE_NAME_ENUM);
        return adminTemplateNames;
    }

    /**
     * 获取前端代码模板名称
     *
     * @return List
     */
    private static List<String> getFrontTemplateNames() {
        List<String> frontTemplateNames = new ArrayList<>();
        frontTemplateNames.add("api");
        frontTemplateNames.add("index");
        frontTemplateNames.add("eForm");
        return frontTemplateNames;
    }

    /**
     * 生成代码
     *
     * @param columnInfos 表元数据
     * @param genConfig   生成代码的参数配置，如包路径，作者
     */
    public static void generatorCode(GenConfig genConfig, String tableName, List<ColumnInfo> columnInfos) throws IOException {
        Map<String, Object> map = new HashMap<>(64);
        map.put("package", genConfig.getPackagePath());
        map.put("moduleName", genConfig.getModuleName());
        map.put("author", genConfig.getAuthor());
        map.put("date", LocalDate.now().toString());
        map.put("tableName", tableName);
        String className = StringTools.toCapitalizeCamelCase(tableName);
        String changeClassName = StringTools.toCamelCase(tableName);

        // 判断是否去除表前缀
        if (StringUtils.isNotEmpty(genConfig.getTablePrefix())) {
            className = StringTools.toCapitalizeCamelCase(StrUtil.removePrefix(tableName, genConfig.getTablePrefix()));
            changeClassName = StringTools.toCamelCase(StrUtil.removePrefix(tableName, genConfig.getTablePrefix()));
        }
        map.put("className", className);
        map.put("upperCaseClassName", className.toUpperCase());
        map.put("changeClassName", changeClassName);
        map.put("hasTimestamp", false);
        map.put("queryHasTimestamp", false);
        map.put("queryHasBigDecimal", false);
        map.put("hasBigDecimal", false);
        map.put("hasQuery", false);
        map.put("auto", false);

        List<Map<String, Object>> columns = new ArrayList<>();
        List<Map<String, Object>> queryColumns = new ArrayList<>();
        for (ColumnInfo column : columnInfos) {
            Map<String, Object> listMap = new HashMap<>();
            listMap.put("columnComment", column.getColumnComment());
            listMap.put("columnKey", column.getColumnKey());

            String colType = PropertiesUtils.cloToJava(column.getColumnType().toString());
            String changeColumnName = StringTools.toCamelCase(column.getColumnName().toString());
            String capitalColumnName = StringTools.toCapitalizeCamelCase(column.getColumnName().toString());
            if (PK.equals(column.getColumnKey())) {
                map.put("pkColumnType", colType);
                map.put("pkChangeColName", changeColumnName);
                map.put("pkCapitalColName", capitalColumnName);
            }
            if (TIMESTAMP.equals(colType)) {
                map.put("hasTimestamp", true);
            }
            if (BIG_DECIMAL.equals(colType)) {
                map.put("hasBigDecimal", true);
            }
            if (EXTRA.equals(column.getExtra())) {
                map.put("auto", true);
            }
            listMap.put("columnType", colType);
            listMap.put("columnName", column.getColumnName());
            listMap.put("isNullable", column.getIsNullable());
            listMap.put("columnShow", column.getColumnShow());
            listMap.put("changeColumnName", changeColumnName);
            listMap.put("capitalColumnName", capitalColumnName);

            // 判断是否有查询，如有则把查询的字段set进columnQuery
            if (!StringUtils.isBlank(column.getColumnQuery())) {
                listMap.put("columnQuery", column.getColumnQuery());
                map.put("hasQuery", true);
                if (TIMESTAMP.equals(colType)) {
                    map.put("queryHasTimestamp", true);
                }
                if (BIG_DECIMAL.equals(colType)) {
                    map.put("queryHasBigDecimal", true);
                }
                queryColumns.add(listMap);
            }
            columns.add(listMap);
        }
        map.put("columns", columns);
        map.put("queryColumns", queryColumns);
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.CLASSPATH));

        // 生成后端代码
        List<String> templates = getAdminTemplateNames();
        for (String templateName : templates) {
            Template template = engine.getTemplate("generator/admin/" + templateName + ".ftl");
            String filePath = getAdminFilePath(templateName, genConfig, className);

            assert filePath != null;
            File file = new File(filePath);

            // 如果非覆盖生成
            if (!genConfig.getIsCover() && FileUtil.exist(file)) {
                continue;
            }
            // 生成代码
            genFile(file, template, map);
        }

        // 生成前端代码
//        templates = getFrontTemplateNames();
//        for (String templateName : templates) {
//            Template template = engine.getTemplate("generator/front/" + templateName + ".ftl");
//            String filePath = getFrontFilePath(templateName, genConfig, map.get("changeClassName").toString());
//
//            assert filePath != null;
//            File file = new File(filePath);
//
//            // 如果非覆盖生成
//            if (!genConfig.getIsCover() && FileUtil.exist(file)) {
//                continue;
//            }
//            // 生成代码
//            genFile(file, template, map);
//        }
    }

    /**
     * 定义后端文件路径以及名称
     */
    private static String getAdminFilePath(String templateName, GenConfig genConfig, String className) {
        String projectPath = System.getProperty("user.dir") + File.separator + genConfig.getModuleName();
        String packagePath = projectPath + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
        if (!ObjectUtils.isEmpty(genConfig.getPackagePath())) {
            packagePath += genConfig.getPackagePath().replace(".", File.separator) + File.separator;
        }

        if (FILE_NAME_CONTROLLER.equals(templateName)) {
            return packagePath + "controller" + File.separator + className + FILE_CONTROLLER;
        }
        if (FILE_NAME_MANAGER.equals(templateName)) {
            return packagePath + "manager" + File.separator + className + FILE_MANAGER;
        }
        if (FILE_NAME_SERVICE.equals(templateName)) {
            return packagePath + "service" + File.separator + className + FILE_SERVICE;
        }
        if (FILE_NAME_SERVICE_IMPL.equals(templateName)) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + FILE_SERVICE_IMPL;
        }
        if (FILE_NAME_ENTITY.equals(templateName)) {
            return packagePath + "entity" + File.separator + className + FILE_ENTITY;
        }
        if (FILE_NAME_MAPPER.equals(templateName)) {
            return packagePath + "mapper" + File.separator + className + FILE_MAPPER;
        }
        if (FILE_NAME_MAPPER_XML.equals(templateName)) {
            return packagePath + "mapper" + File.separator + "xml" + File.separator + className + FILE_MAPPER_XML;
        }
        if (FILE_NAME_INFO.equals(templateName)) {
            return packagePath + "info" + File.separator + className + FILE_INFO;
        }
        if (FILE_NAME_DTO.equals(templateName)) {
            return packagePath + "pojo" + File.separator + "dto" + File.separator + className + FILE_DTO;
        }
        if (FILE_NAME_VO.equals(templateName)) {
            return packagePath + "pojo" + File.separator + "vo" + File.separator + className + FILE_VO;
        }
        if (FILE_NAME_DETAIL_VO.equals(templateName)) {
            return packagePath + "pojo" + File.separator + "vo" + File.separator + className + FILE_DETAIL_VO;
        }
        if (FILE_NAME_QUERY_AO.equals(templateName)) {
            return packagePath + "pojo" + File.separator + "ao" + File.separator + className + FILE_QUERY_AO;
        }
        if (FILE_NAME_ADD_AO.equals(templateName)) {
            return packagePath + "pojo" + File.separator + "ao" + File.separator + className + FILE_ADD_AO;
        }
        if (FILE_NAME_UPDATE_AO.equals(templateName)) {
            return packagePath + "pojo" + File.separator + "ao" + File.separator + className + FILE_UPDATE_AO;
        }
        if (FILE_NAME_DELETE_AO.equals(templateName)) {
            return packagePath + "pojo" + File.separator + "ao" + File.separator + className + FILE_DELETE_AO;
        }
        if (FILE_NAME_VERIFY.equals(templateName)) {
            return packagePath + "pojo" + File.separator + "verify" + File.separator + className + FILE_VERIFY;
        }
        if (FILE_NAME_ENUM.equals(templateName)) {
            return packagePath + "enums" + File.separator + className + FILE_ENUM;
        }

        return null;
    }

    /**
     * 定义前端文件路径以及名称
     */
    private static String getFrontFilePath(String templateName, GenConfig genConfig, String apiName) {
        String path = genConfig.getFrontPath();

        if ("api".equals(templateName)) {
            return genConfig.getApiPath() + File.separator + apiName + ".js";
        }

        if ("index".equals(templateName)) {
            return path + File.separator + "index.vue";
        }

        if ("eForm".equals(templateName)) {
            return path + File.separator + File.separator + "form.vue";
        }
        return null;
    }

    private static void genFile(File file, Template template, Map<String, Object> map) throws IOException {
        // 生成目标文件
        Writer writer = null;
        try {
            FileUtil.touch(file);
            writer = new FileWriter(file);
            template.render(map, writer);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            assert writer != null;
            writer.close();
        }
    }


}
