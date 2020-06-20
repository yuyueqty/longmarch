package top.longmarch.wx.service;

import top.longmarch.wx.entity.GzhFenweiTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公众号粉丝分维解析标签 服务类
 * </p>
 *
 * @author YuYue
 * @since 2020-04-19
 */
public interface IGzhFenweiTagService extends IService<GzhFenweiTag> {

    List<GzhFenweiTag> getGzhFenweiTagList(String openId, Long gzhId);

    List<Map<String, Object>> getFenweiTagList();

    List<GzhFenweiTag> getList(GzhFenweiTag gzhFenweiTag);
}
