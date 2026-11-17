package com.chineselearn.app.data

import androidx.sqlite.SQLiteConnection
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.sqlite.driver.bundled.SQLITE_OPEN_READONLY
import com.chineselearn.app.resources.Res

data class DictEntry(
    val s: String, val t: String, val pinyin: String,
    val en: String, val vi: String, val hv: String, val hsk: Int,
)

/** Phai bang DB_VERSION cua ban Android. */
const val DB_VERSION = 20

object DictionaryRepository {
    private var conn: SQLiteConnection? = null

    suspend fun init() {
        if (conn != null) return
        val dir = appDataDir()
        val path = "$dir/dictionary.db"
        val marker = "$dir/dictionary.ver"
        if (!fileExists(path) || readTextFile(marker)?.trim() != DB_VERSION.toString()) {
            writeFileBytes(path, Res.readBytes("files/dictionary.db"))
            writeTextFile(marker, DB_VERSION.toString())
        }
        conn = BundledSQLiteDriver().open(path, SQLITE_OPEN_READONLY)
    }

    fun totalWords(): Int {
        val st = conn!!.prepare("SELECT COUNT(*) FROM words")
        return try { if (st.step()) st.getLong(0).toInt() else 0 } finally { st.close() }
    }

    private fun fold(s: String): String {
        val src = "àáảãạăằắẳẵặâầấẩẫậèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựỳýỷỹỵđ"
        val dst = "aaaaaaaaaaaaaaaaaeeeeeeeeeeeiiiiiooooooooooooooooouuuuuuuuuuuyyyyyd"
        val sb = StringBuilder()
        for (c in s.lowercase()) { val i = src.indexOf(c); sb.append(if (i >= 0) dst[i] else c) }
        return sb.toString()
    }

    fun search(raw: String, limit: Int = 80): List<DictEntry> {
        val q = raw.trim()
        if (q.isEmpty()) return emptyList()
        val f = fold(q)
        val sql = """SELECT s,t,pt,en,vi,hv,hsk FROM words
            WHERE s=?1 OR t=?1 OR pf LIKE ?2 OR pn LIKE ?2 OR hvf LIKE ?2 OR vif LIKE ?3 OR en LIKE ?3
            ORDER BY (s=?1 OR t=?1) DESC, (hsk>0) DESC, hsk ASC, rk ASC LIMIT ?4"""
        val st = conn!!.prepare(sql)
        val out = ArrayList<DictEntry>()
        try {
            st.bindText(1, q); st.bindText(2, "$f%"); st.bindText(3, "%$q%"); st.bindLong(4, limit.toLong())
            while (st.step()) out.add(DictEntry(
                st.getText(0), st.getText(1), st.getText(2), st.getText(3),
                st.getText(4), st.getText(5), st.getLong(6).toInt()))
        } finally { st.close() }
        return out
    }

    /** Chu don thuoc mot bo thu (cot rad), uu tien HSK roi do pho bien. */
    fun radicalChars(forms: List<String>, limit: Int = 48): List<DictEntry> {
        if (forms.isEmpty()) return emptyList()
        val where = forms.indices.joinToString(" OR ") { "rad = ?${it * 2 + 1} OR rad LIKE ?${it * 2 + 2}" }
        val st = conn!!.prepare(
            "SELECT s,t,pt,en,vi,hv,hsk FROM words WHERE length(s)=1 AND ($where) " +
                "ORDER BY CASE WHEN hsk=0 THEN 9 ELSE hsk END, rk LIMIT $limit"
        )
        val out = ArrayList<DictEntry>()
        try {
            forms.forEachIndexed { i, f -> st.bindText(i * 2 + 1, f); st.bindText(i * 2 + 2, "$f %") }
            while (st.step()) out.add(DictEntry(
                st.getText(0), st.getText(1), st.getText(2), st.getText(3),
                st.getText(4), st.getText(5), st.getLong(6).toInt()))
        } finally { st.close() }
        return out
    }
}
