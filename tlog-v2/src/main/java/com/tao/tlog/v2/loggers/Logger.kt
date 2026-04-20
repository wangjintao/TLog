package com.tao.tlog.v2.loggers

import com.tao.tlog.v2.enums.LogStrategy
import com.tao.tlog.v2.interfaces.Printer

/**
 *Author: WangJintao
 * Date: 2026/4/14 14:15
 **/
class Logger internal constructor( val defaultTag: String,
    private val minLevel: Int, private val consolePrinter: Printer?,
    private val filePrinter: Printer?
){
    internal fun log(level: Int, tag: String,
        msg: String, strategy: LogStrategy
    ) {
        if (level < minLevel) return

        consolePrinter?.println(level, tag, msg)

        // 写文件
        if (strategy == LogStrategy.CONSOLE_AND_FILE) {
            filePrinter?.println(level, tag, msg)
        }
    }
}