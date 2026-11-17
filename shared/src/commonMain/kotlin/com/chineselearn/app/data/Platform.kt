package com.chineselearn.app.data

/** Thu muc ghi du lieu rieng cua app tren tung nen tang. */
expect fun appDataDir(): String

/** Ghi file (ghi de). */
expect fun writeFileBytes(path: String, bytes: ByteArray)

expect fun fileExists(path: String): Boolean

expect fun readTextFile(path: String): String?

expect fun writeTextFile(path: String, text: String)
