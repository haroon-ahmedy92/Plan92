package com.example.plan92.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.plan92.ui.theme.Amethyst
import com.example.plan92.ui.theme.ApricotGlow
import com.example.plan92.ui.theme.AzureDepth
import com.example.plan92.ui.theme.BurntOrange
import com.example.plan92.ui.theme.DividerSoft
import com.example.plan92.ui.theme.InkBlack
import com.example.plan92.ui.theme.Paper
import com.example.plan92.ui.theme.PaperTint

@Composable
fun PlannerEditorSection(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    accent: Color = BurntOrange,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = Paper,
        shadowElevation = 2.dp,
        border = androidx.compose.foundation.BorderStroke(1.dp, accent.copy(alpha = 0.22f)),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = InkBlack,
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = InkBlack.copy(alpha = 0.64f),
                    )
                }
                Box(
                    modifier = Modifier
                        .width(72.dp)
                        .height(4.dp)
                        .background(accent, RoundedCornerShape(999.dp)),
                )
            }
            content()
        }
    }
}

@Composable
fun PlannerSingleLineField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        label = { Text(label) },
        colors = plannerFieldColors(),
        shape = RoundedCornerShape(16.dp),
    )
}

@Composable
fun PlannerNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = plannerFieldColors(),
        shape = RoundedCornerShape(16.dp),
    )
}

@Composable
fun PlannerTextArea(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    minLines: Int = 4,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(label) },
        minLines = minLines,
        colors = plannerFieldColors(),
        shape = RoundedCornerShape(18.dp),
    )
}

@Composable
fun PlannerChecklist(
    title: String,
    items: List<String>,
    checks: List<Boolean>,
    onTextChange: (Int, String) -> Unit,
    onCheckedChange: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    PlannerEditorSection(
        title = title,
        modifier = modifier,
        accent = AzureDepth,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items.forEachIndexed { index, item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Checkbox(
                        checked = checks[index],
                        onCheckedChange = { onCheckedChange(index, it) },
                    )
                    PlannerSingleLineField(
                        value = item,
                        onValueChange = { onTextChange(index, it) },
                        label = "Item ${index + 1}",
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
fun PlannerChoiceChips(
    title: String,
    options: List<String>,
    selected: Int,
    onSelected: (Int) -> Unit,
    accent: Color = BurntOrange,
) {
    PlannerEditorSection(
        title = title,
        accent = accent,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            options.chunked(3).forEach { row ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    row.forEachIndexed { idx, label ->
                        val absoluteIndex = options.indexOf(label)
                        FilterChip(
                            selected = selected == absoluteIndex,
                            onClick = { onSelected(absoluteIndex) },
                            label = { Text(label) },
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WaterTrackerEditor(
    glasses: List<Boolean>,
    onGlassChanged: (Int, Boolean) -> Unit,
    accent: Color = BurntOrange,
) {
    PlannerEditorSection(
        title = "Water Tracker",
        subtitle = "Tap each cup as you hydrate through the day.",
        accent = accent,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            glasses.forEachIndexed { index, filled ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                        .border(1.dp, accent.copy(alpha = 0.35f), RoundedCornerShape(14.dp))
                        .background(
                            if (filled) accent.copy(alpha = 0.2f) else PaperTint,
                            RoundedCornerShape(14.dp),
                        )
                        .padding(6.dp),
                        contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = if (filled) "✓" else "${index + 1}",
                        style = MaterialTheme.typography.titleMedium,
                        color = if (filled) accent else InkBlack.copy(alpha = 0.64f),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Composable
fun WeeklyTrackerGrid(
    labels: List<String>,
    days: List<String>,
    checks: List<Boolean>,
    onToggle: (Int) -> Unit,
    accent: Color = AzureDepth,
) {
    PlannerEditorSection(
        title = "Weekly Habits",
        subtitle = "Mark the habits you complete each day.",
        accent = accent,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                SpacerLabel()
                days.forEach { day ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(accent.copy(alpha = 0.12f), RoundedCornerShape(10.dp))
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(day, style = MaterialTheme.typography.bodySmall, color = InkBlack)
                    }
                }
            }
            labels.forEachIndexed { rowIndex, label ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = label,
                        modifier = Modifier.width(90.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = InkBlack,
                    )
                    days.indices.forEach { dayIndex ->
                        val flatIndex = rowIndex * days.size + dayIndex
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(34.dp)
                                .background(
                                    if (checks[flatIndex]) accent.copy(alpha = 0.2f) else PaperTint,
                                    RoundedCornerShape(10.dp),
                                )
                                .border(1.dp, DividerSoft, RoundedCornerShape(10.dp))
                                .padding(4.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Checkbox(
                                checked = checks[flatIndex],
                                onCheckedChange = { onToggle(flatIndex) },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PlannerMonthGrid(
    cells: List<String>,
    onCellChange: (Int, String) -> Unit,
    accent: Color = Amethyst,
) {
    PlannerEditorSection(
        title = "Month Overview",
        subtitle = "Editable mini cells for planning the month.",
        accent = accent,
    ) {
        val days = listOf("M", "T", "W", "T", "F", "S", "S")
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                days.forEach { day ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(accent.copy(alpha = 0.14f), RoundedCornerShape(10.dp))
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(day, style = MaterialTheme.typography.bodySmall, color = InkBlack)
                    }
                }
            }
            repeat(5) { week ->
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    repeat(7) { day ->
                        val index = week * 7 + day
                        OutlinedTextField(
                            value = cells[index],
                            onValueChange = { onCellChange(index, it) },
                            modifier = Modifier
                                .weight(1f)
                                .height(78.dp),
                            textStyle = MaterialTheme.typography.bodySmall,
                            colors = plannerFieldColors(),
                            shape = RoundedCornerShape(12.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PlannerTimelineRows(
    rows: List<String>,
    onRowChange: (Int, String) -> Unit,
    labels: List<String>,
    title: String,
    accent: Color = AzureDepth,
) {
    PlannerEditorSection(
        title = title,
        accent = accent,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            labels.forEachIndexed { index, label ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .background(accent.copy(alpha = 0.14f), RoundedCornerShape(12.dp))
                            .padding(horizontal = 10.dp, vertical = 10.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(label, style = MaterialTheme.typography.bodySmall, color = InkBlack)
                    }
                    PlannerSingleLineField(
                        value = rows[index],
                        onValueChange = { onRowChange(index, it) },
                        label = "Focus",
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
fun PlannerBudgetTable(
    labels: List<String>,
    budgetValues: List<String>,
    actualValues: List<String>,
    onBudgetChange: (Int, String) -> Unit,
    onActualChange: (Int, String) -> Unit,
    accent: Color = BurntOrange,
) {
    PlannerEditorSection(
        title = "Budget Table",
        subtitle = "Local mock values only for this prototype.",
        accent = accent,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            labels.forEachIndexed { index, label ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = label,
                        modifier = Modifier.width(88.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = InkBlack,
                    )
                    PlannerNumberField(
                        value = budgetValues[index],
                        onValueChange = { onBudgetChange(index, it) },
                        label = "Budget",
                        modifier = Modifier.weight(1f),
                    )
                    PlannerNumberField(
                        value = actualValues[index],
                        onValueChange = { onActualChange(index, it) },
                        label = "Actual",
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
fun PlannerMoodSelector(
    selectedIndex: Int,
    onSelected: (Int) -> Unit,
    accent: Color = BurntOrange,
) {
    PlannerEditorSection(
        title = "Mood",
        subtitle = "Pick the feeling that matches your day.",
        accent = accent,
    ) {
        val moods = listOf("Low", "Okay", "Calm", "Bright", "Amazing")
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            moods.forEachIndexed { index, label ->
                Surface(
                    onClick = { onSelected(index) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp),
                    color = if (selectedIndex == index) accent.copy(alpha = 0.18f) else PaperTint,
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (selectedIndex == index) accent.copy(alpha = 0.42f) else DividerSoft,
                    ),
                ) {
                    Text(
                        text = label,
                        modifier = Modifier.padding(vertical = 12.dp),
                        style = MaterialTheme.typography.bodySmall,
                        color = InkBlack,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Composable
private fun SpacerLabel() {
    Box(
        modifier = Modifier.width(90.dp),
    )
}

@Composable
private fun plannerFieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = PaperTint,
    unfocusedContainerColor = PaperTint,
    disabledContainerColor = PaperTint,
    focusedTextColor = InkBlack,
    unfocusedTextColor = InkBlack,
    focusedLabelColor = BurntOrange,
    unfocusedLabelColor = InkBlack.copy(alpha = 0.54f),
    focusedIndicatorColor = BurntOrange,
    unfocusedIndicatorColor = DividerSoft,
    cursorColor = BurntOrange,
)
