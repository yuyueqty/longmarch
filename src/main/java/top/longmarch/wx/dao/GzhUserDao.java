package top.longmarch.wx.dao;

import com.baomidou.mybatisplus.annotation.SqlParser;
import top.longmarch.wx.entity.GzhUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.longmarch.wx.entity.WxParams;
import top.longmarch.wx.entity.export.SearchDTO;
import top.longmarch.wx.entity.export.UserExportV2;
import top.longmarch.wx.entity.export.UserTagExport;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 粉丝表 Mapper 接口
 * </p>
 *
 * @author YuYue
 * @since 2020-04-18
 */
public interface GzhUserDao extends BaseMapper<GzhUser> {

    List<UserExportV2> selectUserAndTags(SearchDTO searchDTO);

    @SqlParser(filter=true)
    List<GzhUser> getUserNewTags(WxParams param);
}
