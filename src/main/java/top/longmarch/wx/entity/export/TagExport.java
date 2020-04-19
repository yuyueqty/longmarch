package top.longmarch.wx.entity.export;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

@Data
@ExcelTarget("tagExport")
public class TagExport {

    @Excel(name = "标签名称")
    private String name;

    @Excel(name = "标签描述")
    private String content;

    @Excel(name = "等级")
    private String rank;

    @Excel(name = "评分")
    private Integer score;

}
