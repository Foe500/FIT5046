package com.example.fit5046a2.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fit5046a2.ui.theme.FIT5046A2Theme
import androidx.compose.material3.TextButton

private val PrimaryRed = Color(0xFFC62828)
private val MidRed = Color(0xFFD24A4A)
private val LightRed = Color(0xFFEF9A9A)
private val SoftGray = Color(0xFFF8F9FB)
private val DarkText = Color(0xFF1C1B1F)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onLogoutClick: () -> Unit
) {
    var dailyReminderEnabled by remember { mutableStateOf(true) }
    var abnormalAlertEnabled by remember { mutableStateOf(true) }
    var reminderTime by rememberSaveable { mutableStateOf("8:00 AM") }
    var minHeartRate by rememberSaveable { mutableStateOf("60") }
    var maxHeartRate by rememberSaveable { mutableStateOf("100") }
    var timeMenuExpanded by remember { mutableStateOf(false) }

    val reminderOptions = listOf(
        "7:00 AM",
        "8:00 AM",
        "9:00 AM",
        "10:00 AM",
        "6:00 PM",
        "8:00 PM"
    )

    Scaffold(
        containerColor = SoftGray,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Settings",
                        color = DarkText,
                        fontWeight = FontWeight.SemiBold
                    )
                },
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Manage your reminders and heart rate alert preferences.",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF888888),
                lineHeight = 18.sp,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 0.dp, bottom = 10.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 100.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    SettingsSectionTitle("Daily Reminder")

                    Text(
                        text = "Reminder time: $reminderTime",
                        style = MaterialTheme.typography.bodyMedium,
                        color = DarkText
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Column {
                        OutlinedButton(
                            onClick = { timeMenuExpanded = true },
                            enabled = dailyReminderEnabled,
                            shape = RoundedCornerShape(18.dp),
                            border = BorderStroke(1.dp, DarkText),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = DarkText
                            )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Select Time")
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Select time"
                                )
                            }
                        }


                        DropdownMenu(
                            expanded = timeMenuExpanded,
                            onDismissRequest = { timeMenuExpanded = false }
                        ) {
                            reminderOptions.forEach { time ->
                                DropdownMenuItem(
                                    text = { Text(time) },
                                    onClick = {
                                        reminderTime = time
                                        timeMenuExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Enable daily reminder",
                            style = MaterialTheme.typography.bodyLarge,
                            color = DarkText
                        )
                        Switch(
                            checked = dailyReminderEnabled,
                            onCheckedChange = { dailyReminderEnabled = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = MidRed,
                                uncheckedThumbColor = Color.White,
                                uncheckedTrackColor = Color(0xFFD6D6D6)
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    SettingsSectionTitle("Heart Rate Alert")

                    Text(
                        text = "Notify me when my heart rate is outside the normal range.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF666666)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    CardioGuardSettingsTextField(
                        label = "Min normal heart rate (BPM)",
                        value = minHeartRate,
                        onValueChange = { minHeartRate = it.filter { ch -> ch.isDigit() } },
                        placeholder = "Enter minimum BPM",
                        enabled = abnormalAlertEnabled
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    CardioGuardSettingsTextField(
                        label = "Max normal heart rate (BPM)",
                        value = maxHeartRate,
                        onValueChange = { maxHeartRate = it.filter { ch -> ch.isDigit() } },
                        placeholder = "Enter maximum BPM",
                        enabled = abnormalAlertEnabled
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Enable abnormal heart rate alert",
                            style = MaterialTheme.typography.bodyLarge,
                            color = DarkText
                        )
                        Switch(
                            checked = abnormalAlertEnabled,
                            onCheckedChange = { abnormalAlertEnabled = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = MidRed,
                                uncheckedThumbColor = Color.White,
                                uncheckedTrackColor = Color(0xFFD6D6D6)
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = LightRed,
                            contentColor = PrimaryRed
                        )
                    ) {
                        Text("Save Settings")
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    TextButton(
                        onClick = onLogoutClick,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Log out",
                            color = PrimaryRed,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))


                }
            }

            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

@Composable
private fun SettingsSectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = DarkText
    )
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
private fun CardioGuardSettingsTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    enabled: Boolean
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Medium,
            color = DarkText
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            enabled = enabled,
            placeholder = { Text(placeholder) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(18.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryRed,
                focusedLabelColor = PrimaryRed,
                cursorColor = PrimaryRed
            )
        )
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    FIT5046A2Theme {
        SettingsScreen(
            onLogoutClick = {}
        )
    }
}




