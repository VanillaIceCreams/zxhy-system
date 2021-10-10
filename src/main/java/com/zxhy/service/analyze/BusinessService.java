package com.zxhy.service.analyze;

import com.zxhy.Constant.Tendency;
import com.zxhy.bean.dto.Day;
import com.zxhy.bean.dto.FifteenMinute;
import com.zxhy.bean.dto.Hour;
import com.zxhy.bean.dto.ThirtyMinute;
import com.zxhy.cache.BusinessContext;
import com.zxhy.cache.DailyInfo;
import com.zxhy.cache.Depository;
import com.zxhy.cache.TendencyInfoContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class BusinessService {
    @Autowired
    InitService initService;
    @Autowired
    BuyService buyService;
    @Autowired
    SellService sellService;
    private final TendencyInfoContext tendencyInfoContext =initService.getTendencyInfoContext();
    private final BusinessContext businessContext = initService.getBusinessContext();
    public void moveFiveMinute(DailyInfo dailyInfo){
        //修改dailyInfo内的信息
        editDailyInfo(dailyInfo);
        //获取当前时刻各个周期的k线情况
        getAllPeriodInfo(dailyInfo);
        //判断当前价格是否突破了某个周期的某个boll轨,并修改该周期的趋势信息
        checkBoll(dailyInfo);
        //卖点确认并卖出
        sellService.checkSellPointAndSell(dailyInfo);
        //买点确认并尝试买入
        buyService.checkBuyPointAndBuy(dailyInfo);
    }




    /**
     * 检查是否突发事件了
     * @param dailyInfo
     * @return
     */
    private Boolean checkIncident(DailyInfo dailyInfo) {
        return true;
    }

    private void checkBoll(DailyInfo dailyInfo) {
        //算出各个周期boll轨的突破信息
        //然后set进去
        tendencyInfoContext.getDay().setTendency(Tendency.Back);
        tendencyInfoContext.getFifteenMinute().setTendency(Tendency.Back);
        tendencyInfoContext.getThirtyMinute().setTendency(Tendency.Back);
        tendencyInfoContext.getHour().setTendency(Tendency.Back);
    }

    private void getAllPeriodInfo(DailyInfo dailyInfo) {
        //计算各个周期的信息
        //然后set进去
        tendencyInfoContext.setDay(new Day());
        tendencyInfoContext.setFifteenMinute(new FifteenMinute());
        tendencyInfoContext.setHour(new Hour());
        tendencyInfoContext.setThirtyMinute(new ThirtyMinute());
//        tendencyInfoContext.setWeek(new Week());
    }

    private void editDailyInfo(DailyInfo dailyInfo) {

    }

    /**
     * 分析入口
     * @param date 起始日期
     */
    public void mainFun(Date date){
        Date currentDate = new Date(date.getTime());
        while (true){
            DailyInfo dailyInfo = new DailyInfo();
            //设置一下初始dailyInfo的信息
            dailyInfo.setDate(currentDate);
            //开始走5分钟线
            moveFiveMinute(dailyInfo);
        }
    }



}
