package com.gameloft9.demo.utils;


import java.math.BigDecimal;

/**
 * 数字工具类
 * Created by gameloft9 on 2017/12/20.
 */
public class NumberUtil {

    /**
     * 将N位数补零
     * @param number 数字
     * @param count 位数
     * */
    public static String paddingNumber(Integer number,int count){

        if(count <= 1){//位数小等于1直接返回
            return number.toString();
        }

        if(number < 0){//数字小于0直接返回
            return number.toString();
        }

        //获取count最大值
        String arg = "";
        for(int i = 0;i<count;i++){
            arg += "9";
        }

        int res = number.compareTo(Integer.valueOf(arg));
        if(res < 0){//小于最大值才能补零
            int n = 0;
            //找到number几位
            int copy = number;
            while(copy>0){
                n += 1;
                copy /= 10;
            }

            int need = count - n;//需要补多少位
            String padding = "";
            for(int j = 0;j<need;j++){
                padding += "0";
            }

            return padding+number.toString();
        }

        return number.toString();
    }


    public static void main(String[] args){
        System.out.println(paddingNumber(1,2));
        System.out.println(paddingNumber(11,2));
        System.out.println(paddingNumber(111,2));
        System.out.println(paddingNumber(1,3));
        System.out.println(paddingNumber(0,1));
    }

    /**
     * 字符串不为空转int
     *
     * @param str 需要转换的字符串
     * @return int
     */
    public static int strToInt(String str){
        int num = 0;
        if(str != null && !"".equals(str)){
            num =   Integer.parseInt(str);
        }
        return num;
    }

    /**
     * 保留两位小数
     *
     * @param money 价格
     * @return
     *      string
     */
    public static String strToBigdecimal(String money){
        BigDecimal decimal = new BigDecimal(money);
        decimal = decimal.setScale(2,BigDecimal.ROUND_HALF_UP);
        return decimal.toString();
    }
}
