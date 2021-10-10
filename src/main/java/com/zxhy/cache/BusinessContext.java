package com.zxhy.cache;

import com.zxhy.Constant.BuyPoint;
import com.zxhy.bean.dto.Day;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class BusinessContext {
    //账户总资产，包含浮盈
//    private Double totalAsset =1000000.0;
    //账户当前现金
    private Double currentMoney=1000000.0;
    //账户当前实盈
//    private Double trustProfit;
    //持仓信息
    private List<Depository> depositoryList;
    //清仓信息
    private List<Depository> oldDepositoryList;

    //按当前价格清仓
    public void sellAll(Double currentPrice){
        depositoryList.forEach(depository->sellOne(depository,currentPrice));
    }
    //卖出单笔
    public void sellOne(Depository depository,Double currentPrice){
        int index = depositoryList.indexOf(depository);
        Depository remove = depositoryList.remove(index);
        double income = (currentPrice - remove.getBuyPrice()) * remove.getShareNumber();
        currentMoney+=income;
        remove.setHasSell(true);
        oldDepositoryList.add(remove);
    }

    //按止损价止损
    public void stopLosses(Double currentPrice){
        depositoryList.stream()
                .filter(depository->depository.getSellPrice()>currentPrice) //过滤出需要止损的仓位
                .forEach(depository->sellOne(depository,depository.getSellPrice()));
    }

    /**
     * 检查破和高的风险金是否释放
     * @param dailyInfo
     * @return
     */
    public Boolean checkRiskMoneyForBreakAndHigh(DailyInfo dailyInfo) {
        long count = depositoryList.stream()
                .filter(depository -> depository.getSellPrice() < depository.getBuyPrice()) //止损线还在买入价之下
                .filter(depository -> depository.getBuyPoint() == BuyPoint.Fast ||
                        depository.getBuyPoint() == BuyPoint.High ||
                        depository.getBuyPoint() == BuyPoint.Break).count();
        return count==0L;
    }

    /**
     * 检查日线急的买点的风险金是否释放
     * @param dailyInfo
     * @return
     */
    public Boolean checkRiskMoneyForDayFast(DailyInfo dailyInfo, Day day) {
        long count = depositoryList.stream()
                .filter(depository -> depository.getBuyPrice() > day.getLow()*0.97) //成本线还是在当日k底*0.97以上
                .filter(depository -> depository.getBuyPoint() == BuyPoint.Fast ||
                        depository.getBuyPoint() == BuyPoint.High ||
                        depository.getBuyPoint() == BuyPoint.Break).count();
        return count==0L;
    }
}
