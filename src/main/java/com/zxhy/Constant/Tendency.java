package com.zxhy.Constant;

public enum Tendency {
    Break("上升"),
    High("盘整"),
    Back("下跌");

    // 成员变量
    private String name;

    // 构造方法
    Tendency(String name) {
        this.name = name;
    }
    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
