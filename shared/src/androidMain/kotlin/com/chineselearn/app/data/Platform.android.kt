package com.chineselearn.app.data

import android.content.Context
import java.io.File

object AppContextHolder { lateinit var ctx: Context }

actual fun appDataDir(): String = AppContextHolder.ctx.filesDir.absolutePath
actual fun writeFileBytes(path: String, bytes: ByteArray) { File(path).writeBytes(bytes) }
actual fun fileExists(path: String): Boolean = File(path).exists()
actual fun readTextFile(path: String): String? = File(path).takeIf { it.exists() }?.readText()
actual fun writeTextFile(path: String, text: String) { File(path).writeText(text) }
