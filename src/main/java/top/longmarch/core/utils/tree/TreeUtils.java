package top.longmarch.core.utils.tree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TreeUtils {

    /**
     * ====================================================================
     *函 数 名： @param dataList
     *函 数 名： @return
     *功 能： list结构转tree结构
     *      被转对象需要集成T类
    ----------------------------------------------------------------------
     *修改记录 ：
     *日 期  版本 修改人 修改内容
     *2018年7月10日 v0.0.1 yuyue 创建
    ====================================================================
     */
    @SuppressWarnings("unchecked")
    public static <T extends BaseTree> List<T> formatTree(List<T> dataList) {
        try {
            if (dataList != null) {
                List<T> treeData = new ArrayList<T>();
                LinkedHashMap<Long, T> map = new LinkedHashMap<Long, T>();
                for (T tree : dataList) {
                    map.put(tree.getId(), tree);
                }
                for (Long id : map.keySet()) {
                    T tree = map.get(id);
                    Long pid = tree.getPid();
                    if (pid == null || pid == 0L || pid == -1) {
                        treeData.add(tree);
                    } else {
                        T parentData = map.get(pid);
                        if (null == parentData) {
                            treeData.add(tree);
                        } else {
                            List<T> childrenList = (List<T>) parentData.getChildren();
                            if (childrenList == null) {
                                childrenList = new ArrayList<T>();
                            }
                            childrenList.add(tree);
                            parentData.setChildren((List<BaseTree>) childrenList);
                        }
                    }
                }
                return treeData;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
