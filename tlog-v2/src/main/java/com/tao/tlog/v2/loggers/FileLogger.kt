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
    fun v(msg: String) {
        logger.log(LogLevel.VERBOSE, tag, msg, LogStrategy.CONSOLE_AND_FILE)
    }

    fun d(msg: String) {
        logger.log(LogLevel.DEBUG, tag, msg, LogStrategy.CONSOLE_AND_FILE)
    }

    fun i(msg: String) {
        logger.log(LogLevel.INFO, tag, msg, LogStrategy.CONSOLE_AND_FILE)
    }

    fun w(msg: String) {
        logger.log(LogLevel.WARN, tag, msg, LogStrategy.CONSOLE_AND_FILE)
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
