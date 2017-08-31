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
        IConfig.SHOW_LOG = true;
        IConfig.WRITE_LOG = true;
    }
}
