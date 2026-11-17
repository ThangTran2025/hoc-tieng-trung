package com.chineselearn.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.chineselearn.app.data.RadicalGroup
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chineselearn.app.data.DictEntry
import com.chineselearn.app.data.DictionaryRepository
import com.chineselearn.app.data.Radical
import com.chineselearn.app.data.RadicalsData
import com.chineselearn.app.ui.theme.GoldAccent
import com.chineselearn.app.ui.theme.ImperialRedLight

private val RadOrange = Color(0xFFE65100)
private val RadOrangeDark = Color(0xFFBF360C)

private fun strokeColor(n: Int): Color = when {
    n <= 2 -> Color(0xFF1E88E5)
    n <= 4 -> Color(0xFF43A047)
    n <= 6 -> Color(0xFF8E24AA)
    n <= 8 -> Color(0xFFE65100)
    n <= 10 -> Color(0xFFD81B60)
    else -> Color(0xFF00897B)
}

private fun plain(s: String): String {
    val src = "àáảãạăằắẳẵặâầấẩẫậèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựỳýỷỹỵđāáǎàēéěèīíǐìōóǒòūúǔùǖǘǚǜ"
    val dst = "aaaaaaaaaaaaaaaaaeeeeeeeeeeeiiiiiooooooooooooooooouuuuuuuuuuuyyyyydaaaaeeeeiiiioooouuuuuuuu"
    val sb = StringBuilder()
    for (c in s.lowercase()) { val i = src.indexOf(c); sb.append(if (i >= 0) dst[i] else c) }
    return sb.toString()
}

private data class GroupStyle(val icon: ImageVector, val color: Color)

private fun groupStyle(id: String): GroupStyle = when (id) {
    "nature" -> GroupStyle(Icons.Default.Landscape, Color(0xFF2E9E5B))
    "plants" -> GroupStyle(Icons.Default.Park, Color(0xFF7CB342))
    "animals" -> GroupStyle(Icons.Default.Pets, Color(0xFFEF6C00))
    "body" -> GroupStyle(Icons.Default.Person, Color(0xFFD81B60))
    "numbers" -> GroupStyle(Icons.Default.Tag, Color(0xFF1E88E5))
    "colors" -> GroupStyle(Icons.Default.Palette, Color(0xFF8E24AA))
    "food" -> GroupStyle(Icons.Default.Restaurant, Color(0xFFE53935))
    "house" -> GroupStyle(Icons.Default.Home, Color(0xFF6D4C41))
    "tools" -> GroupStyle(Icons.Default.Build, Color(0xFF546E7A))
    "clothing" -> GroupStyle(Icons.Default.Checkroom, Color(0xFF00897B))
    "actions" -> GroupStyle(Icons.Default.DirectionsRun, Color(0xFF3949AB))
    else -> GroupStyle(Icons.Default.Category, Color(0xFFB8860B))
}

@Composable
fun RadicalsScreen(onBack: () -> Unit, isEn: Boolean) {

    var selected by remember { mutableStateOf<Radical?>(null) }
    var group by remember { mutableStateOf<RadicalGroup?>(null) }
    var query by remember { mutableStateOf("") }

    val sel = selected
    if (sel != null) {
        RadicalDetail(
            r = sel, isEn = isEn,
            onBack = { selected = null },
            onPrev = { if (sel.n > 1) selected = RadicalsData.all[sel.n - 2] },
            onNext = { if (sel.n < 214) selected = RadicalsData.all[sel.n] }
        )
        return
    }

    val grp = group
    val filtered = remember(query, isEn) {
        val q = query.trim()
        if (q.isEmpty()) emptyList() else {
            val pq = plain(q)
            RadicalsData.all.filter { r ->
                r.c.contains(q) || r.variants.contains(q) || r.n.toString() == q ||
                    plain(r.pinyin).contains(pq) || plain(r.hanViet).contains(pq) ||
                    plain(r.vi).contains(pq) || (isEn && r.en.lowercase().contains(q.lowercase()))
            }
        }
    }
    val searching = query.isNotBlank()
    val gStyle = grp?.let { groupStyle(it.id) }
    val headColors = if (grp != null && !searching) listOf(gStyle!!.color, gStyle.color.copy(alpha = 0.8f), RadOrangeDark)
        else listOf(RadOrangeDark, RadOrange, GoldAccent)

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Box(modifier = Modifier.fillMaxWidth().background(Brush.linearGradient(headColors)).statusBarsPadding()) {
            Row(Modifier.fillMaxWidth().padding(end = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { if (grp != null) group = null else onBack() }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.White) }
                Text(
                    if (grp != null && !searching) (if (isEn) grp.en else grp.vi) else (if (isEn) "Radicals" else "Bộ Thủ"),
                    fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = Color.White, modifier = Modifier.weight(1f), maxLines = 1
                )
                Text(
                    if (grp != null && !searching) (if (isEn) "${grp.ids.size} radicals" else "${grp.ids.size} bộ") else "214",
                    fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White.copy(alpha = 0.9f)
                )
            }
        }

        LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(14.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item(key = "search") {
                OutlinedTextField(
                    value = query, onValueChange = { query = it },
                    modifier = Modifier.fillMaxWidth().heightIn(min = 46.dp),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    singleLine = true, shape = RoundedCornerShape(14.dp),
                    placeholder = { Text(if (isEn) "Radical, Pinyin or meaning..." else "Bộ thủ, Pinyin, Hán Việt hoặc nghĩa...", fontSize = 13.sp) },
                    leadingIcon = { Icon(Icons.Default.Search, null) },
                    trailingIcon = { if (query.isNotEmpty()) IconButton(onClick = { query = "" }) { Icon(Icons.Default.Clear, null) } }
                )
            }
            when {
                searching -> {
                    if (filtered.isEmpty()) {
                        item { Text(if (isEn) "No radical found" else "Không tìm thấy bộ thủ", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)) }
                    } else {
                        items(filtered, key = { "s" + it.n }) { r -> RadicalRow(r, isEn) { selected = r } }
                    }
                }
                grp != null -> {
                    item(key = "desc") {
                        Text(if (isEn) grp.descEn else grp.descVi, fontSize = 13.sp, lineHeight = 19.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
                    }
                    items(grp.ids, key = { "g$it" }) { id -> RadicalRow(RadicalsData.all[id - 1], isEn) { selected = RadicalsData.all[id - 1] } }
                }
                else -> {
                    items(RadicalsData.groups, key = { it.id }) { g -> GroupCard(g, isEn) { group = g } }
                }
            }
            item { Spacer(Modifier.height(8.dp)) }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun GroupCard(g: RadicalGroup, isEn: Boolean, onClick: () -> Unit) {
    val st = groupStyle(g.id)
    val col = st.color
    val shape = RoundedCornerShape(26.dp)
    val members = remember(g.id) { g.ids.map { RadicalsData.all[it - 1] } }
    Surface(
        modifier = Modifier.fillMaxWidth().shadow(4.dp, shape, ambientColor = col, spotColor = col).clip(shape).clickable(onClick = onClick),
        shape = shape, color = MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(1.dp, col.copy(alpha = 0.3f))
    ) {
        Column(Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier.size(58.dp).clip(RoundedCornerShape(18.dp)).background(Brush.linearGradient(listOf(col, col.copy(alpha = 0.6f)))),
                    contentAlignment = Alignment.Center
                ) { Icon(st.icon, null, tint = Color.White, modifier = Modifier.size(32.dp)) }
                Spacer(Modifier.weight(1f))
                Surface(shape = RoundedCornerShape(20.dp), color = col.copy(alpha = 0.14f)) {
                    Text(if (isEn) "${g.ids.size} radicals" else "${g.ids.size} bộ thủ", color = col, fontWeight = FontWeight.ExtraBold,
                        fontSize = 14.sp, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                }
            }
            Spacer(Modifier.height(14.dp))
            Text(if (isEn) g.en else g.vi, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.onSurface)
            Spacer(Modifier.height(4.dp))
            Text(if (isEn) g.descEn else g.descVi, fontSize = 13.sp, lineHeight = 19.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f))
            Spacer(Modifier.height(14.dp))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                members.take(9).forEach { r ->
                    Surface(shape = RoundedCornerShape(12.dp), color = col.copy(alpha = 0.08f), border = androidx.compose.foundation.BorderStroke(1.dp, col.copy(alpha = 0.2f))) {
                        Row(Modifier.padding(horizontal = 10.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
                            Text(r.c, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                            Spacer(Modifier.width(6.dp))
                            Text(r.hanViet, fontSize = 12.sp, color = col, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
                if (members.size > 9) {
                    Surface(shape = RoundedCornerShape(12.dp), color = col.copy(alpha = 0.08f)) {
                        Text("+${members.size - 9}", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = col, modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp))
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            Box(
                Modifier.fillMaxWidth().height(50.dp).clip(RoundedCornerShape(16.dp)).background(Brush.horizontalGradient(listOf(col, col.copy(alpha = 0.7f)))),
                contentAlignment = Alignment.Center
            ) {
                Text(if (isEn) "View radicals  ›" else "Xem các bộ  ›", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 15.sp)
            }
        }
    }
}

@Composable
private fun RadicalRow(r: Radical, isEn: Boolean, onClick: () -> Unit) {
    val col = strokeColor(r.strokes)
    val shape = RoundedCornerShape(18.dp)
    Surface(
        modifier = Modifier.fillMaxWidth().shadow(2.dp, shape, ambientColor = col, spotColor = col).clip(shape).clickable(onClick = onClick),
        shape = shape, color = MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(1.dp, col.copy(alpha = 0.25f))
    ) {
        Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(64.dp).clip(RoundedCornerShape(16.dp)).background(col.copy(alpha = 0.10f)), contentAlignment = Alignment.Center) {
                Text(r.c, fontSize = 36.sp, fontWeight = FontWeight.Bold, color = col)
            }
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(r.hanViet, fontSize = 17.sp, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(Modifier.width(8.dp))
                    Text(r.pinyin, fontSize = 13.sp, color = col, fontWeight = FontWeight.SemiBold)
                    if (r.variants.isNotEmpty()) {
                        Spacer(Modifier.width(8.dp))
                        Text(r.variants.map { it.toString() }.joinToString(" "), fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                    }
                }
                Spacer(Modifier.height(2.dp))
                Text(if (isEn) r.en else r.vi, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f), maxLines = 2)
            }
            Spacer(Modifier.width(8.dp))
            Surface(shape = RoundedCornerShape(10.dp), color = col.copy(alpha = 0.14f)) {
                Text(if (isEn) "${r.strokes} str." else "${r.strokes} nét", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = col,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
            }
        }
    }
}

@Composable
private fun RadicalCell(r: Radical, modifier: Modifier, onClick: () -> Unit) {
    val col = strokeColor(r.strokes)
    val shape = RoundedCornerShape(16.dp)
    Surface(
        modifier = modifier.shadow(2.dp, shape, ambientColor = col, spotColor = col).clip(shape).clickable(onClick = onClick),
        shape = shape, color = MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(1.dp, col.copy(alpha = 0.3f))
    ) {
        Column(Modifier.padding(vertical = 10.dp, horizontal = 4.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("${r.n}", fontSize = 9.sp, color = col, fontWeight = FontWeight.Bold)
            Text(r.c, fontSize = 34.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            Text(r.pinyin, fontSize = 11.sp, color = col, fontWeight = FontWeight.SemiBold)
            Text(r.hanViet, fontSize = 10.sp, maxLines = 1, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        }
    }
}

@Composable
private fun RadicalDetail(
    r: Radical, isEn: Boolean, onBack: () -> Unit, onPrev: () -> Unit, onNext: () -> Unit
) {
    val col = strokeColor(r.strokes)
    val forms = remember(r.n) { (listOf(r.c) + r.variants.map { it.toString() }).distinct() }
    var chars by remember(r.n) { mutableStateOf<List<DictEntry>>(emptyList()) }
    LaunchedEffect(r.n) {
        chars = try { DictionaryRepository.init(); DictionaryRepository.radicalChars(forms) } catch (e: Throwable) { emptyList() }
    }

    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Box(
            Modifier.fillMaxWidth().background(Brush.linearGradient(listOf(col, col.copy(alpha = 0.8f)))).statusBarsPadding()
        ) {
            Row(Modifier.fillMaxWidth().padding(start = 4.dp, end = 12.dp, top = 2.dp, bottom = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.White) }
                Surface(shape = RoundedCornerShape(18.dp), color = Color.White, modifier = Modifier.size(68.dp).shadow(4.dp, RoundedCornerShape(18.dp))) {
                    Box(contentAlignment = Alignment.Center) { Text(r.c, fontSize = 42.sp, fontWeight = FontWeight.Bold, color = col) }
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(r.hanViet, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                    Text(r.pinyin, fontSize = 15.sp, color = Color.White.copy(alpha = 0.92f))
                    Text("${r.n} / 214", fontSize = 11.sp, color = Color.White.copy(alpha = 0.8f))
                }
            }
        }

        LazyColumn(Modifier.weight(1f), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item {
                InfoCard(col) {
                    InfoRow(if (isEn) "Meaning" else "Ý nghĩa", if (isEn) r.en else r.vi, col)
                    InfoRow(if (isEn) "Strokes" else "Số nét", r.strokes.toString(), col)
                    InfoRow(if (isEn) "Hán Việt" else "Âm Hán Việt", r.hanViet, col)
                    InfoRow(if (isEn) "Variants" else "Dạng biến thể", if (r.variants.isEmpty()) (if (isEn) "None" else "Không có") else r.variants.map { it.toString() }.joinToString("  "), col)
                }
            }
            item {
                Text(if (isEn) "Characters with this radical" else "Chữ thuộc bộ này", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface)
            }
            if (chars.isEmpty()) {
                item { Text(if (isEn) "Coming soon" else "Đang cập nhật", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f)) }
            } else {
                items(chars.chunked(3)) { row ->
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        row.forEach { e -> CharCell(e, isEn, col, Modifier.weight(1f)) }
                        repeat(3 - row.size) { Spacer(Modifier.weight(1f)) }
                    }
                }
            }
        }

        Row(
            Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface).navigationBarsPadding().padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(onClick = onPrev, enabled = r.n > 1, modifier = Modifier.weight(1f), shape = RoundedCornerShape(14.dp)) {
                Text(if (isEn) "‹ Previous" else "‹ Trước")
            }
            Button(onClick = onNext, enabled = r.n < 214, modifier = Modifier.weight(1f), shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = col)) {
                Text(if (isEn) "Next ›" else "Sau ›")
            }
        }
    }
}

@Composable
private fun InfoCard(col: Color, content: @Composable ColumnScope.() -> Unit) {
    val shape = RoundedCornerShape(20.dp)
    Surface(Modifier.fillMaxWidth().shadow(3.dp, shape, ambientColor = col, spotColor = col), shape = shape,
        color = MaterialTheme.colorScheme.surface, border = androidx.compose.foundation.BorderStroke(1.dp, col.copy(alpha = 0.25f))) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp), content = content)
    }
}

@Composable
private fun InfoRow(label: String, value: String, col: Color) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
        Text(label, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = col, modifier = Modifier.width(104.dp))
        Text(value, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun CharCell(e: DictEntry, isEn: Boolean, col: Color, modifier: Modifier) {
    val shape = RoundedCornerShape(14.dp)
    val mean = if (isEn) e.en else e.vi
    Surface(modifier.clip(shape), shape = shape, color = col.copy(alpha = 0.07f),
        border = androidx.compose.foundation.BorderStroke(1.dp, col.copy(alpha = 0.2f))) {
        Column(Modifier.padding(8.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(e.s, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            Text(e.pinyin, fontSize = 11.sp, color = col, fontWeight = FontWeight.SemiBold, maxLines = 1)
            Text(mean.ifBlank { if (isEn) "Coming soon" else "Đang cập nhật" }, fontSize = 10.sp, maxLines = 2, textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f))
        }
    }
}
