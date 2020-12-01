package top.longmarch.core.generator;

import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import org.springframework.beans.BeanUtils;

import java.util.Map;

public class GeneratorConfig {

    private Map<String, Object> conf;


    public GlobalConfig getGlobalConfig() {
        GlobalConfig config = new GlobalConfig();
        BeanUtils.copyProperties(conf, config);
        return config;
    }

    public TemplateConfig getTemplateConfig() {
        TemplateConfig config = new TemplateConfig();
        BeanUtils.copyProperties(conf, config);
        return config;
    }

    public DataSourceConfig getDataSourceConfig() {
        DataSourceConfig config = new DataSourceConfig();
        BeanUtils.copyProperties(conf, config);
        return config;
    }

    public PackageConfig getPackageConfig() {
        PackageConfig config = new PackageConfig();
        BeanUtils.copyProperties(conf, config);
        return config;
    }

    public InjectionConfig getInjectionConfig() {
        InjectionConfig config = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        return config;
    }

    public StrategyConfig getStrategyConfig() {
        StrategyConfig config = new StrategyConfig();
        BeanUtils.copyProperties(conf, config);
        return config;
    }
}
