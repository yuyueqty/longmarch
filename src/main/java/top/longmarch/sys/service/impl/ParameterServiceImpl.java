package top.longmarch.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.longmarch.sys.dao.ParameterDao;
import top.longmarch.sys.entity.Parameter;
import top.longmarch.sys.service.IParameterService;

/**
 * <p>
 * 平台参数表 服务实现类
 * </p>
 *
 * @author YuYue
 * @since 2020-01-14
 */
@CacheConfig(cacheNames = {"ParameterService"})
@Service
public class ParameterServiceImpl extends ServiceImpl<ParameterDao, Parameter> implements IParameterService {

    @Cacheable(key = "'parameter_'+#paramName")
    @Override
    public Parameter getParameterByName(String paramName) {
        return this.getOne(new LambdaQueryWrapper<Parameter>().eq(Parameter::getParamName, paramName));
    }

    @CacheEvict(key = "'parameter_'+#parameter.paramName")
    @Override
    public void updateParameter(Parameter parameter) {
        this.updateById(parameter);
    }

}
