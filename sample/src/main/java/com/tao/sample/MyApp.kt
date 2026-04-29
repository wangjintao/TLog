package com.tao.sample

import android.app.Application
import com.tao.tlog.v2.TLog

/**
 *Author: WangJintao
 * Date: 2026/4/14 15:55
 **/
class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        TLog.init {
            defaultTag = "tao" //日志的tag，全局配置，写日志的时候还可以给每条日志单独设置tag。不设置时默认tag为TLog
        }
    }
}
