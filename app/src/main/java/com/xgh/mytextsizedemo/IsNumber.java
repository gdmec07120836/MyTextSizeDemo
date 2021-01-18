package com.xgh.mytextsizedemo;

import android.text.TextUtils;

/**
 * 创建人：xgh
 * 创建时间：2018/11/9 9:59
 * 类描述：判断字符串是否能转成数字
 * 备注：来自：https://blog.csdn.net/qq_36771269/article/details/79506443
 */
public class IsNumber {

    /**
     * String 转 int
     * @param stringData
     * @return
     */
    public static int StringToNumber(String stringData){
        if (isNumeric2(stringData)){
            return (int) Double.parseDouble(stringData);
        }
        return 0;
    }

    /**
     * string转double
     * @param stringData
     * @return
     */
    public static double StringToDouble(String stringData){
        if (isNumeric2(stringData)){
            return Double.parseDouble(stringData);
        }
        return 0;
    }

    public static boolean isNumeric1(String stringData) {
        //1  用正则表达式   判断其是否是数字
        if (!TextUtils.isEmpty(stringData)){
            return false;
        }
        char[] str = stringData.toCharArray();
        String res=String.valueOf(str);
        return res.matches("[\\+-]?[0-9]*(\\.[0-9])?([eE][\\+-]?[0-9]+)?");
        /**
         * 正则表达式说明:
         * [\+-]?  + -号可出现也可不出现
         * [0-9]*  整数部分是否出现    [0-9]可以用\\d代替
         * (\.[0-9])?  出现小数点后面必须跟数字
         * ([eE][\+-]?[0-9]+)  若有指数部分E或e肯定出现 + -可以不出现
         *                      紧接着可以跟着整数，也可以什么都没有
         */
    }

    public static boolean isNumeric2(String stringData) {
        //2  看它是否能转化为一个数
        if (TextUtils.isEmpty(stringData)){
            return false;
        }
        char[] str = stringData.toCharArray();
        try {
            double db=Double.parseDouble(String.valueOf(str));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //用自动机来判断  开辟新思路
    public static boolean isNumeric3(String stringData) {
        //3 作标记把所有可能的情况都考虑到
        if (!TextUtils.isEmpty(stringData)){
            return false;
        }
        char[] str = stringData.toCharArray();
        boolean sign=false;//+ -是否出现过
        boolean Ee=false;//E e 是否出现过
        boolean decimal=false;//小数点是否出现过
        int len=str.length;
        for(int i=0;i<len;i++) {
            if(str[i]=='E'||str[i]=='e') {
                //e后面一定要有数字
                if(i==len-1) {
                    return false;
                }
                if(Ee) {//不能存在两个E
                    return false;
                }
                Ee=true;
            } else if(str[i]=='+'||str[i]=='-') {
                //第二次出现+ -符号必须跟在E后面
                if(sign&&str[i-1]!='e'&&str[i-1]!='E') {
                    return false;
                }
                //第一次出现+ -号且不是在字符串开头，也必须紧跟在e后面
                if(!sign&&i>0&&str[i-1]!='e'&&str[i-1]!='E') {
                    return false;
                }
                sign=true;
            } else if(str[i]=='.') {
                //e后面不能接小数点 小数点不能出现两次
                if(decimal||Ee) {
                    return false;
                }
                decimal=true;
            } else if(str[i]<'0'||str[i]>'9') {//不合法字符
                return false;
            }
        }
        return true;
    }
}
