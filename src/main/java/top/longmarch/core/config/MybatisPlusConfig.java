package top.longmarch.core.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("top.longmarch.*.dao")
public class MybatisPlusConfig {

    private static final Logger logger = LoggerFactory.getLogger(MybatisPlusConfig.class);

    /**
     * SQL执行效率插件
     */
//    @Bean
//    @Profile({"dev","test"})// 设置 dev test 环境开启
//    public PerformanceInterceptor performanceInterceptor() {
//        return new PerformanceInterceptor();
//    }

    @Bean
    public AutoFillMetaObjectHandler autoFillMetaObjectHandler() {
        logger.info("自动填充插件加载完成");
        return new AutoFillMetaObjectHandler();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        logger.info("分页插件加载完成");
        return new PaginationInterceptor();
    }

}
