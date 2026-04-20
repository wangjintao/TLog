package com.tao.tlog.v2.printers

import android.util.Log
import com.tao.tlog.v2.constants.LogLevel
import com.tao.tlog.v2.interfaces.Printer

/**
 *Author: WangJintao
 * Date: 2026/4/14 10:25
 **/
class ConsolePrinter: Printer {
    override fun println(level: Int, tag: String, msg: String) {
        when (level) {
            LogLevel.VERBOSE -> Log.v(tag, msg)
            LogLevel.DEBUG -> Log.d(tag, msg)
            LogLevel.INFO -> Log.i(tag, msg)
            LogLevel.WARN -> Log.w(tag, msg)
            LogLevel.ERROR -> Log.e(tag, msg)
        }
    }
}