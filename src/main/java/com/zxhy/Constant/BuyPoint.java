package com.zxhy.Constant;

import com.zxhy.bean.dto.BuyCondition;

public enum BuyPoint {
    Break("破",BuyCondition.getBuyCondition(0.33,null)),
    High("高",null),
    Back("调",BuyCondition.getBuyCondition(0.33,null)),
    Fast ("急",BuyCondition.getBuyCondition(0.33,0.08)),
    Boundary("界",BuyCondition.getBuyCondition(0.33,null));

    // 成员变量
    private String name;
    private final BuyCondition buyCondition;

    // 构造方法
    BuyPoint(String name, BuyCondition buyCondition) {
        this.name = name;
        this.buyCondition = buyCondition;
    }
    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public BuyCondition getBuyCondition() {
        return buyCondition;
    }
}
