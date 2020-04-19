package top.longmarch.wx.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.wx.entity.GzhAccount;
import top.longmarch.wx.entity.GzhFenweiTag;
import top.longmarch.wx.entity.GzhUser;
import top.longmarch.wx.entity.export.TagExport;
import top.longmarch.wx.entity.export.UserExport;
import top.longmarch.wx.entity.export.UserTagExport;
import top.longmarch.wx.service.IGzhAccountService;
import top.longmarch.wx.service.IGzhFenweiTagService;
import top.longmarch.wx.service.IGzhUserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Api(value = "导出微信用户", tags = "导出微信用户")
@RestController
@RequestMapping("/wx/gzh-user")
public class ExportWxUserController {

    @Autowired
    private IGzhAccountService gzhAccountService;
    @Autowired
    private IGzhUserService gzhUserService;
    @Autowired
    private IGzhFenweiTagService gzhFenweiTagService;


    @ApiOperation(value = "同步微信用户信息")
    @GetMapping("/download.xls")
    public void export(HttpServletResponse response) {
        GzhAccount gzhAccount = gzhAccountService.getOne(new LambdaQueryWrapper<GzhAccount>()
                .eq(GzhAccount::getCreateBy, UserUtil.getUserId())
                .eq(GzhAccount::getDefaultAccount, 1));
        if (gzhAccount != null) {
            List<UserTagExport> userTagExportList = buildExportData(gzhAccount);
            try {
                Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("微信用户分维标签", "用户标签"),
                        UserTagExport.class, userTagExportList);
                // workbook.write(new FileOutputStream(new File("d:/users.xls")));
                workbook.write(response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<UserTagExport> buildExportData(GzhAccount gzhAccount) {
        List<UserTagExport> userTagExportList = new ArrayList<>();

        List<GzhUser> gzhUserList = gzhUserService.list(new LambdaQueryWrapper<GzhUser>()
                .eq(GzhUser::getGzhId, gzhAccount.getId()));

        for (GzhUser gzhUser : gzhUserList) {
            UserTagExport userTagExport = new UserTagExport();

            UserExport userExport = new UserExport();
            BeanUtils.copyProperties(gzhUser, userExport);
            userTagExport.setUserExport(userExport);

            List<GzhFenweiTag> gzhFenweiTagList = gzhFenweiTagService.list(new LambdaQueryWrapper<GzhFenweiTag>()
                    .eq(GzhFenweiTag::getGzhId, gzhAccount.getId())
                    .eq(GzhFenweiTag::getOpenId, gzhUser.getOpenId())
                    .eq(GzhFenweiTag::getFieldId, gzhAccount.getFwField()));

            List<TagExport> tagExportList = new ArrayList<>();
            for (GzhFenweiTag gzhFenweiTag : gzhFenweiTagList) {
                TagExport tagExport = new TagExport();
                BeanUtils.copyProperties(gzhFenweiTag, tagExport);
                tagExportList.add(tagExport);
            }
            userTagExport.setTagExportList(tagExportList);

            userTagExportList.add(userTagExport);
        }

        return userTagExportList;
    }


}
