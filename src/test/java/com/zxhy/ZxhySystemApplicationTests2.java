package com.zxhy;

import com.zxhy.Constant.KlineEnum;
import com.zxhy.bean.bo.FiveMinute;
import com.zxhy.bean.bo.KlineBase;
import com.zxhy.cache.ThreadLocalContext;
import com.zxhy.service.analyze.KlineService;
import com.zxhy.service.db.MinuteService;
import com.zxhy.utils.BollUtil;
import com.zxhy.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.*;

@SpringBootTest
class ZxhySystemApplicationTests2 {
    @Autowired
    MinuteService minuteService;
    @Autowired
    KlineService abcService;
    @Test
    void testFileInput() {
        File file = new File("C:\\Users\\51505\\OneDrive\\桌面\\SZ#000683.txt");
        minuteService.insertMinuteInfo(file,"000616");
    }
    @Test
    void testThreadLocalContext() {
        System.out.println(ThreadLocalContext.fiveMinuteTable.size());
    }

    @Test
    void testParseKline() throws Exception{
        List<KlineBase> kline = new ArrayList<>();
        abcService.parseKline(DateUtils.createDate("2020/08/03"), KlineEnum.Day,kline);
        System.out.println(kline);
    }

    @Test
    void testComputeBoll() throws Exception{
        List<FiveMinute> kline = new ArrayList<>();
        Date date = DateUtils.createDate("2020/09/22");
        BollUtil.computeMinuteBoll(date,1125,KlineEnum.ThirtyMinute,20,kline);
        BollUtil.computeBoll(kline);
    }

}
