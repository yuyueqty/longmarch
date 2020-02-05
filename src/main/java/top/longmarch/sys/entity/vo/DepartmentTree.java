package top.longmarch.sys.entity.vo;

import top.longmarch.core.utils.tree.Tree;

public class DepartmentTree extends Tree<Long> {

    private String depName;
    private Integer userCount;
    private Integer sort;

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
