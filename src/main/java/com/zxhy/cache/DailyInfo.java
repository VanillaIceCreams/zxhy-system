package com.zxhy.cache;

import lombok.Data;

import java.util.Date;

/**
 * 用于存放本日内当前时刻的一些信息,
 * 主要用于《急》和《突发事件》
 */
@Data
public class DailyInfo {
    //当前日内最高价
    private Double high=0.0;
    //当前日内最低价
    private Double low=0.0;
    //当前日内最大涨幅（百分比）
    private Double maxRise=0.0;
    //当前日内最大跌幅（百分比）
    private Double maxFell=0.0;
    //当前时间点的涨幅（与0轴对比）
    private Double currentRise=0.0;
    //当日日期
    private Date date;
    //当前时间点
    private Integer time=935;
    //当前5分钟线的收盘价
    private Double mEnd=0.0;
}
