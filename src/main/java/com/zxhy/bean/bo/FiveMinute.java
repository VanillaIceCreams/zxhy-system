package com.zxhy.bean.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FiveMinute{

    private Long id;

    private String code;

    private Date mDate;

    private Integer minute;

    private BigDecimal mBegin;

    private BigDecimal mEnd;

    private BigDecimal mHigh;

    private BigDecimal mLow;
}