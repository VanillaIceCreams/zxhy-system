package com.zxhy;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.zxhy.utils.DateUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//@SpringBootTest
class normalTests {

    @Test
    void testDateUtils() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date parse = sdf.parse("2020/08/03");
            System.out.println(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testValueOf() {
        System.out.println(Integer.valueOf("0935"));
    }

    @Test
    @SneakyThrows
    void testDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Table<Date, Integer, String> employeeTable = HashBasedTable.create();
        employeeTable.put(sdf.parse("2020/08/03"),1120,"zxj");
        employeeTable.put(sdf.parse("2020/08/04"),1130,"zxj2");
        System.out.println(employeeTable.get(sdf.parse("2020/08/03"),1120));
        System.out.println(employeeTable.row(sdf.parse("2020/08/03")));
    }
    @Test
    @SneakyThrows
    void testDateCalculate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = sdf.parse("2020/08/10");//周五
        assert DateUtils.dateCalculate(date, -2).equals(sdf.parse("2020/08/7"));
        assert DateUtils.dateCalculate(date, -1).equals(sdf.parse("2020/08/7"));
    }




}
