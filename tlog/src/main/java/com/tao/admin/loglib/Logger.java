package com.tao.admin.loglib;

import android.util.Log;

/**
 * 作者：WangJintao
 * 时间：2017/8/26
 * 邮箱：wangjintao1988@163.com
 */

public class Logger {

    private static final int MAX_PRINT_LENGTH = 500;

    public static void i(String msg) {
        i(null, msg);
    }

    public static void i(String title, String msg) {
        String str = formatString(title, msg);
        if (IConfig.getInstance().getIsShowLog())
            printI(IConfig.getInstance().getTag(), str);
        if (IConfig.getInstance().getIsWriteLog())
            FileUtils.writeLogFile(str);
    }

    public static void w(String msg) {
        w(null, msg);
    }

    public static void w(String title, String msg) {
        String str = formatString(title, msg);
        if (IConfig.getInstance().getIsShowLog())
            printW(IConfig.getInstance().getTag(), str);
        if (IConfig.getInstance().getIsWriteLog())
            FileUtils.writeLogFile(str);
    }

    public static void e(String msg) {
        e(null, msg);
    }

    public static void e(String title, String msg) {
        String str = formatString(title, msg);
        if (IConfig.getInstance().getIsShowLog())
            printE(IConfig.getInstance().getTag(), str);
        if (IConfig.getInstance().getIsWriteLog())
            FileUtils.writeLogFile(str);
    }

    private static String formatString(String title, String msg) {
        if (title == null) {
            return msg == null ? "" : msg;
        }
        return String.format("[%s]: %s", title, msg == null ? "" : msg);
    }

    private static void printI(String title, String msg) {
        int length = msg.length();
        if (length > MAX_PRINT_LENGTH) {
            for (int i = 0; i < length; i += MAX_PRINT_LENGTH) {
                if (i + MAX_PRINT_LENGTH < length)
                    Log.i(title, msg.substring(i, i + MAX_PRINT_LENGTH));
                else
                    Log.i(title, msg.substring(i, length));
            }
        } else
            Log.i(title, msg);

    }

    private static void printW(String title, String msg) {
        int length = msg.length();
        if (length > MAX_PRINT_LENGTH) {
            for (int i = 0; i < length; i += MAX_PRINT_LENGTH) {
                if (i + MAX_PRINT_LENGTH < length)
                    Log.w(title, msg.substring(i, i + MAX_PRINT_LENGTH));
                else
                    Log.w(title, msg.substring(i, length));
            }
        } else
            Log.w(title, msg);
    }

    private static void printE(String title, String msg) {
        int length = msg.length();
        if (length > MAX_PRINT_LENGTH) {
            for (int i = 0; i < length; i += MAX_PRINT_LENGTH) {
                if (i + MAX_PRINT_LENGTH < length)
                    Log.e(title, msg.substring(i, i + MAX_PRINT_LENGTH));
                else
                    Log.e(title, msg.substring(i, length));
            }
        } else
            Log.e(title, msg);
    }

}
