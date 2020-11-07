package top.longmarch.sys.entity.vo;

import lombok.Data;
import top.longmarch.core.utils.tree.Tree;

@Data
public class RoutesTree extends Tree<Long> {

    private String path;

    private String component;

    private String redirect;

    private String name;

    private boolean hidden;

    private RouteMeta meta;

}
