package top.longmarch.wx.entity.export;

import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.util.List;

@Data
@ExcelTarget("userTagExport")
public class UserTagExport {

    @ExcelEntity(name = "微信用户信息", show = true)
    private UserExport userExport;
    @ExcelCollection(name = "分维标签", orderNum = "4")
    private List<TagExport> tagExportList;

}
