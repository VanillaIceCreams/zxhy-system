package com.zxhy.utils;

import com.zxhy.Constant.KlineEnum;
import com.zxhy.Constant.TimeMap;
import com.zxhy.bean.bo.FiveMinute;
import com.zxhy.bean.pojo.Boll;
import com.zxhy.cache.ThreadLocalContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.zxhy.Constant.ConstantValue.*;

public class BollUtil {
    /**
     * 按给出的5分钟线计算对应的boll轨
     * 比如要算日线20根k线的boll轨，则入参需要传前20天每天下午三点那根五分钟线对象进来
     * @param list
     * @return
     */
    public static Boll computeBoll(List<FiveMinute> list){
        List<BigDecimal> endList = list.stream().map(FiveMinute::getMEnd).collect(Collectors.toList());

        BigDecimal total =endList.stream().reduce(BigDecimal.ZERO,BigDecimal::add);
        BigDecimal average = total.divide(new BigDecimal(list.size()),DEF_MIDDLE_SCALE,ROUNDING_MODE);
        BigDecimal standardDeviation =Arithmetic4Double.getStandardDeviation(endList,average,DEF_MIDDLE_SCALE);
        Boll boll = new Boll();
        boll.setDown(average.subtract(standardDeviation.add(standardDeviation)).doubleValue());
        boll.setUp(average.add(standardDeviation.add(standardDeviation)).doubleValue());
        boll.setMiddle(average.doubleValue());
        return boll;
    }

    /**
     * 计算分钟boll轨，可以是5分钟，15分钟，30分钟，60分钟
     * @param date 当前日期
     * @param time 当前时间
     * @param klineEnum 周期
     * @param klineNum  计算多少根k线的boll轨
     * @param resultList  计算结果存放在这个里面
     * @return
     */
    public static void computeMinuteBoll(Date date,Integer time,KlineEnum klineEnum,int klineNum,List<FiveMinute> resultList){
        List<Integer> periodTime = TimeMap.getPeriodTime(klineEnum);
        MyCollectionUtils.sort(periodTime,-1);
        Set<Integer> collect = periodTime.stream().filter(t -> t <= time).limit(klineNum-resultList.size()).collect(Collectors.toSet());
        List<FiveMinute> fiveMinuteList = ThreadLocalContext.fiveMinuteTable.row(date).keySet().stream()
                .filter(collect::contains)
                .map(key -> ThreadLocalContext.fiveMinuteTable.row(date).get(key))
                .collect(Collectors.toList());
        resultList.addAll(fiveMinuteList);
        while (klineNum>resultList.size()){
            //若k线数量不足
            Date lastDay=DateUtils.dateCalculate(date,-1);
            computeMinuteBoll(lastDay,1500,klineEnum,klineNum,resultList);
        }
    }
}
