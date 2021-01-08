package top.longmarch.sys.entity.vo;

import lombok.Data;
import top.longmarch.core.utils.tree.Tree;

@Data
public class DepartmentTree extends Tree<Long> {

    private static final long serialVersionUID = -9189631785455640402L;

    private String pids;

    private String deptName;

    private Integer userCount;

    private Integer sort;

}
