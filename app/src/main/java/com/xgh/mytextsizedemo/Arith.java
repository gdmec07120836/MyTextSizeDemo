package com.xgh.mytextsizedemo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 创建人：xgh
 * 创建时间：2018/11/9 9:59
 * 类描述：加减乘除操作类
 * 备注：
 */
public class Arith {
    private static final int DEF_DIV_SCALE = 10;
    /**
     * 返回集合是否有数据
     *
     * @param list 列表
     * @return 是否有数据  true 有
     */
    public static boolean hasList(List list) {
        return null != list && !list.isEmpty();
    }
    /**
     * 保留2位小数
     * @param v1
     * @return
     */
    public static String keepTwo(Double v1) {
        return keep(v1, 2);
    }

    /**
     * 保留小数位数
     * @param v1
     * @param num  位数
     * @return
     */
    public static String keep(Double v1, int num) {
        String str = v1+"";
        String[]strs = str.split("\\.");
        if (strs.length == 2) {//正常的数据应该是能够根据点切分成两段的
            for (int i = 0; i <= num; i++) {
                //刚好和要求的位数一样
                if (strs[1].length() == num) {
                    return str;
                }
                //没有达到要求的位数，补0
                else if (strs[1].length() < num) {
                    String zeroNum = "";
                    for (int j = 0; j < (num - strs[1].length()); j++) {
                        zeroNum += "0";
                    }
                    return str + zeroNum;
                }
                //太长了，直接切掉后面的小数
                else {
                    return strs[0] + "." + strs[1].substring(0, num);
                }
            }
            //如果保留0位那就直接给
            return strs[1];
        }
        else {//这种情况说明是整数，没有小数点，直接在后面拼上需要的位数
            String zeroNum = "";
            for (int j = 0; j < num; j++) {
                zeroNum += "0";
            }
            return str+"."+zeroNum;
        }
    }


    //两个数相加
    public static Double add(int v1, int v2) {
        return add(Double.parseDouble(v1+""), Double.parseDouble(v2+""), 2);
    }

    //两个数相加
    public static Double add(String v1, String v2) {
        return add(Double.parseDouble(v1), Double.parseDouble(v2), 2);
    }
    public static Double add(String v1, String v2, int newScale) {
        if (isNumericData(v1,v2)){
            return add(Double.parseDouble(v1), Double.parseDouble(v2), newScale);
        }
        else {
            return returnData(v1,v2);
        }
    }
    public static Double add(Double v1, Double v2) {
        return add(v1, v2, 2);
    }

    /**
     * 两个Double数相加
     * @param v1
     * @param v2
     * @param newScale  保留位数
     * @return
     */
    public static Double add(Double v1, Double v2, int newScale) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());

        BigDecimal bd = b1.add(b2);
        bd = bd.setScale(newScale, BigDecimal.ROUND_HALF_UP);//2位小数

        return bd.doubleValue();
    }

    //两个数相减
    public static Double sub(int v1, int v2) {
        return sub(Double.parseDouble(v1+""), Double.parseDouble(v2+""), 2);
    }

    //两个数相减
    public static Double sub(String v1, String v2) {
        return sub(Double.parseDouble(v1), Double.parseDouble(v2), 2);
    }
    //两个数相减
    public static Double sub(String v1, String v2, int newScale) {
        if (isNumericData(v1,v2)){
            return sub(Double.parseDouble(v1), Double.parseDouble(v2), newScale);
        }
        else {
            return returnData(v1,v2);
        }
    }
    //两个Double数相减
    public static Double sub(Double v1, Double v2) {
        return sub(v1, v2, 2);
    }
    /**
     * 两个Double数相减
     *
     * @param v1
     * @param v2
     * @return Double
     */
    public static Double sub(Double v1, Double v2, int newScale) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());

        BigDecimal bd = b1.subtract(b2);
        bd = bd.setScale(newScale, BigDecimal.ROUND_HALF_UP);

        return bd.doubleValue();
    }

    //两个数相乘
    public static Double mul(int v1, int v2) {
        return mul(Double.parseDouble(v1+""), Double.parseDouble(v2+""), 2);
    }

    //两个数相乘
    public static Double mul(String v1, String v2) {
        return mul(Double.parseDouble(v1), Double.parseDouble(v2), 2);
    }
    public static Double mul(String v1, String v2, int newScale) {
        if (isNumericData(v1,v2)){
            return mul(Double.parseDouble(v1), Double.parseDouble(v2), newScale);
        }
        else {
            return returnData(v1,v2);
        }
    }
    public static Double mul(Double v1, Double v2) {
        return mul(v1, v2, 2);
    }
    /**
     * 两个Double数相乘
     * @param v1
     * @param v2
     * @return Double
     */
    public static Double mul(Double v1, Double v2, int newScale) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());

        BigDecimal bd = b1.multiply(b2);
        bd = bd.setScale(newScale, BigDecimal.ROUND_HALF_UP);

        return bd.doubleValue();
    }

    //两个数相除
    public static Double div(int v1, int v2) {
        return div(Double.parseDouble(v1+""), Double.parseDouble(v2+""), 2);
    }

    //两个数相除
    public static Double div(String v1, String v2) {
        return div(Double.parseDouble(v1), Double.parseDouble(v2), 2);
    }
    public static Double div(String v1, String v2, int newScale) {
        if (isNumericData(v1,v2)){
            return div(Double.parseDouble(v1), Double.parseDouble(v2), newScale);
        }
        else {
            return returnData(v1,v2);
        }
    }
    public static Double div(Double v1, Double v2) {
        return div(v1, v2, 2);
    }
    /**
     * 两个Double数相除，并保留scale位小数
     *
     * @param v1
     * @param v2
     * @param scale
     * @return Double
     */
    public static Double div(Double v1, Double v2, int scale) {
        if (v2 == 0){
            return 0.00;
        }
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be activity_certification_main positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        BigDecimal bd = b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
    //判断是否为数字
    private static boolean isNumericData(String v1, String v2){
        if (IsNumber.isNumeric3(v1)&&IsNumber.isNumeric3(v2)){
            return true;
        }
        return false;
    }
    //既然不是数字类型，就校验要返回什么
    private static Double returnData(String v1, String v2){
        if (IsNumber.isNumeric3(v1)){
            return Double.parseDouble(v1);
        }
        else if (IsNumber.isNumeric3(v2)){
            return Double.parseDouble(v2);
        }
        else {
            return 0.00;
        }
    }
}