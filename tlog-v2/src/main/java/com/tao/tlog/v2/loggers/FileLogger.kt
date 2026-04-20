package com.tao.tlog.v2.loggers

import com.tao.tlog.v2.constants.LogLevel
import com.tao.tlog.v2.enums.LogStrategy

/**
 *Author: WangJintao
 * Date: 2026/4/14 15:38
 **/
class FileLogger  internal constructor(
    private val logger: Logger,
    private val tag: String
) {
    fun d(msg: String) {
        logger.log(LogLevel.DEBUG, tag, msg, LogStrategy.CONSOLE_AND_FILE)
    }

    fun e(msg: String) {
        logger.log(LogLevel.ERROR, tag, msg, LogStrategy.CONSOLE_AND_FILE)
    }

    fun e(msg: String, tr: Throwable) {
        logger.log(
            LogLevel.ERROR,
            tag,
            "$msg\n${tr.stackTraceToString()}",
            LogStrategy.CONSOLE_AND_FILE
        )
    }
}