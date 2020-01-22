package top.longmarch.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import top.longmarch.enums.StatusEnum;
import top.longmarch.sys.entity.Dictionary;
import top.longmarch.sys.dao.DictionaryDao;
import top.longmarch.sys.entity.vo.DictionaryVO;
import top.longmarch.sys.service.IDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 字典信息 服务实现类
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
@CacheConfig(cacheNames = {"DictionaryService"})
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryDao, Dictionary> implements IDictionaryService {


    @Cacheable(key = "'all_dictionary'")
    @Override
    public Map<String, List<DictionaryVO>> getAllDict() {
        List<Dictionary> dictionaryList = this.list(new LambdaQueryWrapper<Dictionary>()
                .eq(Dictionary::getStatus, StatusEnum.YES.getValue())
                .orderByDesc(Dictionary::getSort));
        Map<String, List<DictionaryVO>> dictMap = new HashMap<>();
        dictionaryList.stream().collect(Collectors.groupingBy(Dictionary::getCode)).forEach((k, dictionaries) -> {
            List<DictionaryVO> v = dictionaries.stream().map(d -> {
                Map<String, Object> map = new HashMap<>();
                map.put("label", d.getLabel());
                map.put("value", d.getValue());
                return new DictionaryVO(d.getLabel(), Integer.valueOf(d.getValue()));
            }).collect(Collectors.toList());
            dictMap.put(k, v);
        });
        return dictMap;
    }

    @CacheEvict(key = "'all_dictionary'")
    @Override
    public void saveDictionary(Dictionary dictionary) {
        this.save(dictionary);
    }

    @CacheEvict(key = "'all_dictionary'")
    @Override
    public void updateDictionary(Dictionary dictionary) {
        this.updateById(dictionary);
    }

    @CacheEvict(key = "'all_dictionary'")
    @Override
    public void removeDictionary(List<Long> dictionaryIds) {
        this.removeByIds(dictionaryIds);
    }

}
