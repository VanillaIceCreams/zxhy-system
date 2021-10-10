package com.zxhy.Constant;

public enum SellPoint {
    PercentBack("百分比止损", 10),
    Incident("突发事件止损", 8),
    Boll("技术性止损", 0),
    ProfitBack("回撤止损", 35);

    // 成员变量
    private String name;
    private Integer unknow;

    // 构造方法
    SellPoint(String name, int unknow) {
        this.name = name;
        this.unknow = unknow;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUnknow() {
        return unknow;
    }

    public void Integer(Integer unknow) {
        this.unknow = unknow;
    }
}
