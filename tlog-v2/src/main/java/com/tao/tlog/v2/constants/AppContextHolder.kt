package com.tao.tlog.v2.constants

import android.content.Context

internal object AppContextHolder {
    @Volatile
    private var appContext: Context? = null

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    fun get(): Context? = appContext
}