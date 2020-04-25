package top.longmarch;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import cn.afterturn.easypoi.util.PoiPublicUtil;

import java.util.Map;

public class MapImportHandler extends ExcelDataHandlerDefaultImpl<Map<String, Object>> {

        @Override
        public void setMapValue(Map<String, Object> map, String originKey, Object value) {
            if (value instanceof Double) {
                map.put(getRealKey(originKey), PoiPublicUtil.doubleToString((Double) value));
            } else {
                map.put(getRealKey(originKey), value != null ? value.toString() : null);
            }
        }

        private String getRealKey(String originKey) {
            if (originKey.equals("姓名")) {
                return "name";
            }
            if (originKey.equals("身份证")) {
                return "sfz";
            }
            if (originKey.equals("班主任寄语")) {
                return "content";
            }
            if (originKey.equals("学科")) {
                return "subjectName";
            }
            if (originKey.equals("学科成绩")) {
                return "subjectScore";
            }
            if (originKey.equals("子学科")) {
                return "childSubjectName";
            }
            if (originKey.equals("子学科成绩")) {
                return "childSubjectScore";
            }
            return originKey;
        }
    }