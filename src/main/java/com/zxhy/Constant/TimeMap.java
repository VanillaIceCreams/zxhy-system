package com.zxhy.Constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class TimeMap {
    /**
     * 5分钟线一共48条，今日第一根5分钟线的开盘价是竞价出来的，会跳空
     *
     * 所有日内K线，第一根的开盘价就是第一根五分钟线的开盘价
     * 第N根的开盘价是N-1根的收盘价
     *
     * 15分钟线一共16条，第N根的收盘价是第3*N根五分钟线的收盘价
     *
     * 30分钟线一共8条，
     *
     * 小时线一共4条，
     *
     * 日线一共1条，
     */
    private static final HashMap<Integer,Integer> timeMap;
    static {
        List<Integer> times = Arrays.asList(935, 940, 945, 950, 955, 1000, 1005, 1010, 1015, 1020, 1025, 1030, 1035, 1040, 1045, 1050, 1055, 1100, 1105, 1110, 1115, 1120, 1125, 1130, 1305, 1310, 1315, 1320, 1325, 1330, 1335, 1340, 1345, 1350, 1355, 1400, 1405, 1410, 1415, 1420, 1425, 1430, 1435, 1440, 1445, 1450, 1455, 1500);

        timeMap = new HashMap<>();
        for (int i = 0; i < times.size(); i++) {
            timeMap.put(i+1,times.get(i));
        }
    }

    public static HashMap<Integer, Integer> getTimeMap() {
        return timeMap;
    }

    public static List<Integer> getPeriodTime(KlineEnum klineEnum){
        List<Integer> times = timeMap.keySet().stream()
                .filter(index -> index % klineEnum.getMultiple() == 0)
                .sorted()
                .map(timeMap::get)
                .collect(Collectors.toList());
        return times;
    }
}
