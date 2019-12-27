package com.zed.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Tools
 *
 * @Author: zed
 * @Date: 2019/12/16 10:07
 */
public class Tools {
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 下划线转驼峰
     */
    public static String underlineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线
     *
     * @param str
     * @return
     */
    public static String humpToUnderline(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        String lineToHump = underlineToHump(" SSss ");
        System.out.println(lineToHump);// fParentNoLeader
        System.out.println(humpToUnderline(lineToHump));// f_parent_no_leader
    }
}
