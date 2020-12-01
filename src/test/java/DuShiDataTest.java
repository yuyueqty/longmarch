import java.util.regex.Pattern;

public class DuShiDataTest {


    public static void main(String[] args) {
        String tablename = "ods_crm.crm_ser_comment";
        Integer count = 325710;

        StringBuffer sb = new StringBuffer();
        String sql = sql();
        String[] split = sql.split("\n");

        sb.append("SELECT \n");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            boolean matches = Pattern.matches(".+(varchar|char|datetime).+", s);
            String field = s.replaceAll(".+(`.+`).+", "$1");
            if (matches) {
                sb.append("  CONCAT(COUNT(if(")
                        .append(field)
                        .append(" is not null and TRIM(")
                        .append(field)
                        .append(") != '', TRUE, NULL))/")
                        .append(count)
                        .append(", ',') AS ")
                        .append(field);
            } else {
                sb.append("  CONCAT(COUNT(")
                        .append(field)
                        .append(")/")
                        .append(count)
                        .append(", ',') AS ")
                        .append(field);
            }
            if (split.length != (i+1)) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("FROM ").append(tablename).append(";\n");
        System.out.println(sb.toString());

    }


    public static String sql() {
        return "  `Id` bigint(20) NOT NULL,\n" +
                "  `CopId` int(11) NULL DEFAULT NULL,\n" +
                "  `BrandId` int(11) NULL DEFAULT NULL,\n" +
                "  `VipId` bigint(20) NULL DEFAULT NULL,\n" +
                "  `SaleNo` varchar(26) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,\n" +
                "  `SaleId` bigint(20) NULL DEFAULT NULL COMMENT '销售单编号',\n" +
                "  `SaleShopId` int(11) NULL DEFAULT NULL,\n" +
                "  `ServiceChannel` int(11) NULL DEFAULT NULL,\n" +
                "  `ServiceSaler` int(11) NULL DEFAULT NULL,\n" +
                "  `SalerId` int(11) NULL DEFAULT NULL COMMENT '销售导购员',\n" +
                "  `CommtTime` datetime(0) NULL DEFAULT NULL,\n" +
                "  `CommtScore1` smallint(6) NULL DEFAULT NULL,\n" +
                "  `CommtScore2` smallint(6) NULL DEFAULT NULL,\n" +
                "  `CommtScore3` smallint(6) NULL DEFAULT NULL,\n" +
                "  `TotalScore` decimal(4, 1) NULL DEFAULT NULL,\n" +
                "  `Content` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,\n" +
                "  `CommtReplyUserId` int(11) NULL DEFAULT NULL,\n" +
                "  `CommtReplyTime` datetime(0) NULL DEFAULT NULL,\n" +
                "  `CommtReply` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,\n" +
                "  `IsReply` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,\n" +
                "  `CmmtSecondTags1` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '一级评价维度1下的二级维度标签组',\n" +
                "  `CmmtSecondTags2` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '一级评价维度2下的二级维度标签组',\n" +
                "  `CmmtSecondTags3` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '一级评价维度3下的二级维度标签组',\n" +
                "  `CmmtTotalSecondTags` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '一级评价总体评价下的二级维度标签组',\n" +
                "  `IsCmmtHistory` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否历史数据 0历史数据，不参与好/差评率的计算  1新数据参与好/差评率计算',\n" +
                "  `CmmtResultType` int(11) NULL DEFAULT 0 COMMENT '0不参与好评/差评   1好评  2差评',";
    }

}
