package com.chineselearn.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chineselearn.app.data.GrammarTopic
import com.chineselearn.app.data.MockData
import com.chineselearn.app.data.VoicePlayer
import com.chineselearn.app.ui.theme.ImperialRedLight
import com.chineselearn.app.ui.theme.JadeGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrammarScreen(
    onBack: () -> Unit,
    completedIds: Set<String>,
    onToggleCompleted: (String, Boolean) -> Unit,
    isEn: Boolean
) {
    val voicePlayer = remember { VoicePlayer() }


    var selectedTopic by remember { mutableStateOf<GrammarTopic?>(null) }

    // Intercept back button when in detail screen

    if (selectedTopic != null) {
        val topic = selectedTopic!!
        GrammarDetailScreen(
            topic = topic,
            isCompleted = completedIds.contains(topic.id),
            onToggleCompleted = { onToggleCompleted(topic.id, !completedIds.contains(topic.id)) },
            onBack = { selectedTopic = null },
            isEn = isEn,
            onSpeak = { text -> voicePlayer.play(text) }
        )
    } else {
        // Grammar List View
        val allTabName = if (isEn) "All" else "Tất cả"
        var selectedLevel by remember { mutableStateOf("All_LEVEL") }
        val levels = listOf("All_LEVEL", "HSK 1", "HSK 2", "HSK 3", "HSK 4", "HSK 5", "HSK 6")

        val filteredList = remember(selectedLevel) {
            if (selectedLevel == "All_LEVEL") {
                MockData.sampleGrammar
            } else {
                MockData.sampleGrammar.filter { it.level == selectedLevel }
            }
        }

        val totalGrammar = MockData.sampleGrammar.size
        val completedCount = completedIds.size
        val progressPercent = if (totalGrammar > 0) ((completedCount.toFloat() / totalGrammar) * 100).toInt() else 0

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = if (isEn) "Grammar" else "Ngữ Pháp",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = if (isEn) "Back" else "Quay lại")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Progress Card
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(2.dp, RoundedCornerShape(14.dp)),
                    shape = RoundedCornerShape(14.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = androidx.compose.foundation.BorderStroke(1.dp, ImperialRedLight.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (isEn) "Completion Progress:" else "Tiến độ hoàn thành:",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "$completedCount/$totalGrammar ($progressPercent%)",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = ImperialRedLight
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        LinearProgressIndicator(
                            progress = { (progressPercent / 100f).coerceIn(0f, 1f) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp)),
                            color = ImperialRedLight,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Level Filter Tabs (HSK 1 - HSK 6)
                ScrollableTabRow(
                    selectedTabIndex = levels.indexOf(selectedLevel).coerceAtLeast(0),
                    edgePadding = 0.dp,
                    containerColor = Color.Transparent,
                    divider = {}
                ) {
                    levels.forEach { lvl ->
                        val isSelected = lvl == selectedLevel
                        val displayLvl = if (lvl == "All_LEVEL") allTabName else lvl
                        Tab(
                            selected = isSelected,
                            onClick = { selectedLevel = lvl },
                            text = {
                                Text(
                                    text = displayLvl,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    fontSize = 13.sp
                                )
                            },
                            selectedContentColor = ImperialRedLight,
                            unselectedContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Grammar List
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filteredList) { item ->
                        val isCompleted = completedIds.contains(item.id)

                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(2.dp, RoundedCornerShape(16.dp))
                                .clickable { selectedTopic = item },
                            shape = RoundedCornerShape(16.dp),
                            color = MaterialTheme.colorScheme.surface,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isCompleted) JadeGreen.copy(alpha = 0.6f) else MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
                            )
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = ImperialRedLight.copy(alpha = 0.12f)
                                    ) {
                                        Text(
                                            text = item.level,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = ImperialRedLight,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                        )
                                    }

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        val exCount = if (item.examples.isNotEmpty()) item.examples.size else 1
                                        Surface(
                                            shape = RoundedCornerShape(6.dp),
                                            color = MaterialTheme.colorScheme.surfaceVariant
                                        ) {
                                            Text(
                                                text = if (isEn) "$exCount Examples" else "$exCount ví dụ",
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Medium,
                                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                            )
                                        }

                                        if (isCompleted) {
                                            Surface(
                                                shape = RoundedCornerShape(6.dp),
                                                color = JadeGreen.copy(alpha = 0.15f)
                                            ) {
                                                Row(
                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.Check,
                                                        contentDescription = null,
                                                        tint = JadeGreen,
                                                        modifier = Modifier.size(12.dp)
                                                    )
                                                    Spacer(modifier = Modifier.width(3.dp))
                                                    Text(
                                                        text = if (isEn) "Learned" else "Đã học",
                                                        fontSize = 11.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = JadeGreen
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = if (isEn) item.titleEn else item.title,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                // Formula preview
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = MaterialTheme.colorScheme.surfaceVariant
                                ) {
                                    Text(
                                        text = if (isEn && item.formulaEn.isNotEmpty()) item.formulaEn else item.formula,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = ImperialRedLight,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                // Tap to view full detail call-to-action row
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = if (isEn) "Tap to view full structure & examples" else "Nhấn để xem đầy đủ cấu trúc & ví dụ",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = ImperialRedLight
                                    )
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = null,
                                        tint = ImperialRedLight,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrammarDetailScreen(
    topic: GrammarTopic,
    isCompleted: Boolean,
    onToggleCompleted: () -> Unit,
    onBack: () -> Unit,
    isEn: Boolean,
    onSpeak: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isEn) "Structure Details" else "Chi tiết Cấu trúc",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = if (isEn) "Back" else "Quay lại")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Header: Level Badge & Title
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = ImperialRedLight.copy(alpha = 0.12f)
                ) {
                    Text(
                        text = topic.level,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = ImperialRedLight,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }

                if (isCompleted) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = JadeGreen.copy(alpha = 0.15f)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = JadeGreen, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = if (isEn) "Completed" else "Đã hoàn thành", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = JadeGreen)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Grammar Title
            Text(
                text = if (isEn) topic.titleEn else topic.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 1. Structure / Formula Section
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(1.dp, RoundedCornerShape(14.dp)),
                shape = RoundedCornerShape(14.dp),
                color = MaterialTheme.colorScheme.surface,
                border = androidx.compose.foundation.BorderStroke(1.dp, ImperialRedLight.copy(alpha = 0.4f))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = if (isEn) "📌 GRAMMAR FORMULA" else "📌 CÔNG THỨC CẤU TRÚC",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = ImperialRedLight
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Text(
                            text = if (isEn && topic.formulaEn.isNotEmpty()) topic.formulaEn else topic.formula,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = ImperialRedLight,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 2. Explanation Section
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(1.dp, RoundedCornerShape(14.dp)),
                shape = RoundedCornerShape(14.dp),
                color = MaterialTheme.colorScheme.surface,
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = if (isEn) "📖 DETAILED EXPLANATION" else "📖 GIẢI THÍCH CHI TIẾT",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = if (isEn) topic.explanationEn else topic.explanation,
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.88f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 3. Examples Section
            val exampleList = if (topic.examples.isNotEmpty()) {
                topic.examples
            } else {
                listOf(
                    com.chineselearn.app.data.GrammarExample(
                        hanzi = topic.exampleHanzi,
                        pinyin = topic.examplePinyin,
                        meaning = topic.exampleMeaning,
                        meaningEn = topic.exampleMeaningEn
                    )
                )
            }

            Text(
                text = if (isEn) "🎯 PRACTICAL EXAMPLES (${exampleList.size}):" else "🎯 CÁC CÂU VÍ DỤ THỰC TẾ (${exampleList.size} câu):",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(10.dp))

            exampleList.forEachIndexed { index, ex ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .shadow(1.dp, RoundedCornerShape(12.dp)),
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = ImperialRedLight.copy(alpha = 0.12f),
                            modifier = Modifier.size(24.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "${index + 1}",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = ImperialRedLight
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = ex.hanzi,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = ex.pinyin,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = if (isEn) ex.meaningEn else ex.meaning,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.88f)
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        IconButton(
                            onClick = { onSpeak(ex.hanzi) },
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(JadeGreen.copy(alpha = 0.15f))
                        ) {
                            Icon(
                                imageVector = Icons.Default.VolumeUp,
                                contentDescription = if (isEn) "Pronounce" else "Phát âm",
                                tint = JadeGreen,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 4. Mark as Learned Button
            Button(
                onClick = onToggleCompleted,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isCompleted) JadeGreen else ImperialRedLight,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = if (isCompleted) Icons.Default.Check else Icons.Default.BookmarkBorder,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isCompleted) (if (isEn) "Learned (Tap to undo)" else "Đã học (Bấm để hoàn tác)") else (if (isEn) "Mark this Grammar as Learned" else "Đánh dấu đã học ngữ pháp này"),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
