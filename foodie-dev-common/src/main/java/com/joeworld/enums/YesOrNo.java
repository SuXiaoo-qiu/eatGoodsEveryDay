package com.joeworld.enums;

/**
 * 是否 枚举
 */
public enum YesOrNo {
    woman(0, "否"),
    man(1, "是"),
    no(0, "否"),
    yes(1, "是");

    public final Integer type;
    public final String value;
    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
