package com.tao.tlog.v2.printers

import android.util.Log
import com.tao.tlog.v2.constants.LogLevel
import com.tao.tlog.v2.interfaces.Printer
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 *Author: WangJintao
 * Date: 2026/4/14 10:25
 **/
class ConsolePrinter : Printer {
    companion object {
        private const val MAX_LOG_LENGTH = 3000
        private val logExecutor: ExecutorService = Executors.newSingleThreadExecutor { runnable ->
            Thread(runnable, "TLog-Console").apply {
                isDaemon = true
            }
        }
    }

    override fun println(level: Int, tag: String, msg: String) {
        logExecutor.execute {
            printInternal(level, tag, msg)
        }
    }

    private fun printInternal(level: Int, tag: String, msg: String) {
        if (msg.isEmpty()) {
            printChunk(level, tag, msg)
            return
        }

        if (msg.length <= MAX_LOG_LENGTH && msg.indexOf('\n') == -1) {
            printChunk(level, tag, msg)
            return
        }

        val length = msg.length
        var lineStart = 0
        while (lineStart < length) {
            val lineEnd = msg.indexOf('\n', lineStart).let { if (it == -1) length else it }
            printRange(level, tag, msg, lineStart, lineEnd)
            lineStart = lineEnd + 1
        }

        if (msg[length - 1] == '\n') {
            printChunk(level, tag, "")
        }
    }

    private fun printRange(level: Int, tag: String, msg: String, start: Int, end: Int) {
        if (start >= end) {
            printChunk(level, tag, "")
            return
        }

        var chunkStart = start
        while (chunkStart < end) {
            val chunkEnd = minOf(chunkStart + MAX_LOG_LENGTH, end)
            printChunk(level, tag, msg.substring(chunkStart, chunkEnd))
            chunkStart = chunkEnd
        }
    }

    private fun printChunk(level: Int, tag: String, msg: String) {
        Log.println(toAndroidPriority(level), tag, msg)
    }

    private fun toAndroidPriority(level: Int): Int {
        return when (level) {
            LogLevel.VERBOSE -> Log.VERBOSE
            LogLevel.DEBUG -> Log.DEBUG
            LogLevel.INFO -> Log.INFO
            LogLevel.WARN -> Log.WARN
            LogLevel.ERROR -> Log.ERROR
            else -> Log.DEBUG
        }
    }
}
