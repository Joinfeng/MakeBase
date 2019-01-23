package com.mark.common.util;

import java.util.regex.Pattern;

/**
 * @author Marks
 * @date 2018/7/11 14:33
 * email: mottoboy@126.com
 * describe:
 * <li>正则</li>
 */
public class RegularUtil {

    public static void main(String[] args) {
        System.out.println("..:" + isInteger("11"));
    }

    /**
     * 正整数
     */
    private static String INTEGER_REGEX = "[0-9]+";
    private static String EMAIL_REGEX = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
    private static String ID_REGEX = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
    private static String MOBILE_REGEX = "(\\+\\d+)?1[3458]\\d{9}$";
    private static String CHINESE_REGEX = "^[\\u4e00-\\u9fa5]+$";


    public static boolean isInteger(String s) {
        return Pattern.matches(INTEGER_REGEX, s);
    }

}
