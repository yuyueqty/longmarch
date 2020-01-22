package top.longmarch.sys.entity.vo;

import java.io.Serializable;

public class DictionaryVO implements Serializable {

    private String label;
    private Integer value;

    public DictionaryVO() {
    }

    public DictionaryVO(String label, Integer value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
