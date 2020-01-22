package top.longmarch.core.utils.tree;

import cn.hutool.core.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeUtil {

//    private static List childrenIdList = new ArrayList();

    /**
     * 获取指定节点和所有子节点集合
     *
     * @param dataList
     * @param pid
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T extends Tree, E> List<E> allChildrenIdList(List<T> dataList, E pid) {
        List childrenIdList = new ArrayList();
        if (CollectionUtil.isEmpty(dataList)) {
            return Arrays.asList();
        }

        childrenIdList(dataList, childrenIdList, pid);
        childrenIdList.add(pid);
        return childrenIdList;
    }

    /**
     * 获取指定节点的子节点集合
     *
     * @param dataList
     * @param pid
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T extends Tree, E> List<E> childrenIdList(List<T> dataList, List childrenIdList, E pid) {
        if (CollectionUtil.isEmpty(dataList)) {
            return Arrays.asList();
        }

        for (T t : dataList) {
            if (String.valueOf(t.getPid()).equals(String.valueOf(pid))) {
                childrenIdList(dataList, childrenIdList, t.getId());
                childrenIdList.add(t.getId());
            }
        }
        return childrenIdList;
    }

    /**
     * list 转 tree
     *
     * @param dataList
     * @param <T>
     * @return
     */
    public static <T extends Tree> List<T> list2Tree(List<T> dataList) {
        if (CollectionUtil.isEmpty(dataList)) {
            return Arrays.asList();
        }

        List<T> dataTree = new ArrayList<>();
        for (T t : dataList) {

            if (t.getPid() == null || "0".equals(String.valueOf(t.getPid())) || "-1".equals(String.valueOf(t.getPid()))) {
                dataTree.add(t);
            }

            for (T t1 : dataList) {
                if (t1.getPid() == t.getId()) {
                    if (CollectionUtil.isEmpty(t.getChildren())) {
                        t.setChildren(new ArrayList<>());
                    }
                    t.getChildren().add(t1);
                }
            }
        }
        return dataTree;
    }

}