package com.example.fit5046a2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fit5046a2.ui.theme.FIT5046A2Theme

private val HistoryBg = Color(0xFFF5F5F7)
private val CardWhite = Color(0xFFFFFFFF)
private val PrimaryRed = Color(0xFFD62828)
private val SoftPink = Color(0xFFFBEAEC)
private val DarkText = Color(0xFF202124)
private val SubText = Color(0xFF6F6F73)
private val BorderGray = Color(0xFF9EA3AE)
private val GreenBg = Color(0xFFEAF6EE)
private val GreenText = Color(0xFF2E7D32)
private val OrangeBg = Color(0xFFFFF2E4)
private val OrangeText = Color(0xFFE67E22)
private val RedBg = Color(0xFFFCE8E6)

data class HistoryRecord(
    val date: String,
    val time: String,
    val bpm: Int,
    val activity: String,
    val status: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    onBackClick: () -> Unit = {}
) {
    var searchText by rememberSaveable { mutableStateOf("") }
    var selectedFilter by rememberSaveable { mutableStateOf("All") }
    var selectedDate by rememberSaveable { mutableStateOf("21 Apr 2026") }
    var dateMenuExpanded by rememberSaveable { mutableStateOf(false) }

    val availableDates = listOf(
        "21 Apr 2026",
        "20 Apr 2026",
        "19 Apr 2026",
        "18 Apr 2026"
    )

    val allRecords = listOf(
        HistoryRecord("21 Apr 2026", "08:20 AM", 78, "Resting", "Normal"),
        HistoryRecord("21 Apr 2026", "01:10 PM", 96, "Walking", "Normal"),
        HistoryRecord("20 Apr 2026", "09:45 PM", 112, "Resting", "Warning"),
        HistoryRecord("20 Apr 2026", "06:30 PM", 105, "Exercise", "Warning"),
        HistoryRecord("19 Apr 2026", "07:15 AM", 72, "Sleeping", "Normal"),
        HistoryRecord("18 Apr 2026", "10:05 PM", 132, "Resting", "Critical")
    )

    val filteredRecords = allRecords.filter { record ->
        val matchesSearch =
            record.date.contains(searchText, ignoreCase = true) ||
                    record.activity.contains(searchText, ignoreCase = true) ||
                    record.status.contains(searchText, ignoreCase = true) ||
                    record.bpm.toString().contains(searchText)

        val matchesFilter =
            selectedFilter == "All" || record.status == selectedFilter

        val matchesDate = record.date == selectedDate

        matchesSearch && matchesFilter && matchesDate
    }

    Scaffold(
        containerColor = HistoryBg,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "History",
                        fontWeight = FontWeight.SemiBold,
                        color = DarkText
                    )
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 18.dp, vertical = 14.dp)
        ) {
            Text(
                text = "Review your previous heart rate records and identify unusual trends.",
                style = MaterialTheme.typography.bodyMedium,
                color = SubText
            )

            Spacer(modifier = Modifier.height(18.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = CardWhite),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp)
                ) {
                    Text(
                        text = "Filter Records",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = DarkText
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedTextField(
                        value = searchText,
                        onValueChange = { newValue ->
                            searchText = newValue
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search"
                            )
                        },
                        placeholder = {
                            Text("Search by BPM, activity, or status")
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = BorderGray,
                            unfocusedBorderColor = BorderGray
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row {
                        Button(
                            onClick = { dateMenuExpanded = true },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SoftPink,
                                contentColor = PrimaryRed
                            )
                        ) {
                            Text(selectedDate)
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Open date menu"
                            )
                        }

                        DropdownMenu(
                            expanded = dateMenuExpanded,
                            onDismissRequest = { dateMenuExpanded = false }
                        ) {
                            availableDates.forEach { date ->
                                DropdownMenuItem(
                                    text = { Text(date) },
                                    onClick = {
                                        selectedDate = date
                                        dateMenuExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        listOf("All", "Normal", "Warning", "Critical").forEach { filter ->
                            AssistChip(
                                onClick = { selectedFilter = filter },
                                label = {
                                    Text(filter)
                                },
                                colors = AssistChipDefaults.assistChipColors(
                                    containerColor = if (selectedFilter == filter) SoftPink else Color(0xFFF1F2F4),
                                    labelColor = if (selectedFilter == filter) PrimaryRed else DarkText
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Recorded Entries",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (filteredRecords.isEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = CardWhite),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = "No records found for the selected filters.",
                        modifier = Modifier.padding(20.dp),
                        color = SubText
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filteredRecords) { record ->
                        HistoryRecordCard(record = record)
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryRecordCard(record: HistoryRecord) {
    val statusContainerColor = when (record.status) {
        "Normal" -> GreenBg
        "Warning" -> OrangeBg
        else -> RedBg
    }

    val statusTextColor = when (record.status) {
        "Normal" -> GreenText
        "Warning" -> OrangeText
        else -> PrimaryRed
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = record.date,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = DarkText
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = record.time,
                        style = MaterialTheme.typography.bodyMedium,
                        color = SubText
                    )
                }

                Surface(
                    shape = RoundedCornerShape(50),
                    color = statusContainerColor
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (record.status != "Normal") {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = null,
                                tint = statusTextColor
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                        Text(
                            text = record.status,
                            color = statusTextColor,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider(color = Color(0xFFE7E7EA))
            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HistoryInfoItem(
                    title = "Heart Rate",
                    value = "${record.bpm} bpm",
                    valueColor = if (record.status == "Critical" || record.status == "Warning") PrimaryRed else DarkText
                )

                HistoryInfoItem(
                    title = "Activity",
                    value = record.activity,
                    valueColor = DarkText
                )
            }
        }
    }
}

@Composable
fun HistoryInfoItem(
    title: String,
    value: String,
    valueColor: Color
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = SubText
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = valueColor
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HistoryScreenPreview() {
    FIT5046A2Theme {
        HistoryScreen()
    }
}