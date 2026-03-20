@file:OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)

package com.example.plan92.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plan92.ui.theme.ApricotGlow
import com.example.plan92.ui.theme.AzureDepth
import com.example.plan92.ui.theme.BurntOrange
import com.example.plan92.ui.theme.InkBlack
import com.example.plan92.ui.theme.OrchidBurst
import com.example.plan92.ui.theme.Plan92Theme
import com.example.plan92.ui.theme.plan92Palette

@Composable
fun PlannerPage(
    modifier: Modifier = Modifier,
    accent: Color = MaterialTheme.plan92Palette.primaryAccent,
    content: @Composable ColumnScope.() -> Unit,
) {
    PlannerCardSurface(
        modifier = modifier.fillMaxWidth(),
        accent = accent,
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            content = content,
        )
    }
}

@Composable
fun PlannerCardSurface(
    modifier: Modifier = Modifier,
    accent: Color = MaterialTheme.plan92Palette.primaryAccent,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(28.dp),
        color = MaterialTheme.plan92Palette.pageSurface,
        border = BorderStroke(1.dp, accent.copy(alpha = 0.18f)),
        shadowElevation = 8.dp,
    ) {
        Column(content = content)
    }
}

@Composable
fun DecorativeAccentContainer(
    modifier: Modifier = Modifier,
    accent: Color = MaterialTheme.plan92Palette.primaryAccent,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.plan92Palette.sectionSurface,
        border = BorderStroke(1.dp, accent.copy(alpha = 0.14f)),
    ) {
        Column(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        colors = listOf(accent.copy(alpha = 0.14f), Color.Transparent),
                    ),
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            content = content,
        )
    }
}

@Composable
fun PlannerHeader(
    label: String,
    editorKind: String,
    capabilities: List<String>,
    modifier: Modifier = Modifier,
    accent: Color = MaterialTheme.plan92Palette.secondaryAccent,
) {
    DecorativeAccentContainer(
        modifier = modifier,
        accent = accent,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.plan92Palette.titleColor,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = editorKind,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.plan92Palette.bodyColor,
                )
            }

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(accent.copy(alpha = 0.16f)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Outlined.EditNote,
                    contentDescription = null,
                    tint = accent,
                )
            }
        }

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            capabilities.forEach { capability ->
                LabelChip(
                    text = capability,
                    accent = accent,
                )
            }
        }
    }
}

@Composable
fun PlannerTitleBlock(
    title: String,
    subtitle: String,
    chips: List<String>,
    modifier: Modifier = Modifier,
    accent: Color = MaterialTheme.plan92Palette.primaryAccent,
) {
    SectionContainer(
        title = title,
        subtitle = subtitle,
        modifier = modifier,
        accent = accent,
    ) {
        if (chips.isNotEmpty()) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                chips.forEach { chip ->
                    LabelChip(
                        text = chip,
                        accent = accent,
                    )
                }
            }
        }
    }
}

@Composable
fun SectionContainer(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    accent: Color = MaterialTheme.plan92Palette.primaryAccent,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.plan92Palette.sectionSurface,
        border = BorderStroke(1.dp, accent.copy(alpha = 0.14f)),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.plan92Palette.titleColor,
                    fontWeight = FontWeight.SemiBold,
                )
                subtitle?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.plan92Palette.bodyColor,
                    )
                }
            }
            PlannerDivider()
            content()
        }
    }
}

@Composable
fun PlannerDivider(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(MaterialTheme.plan92Palette.lineColor),
    )
}

@Composable
fun LabelChip(
    text: String,
    modifier: Modifier = Modifier,
    accent: Color = MaterialTheme.plan92Palette.secondaryAccent,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(999.dp),
        color = accent.copy(alpha = 0.14f),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            style = MaterialTheme.typography.labelLarge,
            color = accent,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Composable
fun PlannerFieldLine(
    label: String,
    modifier: Modifier = Modifier,
    initialValue: String = "",
    singleLine: Boolean = true,
    minLines: Int = 1,
) {
    var value by rememberSaveable(label) { mutableStateOf(initialValue) }
    OutlinedTextField(
        value = value,
        onValueChange = { value = it },
        modifier = modifier.fillMaxWidth(),
        label = { Text(label) },
        singleLine = singleLine,
        minLines = minLines,
        shape = RoundedCornerShape(16.dp),
        colors = plannerFieldColors(),
    )
}

@Composable
fun DateFieldRow(
    leftLabel: String,
    rightLabel: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        PlannerFieldLine(
            label = leftLabel,
            modifier = Modifier.weight(1f),
        )
        PlannerFieldLine(
            label = rightLabel,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
fun EditableTextSection(
    title: String,
    fields: List<String>,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
) {
    SectionContainer(
        title = title,
        subtitle = subtitle,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            fields.forEach { field ->
                PlannerFieldLine(label = field)
            }
        }
    }
}

@Composable
fun NotesSection(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
) {
    SectionContainer(
        title = title,
        subtitle = subtitle,
        modifier = modifier,
    ) {
        PlannerFieldLine(
            label = "Write here",
            singleLine = false,
            minLines = 6,
        )
    }
}

@Composable
fun PlannerCheckboxRow(
    label: String,
    modifier: Modifier = Modifier,
) {
    var checked by rememberSaveable(label) { mutableStateOf(false) }
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it },
        )
        PlannerFieldLine(
            label = label,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
fun ChecklistSection(
    title: String,
    items: List<String>,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
) {
    SectionContainer(
        title = title,
        subtitle = subtitle,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items.forEach { item ->
                PlannerCheckboxRow(label = item)
            }
        }
    }
}

@Composable
fun ScheduleSection(
    title: String,
    rows: List<String>,
    modifier: Modifier = Modifier,
) {
    SectionContainer(
        title = title,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            rows.forEach { rowLabel ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    LabelChip(
                        text = rowLabel,
                        accent = MaterialTheme.plan92Palette.secondaryAccent,
                    )
                    PlannerFieldLine(
                        label = "Plan",
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
fun HourlyScheduleSection(
    title: String,
    rows: List<String>,
    modifier: Modifier = Modifier,
) {
    ScheduleSection(
        title = title,
        rows = rows,
        modifier = modifier,
    )
}

@Composable
fun DayColumnsSection(
    title: String,
    days: List<String>,
    modifier: Modifier = Modifier,
) {
    SectionContainer(
        title = title,
        modifier = modifier,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            days.forEach { day ->
                Surface(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(18.dp),
                    color = MaterialTheme.plan92Palette.fieldSurface,
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(
                            text = day,
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.plan92Palette.titleColor,
                            fontWeight = FontWeight.SemiBold,
                        )
                        PlannerFieldLine(
                            label = "Tasks",
                            singleLine = false,
                            minLines = 4,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WeekGridSection(
    title: String,
    days: List<String>,
    modifier: Modifier = Modifier,
) {
    DayColumnsSection(
        title = title,
        days = days,
        modifier = modifier,
    )
}

@Composable
fun MonthGridSection(
    title: String,
    headers: List<String>,
    modifier: Modifier = Modifier,
) {
    SectionContainer(
        title = title,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                headers.forEach { header ->
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = header,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.plan92Palette.bodyColor,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }
            repeat(5) { rowIndex ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    repeat(headers.size) { columnIndex ->
                        Surface(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(14.dp),
                            color = MaterialTheme.plan92Palette.fieldSurface,
                            border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
                        ) {
                            Column(
                                modifier = Modifier.padding(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                Text(
                                    text = "${rowIndex * headers.size + columnIndex + 1}",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.plan92Palette.bodyColor,
                                )
                                PlannerFieldLine(
                                    label = "Note",
                                    singleLine = false,
                                    minLines = 2,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BudgetGridSection(
    title: String,
    rows: List<String>,
    columns: List<String>,
    modifier: Modifier = Modifier,
) {
    SectionContainer(
        title = title,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            rows.forEach { row ->
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = MaterialTheme.plan92Palette.fieldSurface,
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(
                            text = row,
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.plan92Palette.titleColor,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            columns.forEach { column ->
                                PlannerFieldLine(
                                    label = column,
                                    modifier = Modifier.weight(1f),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PlannerStatBox(
    title: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.plan92Palette.bodyColor,
                fontWeight = FontWeight.SemiBold,
            )
            PlannerFieldLine(
                label = "Value",
            )
        }
    }
}

@Composable
fun TrackerSection(
    title: String,
    stats: List<String>,
    modifier: Modifier = Modifier,
) {
    SectionContainer(
        title = title,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            stats.chunked(2).forEach { row ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    row.forEach { stat ->
                        PlannerStatBox(
                            title = stat,
                            modifier = Modifier.weight(1f),
                        )
                    }
                    if (row.size == 1) {
                        Box(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun WaterTrackerSection(
    title: String,
    modifier: Modifier = Modifier,
    count: Int = 8,
) {
    val keys = List(count) { index -> "$title-$index" }
    SectionContainer(
        title = title,
        modifier = modifier,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            keys.forEachIndexed { index, key ->
                var checked by rememberSaveable(key) { mutableStateOf(false) }
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { checked = !checked },
                    shape = CircleShape,
                    color = if (checked) MaterialTheme.plan92Palette.secondaryAccent.copy(alpha = 0.2f) else MaterialTheme.plan92Palette.fieldSurface,
                    border = BorderStroke(
                        1.dp,
                        if (checked) MaterialTheme.plan92Palette.secondaryAccent else MaterialTheme.plan92Palette.lineColor,
                    ),
                ) {
                    Box(
                        modifier = Modifier
                            .height(44.dp)
                            .wrapContentHeight(align = Alignment.CenterVertically),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = if (checked) "✓" else "${index + 1}",
                            color = if (checked) MaterialTheme.plan92Palette.secondaryAccent else MaterialTheme.plan92Palette.bodyColor,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MoodSelectorSection(
    title: String,
    moods: List<String>,
    modifier: Modifier = Modifier,
) {
    var selected by rememberSaveable(title) { mutableStateOf(moods.firstOrNull().orEmpty()) }
    SectionContainer(
        title = title,
        modifier = modifier,
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            moods.forEach { mood ->
                FilterChip(
                    selected = selected == mood,
                    onClick = { selected = mood },
                    label = { Text(mood) },
                )
            }
        }
    }
}

@Composable
fun HabitTrackerSection(
    title: String,
    habits: List<String>,
    days: List<String>,
    modifier: Modifier = Modifier,
) {
    SectionContainer(
        title = title,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Box(modifier = Modifier.width(96.dp))
                days.forEach { day ->
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = day,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.plan92Palette.bodyColor,
                        )
                    }
                }
            }

            habits.forEachIndexed { habitIndex, habit ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = habit,
                        modifier = Modifier.width(96.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.plan92Palette.titleColor,
                    )
                    days.forEachIndexed { dayIndex, _ ->
                        var checked by rememberSaveable("$title-$habitIndex-$dayIndex") { mutableStateOf(false) }
                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { checked = !checked },
                            shape = RoundedCornerShape(10.dp),
                            color = if (checked) MaterialTheme.plan92Palette.primaryAccent.copy(alpha = 0.18f) else MaterialTheme.plan92Palette.fieldSurface,
                            border = BorderStroke(
                                1.dp,
                                if (checked) MaterialTheme.plan92Palette.primaryAccent else MaterialTheme.plan92Palette.lineColor,
                            ),
                        ) {
                            Box(
                                modifier = Modifier.height(32.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                if (checked) {
                                    Text("✓", color = MaterialTheme.plan92Palette.primaryAccent)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MealPlannerSection(
    title: String,
    mealTypes: List<String>,
    days: List<String>,
    modifier: Modifier = Modifier,
) {
    SectionContainer(
        title = title,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            mealTypes.forEach { meal ->
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = MaterialTheme.plan92Palette.fieldSurface,
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(
                            text = meal,
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.plan92Palette.titleColor,
                            fontWeight = FontWeight.SemiBold,
                        )
                        days.chunked(2).forEach { chunk ->
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                            ) {
                                chunk.forEach { day ->
                                    PlannerFieldLine(
                                        label = day,
                                        modifier = Modifier.weight(1f),
                                    )
                                }
                                if (chunk.size == 1) {
                                    Box(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ReflectionPromptSection(
    title: String,
    prompts: List<String>,
    modifier: Modifier = Modifier,
) {
    SectionContainer(
        title = title,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            prompts.forEach { prompt ->
                PlannerFieldLine(
                    label = prompt,
                    singleLine = false,
                    minLines = 3,
                )
            }
        }
    }
}

@Composable
fun JournalPromptSection(
    title: String,
    prompts: List<String>,
    modifier: Modifier = Modifier,
) {
    ReflectionPromptSection(
        title = title,
        prompts = prompts,
        modifier = modifier,
    )
}

@Composable
fun ProgressTimelineSection(
    title: String,
    milestones: List<String>,
    modifier: Modifier = Modifier,
) {
    SectionContainer(
        title = title,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            milestones.forEachIndexed { index, milestone ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.Top,
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.plan92Palette.secondaryAccent.copy(alpha = 0.16f)),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "${index + 1}",
                            color = MaterialTheme.plan92Palette.secondaryAccent,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    PlannerFieldLine(
                        label = milestone,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
private fun plannerFieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = MaterialTheme.plan92Palette.fieldSurface,
    unfocusedContainerColor = MaterialTheme.plan92Palette.fieldSurface,
    disabledContainerColor = MaterialTheme.plan92Palette.fieldSurface,
    focusedIndicatorColor = MaterialTheme.plan92Palette.primaryAccent,
    unfocusedIndicatorColor = MaterialTheme.plan92Palette.lineColor,
    focusedLabelColor = MaterialTheme.plan92Palette.primaryAccent,
    unfocusedLabelColor = MaterialTheme.plan92Palette.bodyColor,
    focusedTextColor = MaterialTheme.plan92Palette.titleColor,
    unfocusedTextColor = MaterialTheme.plan92Palette.titleColor,
    cursorColor = MaterialTheme.plan92Palette.primaryAccent,
)

@Preview(showBackground = true)
@Composable
private fun PlannerPagePreview() {
    Plan92Theme {
        PlannerPage(
            accent = BurntOrange,
        ) {
            PlannerHeader(
                label = "Daily Planner",
                editorKind = "STRUCTURED_DAY",
                capabilities = listOf("Checklists", "Schedule", "Notes"),
            )
            PlannerTitleBlock(
                title = "Daily Focus",
                subtitle = "Reusable engine-based planner layout.",
                chips = listOf("Editable", "Template Engine"),
            )
            DateFieldRow(
                leftLabel = "Date",
                rightLabel = "Theme",
            )
            ChecklistSection(
                title = "Top Priorities",
                items = listOf("Priority 1", "Priority 2", "Priority 3"),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TrackerPreview() {
    Plan92Theme {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                TrackerSection(
                    title = "Snapshot",
                    stats = listOf("Sleep", "Energy", "Steps", "Focus"),
                )
            }
            item {
                WaterTrackerSection(
                    title = "Water",
                )
            }
            item {
                HabitTrackerSection(
                    title = "Habits",
                    habits = listOf("Stretch", "Walk", "Read"),
                    days = listOf("M", "T", "W", "T", "F"),
                )
            }
        }
    }
}
