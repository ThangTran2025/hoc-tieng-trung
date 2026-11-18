package com.chineselearn.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.border
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.School
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.font.FontStyle
import com.chineselearn.app.data.VoicePlayer
import com.chineselearn.app.data.MiniJson
import com.chineselearn.app.data.JObj
import com.chineselearn.app.data.SimpleStore
import com.chineselearn.app.resources.Res
import androidx.compose.runtime.produceState

/**
 * Giao trinh Han ngu / Chinese Course.
 * Du lieu nam o assets/curriculum/hsk1.json ... hsk6.json (khong de trong code).
 * Cap chua co file se hien "Dang cap nhat...". Them bai: dung skill chineselearn-giaotrinh-update.
 */
private data class CExample(val zh: String, val py: String, val vi: String, val en: String)
private data class CGrammar(val tvi: String, val ten: String, val dvi: String, val den: String, val ex: List<CExample>)
private data class CDialogue(val sp: String, val ex: CExample)
private data class CLesson(
    val n: Int, val zh: String, val py: String, val vi: String, val en: String,
    val gvi: String, val gen: String,
    val words: List<CExample>, val dialogue: List<CDialogue>, val grammar: List<CGrammar>
)

private fun ex(o: JObj) = CExample(o.str("zh"), o.str("py"), o.str("vi"), o.str("en"))

private suspend fun loadLevel(level: Int): List<CLesson>? {
    return try {
        val text = Res.readBytes("files/curriculum/hsk$level.json").decodeToString()
        MiniJson.parse(text).arr("lessons").map { l ->
            CLesson(
                n = l.int("n"), zh = l.str("zh"), py = l.str("py"),
                vi = l.str("vi"), en = l.str("en"),
                gvi = l.str("gvi"), gen = l.str("gen"),
                words = l.arr("words").map { ex(it) },
                dialogue = l.arr("dialogue").map { CDialogue(it.str("sp"), ex(it)) },
                grammar = l.arr("grammar").map { o ->
                    CGrammar(o.str("tvi"), o.str("ten"), o.str("dvi"), o.str("den"), o.arr("ex").map { ex(it) })
                }
            )
        }
    } catch (e: Exception) {
        null
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurriculumScreen(
    onBack: () -> Unit,
    isEn: Boolean
) {
    var level by remember { mutableStateOf<Int?>(null) }
    var lessonIdx by remember { mutableStateOf<Int?>(null) }
    var startSection by remember { mutableStateOf(0) }
    var progressTick by remember { mutableStateOf(0) }
    val player = remember { VoicePlayer() }
    val lessons by produceState<List<CLesson>?>(null, level) { value = level?.let { loadLevel(it) } }

    val goBack: () -> Unit = {
        when {
            lessonIdx != null -> lessonIdx = null
            level != null -> level = null
            else -> onBack()
        }
    }

    val course = if (isEn) "Chinese Course" else "Giáo trình Hán ngữ"
    val title = when {
        level == null -> course
        lessonIdx == null -> "HSK ${level}"
        else -> {
            val l = lessons?.getOrNull(lessonIdx!!)
            if (l == null) "HSK ${level}" else "HSK ${level} · ${if (isEn) "Lesson" else "Bài"} ${l.n}"
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface)
                },
                navigationIcon = {
                    IconButton(onClick = goBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = if (isEn) "Back" else "Quay lại",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        }
    ) { paddingValues ->
        val mod = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)
        val lessons = lessons
        when {
            level == null -> LevelList(mod, isEn, progressTick,
                onOpenLesson = { lv, idx, sec -> level = lv; lessonIdx = idx; startSection = sec },
                onOpenList = { lv -> level = lv; lessonIdx = null })
            lessons == null -> Column(mod.padding(24.dp)) {
                Text(if (isEn) "Coming soon…" else "Đang cập nhật…", fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            lessonIdx == null -> LessonList(mod, isEn, lessons, level!!, progressTick) { lessonIdx = it; startSection = 0 }
            else -> LessonDetail(mod, isEn, lessons[lessonIdx!!], level!!, player, startSection) { progressTick++ }
        }
    }
}

private val levelColors = listOf(
    Color(0xFF43A047), Color(0xFF00897B), Color(0xFF1E88E5),
    Color(0xFF5E35B1), Color(0xFFD81B60), Color(0xFFF4511E)
)
private fun levelColor(lv: Int) = levelColors[(lv - 1).coerceIn(0, 5)]
private fun cardShape() = RoundedCornerShape(20.dp)

private val viNames = listOf("SƠ CẤP", "CƠ BẢN", "TRUNG CẤP", "TRUNG CAO", "NÂNG CAO", "CHUYÊN GIA")
private val enNames = listOf("BEGINNER", "ELEMENTARY", "INTERMEDIATE", "UPPER-INTERMEDIATE", "ADVANCED", "EXPERT")
private val cefrNames = listOf("CEFR A1", "CEFR A2", "CEFR B1", "CEFR B2", "CEFR C1", "CEFR C2")
private val watermark = listOf("好", "学", "语", "文", "经", "智")
private val viOutcome = listOf(
    "Đầu ra: Chào hỏi, giới thiệu bản thân, giao tiếp cơ bản",
    "Đầu ra: Nói về cuộc sống hằng ngày, mua sắm, du lịch",
    "Đầu ra: Trò chuyện về công việc, học tập, sở thích",
    "Đầu ra: Thảo luận nhiều chủ đề, đọc văn bản dài",
    "Đầu ra: Đọc báo, xem phim, thuyết trình",
    "Đầu ra: Thành thạo như người bản xứ"
)
private val enOutcome = listOf(
    "Outcome: Greet, introduce yourself, basic conversation",
    "Outcome: Talk about daily life, shopping, travel",
    "Outcome: Chat about work, study and hobbies",
    "Outcome: Discuss many topics, read longer texts",
    "Outcome: Read news, watch films, give talks",
    "Outcome: Near-native fluency"
)

private fun doneSet(lv: Int): Set<Int> =
    SimpleStore.get("done$lv").split(",").mapNotNull { it.toIntOrNull() }.toSet()

private fun setDone(lv: Int, n: Int, done: Boolean) {
    val s = doneSet(lv).toMutableSet()
    if (done) s.add(n) else s.remove(n)
    SimpleStore.set("done$lv", s.joinToString(","))
}

@Composable
private fun Pill(text: String, bg: Color, fg: Color = Color.White) {
    Text(text, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = fg,
        modifier = Modifier.clip(RoundedCornerShape(50)).background(bg).padding(horizontal = 12.dp, vertical = 5.dp))
}

@Composable
private fun LevelList(
    mod: Modifier, isEn: Boolean, tick: Int,
    onOpenLesson: (Int, Int, Int) -> Unit, onOpenList: (Int) -> Unit
) {
    LazyColumn(mod, contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
        items((1..6).toList()) { lv ->
            val dataState = produceState<List<CLesson>?>(null, lv) { value = loadLevel(lv) }
            val data = dataState.value
            val done = remember(lv, tick) { doneSet(lv) }
            val c = levelColor(lv)
            Card(
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Column {
                    Box(Modifier.fillMaxWidth().background(Brush.horizontalGradient(listOf(c, c.copy(alpha = 0.7f))))
                        .padding(horizontal = 20.dp, vertical = 18.dp)) {
                        Text(watermark[lv - 1], fontSize = 96.sp, color = Color.White.copy(alpha = 0.18f),
                            modifier = Modifier.align(Alignment.CenterEnd))
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("HSK $lv", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                                Spacer(Modifier.width(10.dp))
                                Pill(if (isEn) enNames[lv - 1] else viNames[lv - 1], Color.White.copy(alpha = 0.25f))
                            }
                            Spacer(Modifier.height(6.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Pill(cefrNames[lv - 1], Color.Black.copy(alpha = 0.25f))
                                Spacer(Modifier.width(10.dp))
                                if (data != null) {
                                    val words = data.sumOf { it.words.size }
                                    Text(if (isEn) "${data.size} lessons · $words words" else "${data.size} bài · $words từ vựng",
                                        fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.White)
                                }
                            }
                        }
                    }
                    Column(Modifier.padding(18.dp)) {
                        Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp))
                            .border(1.dp, c.copy(alpha = 0.6f), RoundedCornerShape(14.dp)).padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = c, modifier = Modifier.size(20.dp))
                            Spacer(Modifier.width(8.dp))
                            Text(if (isEn) enOutcome[lv - 1] else viOutcome[lv - 1], fontSize = 14.sp,
                                fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface)
                        }
                        Spacer(Modifier.height(14.dp))
                        if (data == null) {
                            Text(if (isEn) "Coming soon…" else "Đang cập nhật…", fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant)
                        } else {
                            val cnt = done.count { n -> data.any { it.n == n } }
                            val frac = if (data.isEmpty()) 0f else cnt.toFloat() / data.size
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(if (isEn) "Completed $cnt/${data.size} lessons" else "Đã hoàn thành $cnt/${data.size} bài",
                                    fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(1f))
                                Text("${(frac * 100).toInt()}%", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = c)
                            }
                            Spacer(Modifier.height(8.dp))
                            Box(Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp))
                                .background(MaterialTheme.colorScheme.surfaceVariant)) {
                                Box(Modifier.fillMaxWidth(frac).fillMaxHeight().background(c))
                            }
                            Spacer(Modifier.height(14.dp))
                            val labels = if (isEn) listOf("1. Vocabulary", "2. Dialogue", "3. Grammar")
                                         else listOf("1. Từ vựng", "2. Hội thoại", "3. Ngữ pháp")
                            val next = data.indexOfFirst { it.n !in done }.let { if (it < 0) 0 else it }
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                labels.forEachIndexed { i, t ->
                                    Box(Modifier.weight(1f).clip(RoundedCornerShape(14.dp))
                                        .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(14.dp))
                                        .clickable { onOpenLesson(lv, next, i) }.padding(vertical = 14.dp),
                                        contentAlignment = Alignment.Center) {
                                        Text(t, fontSize = 13.sp, fontWeight = FontWeight.Medium,
                                            color = MaterialTheme.colorScheme.onSurface, textAlign = TextAlign.Center)
                                    }
                                }
                            }
                            Spacer(Modifier.height(14.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Row(Modifier.clip(RoundedCornerShape(50)).background(c)
                                    .clickable { onOpenLesson(lv, next, 0) }.padding(horizontal = 20.dp, vertical = 12.dp),
                                    verticalAlignment = Alignment.CenterVertically) {
                                    Text(if (isEn) "Start Lesson ${data[next].n}" else "Vào học Bài ${data[next].n}",
                                        fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                    Spacer(Modifier.width(6.dp))
                                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null,
                                        tint = Color.White, modifier = Modifier.size(18.dp))
                                }
                                Spacer(Modifier.weight(1f))
                                Text(if (isEn) "All lessons" else "Tất cả bài", fontSize = 14.sp, fontWeight = FontWeight.Bold,
                                    color = c, modifier = Modifier.clip(RoundedCornerShape(50))
                                        .border(1.dp, c, RoundedCornerShape(50))
                                        .clickable { onOpenList(lv) }.padding(horizontal = 18.dp, vertical = 11.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LessonList(mod: Modifier, isEn: Boolean, lessons: List<CLesson>, level: Int, tick: Int, onPick: (Int) -> Unit) {
    val c = levelColor(level)
    val done = remember(level, tick) { doneSet(level) }
    LazyColumn(mod, contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(lessons.indices.toList()) { i ->
            val l = lessons[i]
            val isDone = l.n in done
            Card(
                modifier = Modifier.fillMaxWidth().clickable { onPick(i) },
                shape = cardShape(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(46.dp).clip(CircleShape).background(if (isDone) c else c.copy(alpha = 0.18f)),
                        contentAlignment = Alignment.Center) {
                        if (isDone) Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = Color.White)
                        else Text("${l.n}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = c)
                    }
                    Spacer(Modifier.width(14.dp))
                    Column(Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(l.zh, fontSize = 21.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                            Spacer(Modifier.width(8.dp))
                            Text(l.py, fontSize = 12.sp, color = c, modifier = Modifier.padding(bottom = 3.dp))
                        }
                        Text(if (isEn) l.en else l.vi, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Spacer(Modifier.height(6.dp))
                        Text(if (isEn) "${l.words.size} words" else "${l.words.size} từ vựng", fontSize = 11.sp,
                            fontWeight = FontWeight.Medium, color = c,
                            modifier = Modifier.clip(RoundedCornerShape(50)).background(c.copy(alpha = 0.14f))
                                .padding(horizontal = 8.dp, vertical = 3.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun SpeakBtn(text: String, player: VoicePlayer, tint: Color, size: Int = 42) {
    if (player.hasAudio(text)) {
        Box(Modifier.size(size.dp).clip(CircleShape).background(tint.copy(alpha = 0.16f))
            .clickable { player.play(text) }, contentAlignment = Alignment.Center) {
            Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = null, tint = tint, modifier = Modifier.size(22.dp))
        }
    } else {
        Spacer(Modifier.size(size.dp))
    }
}

@Composable
private fun SectionHeader(text: String, c: Color) {
    Row(Modifier.padding(top = 22.dp, bottom = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.width(5.dp).height(20.dp).clip(RoundedCornerShape(3.dp)).background(c))
        Spacer(Modifier.width(10.dp))
        Text(text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
    }
}

@Composable
private fun WordCard(i: Int, w: CExample, isEn: Boolean, player: VoicePlayer, c: Color) {
    Card(Modifier.fillMaxWidth().padding(vertical = 6.dp), shape = cardShape(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)) {
        Row(Modifier.height(IntrinsicSize.Min)) {
            Box(Modifier.width(5.dp).fillMaxHeight().background(c))
            Column(Modifier.weight(1f).padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(34.dp).clip(RoundedCornerShape(10.dp)).background(c), contentAlignment = Alignment.Center) {
                        Text("$i", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                    Spacer(Modifier.width(12.dp))
                    Text(w.zh, fontSize = 30.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(Modifier.width(10.dp))
                    Text(w.py, fontSize = 16.sp, fontStyle = FontStyle.Italic, color = c, modifier = Modifier.weight(1f))
                    SpeakBtn(w.zh, player, c)
                }
                Spacer(Modifier.height(8.dp))
                Text(if (isEn) w.en else w.vi, fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}

@Composable
private fun Bubble(d: CDialogue, isEn: Boolean, player: VoicePlayer, c: Color) {
    val left = d.sp == "A"
    val bg = if (left) MaterialTheme.colorScheme.surface else c.copy(alpha = 0.14f)
    Row(Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = if (left) Arrangement.Start else Arrangement.End) {
        Column(Modifier.fillMaxWidth(0.88f).clip(RoundedCornerShape(18.dp)).background(bg)
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp)).padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(d.sp, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White, textAlign = TextAlign.Center,
                    modifier = Modifier.clip(CircleShape).background(c).padding(horizontal = 9.dp, vertical = 2.dp))
                Spacer(Modifier.weight(1f))
                SpeakBtn(d.ex.zh, player, c, 36)
            }
            Text(d.ex.zh, fontSize = 19.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface)
            Text(d.ex.py, fontSize = 12.sp, fontStyle = FontStyle.Italic, color = c, modifier = Modifier.padding(top = 2.dp))
            Text(if (isEn) d.ex.en else d.ex.vi, fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = 4.dp))
        }
    }
}

@Composable
private fun LessonDetail(
    mod: Modifier, isEn: Boolean, l: CLesson, level: Int, player: VoicePlayer,
    startSection: Int, onProgress: () -> Unit
) {
    val c = levelColor(level)
    val listState = rememberLazyListState()
    var isDone by remember(l.n, level) { mutableStateOf(l.n in doneSet(level)) }
    var playToken by remember { mutableStateOf(0) }
    val dlgHeader = 2 + l.words.size
    val gramHeader = 3 + l.words.size + l.dialogue.size
    LaunchedEffect(l.n, startSection) {
        val idx = when (startSection) { 1 -> dlgHeader; 2 -> gramHeader; else -> 0 }
        listState.scrollToItem(idx)
    }
    fun playAll(i: Int, token: Int) {
        if (i >= l.words.size || token != playToken) return
        player.play(l.words[i].zh) { playAll(i + 1, token) }
    }
    LazyColumn(mod, state = listState, contentPadding = PaddingValues(16.dp)) {
        item {
            Box(Modifier.fillMaxWidth().clip(RoundedCornerShape(24.dp))
                .background(Brush.horizontalGradient(listOf(c, c.copy(alpha = 0.7f)))).padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text("${if (isEn) "Lesson" else "Bài"} ${l.n}", fontSize = 13.sp, color = Color.White.copy(alpha = 0.85f))
                        Text(l.zh, fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Text(l.py, fontSize = 15.sp, fontStyle = FontStyle.Italic, color = Color.White.copy(alpha = 0.9f))
                        Text(if (isEn) l.en else l.vi, fontSize = 17.sp, fontWeight = FontWeight.Medium, color = Color.White,
                            modifier = Modifier.padding(top = 4.dp))
                        Text(if (isEn) l.gen else l.gvi, fontSize = 13.sp, color = Color.White.copy(alpha = 0.9f),
                            modifier = Modifier.padding(top = 8.dp))
                    }
                    SpeakBtn(l.zh, player, Color.White)
                }
            }
        }
        item {
            Card(Modifier.fillMaxWidth().padding(top = 16.dp), shape = cardShape(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)) {
                Column(Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(if (isEn) "Vocabulary list" else "Danh sách từ vựng", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface)
                        Spacer(Modifier.width(10.dp))
                        Pill(if (isEn) "${l.words.size} words" else "${l.words.size} từ", c.copy(alpha = 0.18f), c)
                    }
                    Spacer(Modifier.height(12.dp))
                    Row(Modifier.clip(RoundedCornerShape(50)).background(c).clickable {
                        playToken++; playAll(0, playToken)
                    }.padding(horizontal = 20.dp, vertical = 11.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.PlayArrow, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                        Spacer(Modifier.width(6.dp))
                        Text(if (isEn) "Play all" else "Phát tất cả", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }
        itemsIndexed(l.words) { i, w -> WordCard(i + 1, w, isEn, player, c) }
        item { SectionHeader(if (isEn) "Dialogue" else "Hội thoại", c) }
        items(l.dialogue) { d -> Bubble(d, isEn, player, c) }
        item { SectionHeader(if (isEn) "Grammar" else "Ngữ pháp", c) }
        items(l.grammar) { g ->
            Card(Modifier.fillMaxWidth().padding(bottom = 12.dp), shape = cardShape(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)) {
                Column(Modifier.padding(14.dp)) {
                    Text(if (isEn) g.ten else g.tvi, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = c)
                    Text(if (isEn) g.den else g.dvi, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(top = 4.dp, bottom = 8.dp))
                    g.ex.forEach { e ->
                        Row(Modifier.fillMaxWidth().padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                            Box(Modifier.width(3.dp).height(48.dp).clip(RoundedCornerShape(2.dp)).background(c.copy(alpha = 0.6f)))
                            Column(Modifier.weight(1f).padding(horizontal = 10.dp)) {
                                Text(e.zh, fontSize = 17.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface)
                                Text(e.py, fontSize = 12.sp, fontStyle = FontStyle.Italic, color = c)
                                Text(if (isEn) e.en else e.vi, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            SpeakBtn(e.zh, player, c, 38)
                        }
                    }
                }
            }
        }
        item {
            Row(Modifier.fillMaxWidth().padding(vertical = 20.dp).clip(RoundedCornerShape(50))
                .background(if (isDone) c.copy(alpha = 0.18f) else c)
                .clickable { isDone = !isDone; setDone(level, l.n, isDone); onProgress() }.padding(vertical = 14.dp),
                horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = if (isDone) c else Color.White)
                Spacer(Modifier.width(8.dp))
                Text(if (isDone) (if (isEn) "Lesson completed" else "Đã hoàn thành bài") else (if (isEn) "Mark lesson complete" else "Hoàn thành bài học"),
                    fontSize = 16.sp, fontWeight = FontWeight.Bold, color = if (isDone) c else Color.White)
            }
        }
    }
}
