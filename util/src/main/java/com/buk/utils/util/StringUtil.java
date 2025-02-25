package com.buk.utils.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: 字符串工具
 *
 * @author jiangbk
 * @date 2021/1/26
 **/
@Slf4j
public class StringUtil {

    /**
     * 去除html
     *
     * @param htmlStr
     * @return
     */
    public static String clearHTML(String htmlStr) {
        if (StringUtils.isBlank(htmlStr)) {
            return "";
        }
        htmlStr = StringUtils.trim(htmlStr);
        // 过滤script标签
        String regexScript = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>";
        Pattern patternScript = Pattern.compile(regexScript, Pattern.CASE_INSENSITIVE);
        Matcher matcherScript = patternScript.matcher(htmlStr);
        htmlStr = matcherScript.replaceAll("");
        // 过滤style标签
        String regexStyle = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>";
        Pattern patternStyle = Pattern.compile(regexStyle, Pattern.CASE_INSENSITIVE);
        Matcher matcherStyle = patternStyle.matcher(htmlStr);
        htmlStr = matcherStyle.replaceAll("");
        // 过滤html标签
        String regexHtml = "<[^>]+>";
        Pattern patternHtml = Pattern.compile(regexHtml, Pattern.CASE_INSENSITIVE);
        Matcher matcherHtml = patternHtml.matcher(htmlStr);
        htmlStr = matcherHtml.replaceAll("");
        // 过滤空格
        htmlStr = htmlStr.replaceAll("((&nbsp;)|(\\s+))", " ");
        //
        htmlStr = StringUtils.trim(htmlStr);
        return htmlStr;
    }

    private StringUtil() {
    }
}
