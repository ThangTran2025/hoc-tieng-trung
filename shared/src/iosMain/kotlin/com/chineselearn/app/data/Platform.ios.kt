package com.chineselearn.app.data

import kotlinx.cinterop.*
import platform.Foundation.*
import platform.posix.memcpy

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
actual fun appDataDir(): String {
    val dirs = NSSearchPathForDirectoriesInDomains(NSApplicationSupportDirectory, NSUserDomainMask, true)
    val dir = dirs.first() as String
    NSFileManager.defaultManager.createDirectoryAtPath(dir, true, null, null)
    return dir
}

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
actual fun writeFileBytes(path: String, bytes: ByteArray) {
    val data = bytes.usePinned { NSData.create(bytes = it.addressOf(0), length = bytes.size.toULong()) }
    data.writeToFile(path, true)
}

actual fun fileExists(path: String): Boolean = NSFileManager.defaultManager.fileExistsAtPath(path)

@OptIn(BetaInteropApi::class)
actual fun readTextFile(path: String): String? =
    NSString.stringWithContentsOfFile(path, NSUTF8StringEncoding, null)

@OptIn(BetaInteropApi::class)
actual fun writeTextFile(path: String, text: String) {
    (text as NSString).writeToFile(path, true, NSUTF8StringEncoding, null)
}
