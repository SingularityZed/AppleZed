package com.zed.admin.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlusConfig
 *
 * <p>
 * sql性能分析插件3.2就移除了
 * </p>
 *
 * @Author: zed
 * @Date: 2019/12/13 11:19
 */
@Configuration
@MapperScan("com.zed.admin.system.mapper*")
public class MybatisPlusConfig {

    /**
     * 分页插件,自动识别数据库类型
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 开启 count 的 join 优化,只针对 left join !!!
        return paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
    }

}
