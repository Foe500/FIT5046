package com.example.fit5046a2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fit5046a2.ui.theme.FIT5046A2Theme
import kotlin.random.Random
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen2(
    onNavigateToHistory: () -> Unit
) {
    // 模拟情境感知数据：心率、活动状态
    var heartRate by remember { mutableStateOf(72) }
    var isResting by remember { mutableStateOf(true) }
    var riskLevel by remember { mutableStateOf("High Risk") }

    // 模拟传感器数据更新
    LaunchedEffect(Unit) {
        while(true) {
            kotlinx.coroutines.delay(2000)
            heartRate = Random.nextInt(60, 100)
            isResting = Random.nextBoolean()
            riskLevel = if (heartRate > 90 && isResting) "High Risk" else "Normal"
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CardioGuard") },
                actions = {
                    IconButton(onClick = {}) {
                        Text(if (riskLevel == "Normal") "🟢" else "🔴")
                    }
                }
            )
        },
        bottomBar = {
//            BottomNavigationBar(navController = navController) // 假设你有一个 navController
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // 1. 情境感知提示 (Context-aware Alert)
            ContextAlert(riskLevel)

            // 2. 数据概览卡片 (Health Overview)
            HealthOverview()

            // 3. 快速操作按钮 (Quick Actions)
            QuickActions(onNavigateToHistory)

            // 4. 查看历史记录按钮
            Button(
                onClick = onNavigateToHistory,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("View Full History")
            }
        }
    }
}


// ==================== 组件定义 ====================

@Composable
private fun ContextAlert(riskLevel: String) {
    if (riskLevel == "High Risk") {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.Yellow.copy())
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                Icon(imageVector = Icons.Default.Warning, contentDescription = "Risk", tint = Color.Red)
                Spacer(Modifier.width(8.dp))
                Column {
                    Text("⚠️ High Heart Rate Detected", style = MaterialTheme.typography.titleMedium)
                    Text("Your current heart rate is above normal. Please rest and monitor.", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
private fun HealthOverview() {
    val normalCount by remember { mutableStateOf(5) }
    val abnormalCount by remember { mutableStateOf(1) }
    val nextCheckTime by remember { mutableStateOf("7:30 PM") }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Red.copy())
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Heart Rate Overview", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            Text("Today's readings show stable heart rate. One reading was slightly elevated.")
            Spacer(Modifier.height(16.dp))

            // 进度条
            LinearProgressIndicator(
                progress = normalCount.toFloat() / (normalCount + abnormalCount),
                modifier = Modifier.height(6.dp).fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("Normal", style = MaterialTheme.typography.labelLarge)
                    Text("$normalCount readings", style = MaterialTheme.typography.bodyMedium)
                }
                Column {
                    Text("Abnormal", style = MaterialTheme.typography.labelLarge)
                    Text("$abnormalCount readings", style = MaterialTheme.typography.bodyMedium)
                }
                Column {
                    Text("Next Check", style = MaterialTheme.typography.labelLarge)
                    Text(nextCheckTime, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
private fun QuickActions(onNavigateToHistory: () -> Unit) {
    Column {
        // Add Measurement
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                Spacer(Modifier.width(8.dp))
                Text("Add Measurement")
            }
        }

        // View History
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "History")
                Spacer(Modifier.width(8.dp))
                Text("View History")
            }
        }

        // Emergency Call
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text("Emergency Call")
        }
    }
}

// ==================== Preview ====================

@Preview(showBackground = true)
@Composable
fun DashboardPreview2() {
    FIT5046A2Theme {
        DashboardScreen2(onNavigateToHistory = {})
    }
}