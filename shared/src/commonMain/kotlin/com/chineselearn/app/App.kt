package com.chineselearn.app

import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import com.chineselearn.app.data.SimpleStore
import com.chineselearn.app.ui.screens.CurriculumScreen
import com.chineselearn.app.ui.screens.GrammarScreen
import com.chineselearn.app.ui.screens.SentenceTopicScreen
import com.chineselearn.app.ui.screens.DictionaryScreen
import com.chineselearn.app.ui.screens.HomeScreen
import com.chineselearn.app.ui.screens.RadicalsScreen
import com.chineselearn.app.ui.theme.AppTheme

private enum class Page { HOME, DICTIONARY, RADICALS, CURRICULUM, GRAMMAR, SENTENCES }

@Composable
fun App() = AppTheme {
    var page by remember { mutableStateOf(Page.HOME) }
    var isEn by remember { mutableStateOf(false) }
    var doneIds by remember { mutableStateOf(SimpleStore.get("doneIds").split(",").filter { it.isNotEmpty() }.toSet()) }
    val toggle: (String, Boolean) -> Unit = { id, on ->
        doneIds = if (on) doneIds + id else doneIds - id
        SimpleStore.set("doneIds", doneIds.joinToString(","))
    }
    Surface {
        when (page) {
            Page.HOME -> HomeScreen(isEn, { isEn = !isEn }) {
                page = when (it) { "dictionary" -> Page.DICTIONARY; "radicals" -> Page.RADICALS; "curriculum" -> Page.CURRICULUM; "grammar" -> Page.GRAMMAR; "sentences" -> Page.SENTENCES; else -> Page.HOME }
            }
            Page.DICTIONARY -> DictionaryScreen({ page = Page.HOME }, isEn)
            Page.RADICALS -> RadicalsScreen({ page = Page.HOME }, isEn)
            Page.CURRICULUM -> CurriculumScreen({ page = Page.HOME }, isEn)
            Page.GRAMMAR -> GrammarScreen({ page = Page.HOME }, doneIds, toggle, isEn)
            Page.SENTENCES -> SentenceTopicScreen({ page = Page.HOME }, doneIds, toggle, isEn)
        }
    }
}
