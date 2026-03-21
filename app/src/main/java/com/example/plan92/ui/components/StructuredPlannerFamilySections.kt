package com.example.plan92.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plan92.ui.theme.plan92Palette

@Composable
fun LinedNotesSection(
    title: String,
    dateLabel: String,
    lines: Int,
    modifier: Modifier = Modifier,
) {
    SectionContainer(
        title = title,
        subtitle = "A clean lined page for long-form writing and notes.",
        modifier = modifier,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = dateLabel,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.plan92Palette.bodyColor,
                fontWeight = FontWeight.SemiBold,
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(MaterialTheme.plan92Palette.lineColor),
            )
        }
        LinedWritingSurface(
            key = title,
            lines = lines,
        )
    }
}

@Composable
fun GridNotesSection(
    title: String,
    dateLabel: String,
    lines: Int,
    modifier: Modifier = Modifier,
) {
    SectionContainer(
        title = title,
        subtitle = "A flexible square-grid page for open writing and structured sketching.",
        modifier = modifier,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = dateLabel,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.plan92Palette.bodyColor,
                fontWeight = FontWeight.SemiBold,
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(MaterialTheme.plan92Palette.lineColor),
            )
        }
        GridWritingSurface(
            key = title,
            lines = lines,
        )
    }
}

@Composable
fun DotGridWritingSection(
    title: String,
    dateLabel: String,
    lines: Int,
    modifier: Modifier = Modifier,
) {
    SectionContainer(
        title = title,
        subtitle = "A dot-grid canvas for bullet journaling, notes, and diagrams.",
        modifier = modifier,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = dateLabel,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.plan92Palette.bodyColor,
                fontWeight = FontWeight.SemiBold,
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(MaterialTheme.plan92Palette.lineColor),
            )
        }
        DotGridWritingSurface(
            key = title,
            lines = lines,
        )
    }
}

@Composable
fun CategoryGridSection(
    title: String,
    categories: List<String>,
    columns: Int,
    checkable: Boolean,
    cellLines: Int,
    modifier: Modifier = Modifier,
) {
    SectionContainer(
        title = title,
        modifier = modifier,
    ) {
        categories.chunked(columns).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                row.forEach { category ->
                    CategoryCell(
                        title = category,
                        checkable = checkable,
                        lines = cellLines,
                        modifier = Modifier.weight(1f),
                    )
                }
                repeat(columns - row.size) {
                    Box(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun WeekListWithSideChecklistSection(
    title: String,
    headerLeftLabel: String,
    headerRightLabel: String,
    weekdays: List<String>,
    checkable: Boolean,
    listLines: Int,
    modifier: Modifier = Modifier,
) {
    var weekOf by rememberSaveable(title) { mutableStateOf("") }

    SectionContainer(
        title = title,
        modifier = modifier,
    ) {
        CompactHeaderField(
            label = headerLeftLabel,
            value = weekOf,
            onValueChange = { weekOf = it },
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top,
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                weekdays.forEach { day ->
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = MaterialTheme.plan92Palette.fieldSurface,
                        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                        ) {
                            Text(
                                text = day,
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.plan92Palette.titleColor,
                                fontWeight = FontWeight.SemiBold,
                            )
                            CompactEntryField(
                                key = "${title}_${day}_entry",
                                label = "Plan",
                                checkable = false,
                            )
                        }
                    }
                }
            }
            CategoryCell(
                title = headerRightLabel,
                checkable = checkable,
                lines = listLines,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
fun QuadrantChecklistSection(
    title: String,
    quadrantTitles: List<String>,
    checkable: Boolean,
    lines: Int,
    modifier: Modifier = Modifier,
) {
    SectionContainer(
        title = title,
        modifier = modifier,
    ) {
        quadrantTitles.chunked(2).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                row.forEach { quadrant ->
                    CategoryCell(
                        title = quadrant,
                        checkable = checkable,
                        lines = lines,
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

@Composable
fun TaskBreakdownBoardSection(
    title: String,
    blocks: List<String>,
    modifier: Modifier = Modifier,
) {
    SectionContainer(
        title = title,
        modifier = modifier,
    ) {
        Surface(
            shape = RoundedCornerShape(14.dp),
            color = MaterialTheme.plan92Palette.fieldSurface,
            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(
                    text = blocks.getOrElse(0) { "Task Identification" },
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.plan92Palette.primaryAccent,
                    fontWeight = FontWeight.SemiBold,
                )
                MultilineCardEditor(
                    key = "${title}_identification",
                    minHeight = 110.dp,
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            BreakdownMiniCard(
                title = blocks.getOrElse(1) { "Prioritization" },
                key = "${title}_prioritization",
                modifier = Modifier.weight(1f),
            )
            BreakdownMiniCard(
                title = blocks.getOrElse(2) { "Estimation" },
                key = "${title}_estimation",
                modifier = Modifier.weight(1f),
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            BreakdownMiniCard(
                title = blocks.getOrElse(3) { "Task Assignment" },
                key = "${title}_assignment",
                modifier = Modifier.weight(1f),
            )
            BreakdownMiniCard(
                title = blocks.getOrElse(4) { "Conclusion" },
                key = "${title}_conclusion",
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
fun MeetingNotesBoardSection(
    title: String,
    headerFields: List<String>,
    topics: List<String>,
    modifier: Modifier = Modifier,
) {
    SectionContainer(
        title = title,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            headerFields.chunked(2).forEach { row ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    row.forEach { field ->
                        CompactHeaderField(
                            label = field,
                            modifier = Modifier.weight(1f),
                        )
                    }
                    if (row.size == 1) {
                        Box(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        topics.forEach { topic ->
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = MaterialTheme.plan92Palette.fieldSurface,
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Text(
                        text = topic,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.plan92Palette.primaryAccent,
                        fontWeight = FontWeight.Bold,
                    )
                    MultilineCardEditor(
                        key = "${title}_${topic}",
                        minHeight = 120.dp,
                    )
                }
            }
        }
    }
}

@Composable
fun GoalsTrackerBoardSection(
    title: String,
    monthLabel: String,
    stepLabels: List<String>,
    rewardLabels: List<String>,
    modifier: Modifier = Modifier,
) {
    var month by rememberSaveable(title) { mutableStateOf("") }

    SectionContainer(
        title = title,
        modifier = modifier,
    ) {
        CompactHeaderField(
            label = monthLabel,
            value = month,
            onValueChange = { month = it },
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            CategoryCell(
                title = "My Top Five Goals",
                checkable = false,
                lines = 5,
                modifier = Modifier.weight(1f),
            )
            CategoryCell(
                title = "To-Do List",
                checkable = true,
                lines = 5,
                modifier = Modifier.weight(1f),
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            stepLabels.forEachIndexed { index, step ->
                Surface(
                    modifier = Modifier.padding(start = (index * 18).dp),
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.plan92Palette.fieldSurface,
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        Text(
                            text = step,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.plan92Palette.secondaryAccent,
                            fontWeight = FontWeight.SemiBold,
                        )
                        CompactEntryField(
                            key = "${title}_step_$index",
                            label = "Action",
                            checkable = false,
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            rewardLabels.forEachIndexed { index, reward ->
                BreakdownMiniCard(
                    title = reward,
                    key = "${title}_reward_$index",
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun CategoryCell(
    title: String,
    checkable: Boolean,
    lines: Int,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.plan92Palette.titleColor,
                fontWeight = FontWeight.SemiBold,
            )
            repeat(lines) { index ->
                CompactEntryField(
                    key = "${title}_$index",
                    label = "Entry",
                    checkable = checkable,
                )
            }
        }
    }
}

@Composable
private fun BreakdownMiniCard(
    title: String,
    key: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.plan92Palette.secondaryAccent,
                fontWeight = FontWeight.SemiBold,
            )
            MultilineCardEditor(
                key = key,
                minHeight = 90.dp,
            )
        }
    }
}

@Composable
private fun CompactHeaderField(
    label: String,
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
) {
    var internalValue by rememberSaveable(label) { mutableStateOf(value) }
    val resolvedValue = if (value.isNotEmpty() || internalValue.isEmpty()) value else internalValue

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.plan92Palette.bodyColor,
        )
        BasicTextField(
            value = resolvedValue,
            onValueChange = {
                internalValue = it
                onValueChange(it)
            },
            textStyle = TextStyle(
                color = MaterialTheme.plan92Palette.titleColor,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            ),
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    innerTextField()
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(MaterialTheme.plan92Palette.lineColor),
                    )
                }
            },
        )
    }
}

@Composable
private fun CompactEntryField(
    key: String,
    label: String,
    checkable: Boolean,
) {
    var text by rememberSaveable("${key}_text") { mutableStateOf("") }
    var checked by rememberSaveable("${key}_checked") { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (checkable) {
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it },
                modifier = Modifier.width(20.dp),
            )
        }
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(
                color = MaterialTheme.plan92Palette.titleColor,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
            ),
            modifier = Modifier.weight(1f),
            decorationBox = { innerTextField ->
                Column {
                    if (text.isEmpty()) {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.plan92Palette.bodyColor.copy(alpha = 0.46f),
                        )
                    }
                    innerTextField()
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(MaterialTheme.plan92Palette.lineColor),
                    )
                }
            },
        )
    }
}

@Composable
private fun MultilineCardEditor(
    key: String,
    minHeight: androidx.compose.ui.unit.Dp,
) {
    var text by rememberSaveable(key) { mutableStateOf("") }
    BasicTextField(
        value = text,
        onValueChange = { text = it },
        textStyle = TextStyle(
            color = MaterialTheme.plan92Palette.titleColor,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(minHeight),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Transparent)
                    .padding(horizontal = 4.dp, vertical = 2.dp),
            ) {
                if (text.isEmpty()) {
                    Text(
                        text = "Write here",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.plan92Palette.bodyColor.copy(alpha = 0.42f),
                    )
                }
                innerTextField()
            }
        },
    )
}

@Composable
private fun LinedWritingSurface(
    key: String,
    lines: Int,
) {
    var text by rememberSaveable(key) { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height((lines * 22).dp)
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.plan92Palette.fieldSurface)
            .border(1.dp, MaterialTheme.plan92Palette.lineColor, RoundedCornerShape(14.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(
                color = MaterialTheme.plan92Palette.titleColor,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                lineHeight = 20.sp,
            ),
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(19.dp),
                    ) {
                        repeat(lines) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(MaterialTheme.plan92Palette.lineColor),
                            )
                        }
                    }
                    innerTextField()
                }
            },
        )
    }
}

@Composable
private fun GridWritingSurface(
    key: String,
    lines: Int,
) {
    var text by rememberSaveable(key) { mutableStateOf("") }
    val height = (lines * 22).dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.plan92Palette.fieldSurface)
            .border(1.dp, MaterialTheme.plan92Palette.lineColor, RoundedCornerShape(14.dp))
            .padding(10.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            repeat(lines) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.plan92Palette.lineColor.copy(alpha = 0.55f)),
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            repeat(10) {
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxSize()
                        .background(MaterialTheme.plan92Palette.lineColor.copy(alpha = 0.35f)),
                )
            }
        }
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(
                color = MaterialTheme.plan92Palette.titleColor,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                lineHeight = 20.sp,
            ),
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        )
    }
}

@Composable
private fun DotGridWritingSurface(
    key: String,
    lines: Int,
) {
    var text by rememberSaveable(key) { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height((lines * 22).dp)
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.plan92Palette.fieldSurface)
            .border(1.dp, MaterialTheme.plan92Palette.lineColor, RoundedCornerShape(14.dp))
            .padding(10.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            repeat(lines) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    repeat(12) {
                        Box(
                            modifier = Modifier
                                .width(3.dp)
                                .height(3.dp)
                                .clip(RoundedCornerShape(999.dp))
                                .background(MaterialTheme.plan92Palette.lineColor.copy(alpha = 0.75f)),
                        )
                    }
                }
            }
        }
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(
                color = MaterialTheme.plan92Palette.titleColor,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                lineHeight = 20.sp,
            ),
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        )
    }
}
