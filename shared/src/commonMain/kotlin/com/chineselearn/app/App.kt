package com.chineselearn.app

import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import com.chineselearn.app.ui.screens.DictionaryScreen
import com.chineselearn.app.ui.screens.HomeScreen
import com.chineselearn.app.ui.screens.RadicalsScreen
import com.chineselearn.app.ui.theme.AppTheme

private enum class Page { HOME, DICTIONARY, RADICALS }

@Composable
fun App() = AppTheme {
    var page by remember { mutableStateOf(Page.HOME) }
    var isEn by remember { mutableStateOf(false) }
    Surface {
        when (page) {
            Page.HOME -> HomeScreen(isEn, { isEn = !isEn }, { page = Page.DICTIONARY }, { page = Page.RADICALS })
            Page.DICTIONARY -> DictionaryScreen({ page = Page.HOME }, isEn)
            Page.RADICALS -> RadicalsScreen({ page = Page.HOME }, isEn)
        }
    }
}
