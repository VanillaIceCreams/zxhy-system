package com.zxhy.service.analyze;

import com.zxhy.cache.BusinessContext;
import com.zxhy.cache.DailyInfo;
import com.zxhy.cache.Depository;
import com.zxhy.cache.TendencyInfoContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SellService {
    @Autowired
    InitService initService;
    private final TendencyInfoContext tendencyInfoContext =initService.getTendencyInfoContext();
    private final BusinessContext businessContext = initService.getBusinessContext();
    public void checkSellPointAndSell(DailyInfo dailyInfo) {
        //获取当前持仓
        List<Depository> depositoryList = businessContext.getDepositoryList();
        //确认突发事件，直接按现价清仓
        if (checkIncident(dailyInfo)){
            businessContext.sellAll(dailyInfo.getMEnd());
            return;
        }
        //将现价与止损价比对，清仓掉止损线在现价以上的仓位
        businessContext.stopLosses(dailyInfo.getMEnd());
    }

    private boolean checkIncident(DailyInfo dailyInfo) {
        return false;
    }
}
