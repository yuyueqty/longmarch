package top.longmarch.core.utils.tree;

import java.io.Serializable;
import java.util.List;

public class Tree<T> implements Serializable {

    private static final long serialVersionUID = -9189631785452440402L;

    /**
     * 节点ID
     */
    private T id;
    /**
     * 父节点ID
     */
    private T pid;
    /**
     * 子节点集合
     */
    private List<Tree<T>> children;

    public Tree() {
    }

    public Tree(T id, T pid) {
        this.id = id;
        this.pid = pid;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public T getPid() {
        return pid;
    }

    public void setPid(T pid) {
        this.pid = pid;
    }

    public List<Tree<T>> getChildren() {
        return children;
    }

    public void setChildren(List<Tree<T>> children) {
        this.children = children;
    }
}