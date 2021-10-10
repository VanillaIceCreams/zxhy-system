package com.zxhy.utils;

import com.sun.org.apache.regexp.internal.RE;
import com.zxhy.Constant.KlineEnum;
import com.zxhy.Constant.TimeMap;
import com.zxhy.bean.bo.FiveMinute;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DateUtils {
    public static Date createDate(String date) throws Exception{
        String[] split = date.split("/");
        if (split.length!=3) {
            throw new Exception("入参不规范");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.parse(split[0]+split[1]+split[2]);
    }

    /**
     * 根据当前五分钟线的时间，查询在其他周期中所对应的k线的时间,比如955是15分钟的第二根k线，此时我们需要给出第一根k线time
     * @param klineEnum
     * @param time
     * @return
     * @throws Exception
     */
    public static Integer getMinute(KlineEnum klineEnum,Integer time) throws Exception{
        HashMap<Integer, Integer> timeMap = TimeMap.getTimeMap();
        List<Integer> collect = timeMap.keySet().stream()
                .filter(index -> index % klineEnum.getMultiple() == 0)
                .map(timeMap::get)
                .collect(Collectors.toList());
        for (int i = 0; i < collect.size(); i++) {
            if (time<=collect.get(i)){
                return collect.get(i);
            }
        }
        return null;
    }

    public static Date dateCalculate(Date date,Integer day) {

        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);

        rightNow.add(Calendar.DAY_OF_YEAR, day);//日期加减指定天数
        if(rightNow.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
            rightNow.add(Calendar.DAY_OF_YEAR, -2);
        }else if(rightNow.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
            rightNow.add(Calendar.DAY_OF_YEAR, -1);
        }

        return rightNow.getTime();
    }
}
