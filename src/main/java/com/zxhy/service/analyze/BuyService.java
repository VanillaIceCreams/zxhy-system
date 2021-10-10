package com.zxhy.service.analyze;

import com.zxhy.Constant.BuyPoint;
import com.zxhy.Constant.KlineEnum;
import com.zxhy.bean.bo.KlineBase;
import com.zxhy.bean.dto.*;
import com.zxhy.cache.BusinessContext;
import com.zxhy.cache.DailyInfo;
import com.zxhy.cache.PrivateSystem;
import com.zxhy.cache.TendencyInfoContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BuyService {
    @Autowired
    InitService initService;
    private final TendencyInfoContext tendencyInfoContext =initService.getTendencyInfoContext();
    private final BusinessContext businessContext = initService.getBusinessContext();
    private final PrivateSystem privateSystem =  initService.getPrivateSystem();

    public void checkBuyPointAndBuy(DailyInfo dailyInfo) {
        //1.判断当前是什么买点
        Map<KlineEnum, List<BuyPoint>> klineEnumListMap = checkBuyPoint(dailyInfo);
        //2.从用户系统中筛选出需要的买点
        klineEnumListMap = filterByPrivateSystem(privateSystem,klineEnumListMap);
        //3.判断是否有钱买
        Boolean ifBreakAndHigh = businessContext.checkRiskMoneyForBreakAndHigh(dailyInfo);
        Boolean ifFast = businessContext.checkRiskMoneyForDayFast(dailyInfo, tendencyInfoContext.getDay());
        //4.判断盈亏比是否高于3:1,这里估计要选出最高盈亏比的那个

        //5.买入

    }

    private Map<KlineEnum, List<BuyPoint>> filterByPrivateSystem(PrivateSystem privateSystem, Map<KlineEnum, List<BuyPoint>> klineEnumListMap) {
        HashMap<KlineEnum, List<BuyPoint>> result = new HashMap<>();
        List<KlineEnum> collect = klineEnumListMap.keySet().stream()
                .filter(key -> privateSystem.getKlineEnums().contains(key))
                .collect(Collectors.toList());
        for (KlineEnum klineEnum:collect) {
            List<BuyPoint> finalBuyPoint = klineEnumListMap.get(klineEnum).stream()
                    .filter(buyPoint -> privateSystem.getBuyPoints().contains(buyPoint)).collect(Collectors.toList());
            result.put(klineEnum,finalBuyPoint);
        }
        return result;

    }

    /**
     * 计算出各个周期的买点
     * @param dailyInfo
     * @return
     */
    private Map<KlineEnum, List<BuyPoint>> checkBuyPoint(DailyInfo dailyInfo) {
        HashMap<KlineEnum, List<BuyPoint>> map = new HashMap<>();
        //日线
        KlineBase day = tendencyInfoContext.getDay();
        map.put(KlineEnum.Day,checkBuyPointType(day, dailyInfo));
        //15分钟线
        FifteenMinute fifteenMinute = tendencyInfoContext.getFifteenMinute();
        map.put(KlineEnum.FifteenMinute,checkBuyPointType(fifteenMinute, dailyInfo));
        //60分钟线
        Hour hour = tendencyInfoContext.getHour();
        map.put(KlineEnum.Hour,checkBuyPointType(hour, dailyInfo));
        //30分钟线
        ThirtyMinute thirtyMinute = tendencyInfoContext.getThirtyMinute();
        map.put(KlineEnum.ThirtyMinute,checkBuyPointType(thirtyMinute, dailyInfo));
        //周线
        Week week = tendencyInfoContext.getWeek();
        map.put(KlineEnum.Week,checkBuyPointType(week, dailyInfo));

        return map;
    }

    private List<BuyPoint> checkBuyPointType(KlineBase klineBase, DailyInfo dailyInfo) {
        ArrayList<BuyPoint> buyPoints = new ArrayList<>();
        //高
        if (tendencyInfoContext.getTotalMax()<dailyInfo.getMEnd()){
            buyPoints.add(BuyPoint.High);
        }
        //破,要求当前价格在boll上轨之上，并且本周期k线起始点在boll上轨之下
        if(klineBase.getBoll().getUp()<dailyInfo.getMEnd()
                && klineBase.getBoll().getUp()>klineBase.getBegin()){
            buyPoints.add(BuyPoint.Break);
        }
        //急
        if(klineBase instanceof Day && dailyInfo.getMaxRise()>BuyPoint.Fast.getBuyCondition().getRisePercent()){
            buyPoints.add(BuyPoint.Fast);
        }
        //调和界晚点再说
//        Boolean b = isBack(klineBase,dailyInfo);

        return buyPoints;

    }

//    private Boolean isBack(KlineBase klineBase, DailyInfo dailyInfo) {
//        if(klineBase instanceof Day){
//            Double highest = tendencyInfoContext.getTotalMax();
//            Double mEnd = dailyInfo.getMEnd();
//            klineBase.getBoll().getDown()
//        }
//        if(klineBase instanceof Hour){
//
//        }
//        if(klineBase instanceof Week){
//
//        }
//        return false;
//    }
}
