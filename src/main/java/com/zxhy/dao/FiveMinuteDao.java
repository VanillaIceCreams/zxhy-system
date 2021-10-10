package com.zxhy.dao;

import com.google.common.collect.Lists;
import com.zxhy.bean.bo.FiveMinute;
import com.zxhy.mapper.FiveMinuteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FiveMinuteDao {
    @Autowired
    FiveMinuteMapper fiveMinuteMapper;
    public void batchInsert(List<FiveMinute> fiveMinuteList){
        List<List<FiveMinute>> partition = Lists.partition(fiveMinuteList, 1000);
        partition.forEach(p->fiveMinuteMapper.batchInsert(p));
    }
}
