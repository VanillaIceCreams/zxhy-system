package com.zxhy.bean.bo;

import com.zxhy.Constant.Tendency;
import com.zxhy.bean.pojo.Boll;
import lombok.Data;

import java.util.Date;

@Data
public class KlineBase {
    private Long id;

    private String code;

    private Date date;

    private Integer minute;

    private Double begin;

    private Double end;

    private Double high;

    private Double low;

    private Tendency tendency;

    private Boll boll;
}
