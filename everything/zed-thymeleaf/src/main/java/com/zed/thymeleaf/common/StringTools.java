package com.zed.thymeleaf.common;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 *
 * <p>
 * 驼峰命名法工具
 * 下划线转驼峰:                 toCamelCase(" hello_world ") ==> "helloWorld"
 * 下划线转驼峰首字母大写:        toCapitalizeCamelCase(" hello_world ") ==> "HelloWorld"
 * 驼峰转下划线:                 toUnderScoreCase(" helloWorld ") ==> "hello_world"
 * 驼峰首字母大写转首字母小写:     upper2LowerHump(" HelloWorld ") ==> "helloWorld"
 * 驼峰首字母大写转下划线全部大写: upper2UpperUnderLine(" HelloWorld ") ==> "HELLO_WORLD"
 * 驼峰首字母大写转中横线全部小写: upper2LowerMidLine(" HelloWorld ") ==> "hello-world"
 * </p>
 *
 * @author zed
 */
public class StringTools extends StringUtils {


    /**
     * 用于IP定位转换
     */
    private static final String REGION = "内网IP|内网IP";
    private static final char SEPARATOR = '_';
    private static final String UNKNOWN = "unknown";
    private static final String LOCALHOST = "127.0.0.1";


    /*--start--驼峰命名法工具----*/

    /**
     * 下划线转驼峰
     *
     * @return toCamelCase(" hello_world ") ==> "helloWorld"
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 下划线转驼峰首字母大写
     *
     * @return toCapitalizeCamelCase(" hello_world ") ==> "HelloWorld"
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 驼峰转下划线
     *
     * @return toUnderScoreCase(" helloWorld ") ==> "hello_world"
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }


    /**
     * 驼峰首字母大写转首字母小写
     *
     * @param str
     * @return upper2LowerHump(" HelloWorld ") ==> "helloWorld"
     */
    public static String upper2LowerHump(String str) {
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 驼峰首字母大写转下划线全部大写
     *
     * @param name
     * @return upper2UpperUnderLine(" HelloWorld ") ==> "HELLO_WORLD"
     */
    public static String upper2UpperUnderLine(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toUpperCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString();

    }

    /**
     * 驼峰首字母大写转中横线全部小写
     *
     * @param name
     * @return upper2LowerMidLine(" HelloWorld ") ==> "hello-world"
     */
    public static String upper2LowerMidLine(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toLowerCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("-");
                }
                // 其他字符直接转成大写
                result.append(s.toLowerCase());
            }
        }
        return result.toString();

    }

    /*--end--驼峰命名法工具----*/


}