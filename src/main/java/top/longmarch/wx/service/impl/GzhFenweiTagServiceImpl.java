package top.longmarch.wx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.wx.entity.GzhFenweiTag;
import top.longmarch.wx.dao.GzhFenweiTagDao;
import top.longmarch.wx.service.IGzhFenweiTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 公众号粉丝分维解析标签 服务实现类
 * </p>
 *
 * @author YuYue
 * @since 2020-04-19
 */
@Service
public class GzhFenweiTagServiceImpl extends ServiceImpl<GzhFenweiTagDao, GzhFenweiTag> implements IGzhFenweiTagService {

    @Override
    public List<GzhFenweiTag> getGzhFenweiTagList(String openId, Long gzhId) {
        List<GzhFenweiTag> gzhFenweiTagList = this.list(new QueryWrapper<GzhFenweiTag>()
                .select("distinct `name`, score, content")
                .eq("create_by", UserUtil.getUserId())
                .eq("gzh_id", gzhId)
                .eq("open_id", openId)
                .groupBy("name"));
        return gzhFenweiTagList;
    }
}
