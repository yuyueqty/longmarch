package top.longmarch.core.enums;

import java.util.Objects;

public enum StatusEnum {

    NO(1), YES(0);
    private final Integer value;

    StatusEnum(final Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static boolean YES(Integer value) {
        return Objects.nonNull(value) && YES.getValue() == value;
    }

}
