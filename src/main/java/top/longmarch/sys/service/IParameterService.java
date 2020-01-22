package top.longmarch.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.longmarch.sys.entity.Parameter;

/**
 * <p>
 * 平台参数表 服务类
 * </p>
 *
 * @author YuYue
 * @since 2020-01-14
 */
public interface IParameterService extends IService<Parameter> {

    Parameter getParameterByName(String paramValue);

    void updateParameter(Parameter parameter);

}
