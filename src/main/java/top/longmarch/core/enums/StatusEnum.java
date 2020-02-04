package top.longmarch.core.enums;

public enum StatusEnum {

    NO(0), YES(1);
    private final Integer value;

    StatusEnum(final Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
