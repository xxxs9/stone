package com.gameloft9.demo.utils;

import org.junit.Test;

/**
 * @author: Sxiu
 * @create: 2019/3/19 15:20
 * @description:
 */
public class DocumentNumberUtil {


    /**
     *
     * 产生订单编号
     * @str  单据开头字母
     */
    public static String getDocumentNumber(String str){
        //产生2个0-9的随机数
        int r1=(int)(Math.random()*(10));
        int r2=(int)(Math.random()*(10));
        //一个13位的时间戳
        long now = System.currentTimeMillis();
        // 单据编号
        String DocumentNumber =str+String.valueOf(r1)+String.valueOf(r2)+String.valueOf(now);
        return DocumentNumber;
    }

}
