package top.longmarch.sys.entity.vo;

import top.longmarch.core.utils.tree.Tree;

public class RoutesTree extends Tree<Long> {

    private String path;

    private String component;

    private String redirect;

    private String name;

    private boolean hidden;

    private RouteMeta meta;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RouteMeta getMeta() {
        return meta;
    }

    public void setMeta(RouteMeta meta) {
        this.meta = meta;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public String toString() {
        return "RoutesTrr{" +
                "path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", redirect='" + redirect + '\'' +
                ", name='" + name + '\'' +
                ", hidden='" + hidden + '\'' +
                ", meta=" + meta +
                '}';
    }
}
