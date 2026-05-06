package com.tao.sample

import android.app.Application
import com.tao.tlog.v2.TLog

/**
 *Author: WangJintao
 * Date: 2026/4/14 15:55
 **/
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        TLog.init {
            defaultTag = "AppLog"//全局tag，默认TLog
            enableFileLogging = true//是否开启文件日志存储，默认true
            maxFileSizeBytes = 2L * 1024L * 1024L//单个日志文件大小，默认2M
            maxFileCount = 5//存储的最大日志文件个数,默认5
        }
    }
}
