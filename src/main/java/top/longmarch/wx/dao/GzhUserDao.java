package top.longmarch.wx.dao;

import top.longmarch.wx.entity.GzhUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.longmarch.wx.entity.export.SearchDTO;
import top.longmarch.wx.entity.export.UserExportV2;
import top.longmarch.wx.entity.export.UserTagExport;

import java.util.List;

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

}
