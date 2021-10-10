package com.zxhy.service.db;

import ch.qos.logback.core.util.FileUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zxhy.bean.bo.FiveMinute;
import com.zxhy.dao.FiveMinuteDao;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SocketUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MinuteService {
    @Autowired
    private FiveMinuteDao fiveMinuteDao;

    @SneakyThrows
    public void insertMinuteInfo(File file,String code){
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        BufferedReader br = new BufferedReader(reader);
        String line;
        ArrayList<String[]> array = new ArrayList<>();
        while ((line = br.readLine()) != null) {//使用readLine方法，一次读一行
            String[] split = line.split("\t");
            if (split.length<5) continue;
            array.add(split);

        }
        br.close();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        List<FiveMinute> FiveMinutes = array.stream().map(stringArr ->
        {
            try {
                return FiveMinute.builder()
                        .code(code)
                        .mDate(sdf.parse(stringArr[0]))
                        .minute(Integer.valueOf(stringArr[1]))
                        .mBegin(new BigDecimal(stringArr[2]))
                        .mHigh(new BigDecimal(stringArr[3]))
                        .mLow(new BigDecimal(stringArr[4]))
                        .mEnd(new BigDecimal(stringArr[5])).build();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        fiveMinuteDao.batchInsert(FiveMinutes);
    }


}
