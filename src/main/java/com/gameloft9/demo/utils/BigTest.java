package com.gameloft9.demo.utils;

import java.math.BigDecimal;

/**
 * @author: 啊发包
 * @Date: 2019/03/29 2019-03-29
 */
public class BigTest {
    public static void main(String[] args) {
        String str = "2222.34565";
        String decimal = NumberUtil.strToBigdecimal(str);
        System.out.println(decimal.toString());

    }
}
