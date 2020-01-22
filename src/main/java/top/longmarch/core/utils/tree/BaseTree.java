package top.longmarch.core.utils.tree;

import java.io.Serializable;
import java.util.List;


public class BaseTree implements Serializable{  
  
    private static final long serialVersionUID = -9189631784252440402L;  
    // 节点id  
    public Long id;
    // 节点父id
    public Long pid;
    // 节点名称 
    private String pname;
    // 是否选择
    private Boolean checked;
    // 子节点集合
    public List<BaseTree> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<BaseTree> getChildren() {
        return children;
    }

    public void setChildren(List<BaseTree> children) {
        this.children = children;
    }
    
}  