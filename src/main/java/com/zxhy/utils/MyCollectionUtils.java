package com.zxhy.utils;

import java.util.List;

public class MyCollectionUtils {
    public static void sort(List<Integer> list,int b){
        if (b==1){//正序
            list.sort((a1,a2)->a1<a2?-1: a1.equals(a2) ?0:1);
        }else {
            list.sort((a1,a2)->a1<a2?1: a1.equals(a2) ?0:-1);
        }

    }
}
