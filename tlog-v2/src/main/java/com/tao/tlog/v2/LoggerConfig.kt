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
    var defaultTag: String = "TLog"
    var minLevel: Int = LogLevel.DEBUG

    var consolePrinter: Printer? = ConsolePrinter()
    var filePrinter: Printer? = null

    var logDir: File? = null
}