package com.zxhy.service.analyze;

import com.zxhy.cache.BusinessContext;
import com.zxhy.cache.PrivateSystem;
import com.zxhy.cache.TendencyInfoContext;

import java.util.Date;

public class InitService {
    private final TendencyInfoContext tendencyInfoContext = new TendencyInfoContext();
    private final BusinessContext businessContext = new BusinessContext();
    private final PrivateSystem privateSystem = new PrivateSystem();
    /**
     * 初始化入口
     * @param date 起始日期
     */
    public void initContext(Date date) {

    }

    public TendencyInfoContext getTendencyInfoContext() {
        return tendencyInfoContext;
    }

    public BusinessContext getBusinessContext() {
        return businessContext;
    }

    public PrivateSystem getPrivateSystem() {
        return privateSystem;
    }
}
