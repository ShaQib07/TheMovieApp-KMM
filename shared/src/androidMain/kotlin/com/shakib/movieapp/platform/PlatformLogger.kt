package com.shakib.movieapp.platform

import android.util.Log

internal actual class PlatformLogger {
    actual fun debug(tag: String, message: String) { Log.d(tag, message) }

    actual fun error(tag: String, message: String) { Log.e(tag, message) }
}