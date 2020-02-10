package top.longmarch.sys.entity.vo;

import top.longmarch.core.utils.tree.Tree;

public class DepartmentTree extends Tree<Long> {

    private String pids;
    private String deptName;
    private Integer userCount;
    private Integer sort;

    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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
