package com.tao.admin.loglib;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：WangJintao
 * 时间：2017/8/26
 * 邮箱：wangjintao1988@163.com
 */

public class FileUtils {

    private static Object obj = new Object();

    public static void writeLogFile(String msg) {
        synchronized (obj) {
            try {
                File file = new File(TLogApplication.getAPP().getFilesDir(), IConfig.fileName);
                FileWriter fw = null;
                if (file.exists()) {
                    if (file.length() > IConfig.getInstance().getFileSize())
                        fw = new FileWriter(file, false);
                    else
                        fw = new FileWriter(file, true);
                } else
                    fw = new FileWriter(file, false);

                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("MM-dd HH:mm:ss");
                String dateStr = s.format(d);
                fw.write(String.format("[%s] %s", dateStr, msg));
                fw.write(13);
                fw.write(10);
                fw.write(10);
                fw.flush();
                fw.close();
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String readLogText() {
        FileReader fr = null;
        try {
            File file = new File(TLogApplication.getAPP().getFilesDir(), IConfig.fileName);
            if (!file.exists()) {
                return "";
            }
            long n = IConfig.getInstance().getFileSize();
            long len = file.length();
            long skip = len - n;
            fr = new FileReader(file);
            fr.skip(Math.max(0, skip));
            char[] cs = new char[(int) Math.min(len, n)];
            fr.read(cs);
            return new String(cs).trim();
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fr != null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
