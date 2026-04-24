package com.tao.tlog.v2

import com.tao.tlog.v2.constants.LogLevel
import com.tao.tlog.v2.interfaces.Printer
import com.tao.tlog.v2.printers.ConsolePrinter
import java.io.File

/**
 *Author: WangJintao
 * Date: 2026/4/13 17:54
 **/
class LoggerConfig {
    companion object {
        const val DEFAULT_MAX_FILE_SIZE_BYTES: Long = 2L * 1024L * 1024L
        const val DEFAULT_MAX_FILE_COUNT: Int = 5
    }

    var defaultTag: String = "TLog"
    var minLevel: Int = LogLevel.DEBUG

    var consolePrinter: Printer? = ConsolePrinter()
    var filePrinter: Printer? = null

    var logDir: File? = null
    var enableFileLogging: Boolean = true
    var maxFileSizeBytes: Long = DEFAULT_MAX_FILE_SIZE_BYTES
    var maxFileCount: Int = DEFAULT_MAX_FILE_COUNT
}
