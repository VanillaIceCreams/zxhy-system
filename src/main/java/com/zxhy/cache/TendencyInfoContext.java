package com.zxhy.cache;

import com.zxhy.bean.dto.*;
import lombok.Data;

import java.util.Date;

/**
 * 用于存放开局至今最高点
 * 主要用于《急》和《突发事件》
 */
@Data
public class TendencyInfoContext {
    //历史最高价
    private Double totalMax = 0.0;
    //当前日期
    private Date currentDate;
    //当前时间点，各个周期的情况
    private Day day;
    private FifteenMinute fifteenMinute;
    private Hour hour;
    private ThirtyMinute thirtyMinute;
    private Week week;
}
