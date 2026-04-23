package com.example.fit5046a2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 从 Register.kt 提取的设计令牌，确保颜色一致性
private val PrimaryRed = Color(0xFFC62828)
private val SoftRed = Color(0xFFFFEBEE)
private val DarkText = Color(0xFF1C1B1F)
private val LightGrayText = Color(0xFF666666)
private val CardBackground = Color.White
private val HeartRateBg = Color(0xFFFCE4EC) // 使用更柔和的粉色系配合 PrimaryRed
private val BpBg = Color(0xFFE3F2FD)      // 保持蓝色系作为对比，但调整饱和度以匹配整体柔和风格

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen3() {
    // 统一卡片修饰符：白色背景，大圆角，阴影
    val cardModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 0.dp) // 外层 LazyColumn 已有 padding，这里不需要额外水平 padding，或者根据需求调整
        .aspectRatio(1f) 
        .background(CardBackground, shape = RoundedCornerShape(20.dp)) // 使用 20dp 圆角匹配 Register 页面卡片

    Scaffold(
        containerColor = Color(0xFFF8F9FB) // 匹配 Register 页面的背景色
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp), // 整体内边距
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Health Dashboard",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = DarkText,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // 左上：日历
                    Card(
                        modifier = cardModifier.weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = CardBackground),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("Calendar", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DarkText)
                            Text("Jul 2022", fontSize = 14.sp, color = LightGrayText)
                            // 简单模拟日历网格
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                (1..7).forEach { day ->
                                    Box(
                                        modifier = Modifier.size(32.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text("$day", fontSize = 12.sp, color = DarkText)
                                    }
                                }
                            }
                        }
                    }

                    // 右上：用户信息
                    Card(
                        modifier = cardModifier.weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = CardBackground),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("General Info", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DarkText)
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                InfoItem("25", "Years old")
                                InfoItem("180", "Centimeter")
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
                    // 左下：心率 - 使用 SoftRed 变体或强调色背景
                    Card(
                        modifier = cardModifier.weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = HeartRateBg), // 浅粉背景呼应 PrimaryRed
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text("Heart Rate", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DarkText)
                            Text("112 bpm", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = PrimaryRed) // 使用 PrimaryRed
                            // 简单折线图模拟
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

                    // 右下：血压 - 保持蓝色系作为功能区分，但调整色调
                    Card(
                        modifier = cardModifier.weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = BpBg),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text("Blood Pressure", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DarkText)
                            Text("132/90", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1976D2)) // 深蓝色保持可读性
                            // 简单柱状图模拟
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

@Composable
fun InfoItem(value: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = DarkText)
        Text(label, fontSize = 12.sp, color = LightGrayText)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    MaterialTheme {
        DashboardScreen3()
    }
}