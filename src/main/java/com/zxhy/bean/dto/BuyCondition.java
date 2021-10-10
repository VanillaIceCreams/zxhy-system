package com.zxhy.bean.dto;

import lombok.Data;

@Data
public class BuyCondition {
    //盈亏比
    private Double winPercent;
    //急的买点
    private Double risePercent;

    private BuyCondition() {
    }
    public static BuyCondition getBuyCondition(Double winPercent,Double risePercent){
        BuyCondition buyCondition = new BuyCondition();
        buyCondition.setRisePercent(risePercent);
        buyCondition.setWinPercent(winPercent);
        return buyCondition;
    }
}
