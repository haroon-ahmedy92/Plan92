@file:OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)

package com.example.plan92.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.horizontalScroll
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
fun DailyJournalBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        JournalHeaderRow("Date", "Today's Theme")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            PromptEditorCard(
                title = "Top Priorities",
                prompts = listOf("Priority 1", "Priority 2", "Priority 3"),
                modifier = Modifier.weight(1f),
            )
            PromptEditorCard(
                title = "To-Do List",
                prompts = listOf("Task 1", "Task 2", "Task 3", "Task 4", "Task 5"),
                modifier = Modifier.weight(1f),
            )
        }
        MoodAndRatingRow()
        PromptEditorCard(
            title = "Reflection Questions",
            prompts = listOf(
                "What happened today?",
                "What helped me feel productive?",
                "What am I carrying forward tomorrow?",
            ),
        )
        LinedWritingCard(
            title = "Free Writing",
            key = "daily_journal_free",
            lines = 10,
        )
    }
}

@Composable
fun MyDailyJournalBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        JournalHeaderRow("Date", "How I Feel")
        GratitudeAffirmationRow(
            leftTitle = "Grateful For",
            rightTitle = "Affirmation",
            leftKey = "my_daily_gratitude",
            rightKey = "my_daily_affirmation",
        )
        PromptEditorCard(
            title = "Today",
            prompts = listOf(
                "What stood out today?",
                "What lesson or moment do I want to remember?",
            ),
        )
        PromptEditorCard(
            title = "For Tomorrow",
            prompts = listOf(
                "What do I want to focus on next?",
                "What would make tomorrow feel good?",
            ),
        )
        LinedWritingCard(
            title = "Dear Diary",
            key = "my_daily_journal_body",
            lines = 12,
        )
    }
}

@Composable
fun FeelingsJournalBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        JournalHeaderRow("Date", "Energy")
        MoodSelectorRow(
            title = "Feelings",
            options = listOf("Happy", "Calm", "Anxious", "Sad", "Hopeful", "Proud"),
        )
        RatingSelectorRow(
            title = "Rate Today",
            options = listOf("1", "2", "3", "4", "5"),
        )
        GratitudeAffirmationRow(
            leftTitle = "Self-Love Note",
            rightTitle = "Gratitude",
            leftKey = "feelings_self_love",
            rightKey = "feelings_gratitude",
        )
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            PromptEditorCard(
                title = "Why I Feel This Way",
                prompts = listOf("What triggered this feeling?", "What do I need right now?"),
                modifier = Modifier.weight(1f),
            )
            PromptEditorCard(
                title = "Water & Care",
                prompts = listOf("Hydration", "Rest", "Movement", "Support"),
                modifier = Modifier.weight(1f),
            )
        }
        LinedWritingCard(
            title = "Thoughts",
            key = "feelings_journal_body",
            lines = 8,
        )
    }
}

@Composable
fun JournalPromptsBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        JournalHeaderRow("Date", "Prompt Theme")
        PromptEditorCard(
            title = "Reflecting on Your Day",
            prompts = listOf(
                "What made me smile today?",
                "What challenged me today?",
                "What did I learn about myself?",
                "What am I grateful for tonight?",
            ),
        )
        LinedWritingCard(
            title = "Long-Form Response",
            key = "journal_prompts_body",
            lines = 12,
        )
    }
}

@Composable
fun SelfCareJournalBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    SectionContainer(title = title, modifier = modifier) {
        JournalHeaderRow("Week Of", "Care Focus")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            days.forEach { day ->
                PromptEditorCard(
                    title = day,
                    prompts = listOf("How will I care for myself?", "One gentle reminder"),
                    modifier = Modifier.width(168.dp),
                )
            }
        }
        PromptEditorCard(
            title = "Weekly Reflection",
            prompts = listOf(
                "What restored me this week?",
                "What drained me?",
                "What do I want more of next week?",
            ),
        )
    }
}

@Composable
fun ReadingLogBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        JournalHeaderRow("Date", "Reading Mood")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            PromptEditorCard(
                title = "Book Details",
                prompts = listOf("Title", "Author", "Start Date", "Finish Date"),
                modifier = Modifier.weight(1f),
            )
            PromptEditorCard(
                title = "Reading Stats",
                prompts = listOf("Pages", "Chapter", "Rating", "Genre"),
                modifier = Modifier.weight(1f),
            )
        }
        PromptEditorCard(
            title = "Favorite Quotes",
            prompts = listOf("Quote 1", "Quote 2"),
        )
        PromptEditorCard(
            title = "Reflection",
            prompts = listOf(
                "What did I learn from this reading?",
                "What stood out the most?",
                "Would I recommend it?",
            ),
        )
    }
}

@Composable
fun SoapBibleBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        JournalHeaderRow("Date", "Scripture Reference")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            PromptEditorCard(
                title = "Scripture",
                prompts = listOf("Passage", "Key Verse"),
                modifier = Modifier.weight(1f),
            )
            PromptEditorCard(
                title = "Observation",
                prompts = listOf("What stands out?", "What is happening here?"),
                modifier = Modifier.weight(1f),
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            PromptEditorCard(
                title = "Application",
                prompts = listOf("How does this apply to my life?", "What action will I take?"),
                modifier = Modifier.weight(1f),
            )
            PromptEditorCard(
                title = "Prayer",
                prompts = listOf("Prayer points", "Closing prayer"),
                modifier = Modifier.weight(1f),
            )
        }
        LinedWritingCard(
            title = "Additional Notes",
            key = "soap_notes",
            lines = 8,
        )
    }
}

@Composable
fun FindBalanceJournalBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        JournalHeaderRow("Date", "Balance Focus")
        RatingSelectorRow(
            title = "Life Balance Check",
            options = listOf("Work", "Mind", "Body", "Home", "Joy"),
        )
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            PromptEditorCard(
                title = "What Feels Balanced",
                prompts = listOf("Where do I feel steady?", "What is supporting me?"),
                modifier = Modifier.weight(1f),
            )
            PromptEditorCard(
                title = "What Needs Attention",
                prompts = listOf("Where am I stretched?", "What needs more care?"),
                modifier = Modifier.weight(1f),
            )
        }
        PromptEditorCard(
            title = "Reset Plan",
            prompts = listOf(
                "One boundary to set",
                "One nourishing thing to do",
                "One priority to simplify",
            ),
        )
        LinedWritingCard(
            title = "Balance Notes",
            key = "find_balance_body",
            lines = 10,
        )
    }
}

@Composable
fun BulletLifeJournalBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        JournalHeaderRow("Month", "Theme")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            PromptEditorCard(
                title = "Key",
                prompts = listOf("Tasks", "Events", "Notes", "Done"),
                modifier = Modifier.weight(1f),
            )
            PromptEditorCard(
                title = "Collections",
                prompts = listOf("Goals", "Habits", "Ideas", "Future Log"),
                modifier = Modifier.weight(1f),
            )
        }
        BulletWritingSpread(
            title = "Bullet Notes",
            key = "bullet_life_notes",
        )
        PromptEditorCard(
            title = "Migration",
            prompts = listOf("What moves forward?", "What can be released?"),
        )
    }
}

@Composable
fun DearDiaryBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        JournalHeaderRow("Date", "Today's Mood")
        GratitudeAffirmationRow(
            leftTitle = "Today's Thought",
            rightTitle = "Little Win",
            leftKey = "dear_diary_thought",
            rightKey = "dear_diary_win",
        )
        LinedWritingCard(
            title = "Diary Entry",
            key = "dear_diary_body",
            lines = 14,
        )
    }
}

@Composable
fun DailyBulletBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        JournalHeaderRow("Date", "Focus")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            PromptEditorCard(
                title = "Top Tasks",
                prompts = listOf("Task 1", "Task 2", "Task 3"),
                modifier = Modifier.weight(1f),
            )
            PromptEditorCard(
                title = "Quick Notes",
                prompts = listOf("Appointments", "Ideas", "Reminders"),
                modifier = Modifier.weight(1f),
            )
        }
        BulletWritingSpread(
            title = "Bullet Spread",
            key = "daily_bullet_spread",
        )
    }
}

@Composable
private fun JournalHeaderRow(
    leftLabel: String,
    rightLabel: String,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        JournalHeaderField(
            key = "journal_header_$leftLabel",
            label = leftLabel,
            modifier = Modifier.weight(1f),
        )
        JournalHeaderField(
            key = "journal_header_$rightLabel",
            label = rightLabel,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun GratitudeAffirmationRow(
    leftTitle: String,
    rightTitle: String,
    leftKey: String,
    rightKey: String,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        SinglePromptCard(
            title = leftTitle,
            key = leftKey,
            modifier = Modifier.weight(1f),
            minLines = 4,
        )
        SinglePromptCard(
            title = rightTitle,
            key = rightKey,
            modifier = Modifier.weight(1f),
            minLines = 4,
        )
    }
}

@Composable
private fun MoodAndRatingRow() {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        MoodSelectorRow(
            title = "Mood",
            options = listOf("Calm", "Focused", "Tired", "Grateful", "Excited"),
            modifier = Modifier.weight(1f),
        )
        RatingSelectorRow(
            title = "Productivity",
            options = listOf("1", "2", "3", "4", "5"),
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun MoodSelectorRow(
    title: String,
    options: List<String>,
    modifier: Modifier = Modifier,
) {
    var selected by rememberSaveable(title) { mutableStateOf(options.firstOrNull().orEmpty()) }
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.plan92Palette.primaryAccent,
                fontWeight = FontWeight.Bold,
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                options.forEach { option ->
                    JournalChip(
                        key = "${title}_$option",
                        label = option,
                        selected = selected == option,
                        onClick = { selected = option },
                    )
                }
            }
        }
    }
}

@Composable
private fun RatingSelectorRow(
    title: String,
    options: List<String>,
    modifier: Modifier = Modifier,
) {
    var selected by rememberSaveable(title) { mutableStateOf("") }
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.plan92Palette.secondaryAccent,
                fontWeight = FontWeight.Bold,
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                options.forEach { option ->
                    RatingBubble(
                        key = "${title}_$option",
                        label = option,
                        selected = selected == option,
                        onClick = { selected = option },
                    )
                }
            }
        }
    }
}

@Composable
private fun PromptEditorCard(
    title: String,
    prompts: List<String>,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.plan92Palette.titleColor,
                fontWeight = FontWeight.Bold,
            )
            prompts.forEachIndexed { index, prompt ->
                JournalPromptField(
                    key = "${title}_$index",
                    label = prompt,
                    minLines = 2,
                )
            }
        }
    }
}

@Composable
private fun SinglePromptCard(
    title: String,
    key: String,
    modifier: Modifier = Modifier,
    minLines: Int = 3,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.plan92Palette.secondaryAccent,
                fontWeight = FontWeight.Bold,
            )
            JournalPromptField(
                key = key,
                label = title,
                minLines = minLines,
            )
        }
    }
}

@Composable
private fun LinedWritingCard(
    title: String,
    key: String,
    lines: Int,
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.plan92Palette.primaryAccent,
                fontWeight = FontWeight.Bold,
            )
            LinedWritingEditor(
                key = key,
                lines = lines,
            )
        }
    }
}

@Composable
private fun BulletWritingSpread(
    title: String,
    key: String,
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.plan92Palette.primaryAccent,
                fontWeight = FontWeight.Bold,
            )
            DotGridEditor(key = key)
        }
    }
}

@Composable
private fun JournalHeaderField(
    key: String,
    label: String,
    modifier: Modifier = Modifier,
) {
    var value by rememberSaveable(key) { mutableStateOf("") }
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
            value = value,
            onValueChange = { value = it },
            textStyle = TextStyle(
                color = MaterialTheme.plan92Palette.titleColor,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            ),
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
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
private fun JournalPromptField(
    key: String,
    label: String,
    minLines: Int,
) {
    var value by rememberSaveable(key) { mutableStateOf("") }
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.plan92Palette.pageSurface,
        border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        BasicTextField(
            value = value,
            onValueChange = { value = it },
            textStyle = TextStyle(
                color = MaterialTheme.plan92Palette.titleColor,
                fontSize = 13.sp,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            minLines = minLines,
            decorationBox = { innerTextField ->
                if (value.isBlank()) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.plan92Palette.bodyColor.copy(alpha = 0.7f),
                    )
                }
                innerTextField()
            },
        )
    }
}

@Composable
private fun LinedWritingEditor(
    key: String,
    lines: Int,
) {
    var text by rememberSaveable(key) { mutableStateOf("") }
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.plan92Palette.pageSurface,
        border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.plan92Palette.pageSurface),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                repeat(lines) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(MaterialTheme.plan92Palette.lineColor.copy(alpha = 0.6f)),
                    )
                }
            }
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                textStyle = TextStyle(
                    color = MaterialTheme.plan92Palette.titleColor,
                    fontSize = 14.sp,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                minLines = lines / 2,
            )
        }
    }
}

@Composable
private fun DotGridEditor(
    key: String,
) {
    var text by rememberSaveable(key) { mutableStateOf("") }
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.plan92Palette.pageSurface,
        border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                repeat(9) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        repeat(12) {
                            Box(
                                modifier = Modifier
                                    .width(4.dp)
                                    .height(4.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.plan92Palette.lineColor.copy(alpha = 0.7f)),
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
                    fontSize = 14.sp,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                minLines = 10,
            )
        }
    }
}

@Composable
private fun JournalChip(
    key: String,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(999.dp),
        color = if (selected) MaterialTheme.plan92Palette.primaryAccent.copy(alpha = 0.16f) else MaterialTheme.plan92Palette.pageSurface,
        border = BorderStroke(1.dp, if (selected) MaterialTheme.plan92Palette.primaryAccent else MaterialTheme.plan92Palette.lineColor),
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelMedium,
            color = if (selected) MaterialTheme.plan92Palette.primaryAccent else MaterialTheme.plan92Palette.bodyColor,
        )
    }
}

@Composable
private fun RatingBubble(
    key: String,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Surface(
        onClick = onClick,
        shape = CircleShape,
        color = if (selected) MaterialTheme.plan92Palette.secondaryAccent.copy(alpha = 0.16f) else MaterialTheme.plan92Palette.pageSurface,
        border = BorderStroke(1.dp, if (selected) MaterialTheme.plan92Palette.secondaryAccent else MaterialTheme.plan92Palette.lineColor),
    ) {
        Box(
            modifier = Modifier
                .width(32.dp)
                .height(32.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                color = if (selected) MaterialTheme.plan92Palette.secondaryAccent else MaterialTheme.plan92Palette.bodyColor,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
