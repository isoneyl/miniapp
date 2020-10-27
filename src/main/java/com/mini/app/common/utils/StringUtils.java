package com.mini.app.common.utils;

import org.apache.commons.lang3.StringEscapeUtils;

import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: StringUtils
 */
public class StringUtils {

    /**
     * @param data
     * @return java.lang.String
     * @Description 对于请求内容解码
     **/
    public static String replacer(String data) {
        try {
            data = data.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
            data = data.replaceAll("\\+", "%2B");
            data = URLDecoder.decode(data, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * @param builder
     */
    public static String subString(StringBuilder builder) {
        String s = "";
        if (builder == null) {
            return s;
        }
        s = builder.toString();
        if (s.length() > 1) {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }

    /**
     * 去掉字符串中多余逗号
     * 在进行in 关键字进行处理时，如果有进行 ''的加入，则去掉。重新组成
     * 例：,sds,,dsd,||,sds,,,,dsd,
     * after remove:,sds,dsd,
     *
     * @return
     */
    public static String replaceComma(String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }

        //先去掉所有的'
        str = str.replaceAll("'", "");

        // 是否有连续的逗号，有则去掉
        if (str.indexOf(",,") >= 0) {
            while (true) {
                str = str.replace(",,", ",");
                if (str.indexOf(",,") < 0) {
                    break;
                }
            }
        }
        // 去掉开头多余的,
        if (str.startsWith(",")) {
            str = str.substring(1, str.length());
        }
        // 去掉结尾多余的,
        if (str.endsWith(",")) {
            str = str.substring(0, str.length() - 1);
        }
        // 重新组成   '123','321','132'
        if (str.length() > 0) {
            str = str.replaceAll(",", "','");
            str = "'" + str + "'";
        }
        return str;
    }

    /**
     * 提取html中的文本内容
     *
     * @param strHtml
     * @return
     */
    public static String stripHT(String strHtml) {
        if (org.apache.commons.lang3.StringUtils.isBlank(strHtml)) {
            return strHtml;
        }
        String txtcontent = strHtml.replaceAll("</?[^>]+>", "");//剔出<html>的标签    
        txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");//去除字符串中的空格,回车,换行符,制表符    
        return StringEscapeUtils.unescapeHtml4(txtcontent);
    }

    /**
     * @param value
     * @return
     * @Description 判断是否包含中文
     */
    public static boolean hasChinese(String value) {
        // 汉字的Unicode取值范围
        String regex = "[\u4e00-\u9fa5]";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(value);
        return match.find();

    }
}
