//package com.example.fit5046a2.screens
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DashboardScreen3() {
//    val cardModifier = Modifier
//        .fillMaxWidth()
//        .padding(8.dp)
//        .aspectRatio(1f) // 保持正方形卡片
//        .background(MaterialTheme.colorScheme.surface)
//
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()          .padding(
//                16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        item {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//                // 左上：日历
//                Card(
//                    modifier = cardModifier.weight(1f),
//                    shape = RoundedCornerShape(16.dp),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//                ) {
//                    Column(
//                        modifier = Modifier.padding(16.dp),
//                        verticalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        Text("Calendar", fontSize = 18.sp, fontWeight = FontWeight.Bold)
//                        Text("Jul 2022", fontSize = 14.sp, color = Color.Gray)
//                        // 简单模拟日历网格
//                        Row(
//                            horizontalArrangement = Arrangement.SpaceEvenly,
//                            modifier = Modifier.fillMaxWidth()
//                        ) {
//                            (1..7).forEach { day ->
//                                Box(
//                                    modifier = Modifier.size(32.dp),
//                                    contentAlignment = Alignment.Center
//                                ) {
//                                    Text("$day", fontSize = 12.sp)
//                                }
//                            }
//                        }
//                    }
//                }
//
//                // 右上：用户信息
//                Card(
//                    modifier = cardModifier.weight(1f),
//                    shape = RoundedCornerShape(16.dp),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//                ) {
//                    Column(
//                        modifier = Modifier.padding(16.dp),
//                        verticalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        Text("General Info", fontSize = 18.sp, fontWeight = FontWeight.Bold)
//                        Row(
//                            horizontalArrangement = Arrangement.SpaceBetween,
//                            modifier = Modifier.fillMaxWidth()
//                        ) {
//                            InfoItem("25", "Years old")
//                            InfoItem("180", "Centimeter")
//                        }
//                        Row(
//                            horizontalArrangement = Arrangement.SpaceBetween,
//                            modifier = Modifier.fillMaxWidth()
//                        ) {
//                            InfoItem("55", "Kg")
//                            InfoItem("+A", "Blood")
//                        }
//                    }
//                }
//            }
//        }
//
//        item {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//                // 左下：心率
//                Card(
//                    modifier = cardModifier.weight(1f),
//                    shape = RoundedCornerShape(16.dp),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)) // 浅蓝背景
//                ) {
//                    Column(
//                        modifier = Modifier.padding(16.dp),
//                        verticalArrangement = Arrangement.spacedBy(8.dp),
//                        horizontalAlignment = Alignment.Start
//                    ) {
//                        Text("Heart Rate", fontSize = 18.sp, fontWeight = FontWeight.Bold)
//                        Text("112 bpm", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1976D2))
//                        // 简单折线图模拟
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(60.dp)
//                                .background(Color(0xFFBBDEFB))
//                                .padding(8.dp),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Text("📈 Waveform", fontSize = 12.sp)
//                        }
//                    }
//                }
//
//                // 右下：血压
//                Card(
//                    modifier = cardModifier.weight(1f),
//                    shape = RoundedCornerShape(16.dp),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFCE4EC)) // 浅粉背景
//                ) {
//                    Column(
//                        modifier = Modifier.padding(16.dp),
//                        verticalArrangement = Arrangement.spacedBy(8.dp),
//                        horizontalAlignment = Alignment.Start
//                    ) {
//                        Text("Blood Pressure", fontSize = 18.sp, fontWeight = FontWeight.Bold)
//                        Text("132/90", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFFC2185B))
//                        // 简单柱状图模拟
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(60.dp)
//                                .background(Color(0xFFF8BBD0))
//                                .padding(8.dp),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Text("📊 Bar Chart", fontSize = 12.sp)
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun InfoItem(value: String, label: String) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold)
//        Text(label, fontSize = 12.sp, color = Color.Gray)
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DashboardScreenPreview() {
//    MaterialTheme {
//        DashboardScreen3()
//    }
//}