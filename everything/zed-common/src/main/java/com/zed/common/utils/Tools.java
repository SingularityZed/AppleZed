package com.zed.common.utils;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import org.apache.commons.lang3.StringUtils;

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
    public static String underlineToHump(String value) {
        value = value.toLowerCase();
        Matcher matcher = linePattern.matcher(value);
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
     * @param value
     * @return
     */
    public static String humpToUnderline(String value) {
        if (StringUtils.isBlank(value)) {
            return value;
        }
        Matcher matcher = humpPattern.matcher(value);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, StringPool.UNDERSCORE + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        String lineToHump = underlineToHump("f_parent_no_leader");
        // fParentNoLeader
        System.out.println(lineToHump);
        // f_parent_no_leader
        System.out.println(humpToUnderline(lineToHump));
    }
}
