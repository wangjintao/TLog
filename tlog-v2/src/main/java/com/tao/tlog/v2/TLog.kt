package com.tao.tlog.v2

import com.tao.tlog.v2.constants.LogLevel
import com.tao.tlog.v2.enums.LogStrategy
import com.tao.tlog.v2.loggers.FileLogger
import com.tao.tlog.v2.loggers.Logger

/**
 *Author: WangJintao
 * Date: 2026/4/13 17:53
 **/
object TLog {

    private val lock = Any()
    private lateinit var logger: Logger
    private var defaultTag: String = "TLog"
    private val tempTag = ThreadLocal<String?>()

    fun init(block: LoggerConfig.() -> Unit) {
        val config = LoggerConfig().apply(block)

        synchronized(lock) {
            logger = createLogger(config)
            defaultTag = config.defaultTag
        }
    }
    private fun createLogger(config: LoggerConfig): Logger {
        return Logger(
            defaultTag = config.defaultTag,
            minLevel = config.minLevel,
            consolePrinter = config.consolePrinter,
            filePrinter = config.filePrinter
        )
    }

    private fun get(): Logger {
        if (!::logger.isInitialized) {
            synchronized(lock) {
                if (!::logger.isInitialized) {
                    val config = LoggerConfig()
                    logger = createLogger(config)
                    defaultTag = config.defaultTag
                }
            }
        }
        return logger
    }

    fun tag(tag: String): TLog {
        tempTag.set(tag)
        return this
    }

    private fun consumeTag(): String {
        val tag = tempTag.get()
        tempTag.remove()
        return tag ?: defaultTag
    }

    fun v(msg: String) {
        get().log(LogLevel.VERBOSE, consumeTag(), msg, LogStrategy.CONSOLE_ONLY)
    }

    fun d(msg: String) {
        get().log(LogLevel.DEBUG, consumeTag(), msg, LogStrategy.CONSOLE_ONLY)
    }

    fun i(msg: String) {
        get().log(LogLevel.INFO, consumeTag(), msg, LogStrategy.CONSOLE_ONLY)
    }

    fun w(msg: String) {
        get().log(LogLevel.WARN, consumeTag(), msg, LogStrategy.CONSOLE_ONLY)
    }

    fun e(msg: String) {
        get().log(LogLevel.ERROR, consumeTag(), msg, LogStrategy.CONSOLE_ONLY)
    }

    fun e(msg: String, tr: Throwable) {
        get().log(
            LogLevel.ERROR,
            consumeTag(),
            "$msg\n${tr.stackTraceToString()}",
            LogStrategy.CONSOLE_ONLY
        )
    }


    fun file(): FileLogger {
        return FileLogger(get(), consumeTag())
    }

}
