package com.tao.tlog.v2.printers

import com.tao.tlog.v2.interfaces.Printer
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 *Author: WangJintao
 * Date: 2026/4/14 10:28
 **/
class FilePrinter(
    private val logDir: File,
    private val maxFileSizeBytes: Long,
    private val maxFileCount: Int
) : Printer {
    companion object {
        private val fileExecutor: ExecutorService = Executors.newSingleThreadExecutor { runnable ->
            Thread(runnable, "TLog-File").apply {
                isDaemon = true
            }
        }
    }

    private val lineDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
    private val fileDateFormat = SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.getDefault())

    override fun println(level: Int, tag: String, msg: String) {
        fileExecutor.execute {
            writeInternal(tag, msg)
        }
    }

    private fun writeInternal(tag: String, msg: String) {
        try {
            if (!logDir.exists()) logDir.mkdirs()

            val time = lineDateFormat.format(Date())
            val log = "$time [$tag] $msg\n"
            val file = resolveCurrentLogFile(log.toByteArray().size.toLong())

            file.appendText(log)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun resolveCurrentLogFile(nextLogSizeBytes: Long): File {
        val logFiles = listLogFiles()
        val latest = logFiles.lastOrNull()
        if (latest != null && latest.length() + nextLogSizeBytes <= maxFileSizeBytes) {
            return latest
        }

        val nextFile = createNewLogFile()
        trimOverflowFiles()
        return nextFile
    }

    private fun createNewLogFile(): File {
        var file = File(logDir, buildFileName(Date()))
        var suffix = 1
        while (file.exists()) {
            file = File(logDir, buildFileName(Date(), suffix++))
        }
        file.createNewFile()
        return file
    }

    private fun buildFileName(date: Date, suffix: Int? = null): String {
        val timestamp = fileDateFormat.format(date)
        val suffixPart = suffix?.let { "_$it" }.orEmpty()
        return "log_$timestamp$suffixPart.txt"
    }

    private fun trimOverflowFiles() {
        val files = listLogFiles()
        if (files.size <= maxFileCount) return

        files.take(files.size - maxFileCount).forEach { file ->
            file.delete()
        }
    }

    private fun listLogFiles(): List<File> {
        return logDir.listFiles { file ->
            file.isFile && file.name.startsWith("log_") && file.name.endsWith(".txt")
        }
            ?.sortedBy { it.name }
            .orEmpty()
    }
}
