package com.gameloft9.demo.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderUtil {


    private static int sequence = 0;

    private static int length = 6;

    /**
     * YYYYMMDDHHMMSS+6位自增长码(20位)
     * @author shijing
     * 2015年6月29日下午1:25:23
     * @return
     */
    public static synchronized String getLocalTrmSeqNum() {
        sequence = sequence >= 999999 ? 1 : sequence + 1;
        String datetime = new SimpleDateFormat("MMdd")
                .format(new Date());
        String s = Integer.toString(sequence);
        return datetime +addLeftZero(s, length);
    }

    /**
     * 左填0
     * @author shijing
     * 2015年6月29日下午1:24:32
     * @param s
     * @param length
     * @return
     */
    public static String addLeftZero(String s, int length) {
        // StringBuilder sb=new StringBuilder();
        int old = s.length();
        if (length > old) {
            char[] c = new char[length];
            char[] x = s.toCharArray();
            if (x.length > length) {
                throw new IllegalArgumentException(
                        "Numeric value is larger than intended length: " + s
                                + " LEN " + length);
            }
            int lim = c.length - x.length;
            for (int i = 0; i < lim; i++) {
                c[i] = '0';
            }
            System.arraycopy(x, 0, c, lim, x.length);
            return new String(c);
        }
        return s.substring(0, length);
    }

    /**
     * 生成编号(由编号类型编码+编号创建平台编码+6位日期+时间戳后4位+4位随机数组成)
     * @param numType 编号类型,1位(1-支付订单,2-退款订单)
     * @param platform 编号生成平台,1位(1-PC平台,2app平台,3移动web平台)
     * @return
     * @throws Exception
     */
    public static String createOrderNumber(){
        //格式化日期为"yymmdd"
        DateFormat format = new SimpleDateFormat("MMdd");
        Date date = new Date();
        StringBuffer buffer = new StringBuffer();

        buffer.append(format.format(date));
        buffer.append((date.getTime() + "").substring(9));
        buffer.append(getRandNum(4));
        return buffer.toString();
    }
    /**
     * 获取四位随机数
     * @param leng  随机数长度
     * @return
     */
    public static String getRandNum(int leng){
        Random random = new Random();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < leng; i++) {
            result.append(random.nextInt(10));
        }
        if(result.length()>0){
            return result.toString();
        }
        return null;
    }
}
