package com.zxhy.mapper;

import com.zxhy.bean.bo.FiveMinute;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FiveMinuteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FiveMinute record);

    int insertSelective(FiveMinute record);

    FiveMinute selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FiveMinute record);

    int updateByPrimaryKey(FiveMinute record);

    int batchInsert(List<FiveMinute> fiveMinuteList);

    List<FiveMinute> selectByParam(FiveMinute record);
}