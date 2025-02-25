package com.buk.advicecontroller.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * TODO: 异常工具
 *
 * @author BuK
 * @since 2020/08/21
 */
public class ExceptionUtil {

    /**
     * 堆栈跟踪
     *
     * @param exception
     * @return
     */
    public static String getStackTrace(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        // 清空缓冲区
        printWriter.flush();
        stringWriter.flush();
        //
        return stringWriter.toString();
    }
}
