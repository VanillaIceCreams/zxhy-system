package com.zxhy.cache;

import com.zxhy.Constant.BuyPoint;
import com.zxhy.Constant.BuyType;
import com.zxhy.Constant.KlineEnum;
import com.zxhy.Constant.SellPoint;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * 个人交易系统
 */
@Data
public class PrivateSystem {
    //买点集合
    private  Set<BuyPoint> buyPoints;
    //卖点集合
    private  Set<SellPoint> sellPoints;
    //操作周期
    private  Set<KlineEnum> klineEnums;
    //加仓方式
    private  BuyType buyType;

    public PrivateSystem() {
    }
}
