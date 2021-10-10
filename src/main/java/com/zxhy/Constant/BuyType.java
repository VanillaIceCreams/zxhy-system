package com.zxhy.Constant;

public enum BuyType {
    AAA ("保本加仓"),
    BBB("实盈加仓"),
    CCC("释放额度加仓");


    // 成员变量
    private String name;

    // 构造方法
    BuyType(String name) {
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
