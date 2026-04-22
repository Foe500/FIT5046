package com.example.fit5046a2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fit5046a2.ui.theme.FIT5046A2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToHistory: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // 心率卡片
            HeartRateCard()

            // 图表区域
            HeartRateChart()

            Spacer(Modifier.weight(1f))

            Button(
                onClick = onNavigateToHistory,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("查看历史记录")
            }
        }
    }
}


@Composable
fun HeartRateCard() {
    var heartRate by remember { mutableStateOf(72) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text(
                text = "$heartRate",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "BPM",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


@Composable
fun HeartRateChart() {
    val data = remember {
        listOf(60, 65, 70, 68, 72, 75, 71, 69)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("今日心率趋势", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))

            // 简易折线示意
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = androidx.compose.ui.Alignment.Bottom
            ) {
                data.forEach { value ->
                    Box(
                        modifier = Modifier
                            .width(12.dp)
                            .height((value * 1.5).dp)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    FIT5046A2Theme {
        DashboardScreen(
            onNavigateToHistory = {
            }
        )
    }
}