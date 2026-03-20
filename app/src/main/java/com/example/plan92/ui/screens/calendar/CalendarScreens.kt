package com.example.plan92.ui.screens.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Alarm
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plan92.ui.theme.ApricotGlow
import com.example.plan92.ui.theme.AzureDepth
import com.example.plan92.ui.theme.CoralAccent
import com.example.plan92.ui.theme.InkBlack
import com.example.plan92.ui.theme.Paper
import com.example.plan92.ui.theme.Plan92Theme
import com.example.plan92.ui.theme.ShellWhite

@Composable
fun CalendarScreen(
    onAddReminder: () -> Unit,
) {
    val days = remember {
        listOf(
            "Thu\n12",
            "Fri\n13",
            "Sat\n14",
            "Sun\n15",
            "Mon\n16",
            "Tue\n17",
            "Wed\n18",
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = ShellWhite.copy(alpha = 0.97f),
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    days.forEachIndexed { index, day ->
                        Surface(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(18.dp),
                            color = if (index == 3) CoralAccent else Color.White,
                            tonalElevation = 2.dp,
                        ) {
                            Text(
                                text = day,
                                modifier = Modifier.padding(vertical = 10.dp),
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                color = if (index == 3) InkBlack else Color(0xFF534D61),
                                style = MaterialTheme.typography.labelLarge,
                            )
                        }
                    }
                }

                Text(
                    text = "Today",
                    style = MaterialTheme.typography.headlineMedium,
                    color = InkBlack,
                )

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(28.dp),
                    color = Color.White,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 26.dp, vertical = 38.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .size(132.dp)
                                .clip(RoundedCornerShape(28.dp))
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(CoralAccent.copy(alpha = 0.25f), ApricotGlow.copy(alpha = 0.12f)),
                                    ),
                                ),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.NotificationsActive,
                                contentDescription = null,
                                tint = CoralAccent,
                                modifier = Modifier.size(56.dp),
                            )
                        }
                        Text(
                            text = "Achieve More with Reminders!",
                            style = MaterialTheme.typography.headlineMedium,
                            color = InkBlack,
                        )
                        Text(
                            text = "Tap the add button to schedule planner reminders and manage your day efficiently.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = InkBlack.copy(alpha = 0.68f),
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = onAddReminder,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp),
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = "Add reminder",
            )
        }
    }
}

@Composable
fun ReminderOnboardingScreen(
    onBack: () -> Unit,
    onContinue: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = ShellWhite,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            Box(
                modifier = Modifier
                    .size(190.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(CoralAccent.copy(alpha = 0.18f), Color.White),
                        ),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Outlined.Alarm,
                    contentDescription = null,
                    tint = CoralAccent,
                    modifier = Modifier.size(86.dp),
                )
            }

            Spacer(modifier = Modifier.height(26.dp))

            Text(
                text = "Stay on Track Effortlessly",
                style = MaterialTheme.typography.headlineLarge,
                color = InkBlack,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "We'll remind you at the right time, so you don't have to remember everything.",
                style = MaterialTheme.typography.bodyLarge,
                color = InkBlack.copy(alpha = 0.68f),
            )

            Spacer(modifier = Modifier.height(24.dp))

            listOf(
                "Never miss a deadline again",
                "Boost daily productivity",
                "Hit your milestones on time",
                "Avoid last-minute stress",
                "Maintain your momentum",
            ).forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CheckCircle,
                        contentDescription = null,
                        tint = CoralAccent,
                    )
                    Text(
                        text = item,
                        style = MaterialTheme.typography.titleMedium,
                        color = InkBlack,
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Maybe later",
                style = MaterialTheme.typography.bodyMedium,
                color = InkBlack.copy(alpha = 0.52f),
            )
            Spacer(modifier = Modifier.height(14.dp))
            Button(
                onClick = onContinue,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Let's Stay on Track")
            }
        }
    }
}

@Composable
fun ReminderScheduleScreen(
    onBack: () -> Unit,
) {
    var remindersEnabled by rememberSaveable { mutableStateOf(true) }
    var selectedFrequency by rememberSaveable { mutableStateOf("Daily") }
    val timeColumns = remember { listOf(listOf("8", "9", "10"), listOf("59", "00", "01"), listOf("AM", "PM")) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = ShellWhite,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                contentDescription = "Back",
                            )
                        }
                        Text(
                            text = "Schedule Reminders",
                            style = MaterialTheme.typography.headlineMedium,
                            color = InkBlack,
                        )
                    }
                }
            }

            item {
                Text(
                    text = "Most people start with one daily reminder in the morning.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = InkBlack.copy(alpha = 0.66f),
                )
            }

            item {
                Surface(
                    shape = RoundedCornerShape(22.dp),
                    color = Color.White,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(CoralAccent.copy(alpha = 0.14f)),
                                contentAlignment = Alignment.Center,
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.NotificationsActive,
                                    contentDescription = null,
                                    tint = CoralAccent,
                                )
                            }
                            Text(
                                text = "Get Reminders",
                                style = MaterialTheme.typography.titleMedium,
                                color = InkBlack,
                            )
                        }
                        Switch(
                            checked = remindersEnabled,
                            onCheckedChange = { remindersEnabled = it },
                        )
                    }
                }
            }

            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text(
                        text = "Reminder Frequency",
                        style = MaterialTheme.typography.titleLarge,
                        color = InkBlack,
                    )
                    listOf("Daily", "Three times a week", "Once a week", "Manually selected days").forEach { option ->
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = Color.White,
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                RadioButton(
                                    selected = selectedFrequency == option,
                                    onClick = { selectedFrequency = option },
                                )
                                Text(
                                    text = option,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = InkBlack,
                                )
                            }
                        }
                    }
                }
            }

            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text(
                        text = "Choose your reminder time",
                        style = MaterialTheme.typography.titleLarge,
                        color = InkBlack,
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        timeColumns.forEachIndexed { columnIndex, values ->
                            Surface(
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(24.dp),
                                color = Color.White,
                            ) {
                                Column(
                                    modifier = Modifier.padding(vertical = 12.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(10.dp),
                                ) {
                                    values.forEachIndexed { index, value ->
                                        Text(
                                            text = value,
                                            style = if ((columnIndex != 2 && index == 1) || (columnIndex == 2 && index == 0)) {
                                                MaterialTheme.typography.headlineMedium
                                            } else {
                                                MaterialTheme.typography.bodyLarge
                                            },
                                            color = if ((columnIndex != 2 && index == 1) || (columnIndex == 2 && index == 0)) {
                                                InkBlack
                                            } else {
                                                InkBlack.copy(alpha = 0.35f)
                                            },
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            item {
                Text(
                    text = "We'll only send the reminders you choose.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = InkBlack.copy(alpha = 0.54f),
                )
            }

            item {
                Button(
                    onClick = onBack,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Save")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CalendarPreview() {
    Plan92Theme {
        CalendarScreen(onAddReminder = {})
    }
}

