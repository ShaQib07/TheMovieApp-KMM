package com.shakib.movieapp.platform

import platform.Foundation.NSLog

internal actual class PlatformLogger {

    actual fun debug(tag: String, message: String) { NSLog("$tag | $message") }

    actual fun error(tag: String, message: String) { NSLog("$tag | $message") }
}
