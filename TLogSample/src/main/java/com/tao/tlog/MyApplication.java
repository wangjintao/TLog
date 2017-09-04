package com.tao.tlog;

import android.app.Application;

import com.tao.admin.loglib.IConfig;
import com.tao.admin.loglib.TLogApplication;

/**
 * 作者：WangJintao
 * 时间：2017/8/26
 * 邮箱：wangjintao1988@163.com
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TLogApplication.initialize(this);
        IConfig.getInstance().isShowLog(true)//是否在logcat中打印log,默认不打印
        .isWriteLog(true)//是否在文件中记录，默认不记录
        .fileSize(100000)//日志文件的大小，默认0.1M
        .tag("myTag");//logcat 日志过滤tag
    }
}
