package top.longmarch;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import top.longmarch.wx.entity.export.TagExport;
import top.longmarch.wx.entity.export.UserExport;
import top.longmarch.wx.entity.export.UserTagExport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportTest {


    public static void main(String[] args) {
        List<UserTagExport> userTagExportList = new ArrayList<>();

        UserTagExport userTagExport = new UserTagExport();
        UserExport userExport = new UserExport();
        userExport.setOpenId("oG-Lc1E8kMH--SxsdhJzzcfrT73M");
        userExport.setNickname("暗•\uD83C\uDFC2");
        userExport.setSexDesc("男");
        userExport.setHeadImgUrl("http://thirdwx.qlogo.cn/mmopen/PiajxSqBRaELBWntdvOMrCPPto4jUHL8uo5uhUXEKrU9hljWC0PqJWwBfficL3B1YwWeBbAqhSiat3kVJsRTibGINQ/132");
        userExport.setCountry("中国");
        userExport.setProvince("上海");
        userExport.setCity("上海");
        userTagExport.setUserExport(userExport);

        List<TagExport> tagExportList = new ArrayList<>();
        TagExport tagExport = new TagExport();
        tagExport.setName("r2309");
        tagExport.setContent("抗压");
        tagExport.setRank("中");
        tagExport.setScore(50);
        tagExportList.add(tagExport);
        tagExport = new TagExport();
        tagExport.setName("tb100");
        tagExport.setContent("执行力");
        tagExport.setRank("中");
        tagExport.setScore(45);
        tagExportList.add(tagExport);
        userTagExport.setTagExportList(tagExportList);

        userTagExportList.add(userTagExport);


        userTagExport = new UserTagExport();
        userExport = new UserExport();
        userExport.setOpenId("oG-Lc1E8kMH--SxsdhJzzcfrT73M");
        userExport.setNickname("暗•\uD83C\uDFC2");
        userExport.setSexDesc("男");
        userExport.setHeadImgUrl("http://thirdwx.qlogo.cn/mmopen/PiajxSqBRaELBWntdvOMrCPPto4jUHL8uo5uhUXEKrU9hljWC0PqJWwBfficL3B1YwWeBbAqhSiat3kVJsRTibGINQ/132");
        userExport.setCountry("中国");
        userExport.setProvince("上海");
        userExport.setCity("上海");
        userTagExport.setUserExport(userExport);

        tagExportList = new ArrayList<>();
        tagExport = new TagExport();
        tagExport.setName("r2309");
        tagExport.setContent("抗压");
        tagExport.setRank("中");
        tagExport.setScore(50);
        tagExportList.add(tagExport);
        tagExport = new TagExport();
        tagExport.setName("tb100");
        tagExport.setContent("执行力");
        tagExport.setRank("中");
        tagExport.setScore(45);
        tagExportList.add(tagExport);
        userTagExport.setTagExportList(tagExportList);

        userTagExportList.add(userTagExport);

        try {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("微信用户分维标签", "用户标签"),
                    UserTagExport.class, userTagExportList);
            workbook.write(new FileOutputStream(new File("d:/users.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
