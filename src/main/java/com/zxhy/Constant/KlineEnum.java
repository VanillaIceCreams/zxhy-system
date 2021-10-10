package com.zxhy.Constant;

public enum KlineEnum {
    Week("week",240),
    Day("day",48),
    Hour("hour",12),
    ThirtyMinute("thirtyMinute",6),
    FifteenMinute("fifteenMinute",3),
    FiveMinute("fiveMinute",1);

    // 成员变量
    private String name;
    //相对于5分钟的倍数
    private int multiple;
    // 构造方法
    KlineEnum(String name, int index) {
        this.name = name;
        this.multiple = index;
    }

    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getMultiple() {
        return multiple;
    }
    public void setMultiple(int multiple) {
        this.multiple = multiple;
    }

}
