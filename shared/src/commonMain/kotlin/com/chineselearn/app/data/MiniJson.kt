package com.chineselearn.app.data

/** Bo doc JSON nho (chi de doc du lieu giao trinh), khong can thu vien ngoai. */
class JObj(private val m: Map<String, Any?>) {
    fun str(k: String): String = (m[k] as? String) ?: ""
    fun int(k: String): Int = (m[k] as? Double)?.toInt() ?: 0
    @Suppress("UNCHECKED_CAST")
    fun arr(k: String): List<JObj> = ((m[k] as? List<Any?>) ?: emptyList()).mapNotNull { (it as? Map<String, Any?>)?.let(::JObj) }
}

object MiniJson {
    fun parse(text: String): JObj {
        val p = P(text)
        @Suppress("UNCHECKED_CAST")
        return JObj(p.value() as Map<String, Any?>)
    }

    private class P(val s: String) {
        var i = 0
        fun ws() { while (i < s.length && s[i].isWhitespace()) i++ }
        fun value(): Any? {
            ws()
            return when (val c = s[i]) {
                '{' -> obj()
                '[' -> list()
                '"' -> str()
                't' -> { i += 4; true }
                'f' -> { i += 5; false }
                'n' -> { i += 4; null }
                else -> {
                    val st = i
                    while (i < s.length && (s[i].isDigit() || s[i] in "+-.eE")) i++
                    if (st == i) error("bad json at $i: $c")
                    s.substring(st, i).toDouble()
                }
            }
        }
        fun obj(): Map<String, Any?> {
            val m = LinkedHashMap<String, Any?>()
            i++; ws()
            if (s[i] == '}') { i++; return m }
            while (true) {
                ws(); val k = str(); ws(); i++ // ':'
                m[k] = value(); ws()
                if (s[i] == ',') { i++; continue }
                i++; return m
            }
        }
        fun list(): List<Any?> {
            val l = ArrayList<Any?>()
            i++; ws()
            if (s[i] == ']') { i++; return l }
            while (true) {
                l.add(value()); ws()
                if (s[i] == ',') { i++; continue }
                i++; return l
            }
        }
        fun str(): String {
            i++
            val sb = StringBuilder()
            while (s[i] != '"') {
                val c = s[i++]
                if (c == '\\') {
                    when (val e = s[i++]) {
                        'n' -> sb.append('\n'); 't' -> sb.append('\t'); 'r' -> sb.append('\r')
                        'b' -> sb.append('\b'); 'f' -> sb.append('\u000C')
                        'u' -> { sb.append(s.substring(i, i + 4).toInt(16).toChar()); i += 4 }
                        else -> sb.append(e)
                    }
                } else sb.append(c)
            }
            i++
            return sb.toString()
        }
    }
}

/** Luu tien do hoc (cac bai da xong) trong file van ban trong thu muc du lieu app. */
object SimpleStore {
    private fun path() = appDataDir().trimEnd('/', '\\') + "/progress.txt"
    private fun all(): MutableMap<String, String> {
        val m = LinkedHashMap<String, String>()
        readTextFile(path())?.lines()?.forEach { ln ->
            val k = ln.substringBefore('=', ""); if (k.isNotEmpty()) m[k] = ln.substringAfter('=')
        }
        return m
    }
    fun get(key: String): String = all()[key] ?: ""
    fun set(key: String, value: String) {
        val m = all(); m[key] = value
        writeTextFile(path(), m.entries.joinToString("\n") { it.key + "=" + it.value })
    }
}

/** iOS ban xem thu chua co am thanh: nut loa tu an. */
class VoicePlayer {
    fun hasAudio(text: String) = false
    fun play(text: String, onDone: () -> Unit = {}) { onDone() }
    fun shutdown() {}
}
