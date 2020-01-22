package top.longmarch.sys.service;

import top.longmarch.sys.entity.Dictionary;
import com.baomidou.mybatisplus.extension.service.IService;
import top.longmarch.sys.entity.vo.DictionaryVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典信息 服务类
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
public interface IDictionaryService extends IService<Dictionary> {

    Map<String, List<DictionaryVO>> getAllDict();

    void saveDictionary(Dictionary dictionary);

    void updateDictionary(Dictionary dictionary);

    void removeDictionary(List<Long> dictionaryIds);
}
