package com.chineselearn.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chineselearn.app.data.DictEntry
import com.chineselearn.app.data.DictionaryRepository

@Composable
fun DictionaryScreen(onBack: () -> Unit, isEn: Boolean) {
    var ready by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    var query by remember { mutableStateOf("") }
    var results by remember { mutableStateOf<List<DictEntry>>(emptyList()) }

    LaunchedEffect(Unit) {
        try { DictionaryRepository.init(); ready = true } catch (e: Throwable) { error = e.toString() }
    }
    LaunchedEffect(query, ready) { if (ready) results = DictionaryRepository.search(query) }

    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Box(Modifier.fillMaxWidth().background(Brush.linearGradient(listOf(Color(0xFF1565C0), Color(0xFF1E88E5)))).statusBarsPadding()) {
            Row(Modifier.fillMaxWidth().padding(end = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.White) }
                Text(if (isEn) "Dictionary" else "Từ Điển", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
            }
        }
        Column(Modifier.padding(14.dp)) {
            error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
            OutlinedTextField(
                query, { query = it }, Modifier.fillMaxWidth(), singleLine = true, shape = RoundedCornerShape(14.dp),
                leadingIcon = { Icon(Icons.Default.Search, null) },
                placeholder = { Text(if (isEn) "Search hanzi, pinyin, meaning..." else "Tìm Hán tự, pinyin, nghĩa...", fontSize = 13.sp) }
            )
            Spacer(Modifier.height(8.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(results) { e ->
                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(12.dp)) {
                            Text(e.s + if (e.t != e.s) " (${e.t})" else "", fontSize = 26.sp, fontWeight = FontWeight.Bold)
                            Text(e.pinyin + if (e.hsk > 0) "  · HSK ${e.hsk}" else "", fontSize = 14.sp, color = MaterialTheme.colorScheme.primary)
                            if (!isEn && e.hv.isNotBlank()) Text("Hán Việt: ${e.hv}", fontSize = 13.sp)
                            val m = if (isEn) e.en else e.vi
                            Text(m.ifBlank { if (isEn) "Coming soon" else "Đang cập nhật" }, fontSize = 15.sp)
                        }
                    }
                }
            }
        }
    }
}
