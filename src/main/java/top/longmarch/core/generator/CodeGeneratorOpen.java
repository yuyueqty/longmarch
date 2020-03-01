package top.longmarch.core.generator;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import top.longmarch.sys.entity.Generator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeGeneratorOpen {


    public static void main(String[] args) throws SQLException {
        String moduleName = "test";
        String tableName = "test_member";
        CodeGeneratorOpen codeGeneratorOpen = new CodeGeneratorOpen();
        List<Entity> entityList = Db.use().findAll(Entity.create("sys_generator").set("table_name", tableName));
        List<Generator> generatorList = codeGeneratorOpen.getGenerator(entityList);
        codeGeneratorOpen.setProjectPath("D:/" + tableName);
        codeGeneratorOpen.buildAutoGenerator(moduleName, tableName, generatorList).execute();
    }

    private String projectPath;

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public AutoGenerator buildAutoGenerator(String moduleName, String tableName, List<Generator> fieldGenerationConditionList) {
        AutoGenerator autoGenerator = new AutoGenerator();
        GlobalConfig globalConfig = buildGlobalConfig();
        TemplateConfig templateConfig = buildTemplateConfig();
        DataSourceConfig dataSourceConfig = buildDataSourceConfig();
        PackageConfig packageConfig = buildPackageConfig(moduleName);
        InjectionConfig injectionConfig = buildInjectionConfig(packageConfig, tableName);
        StrategyConfig strategy = buildStrategyConfig(packageConfig, tableName);

        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setTemplate(templateConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setPackageInfo(packageConfig);
        autoGenerator.setCfg(injectionConfig);
        autoGenerator.setStrategy(strategy);
        autoGenerator.setTemplateEngine(new LmFreemarkerTemplateEngine(fieldGenerationConditionList));

        return autoGenerator;
    }

    private TemplateConfig buildTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController("/generator/controller.java");
        return templateConfig;
    }

    private StrategyConfig buildStrategyConfig(PackageConfig packageConfig, String... tableNames) {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        strategy.setLogicDeleteFieldName("is_delete");
        strategy.setTableFillList(Arrays.asList(
                new TableFill("create_by", FieldFill.INSERT),
                new TableFill("create_time", FieldFill.INSERT),
                new TableFill("update_by", FieldFill.UPDATE),
                new TableFill("update_time", FieldFill.UPDATE)));
        strategy.setRestControllerStyle(true);
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setInclude(tableNames);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(packageConfig.getModuleName() + "_");
        return strategy;
    }

    private InjectionConfig buildInjectionConfig(PackageConfig packageConfig, String tableName) {
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        List<FileOutConfig> fileOutConfigList = new ArrayList<>();
        fileOutConfigList.add(new FileOutConfig("/generator/index.vue.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format("%s/page/index.vue", projectPath);
            }
        });
        fileOutConfigList.add(new FileOutConfig("/generator/api.js.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format("%s/page/%sApi.js", projectPath, tableInfo.getEntityName());
            }
        });
        fileOutConfigList.add(new FileOutConfig("/generator/lang_zh.js.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format("%s/page/lang_zh.js", projectPath);
            }
        });
        fileOutConfigList.add(new FileOutConfig("/generator/lang_en.js.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format("%s/page/lang_en.js", projectPath);
            }
        });
        fileOutConfigList.add(new FileOutConfig("/generator/longmarch.sql.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format("%s/sql/%s.sql", projectPath, tableName);
            }
        });
        injectionConfig.setFileOutConfigList(fileOutConfigList);
        return injectionConfig;
    }

    private PackageConfig buildPackageConfig(String moduleName) {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(moduleName);
        packageConfig.setParent("top.longmarch");
        packageConfig.setMapper("dao");
        return packageConfig;
    }

    private DataSourceConfig buildDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://122.51.244.159:3306/longmarch?useUnicode=true&characterEncoding=utf8&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("longmarch");
        dataSourceConfig.setPassword("pfbNKZmkcZ7CDhSf");
        return dataSourceConfig;
    }

    private GlobalConfig buildGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(String.format("%s/java", projectPath));
        globalConfig.setAuthor("YuYue");
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(true);
        globalConfig.setSwagger2(true);
        globalConfig.setMapperName("%sDao");
        globalConfig.setDateType(DateType.ONLY_DATE);
        return globalConfig;
    }

    public List<Generator> getGenerator(List<Entity> entityList) {
        List<Generator> generatorList = new ArrayList<>();
        for (Entity entity : entityList) {
            Generator generator = new Generator();
            for (String fieldName : entity.getFieldNames()) {
                if ("id".equals(fieldName)) {
                    generator.setId(entity.getLong(fieldName));
                }
                if ("table_name".equals(fieldName)) {
                    generator.setTableName(entity.getStr(fieldName));
                }
                if ("column_name".equals(fieldName)) {
                    generator.setColumnName(entity.getStr(fieldName));
                }
                if ("column_type".equals(fieldName)) {
                    generator.setColumnType(entity.getStr(fieldName));
                }
                if ("property_name".equals(fieldName)) {
                    generator.setPropertyName(entity.getStr(fieldName));
                }
                if ("remark".equals(fieldName)) {
                    generator.setRemark(entity.getStr(fieldName));
                }
                if ("not_null".equals(fieldName)) {
                    generator.setNotNull(entity.getBool(fieldName));
                }
                if ("list_show".equals(fieldName)) {
                    generator.setListShow(entity.getBool(fieldName));
                }
                if ("form_show".equals(fieldName)) {
                    generator.setFormShow(entity.getBool(fieldName));
                }
                if ("form_type".equals(fieldName)) {
                    generator.setFormType(entity.getStr(fieldName));
                }
                if ("query_type".equals(fieldName)) {
                    generator.setQueryType(entity.getStr(fieldName));
                }
                if ("order_by".equals(fieldName)) {
                    generator.setOrderBy(entity.getBool(fieldName));
                }
                if ("parameter".equals(fieldName)) {
                    generator.setParameter(entity.getBool(fieldName));
                }
                if ("default_value".equals(fieldName)) {
                    generator.setDefaultValue(entity.getStr(fieldName));
                }
                if ("dict_code".equals(fieldName)) {
                    generator.setDictCode(entity.getStr(fieldName));
                }
            }
            generatorList.add(generator);
        }
        return generatorList;
    }

}
