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
            defaultTag = "SampleApp"
        }
    }
}
