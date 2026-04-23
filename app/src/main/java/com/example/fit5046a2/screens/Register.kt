package com.example.fit5046a2.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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

private val PrimaryRed = Color(0xFFC62828)
private val SoftRed = Color(0xFFFFEBEE)
private val DarkText = Color(0xFF1C1B1F)
private val ErrorRed = Color(0xFFD32F2F)

@Composable
fun RegisterScreen(
    onBackToLogin: () -> Unit = {},
    onRegisterSuccess: () -> Unit = {}
) {
    var age by rememberSaveable { mutableStateOf("") }
    var weight by rememberSaveable { mutableStateOf("") }
    var emergencyContact by rememberSaveable { mutableStateOf("") }
    var selectedCondition by rememberSaveable { mutableStateOf("") }
    var dropdownExpanded by remember { mutableStateOf(false) }

    var ageTouched by remember { mutableStateOf(false) }
    var weightTouched by remember { mutableStateOf(false) }
    var contactTouched by remember { mutableStateOf(false) }

    val medicalConditions = listOf(
        "Hypertension",
        "Diabetes",
        "Heart Disease",
        "Arrhythmia",
        "None"
    )

    val ageError = when {
        !ageTouched -> null
        age.isBlank() -> "Age is required"
        age.toIntOrNull() == null -> "Enter a valid number"
        age.toInt() !in 40..65 -> "Recommended target group: 40-65"
        else -> null
    }

    val weightError = when {
        !weightTouched -> null
        weight.isBlank() -> "Weight is required"
        weight.toDoubleOrNull() == null -> "Enter a valid weight"
        else -> null
    }

    val contactError = when {
        !contactTouched -> null
        emergencyContact.isBlank() -> "Emergency contact is required"
        emergencyContact.length < 8 -> "Contact number is too short"
        else -> null
    }

    val formValid = ageError == null &&
            weightError == null &&
            contactError == null &&
            age.isNotBlank() &&
            weight.isNotBlank() &&
            emergencyContact.isNotBlank() &&
            selectedCondition.isNotBlank()

    Scaffold(
        containerColor = Color(0xFFF8F9FB)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Create Health Profile",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = DarkText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 12.dp)
            )

            Surface(
                shape = CircleShape,
                color = SoftRed,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Heart icon",
                    tint = PrimaryRed,
                    modifier = Modifier.padding(18.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "CardioGuard Registration",
                style = MaterialTheme.typography.headlineSmall,
                color = DarkText,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Set up your cardiovascular health profile for monitoring and personalised alerts.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF666666)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    SectionTitle("Personal Information")

                    AppTextField(
                        label = "Age *",
                        value = age,
                        onValueChange = {
                            age = it.filter { ch -> ch.isDigit() }
                            ageTouched = true
                        },
                        placeholder = "Enter your age",
                        keyboardType = KeyboardType.Number,
                        errorText = ageError
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    AppTextField(
                        label = "Weight (kg) *",
                        value = weight,
                        onValueChange = {
                            weight = it.filter { ch -> ch.isDigit() || ch == '.' }
                            weightTouched = true
                        },
                        placeholder = "Enter your weight",
                        keyboardType = KeyboardType.Decimal,
                        errorText = weightError
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    AppTextField(
                        label = "Emergency Contact *",
                        value = emergencyContact,
                        onValueChange = {
                            emergencyContact = it
                            contactTouched = true
                        },
                        placeholder = "Phone number",
                        keyboardType = KeyboardType.Phone,
                        errorText = contactError
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(16.dp))

                    SectionTitle("Medical History")

                    Text(
                        text = "Medical Condition *",
                        style = MaterialTheme.typography.labelLarge,
                        color = DarkText,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Box {
                        OutlinedTextField(
                            value = selectedCondition,
                            onValueChange = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { dropdownExpanded = true },
                            readOnly = true,
                            placeholder = { Text("Select a condition") },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Dropdown"
                                )
                            },
                            shape = RoundedCornerShape(14.dp),
                            colors = OutlinedTextFieldDefaults.colors()
                        )

                        DropdownMenu(
                            expanded = dropdownExpanded,
                            onDismissRequest = { dropdownExpanded = false }
                        ) {
                            medicalConditions.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(item) },
                                    onClick = {
                                        selectedCondition = item
                                        dropdownExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    if (selectedCondition.isBlank()) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Please select one condition",
                            color = ErrorRed,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        TextButton(
                            onClick = onBackToLogin,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Back")
                        }

                        Button(
                            onClick = onRegisterSuccess,
                            modifier = Modifier
                                .weight(1f)
                                .height(52.dp),
                            enabled = true,
                            shape = RoundedCornerShape(18.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryRed,
                                contentColor = Color.White,
                                disabledContainerColor = Color(0xFFEF9A9A),
                                disabledContentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Register",
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SoftRed)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = PrimaryRed
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Design notes used here",
                            fontWeight = FontWeight.Bold,
                            color = PrimaryRed
                        )
                        Text(
                            text = "• Labels are placed above each field\n• Required fields use an asterisk (*)\n• Inline validation appears below the field",
                            style = MaterialTheme.typography.bodySmall,
                            color = DarkText
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun AppTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType,
    errorText: String?
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = DarkText,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = { Text(placeholder) },
            isError = errorText != null,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            shape = RoundedCornerShape(18.dp),
            colors = OutlinedTextFieldDefaults.colors()
        )

        if (errorText != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = errorText,
                color = ErrorRed,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    MaterialTheme {
        RegisterScreen()
    }
}