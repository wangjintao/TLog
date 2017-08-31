package com.tao.admin.loglib;

import android.app.Application;
import android.content.Context;

/**
 * 作者：WangJintao
 * 时间：2017/8/26
 * 邮箱：wangjintao1988@163.com
 */

public class TLogApplication extends Application {

    private static Context mContenx;

    public static Context getAPP(){
        return mContenx;
    }

    public static void initialize(Context context){
        mContenx = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContenx = this;
    }
}
