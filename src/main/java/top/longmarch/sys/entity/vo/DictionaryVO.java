package top.longmarch.sys.entity.vo;

import java.io.Serializable;

public class DictionaryVO implements Serializable {

    private String label;
    private Object value;

    public DictionaryVO() {
    }

    public DictionaryVO(String label, Object value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
