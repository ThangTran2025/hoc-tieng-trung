package com.chineselearn.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.School
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(isEn: Boolean, onToggleLang: () -> Unit, onOpen: (String) -> Unit) {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).background(MaterialTheme.colorScheme.background).statusBarsPadding().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("CHINESELEARN", fontSize = 20.sp, fontWeight = FontWeight.Black, color = Color(0xFFC62828))
            Spacer(Modifier.weight(1f))
            TextButton(onClick = onToggleLang) { Text(if (isEn) "VI" else "EN", fontWeight = FontWeight.Bold) }
        }
        Spacer(Modifier.height(10.dp))
        Box(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(26.dp))
                .background(Brush.linearGradient(listOf(Color(0xFFB71C1C), Color(0xFFE53935), Color(0xFFFFA000))))
                .padding(22.dp)
        ) {
            Column {
                Text("学", fontSize = 56.sp, fontWeight = FontWeight.Black, color = Color.White)
                Text(if (isEn) "Learn Chinese offline" else "Học tiếng Trung ngoại tuyến", fontSize = 17.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                Text(if (isEn) "iOS preview" else "Bản xem thử iOS", fontSize = 12.sp, color = Color.White.copy(alpha = 0.85f))
            }
        }
        Spacer(Modifier.height(18.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            Tile(Modifier.weight(1f), if (isEn) "Chinese Course" else "Giáo trình", Icons.Default.School, Color(0xFF43A047)) { onOpen("curriculum") }
            Tile(Modifier.weight(1f), if (isEn) "Dictionary" else "Từ Điển", Icons.Default.Book, Color(0xFF1E88E5)) { onOpen("dictionary") }
        }
        Spacer(Modifier.height(14.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            Tile(Modifier.weight(1f), if (isEn) "Radicals" else "Bộ Thủ", Icons.Default.GridView, Color(0xFFE65100)) { onOpen("radicals") }
            Spacer(Modifier.weight(1f))
        }
    }
}

@Composable
private fun Tile(modifier: Modifier, title: String, icon: ImageVector, color: Color, onClick: () -> Unit) {
    val shape = RoundedCornerShape(22.dp)
    Column(
        modifier.clip(shape).background(Brush.linearGradient(listOf(color, color.copy(alpha = 0.7f)))).clickable(onClick = onClick).padding(18.dp)
    ) {
        Icon(icon, null, tint = Color.White, modifier = Modifier.size(34.dp))
        Spacer(Modifier.height(24.dp))
        Text(title, color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 17.sp)
    }
}
