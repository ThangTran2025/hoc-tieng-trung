package com.chineselearn.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.chineselearn.app.App

fun main() = application { Window(onCloseRequest = ::exitApplication, title = "ChineseLearn Trial") { App() } }
