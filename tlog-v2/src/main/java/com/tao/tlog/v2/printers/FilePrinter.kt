package com.tao.tlog.v2.printers

import com.tao.tlog.v2.interfaces.Printer
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 *Author: WangJintao
 * Date: 2026/4/14 10:28
 **/
class FilePrinter(private val logDir: File) : Printer {
    private val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
    override fun println(level: Int, tag: String, msg: String) {
        try {
            if (!logDir.exists()) logDir.mkdirs()

            val file = File(logDir, getFileName())
            val time = sdf.format(Date())

            val log = "$time [$tag] $msg\n"

            file.appendText(log)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getFileName(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return "log_${sdf.format(Date())}.txt"
    }
}