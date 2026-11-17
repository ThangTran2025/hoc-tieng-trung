package com.chineselearn.app.data

import java.io.File

actual fun appDataDir(): String = File(System.getProperty("user.home"), ".chineselearn_trial").also { it.mkdirs() }.absolutePath
actual fun writeFileBytes(path: String, bytes: ByteArray) { File(path).writeBytes(bytes) }
actual fun fileExists(path: String): Boolean = File(path).exists()
actual fun readTextFile(path: String): String? = File(path).takeIf { it.exists() }?.readText()
actual fun writeTextFile(path: String, text: String) { File(path).writeText(text) }
