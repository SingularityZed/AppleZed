package com.zed.admin.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * MybatisPlusConfig
 *
 * @Author: wang_ycong(Tel : 16602526966)
 * @Date: 2019/12/13 11:19
 */
@Configuration
public class MybatisPlusConfig  {

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
