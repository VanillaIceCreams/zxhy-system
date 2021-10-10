package com.zxhy.cache;

import com.zxhy.Constant.BuyPoint;
import com.zxhy.Constant.SellPoint;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

/**
 *仓位控制
 */
@Getter
@Setter
public class Depository {
    //买入时间
    private Date buyDate;
    private Integer buyTime;
    //卖出时间
    private Date sellDate;
    private Integer sellTime;
    //是否已清仓
    private boolean hasSell;
    //买入价
    private Double buyPrice;
    //卖出价 or 止损价
    private Double sellPrice;
    //总股数
    private Integer shareNumber;
    //买点
    private BuyPoint buyPoint;
    //卖点
    private SellPoint sellPoint;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depository that = (Depository) o;
        return buyDate.equals(that.buyDate) && buyTime.equals(that.buyTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyDate, buyTime);
    }
}