package com.example.fit5046a2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val PrimaryRed = Color(0xFFC62828)
private val SoftRed = Color(0xFFFFEBEE)
private val DarkText = Color(0xFF1C1B1F)
private val LightGrayText = Color(0xFF666666)
private val CardBackground = Color.White
private val HeartRateBg = Color(0xFFFCE4EC)
private val BpBg = Color(0xFFE3F2FD)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    val cardModifier = Modifier
        .fillMaxWidth()
        .background(CardBackground, shape = RoundedCornerShape(20.dp))

    Scaffold(
        containerColor = Color(0xFFF8F9FB)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // --- 新增：顶部风险预警模块 ---
            item {
                RiskAlertCard()
            }

            item {
                Text(
                    text = "Health Overview",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = DarkText,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)

                ) {
                    // 左上：日历
                    Card(
                        modifier = cardModifier.weight(1f).wrapContentHeight(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = CardBackground),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("Calendar", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DarkText)
                            Text("Jul 2026", fontSize = 14.sp, color = LightGrayText)

                            // 日期（压缩版）
                            val days = listOf(
                                listOf("", "", "", "1", "2", "3", "4"),
                                listOf("5", "6", "7", "8", "9", "10", "11"),
                                listOf("12", "13", "14", "15", "16", "17", "18"),
                                listOf("19", "20", "21", "22", "23", "24", "25"),
                                listOf("26", "27", "28", "29", "30", "31", "")
                            )

                            days.forEach { week ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    week.forEach { day ->
                                        Box(
                                            modifier = Modifier.weight(1f),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                day,
                                                fontSize = 10.sp,
                                                color = if (day == "15") PrimaryRed else DarkText,
                                                fontWeight = if (day == "15") FontWeight.Bold else FontWeight.Normal
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // 右上：用户信息
                    Card(
                        modifier = cardModifier.weight(1f).aspectRatio(1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = CardBackground),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("General Info", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DarkText)
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                InfoItem("25", "Years")
                                InfoItem("180", "CM")
                            }
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                InfoItem("55", "Kg")
                                InfoItem("+A", "Blood")
                            }
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // 左下：心率
                    Card(
                        modifier = cardModifier.weight(1f).aspectRatio(1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = HeartRateBg),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text("Heart Rate", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DarkText)
                            Text("112 bpm", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = PrimaryRed)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .background(Color.White.copy(alpha = 0.6f), shape = RoundedCornerShape(12.dp))
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("📈 Waveform", fontSize = 12.sp, color = PrimaryRed)
                            }
                        }
                    }

                    // 右下：血压
                    Card(
                        modifier = cardModifier.weight(1f).aspectRatio(1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = BpBg),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text("Blood Pressure", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DarkText)
                            Text("132/90", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1976D2))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .background(Color.White.copy(alpha = 0.6f), shape = RoundedCornerShape(12.dp))
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("📊 Bar Chart", fontSize = 12.sp, color = Color(0xFF1976D2))
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * 风险预警卡片组件
 * 显示近7天风险等级及健康提示
 */
@Composable
fun RiskAlertCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp), // 固定高度，使其比下方卡片更宽大
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(SoftRed, Color.White)
                    )
                )
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // 头部：标题与风险等级
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Cardiovascular Risk",
                            fontSize = 14.sp,
                            color = LightGrayText,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Moderate Risk", // 动态风险等级
                            fontSize = 22.sp,
                            color = PrimaryRed,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // 简单的7天指示器模拟
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        repeat(7) { index ->
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .background(
                                        color = if (index == 5) PrimaryRed else Color.Gray.copy(alpha = 0.3f),
                                        shape = RoundedCornerShape(4.dp)
                                    )
                            )
                        }
                    }
                }

                // 底部：智能提示语
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp),
                    shadowElevation = 2.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("💡", fontSize = 18.sp)
                        Column {
                            Text(
                                text = "Health Alert",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkText
                            )
                            Text(
                                text = "Temp drop detected. BP may rise. Keep warm.", // 动态提示语
                                fontSize = 12.sp,
                                color = LightGrayText
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoItem(value: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DarkText)
        Text(label, fontSize = 11.sp, color = LightGrayText)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    MaterialTheme {
        DashboardScreen()
    }
}