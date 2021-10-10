package com.zxhy.cache;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.zxhy.bean.bo.FiveMinute;
import com.zxhy.mapper.FiveMinuteMapper;
import com.zxhy.utils.DateUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@Component
public class ThreadLocalContext {
    @Autowired
    FiveMinuteMapper fiveMinuteMapper;
    public static Table<Date, Integer, FiveMinute> fiveMinuteTable = HashBasedTable.create();
    @PostConstruct
    public void init() throws Exception{
        FiveMinute fm = new FiveMinute();
//        fm.setMDate(DateUtils.createDate("2020/08/03"));
        List<FiveMinute> fiveMinutes = fiveMinuteMapper.selectByParam(fm);
        fiveMinutes.forEach(fiveMinute -> fiveMinuteTable.put(fiveMinute.getMDate(),fiveMinute.getMinute(),fiveMinute));
    }
}
