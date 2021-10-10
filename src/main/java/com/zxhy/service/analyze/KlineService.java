package com.zxhy.service.analyze;

import com.zxhy.Constant.KlineEnum;
import com.zxhy.Constant.TimeMap;
import com.zxhy.bean.bo.FiveMinute;
import com.zxhy.bean.bo.KlineBase;
import com.zxhy.bean.dto.Day;
import com.zxhy.bean.dto.FifteenMinute;
import com.zxhy.bean.dto.Hour;
import com.zxhy.bean.dto.ThirtyMinute;
import com.zxhy.bean.pojo.Boll;
import com.zxhy.cache.ThreadLocalContext;
import com.zxhy.utils.BollUtil;
import com.zxhy.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class KlineService {

    /**
     * 计算除周线外，各个周期当前时间点的boll线值
     * @param date
     * @param time
     * @return
     */
    public Boll computeBoll(Date date, Integer time,KlineEnum klineEnum){
        List<FiveMinute> kline = new ArrayList<>();
        BollUtil.computeMinuteBoll(date,time,klineEnum,20,kline);
        return BollUtil.computeBoll(kline);
    }


    /**
     * 获取今日各个周期（不包含周线）的所有k线对象
     * @param date
     * @param klineEnum
     * @param kline
     */
    public void parseKline(Date date, KlineEnum klineEnum, List<KlineBase> kline) {
        Map<Integer, FiveMinute> row = ThreadLocalContext.fiveMinuteTable.row(date);
        List<Integer> times = TimeMap.getPeriodTime(klineEnum);
        for (int i = 0; i < times.size(); i++) {
            List<FiveMinute> fiveMinutes;
            if(i==0){
                fiveMinutes=row.keySet().stream()
                        .filter(t -> t >= 935 && t <= times.get(0))
                        .map(row::get)
                        .collect(Collectors.toList());
            }else {
                int j=i;
                fiveMinutes=row.keySet().stream()
                        .filter(t -> t > times.get(j-1) && t <= times.get(j))
                        .map(row::get)
                        .collect(Collectors.toList());
            }
            KlineBase klineBase = getHighLowBeginEnd(klineEnum, fiveMinutes);
            kline.add(klineBase);
        }
    }

    /**
     * 获取今日各个周期（不包含周线）某一根k线的最高最低开始结束价格
     * @param klineEnum
     * @param fiveMinutes  包含在这根k线内的5分钟线
     * @return
     */
    @SuppressWarnings("ALL")
    private KlineBase getHighLowBeginEnd(KlineEnum klineEnum,List<FiveMinute> fiveMinutes) {
        double high = fiveMinutes.stream().map(FiveMinute::getMHigh).mapToDouble(BigDecimal::doubleValue).max().getAsDouble();
        double low = fiveMinutes.stream().map(FiveMinute::getMLow).mapToDouble(BigDecimal::doubleValue).min().getAsDouble();
        double begin=fiveMinutes.get(0).getMBegin().doubleValue();
        double end=fiveMinutes.get(fiveMinutes.size() - 1).getMEnd().doubleValue();
        KlineBase klineBase=null;
        switch (klineEnum){
            case Day:klineBase=new Day();break;
            case Hour:klineBase=new Hour();break;
            case ThirtyMinute:klineBase=new ThirtyMinute();break;
            case FifteenMinute:klineBase=new FifteenMinute();break;
        }
        klineBase.setCode(fiveMinutes.get(0).getCode());
        klineBase.setDate(fiveMinutes.get(0).getMDate());
        klineBase.setMinute(fiveMinutes.get(fiveMinutes.size() - 1).getMinute());
        klineBase.setBegin(begin);
        klineBase.setEnd(end);
        klineBase.setHigh(high);
        klineBase.setLow(low);
        return klineBase;
    }


}
