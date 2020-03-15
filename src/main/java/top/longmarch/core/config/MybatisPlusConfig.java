package top.longmarch.core.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.schema.Column;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.longmarch.core.common.Constant;
import top.longmarch.core.utils.UserUtil;

import java.util.ArrayList;
import java.util.List;


@Configuration
@MapperScan("top.longmarch.*.dao")
public class MybatisPlusConfig {

    private static final Logger logger = LoggerFactory.getLogger(MybatisPlusConfig.class);
    private static final String USER_ID = "create_by";
    private static List<String> tableList = new ArrayList<String>();

    static {
        tableList.add("cms_article");
        tableList.add("cms_category");
    }

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

    //    @Bean
    public PaginationInterceptor paginationInterceptor2() {
        logger.info("分页插件加载完成");
        return new PaginationInterceptor();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        logger.info("分页插件加载完成");
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        /*
         * 【测试多租户】 SQL 解析处理拦截器<br>
         * 这里固定写成住户 1 实际情况你可以从cookie读取，因此数据看不到 【 麻花藤 】 这条记录（ 注意观察 SQL ）<br>
         */
        List<ISqlParser> sqlParserList = new ArrayList<>();
        TenantSqlParser tenantSqlParser = new LMTenantParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            @Override
            public Expression getTenantId(boolean where) {
                //如果是where，可以追加多租户多个条件in，不是where的情况：比如当insert时，
                // 不能insert into user(name, tenant_id) values('test', tenant_id IN (1, 2));
                //自己判断是单个tenantId还是需要多个id in(1,2,3)
                final boolean multipleTenantIds = Constant.MORE_USER.equals(UserUtil.loginUser().getType());
                if (where && multipleTenantIds) {
                    //演示如何实现tenant_id in (1,2)
                    return multipleTenantIdCondition();
                } else {
                    //演示：tenant_id=1
                    return singleTenantIdCondition();
                }
            }

            private Expression singleTenantIdCondition() {
                if (Constant.ONE_USER.equals(UserUtil.loginUser().getType())) {
                    return new LongValue(UserUtil.loginUser().getId());
                }
                return new LongValue(0);//ID自己想办法获取到
            }

            private Expression multipleTenantIdCondition() {
                final InExpression inExpression = new InExpression();
                inExpression.setLeftExpression(new Column(getTenantIdColumn()));
                final ExpressionList itemsList = new ExpressionList();
                final List<Expression> inValues = new ArrayList<>(UserUtil.loginUser().getUserIdSet().size());
                if (Constant.MORE_USER.equals(UserUtil.loginUser().getType())) {
                    for (Long deptId : UserUtil.loginUser().getUserIdSet()) {
                        inValues.add(new LongValue(deptId));
                    }
                }
                itemsList.setExpressions(inValues);
                inExpression.setRightItemsList(itemsList);
                return inExpression;
            }

            @Override
            public String getTenantIdColumn() {
                return USER_ID;
            }

            @Override
            public boolean doTableFilter(String tableName) {
                // 这里可以判断是否过滤表
                if (tableList.contains(tableName) && !Constant.All_USER.equals(UserUtil.loginUser().getType())) {
                    return false;
                }
                return true;
            }
        });
        sqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }

}
