@file:OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)

package com.example.plan92.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plan92.ui.theme.plan92Palette

private val DailyCardShape = RoundedCornerShape(PlannerSheetMetrics.SectionRadius)
private val DailyCardBorder
    @Composable get() = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor.copy(alpha = 0.72f))

private enum class DailyCardTone {
    Primary,
    Secondary,
}

@Composable
private fun DailyCardFrame(
    title: String,
    modifier: Modifier = Modifier,
    tone: DailyCardTone = DailyCardTone.Primary,
    titleColor: Color = MaterialTheme.plan92Palette.titleColor,
    content: @Composable ColumnScope.() -> Unit,
) {
    val palette = MaterialTheme.plan92Palette
    val background = when (tone) {
        DailyCardTone.Primary -> palette.fieldSurface
        DailyCardTone.Secondary -> palette.sectionSurface.copy(alpha = 0.78f)
    }
    val borderColor = when (tone) {
        DailyCardTone.Primary -> palette.lineColor.copy(alpha = 0.72f)
        DailyCardTone.Secondary -> palette.lineColor.copy(alpha = 0.5f)
    }

    Surface(
        modifier = modifier,
        shape = DailyCardShape,
        color = background,
        border = androidx.compose.foundation.BorderStroke(1.dp, borderColor),
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = PlannerSheetMetrics.SectionPaddingHorizontal,
                vertical = PlannerSheetMetrics.SectionPaddingVertical,
            ),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge.copy(fontSize = 10.5.sp, lineHeight = 12.sp),
                color = titleColor,
                fontWeight = FontWeight.SemiBold,
            )
            content()
        }
    }
}

@Composable
fun DailyClassicBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(
        title = title,
        modifier = modifier,
    ) {
        HeaderLineRow(
            leftLabel = "Date",
            rightLabel = "Weather",
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            listOf("Sunny", "Cloudy", "Rain", "Wind", "Snow", "Hot").forEach { weather ->
                SelectionBubble(label = weather)
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyChecklistCard(
                title = "3 Priorities",
                lines = 3,
                modifier = Modifier.weight(0.84f),
            )
            DailyScheduleCard(
                title = "Plans & Schedules",
                times = listOf("6 AM", "7 AM", "8 AM", "9 AM", "10 AM", "11 AM", "12 PM", "1 PM", "2 PM", "3 PM", "4 PM", "5 PM", "6 PM", "7 PM", "8 PM"),
                modifier = Modifier.weight(1.16f),
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyChecklistCard(
                title = "To Do List",
                lines = 7,
                modifier = Modifier.weight(0.92f),
            )
            DailyNotesCard(
                title = "Things to Get Done",
                lines = 7,
                modifier = Modifier.weight(1.08f),
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            Column(
                modifier = Modifier.weight(0.78f),
                verticalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap),
            ) {
                MiniTextCard("Money In", tone = DailyCardTone.Secondary)
                MiniTextCard("Money Out", tone = DailyCardTone.Secondary)
            }
            DailyNotesCard(
                title = "Comment",
                lines = 3,
                modifier = Modifier.weight(1.22f),
                tone = DailyCardTone.Secondary,
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            WaterTrackerBar(title = "Water", modifier = Modifier.weight(1.08f), tone = DailyCardTone.Secondary)
            MoodTrackerBar(title = "Mood", modifier = Modifier.weight(0.92f), tone = DailyCardTone.Secondary)
        }
    }
}

@Composable
fun DailyAgendaBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        HeaderLineRow(leftLabel = "Date", rightLabel = "")
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            NumberedPriorityCard(
                title = "Top 3 Priorities",
                count = 3,
                modifier = Modifier.weight(0.88f),
            )
            DailyScheduleCard(
                title = "Agenda",
                times = listOf("Morning", "Midday", "Afternoon", "Evening"),
                modifier = Modifier.weight(1.12f),
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyChecklistCard(
                title = "To-Do List",
                lines = 10,
                modifier = Modifier.weight(1.02f),
            )
            Column(
                modifier = Modifier.weight(0.98f),
                verticalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap),
            ) {
                DailyNotesCard(title = "Meals", lines = 4, tone = DailyCardTone.Secondary)
                WaterTrackerBar(title = "Water", count = 6, tone = DailyCardTone.Secondary)
            }
        }
    }
}

@Composable
fun ProductiveDayBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        HeaderLineRow(leftLabel = "Date", rightLabel = "No.1 Goal For The Day")
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            NumberedPriorityCard(
                title = "3 Main Tasks",
                count = 3,
                modifier = Modifier.weight(0.82f),
            )
            DailyScheduleCard(
                title = "Scheduled Appointments/Calls",
                times = listOf("10 AM", "11 AM", "2 PM", "5 PM", "7 PM"),
                modifier = Modifier.weight(1.18f),
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            PomodoroTrackerCard(title = "Time Tracker", modifier = Modifier.weight(0.84f), tone = DailyCardTone.Secondary)
            DailyNotesCard(title = "Notes", lines = 5, modifier = Modifier.weight(1.16f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyNotesCard(title = "Must Do", lines = 5, modifier = Modifier.weight(1.05f))
            DailyNotesCard(title = "Can Wait", lines = 4, modifier = Modifier.weight(0.9f), tone = DailyCardTone.Secondary)
            DailyNotesCard(title = "Tomorrow", lines = 4, modifier = Modifier.weight(1.05f), tone = DailyCardTone.Secondary)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            PomodoroTrackerCard(title = "Focus Sprint", modifier = Modifier.weight(0.96f), tone = DailyCardTone.Secondary)
            PomodoroTrackerCard(title = "Reset", modifier = Modifier.weight(1.04f), tone = DailyCardTone.Secondary)
        }
    }
}

@Composable
fun DailyTaskBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        HeaderLineRow(leftLabel = "Date", rightLabel = "")
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyChecklistCard(
                title = "Task List",
                lines = 12,
                modifier = Modifier.weight(1.04f),
            )
            Column(
                modifier = Modifier.weight(0.96f),
                verticalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap),
            ) {
                DailyNotesCard(title = "Urgent", lines = 4)
                DailyChecklistCard(title = "Top Priorities", lines = 4, tone = DailyCardTone.Secondary)
                DailyNotesCard(title = "Expenses", lines = 3, tone = DailyCardTone.Secondary)
                DailyNotesCard(title = "Notes", lines = 4, tone = DailyCardTone.Secondary)
            }
        }
    }
}

@Composable
fun DailyWorkBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        HeaderLineRow(leftLabel = "Date", rightLabel = "S M T W T F S")
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyChecklistCard(title = "Top Priorities", lines = 4, modifier = Modifier.weight(0.9f))
            DailyChecklistCard(title = "To-Do List", lines = 5, modifier = Modifier.weight(1.1f))
        }
        DailyNotesCard(title = "Meetings & Deadlines", lines = 9)
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyNotesCard(title = "Reminders For Me", lines = 3, modifier = Modifier.weight(0.88f), tone = DailyCardTone.Secondary)
            DailyNotesCard(title = "Upcoming Projects", lines = 4, modifier = Modifier.weight(1.12f), tone = DailyCardTone.Secondary)
        }
    }
}

@Composable
fun FullDayHourlyBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        HeaderLineRow(leftLabel = "Date", rightLabel = "")
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyScheduleCard(
                title = "AM",
                times = listOf("5 AM", "6 AM", "7 AM", "8 AM", "9 AM", "10 AM", "11 AM", "12 PM", "1 PM", "2 PM", "3 PM", "4 PM"),
                modifier = Modifier.weight(1.02f),
            )
            DailyScheduleCard(
                title = "PM",
                times = listOf("5 PM", "6 PM", "7 PM", "8 PM", "9 PM", "10 PM", "11 PM", "12 AM", "1 AM", "2 AM", "3 AM", "4 AM"),
                modifier = Modifier.weight(0.98f),
            )
        }
    }
}

@Composable
fun DailyGoalBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        HeaderLineRow(leftLabel = "Date", rightLabel = "")
        SingleHeaderField(label = "Today's Goal")
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyChecklistCard(title = "Goal Breakdown", lines = 5, modifier = Modifier.weight(1f))
            DailyChecklistCard(title = "Goal Breakdown", lines = 5, modifier = Modifier.weight(1f), tone = DailyCardTone.Secondary)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyChecklistCard(title = "To-Do List", lines = 8, modifier = Modifier.weight(0.94f))
            DailyScheduleCard(
                title = "Schedule",
                times = listOf("6:00 AM", "7:30 AM", "9:00 AM", "10:30 AM", "1:00 PM", "4:30 PM", "6:00 PM", "9:00 PM"),
                modifier = Modifier.weight(1.06f),
            )
        }
        DailyNotesCard(title = "Notes", lines = 4, tone = DailyCardTone.Secondary)
    }
}

@Composable
fun RoutineBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            Column(
                modifier = Modifier.weight(0.84f),
                verticalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap),
            ) {
                SingleHeaderField(label = "Best")
                DailyChecklistCard(title = "Top Priorities", lines = 5)
                DailyChecklistCard(title = "Other To-Dos", lines = 5, tone = DailyCardTone.Secondary)
            }
            Column(
                modifier = Modifier.weight(1.16f),
                verticalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap),
            ) {
                RoutineTimeCard(title = "Good Morning", accent = MaterialTheme.plan92Palette.warmAccent)
                RoutineTimeCard(title = "Good Afternoon", accent = MaterialTheme.plan92Palette.primaryAccent.copy(alpha = 0.7f))
                RoutineTimeCard(title = "Good Evening", accent = MaterialTheme.plan92Palette.secondaryAccent.copy(alpha = 0.7f))
            }
        }
    }
}

@Composable
fun WorkTasksBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        HeaderLineRow(leftLabel = "Date", rightLabel = "")
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyChecklistCard(title = "Must Do", lines = 5, modifier = Modifier.weight(1.08f))
            DailyChecklistCard(title = "To Do (If Time)", lines = 4, modifier = Modifier.weight(0.92f), tone = DailyCardTone.Secondary)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyChecklistCard(title = "Projects", lines = 5, modifier = Modifier.weight(1.06f))
            DailyChecklistCard(title = "Meetings", lines = 4, modifier = Modifier.weight(0.94f), tone = DailyCardTone.Secondary)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyChecklistCard(title = "Morning Tasks", lines = 4, modifier = Modifier.weight(0.96f), tone = DailyCardTone.Secondary)
            DailyChecklistCard(title = "Afternoon Tasks", lines = 4, modifier = Modifier.weight(1.04f), tone = DailyCardTone.Secondary)
        }
    }
}

@Composable
fun TaskManagementBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        HeaderLineRow(leftLabel = "Most Important Tasks", rightLabel = "Date")
        QuadrantChecklist(title = "Do First", lines = 6, modifier = Modifier.fillMaxWidth())
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            QuadrantChecklist(title = "Do First", lines = 6, modifier = Modifier.weight(1f))
            QuadrantChecklist(title = "Plan", lines = 6, modifier = Modifier.weight(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            QuadrantChecklist(title = "Delegate", lines = 6, modifier = Modifier.weight(0.94f))
            QuadrantChecklist(title = "Eliminate", lines = 6, modifier = Modifier.weight(1.06f))
        }
    }
}

@Composable
fun AdhdDailyBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        HeaderLineRow(leftLabel = "Date", rightLabel = "M T W T F S S")
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyNotesCard(title = "Today's Focus", lines = 4, modifier = Modifier.weight(1f))
            MoodAndReminderCard(modifier = Modifier.weight(0.96f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyNotesCard(title = "Do Immediately", lines = 4, modifier = Modifier.weight(1.04f))
            DailyNotesCard(title = "Do Later", lines = 4, modifier = Modifier.weight(0.96f), tone = DailyCardTone.Secondary)
            DailyNotesCard(title = "Delegate", lines = 4, modifier = Modifier.weight(0.96f), tone = DailyCardTone.Secondary)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyChecklistCard(title = "Morning Routine", lines = 4, modifier = Modifier.weight(0.92f), tone = DailyCardTone.Secondary)
            DailyScheduleCard(
                title = "Time",
                times = listOf("6:00 AM", "7:30 AM", "9:00 AM", "10:30 AM", "12:30 PM", "2:00 PM", "3:30 PM", "5:00 PM", "7:00 PM", "9:00 PM"),
                modifier = Modifier.weight(1.18f),
            )
            DailyChecklistCard(title = "To-Do", lines = 8, modifier = Modifier.weight(0.9f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyChecklistCard(title = "Evening Routine", lines = 4, modifier = Modifier.weight(0.86f), tone = DailyCardTone.Secondary)
            DailyNotesCard(title = "Brain Dump", lines = 6, modifier = Modifier.weight(1.14f))
        }
    }
}

@Composable
fun ExerciseDailyBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            MetricFieldGroup(
                title = "Food",
                labels = listOf("1", "2", "3", "4", "5"),
                modifier = Modifier.weight(1f),
            )
            MetricFieldGroup(
                title = "Calories",
                labels = listOf("Target", "Actual"),
                modifier = Modifier.weight(0.7f),
            )
            MetricFieldGroup(
                title = "Date",
                labels = listOf("Today"),
                modifier = Modifier.weight(0.8f),
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            MiniTrackerCard(title = "Hydration", entries = 6, modifier = Modifier.weight(1f))
            MiniTrackerCard(title = "Sleep", entries = 7, modifier = Modifier.weight(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyNotesCard(title = "Today's Goal", lines = 4, modifier = Modifier.weight(1f))
            FocusSelectorCard(modifier = Modifier.weight(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            TrackerTableCard(
                title = "Exercise Log",
                columns = listOf("Exercise", "Sets", "Reps", "Weight", "Time", "Distance"),
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            MiniTextCard("Daily Steps", modifier = Modifier.weight(1f), tone = DailyCardTone.Secondary)
            MiniTextCard("Distance", modifier = Modifier.weight(1f), tone = DailyCardTone.Secondary)
            MiniTextCard("Calories", modifier = Modifier.weight(1f), tone = DailyCardTone.Secondary)
        }
    }
}

@Composable
fun SelfCareDailyBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        HeaderLineRow(leftLabel = "Date", rightLabel = "M T W T F S S")
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyChecklistCard(title = "Checklist", lines = 8, modifier = Modifier.weight(1.4f))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap),
            ) {
                ChoiceGroupCard(title = "Workout", options = listOf("Cardio", "Weight", "Stretch", "Rest Day", "Yoga", "Other"))
                MiniTrackerCard(title = "Hours Of Sleep", entries = 7)
                WaterTrackerBar(title = "Water Balance", count = 7, tone = DailyCardTone.Secondary)
                MoodTrackerBar(title = "Mood", count = 5, tone = DailyCardTone.Secondary)
            }
        }
        DailyNotesCard(title = "Things That Make Me Happy Today...", lines = 4, tone = DailyCardTone.Secondary)
    }
}

@Composable
fun DailyReflectionBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        HeaderLineRow(leftLabel = "Date", rightLabel = "")
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyNotesCard(title = "Thing I'm Grateful For", lines = 3, modifier = Modifier.weight(1f))
            DailyNotesCard(title = "Today's Affirmation", lines = 3, modifier = Modifier.weight(1f), tone = DailyCardTone.Secondary)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyNotesCard(title = "What I Achieved Today", lines = 4, modifier = Modifier.weight(1f))
            DailyNotesCard(title = "What I Couldn't Achieve Today", lines = 4, modifier = Modifier.weight(1f), tone = DailyCardTone.Secondary)
        }
        DailyNotesCard(title = "Today Review", lines = 5)
        DailyNotesCard(title = "What I'm Looking Forward To Tomorrow", lines = 4, tone = DailyCardTone.Secondary)
        DailyChecklistCard(title = "Tomorrow Plan & To Do List", lines = 6, tone = DailyCardTone.Secondary)
    }
}

@Composable
fun DailyReflectionJournalBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        HeaderLineRow(leftLabel = "Date", rightLabel = "")
        ReflectionPromptCard(
            title = "Current Mindset Check-In",
            prompts = listOf(
                "What's my dominant emotion today?",
                "Why do I feel this way?",
                "List up to 3 things you're proud of today",
                "When did I avoid something important or feel stuck?",
            ),
        )
        ReflectionPromptCard(
            title = "Growth Goal Check-In",
            prompts = listOf(
                "My current personal growth goal is",
                "What did I do today that moved me closer?",
                "Where did I hold back or procrastinate?",
                "What triggered me?",
            ),
        )
        ReflectionPromptCard(
            title = "Action Step For Next Session",
            prompts = listOf(
                "Support needed / questions for my coach",
                "I need help with",
                "I want to ask my coach",
                "Today's anchor affirmation",
                "Coaching notes / free thoughts",
            ),
        )
    }
}

@Composable
fun DailyDevotionalBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        HeaderLineRow(leftLabel = "M T W T F S S", rightLabel = "")
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap),
            ) {
                DailyChecklistCard(title = "Prayer List", lines = 8)
                DailyNotesCard(title = "Answered Prayers", lines = 5, tone = DailyCardTone.Secondary)
                DailyNotesCard(title = "Notes", lines = 4, tone = DailyCardTone.Secondary)
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap),
            ) {
                DailyNotesCard(title = "Scripture", lines = 8)
                DailyNotesCard(title = "Observation", lines = 5, tone = DailyCardTone.Secondary)
                DailyNotesCard(title = "Application", lines = 5, tone = DailyCardTone.Secondary)
            }
        }
    }
}

@Composable
fun DailyManifestBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        HeaderLineRow(leftLabel = "Date", rightLabel = "")
        DailyNotesCard(title = "Journaling", lines = 4)
        DailyNotesCard(title = "Gratitude", lines = 3)
        DailyNotesCard(title = "Affirmation", lines = 4)
        DailyNotesCard(title = "Visualization", lines = 4)
    }
}

@Composable
fun DailyBrainDumpBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        HeaderLineRow(leftLabel = "Date", rightLabel = "Time")
        Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
            DailyChecklistCard(title = "To-Do List", lines = 14, modifier = Modifier.weight(1.02f))
            Column(
                modifier = Modifier.weight(0.98f),
                verticalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap),
            ) {
                SingleLinePrompt(title = "Mood")
                DailyChecklistCard(title = "Top Priorities", lines = 5)
                DailyChecklistCard(title = "Big Goals", lines = 4, tone = DailyCardTone.Secondary)
                DailyChecklistCard(title = "What Can Wait", lines = 4, tone = DailyCardTone.Secondary)
                SingleLinePrompt(title = "You've Got This!")
            }
        }
    }
}

@Composable
private fun ReflectionPromptCard(
    title: String,
    prompts: List<String>,
) {
    DailyCardFrame(
        title = title,
        titleColor = MaterialTheme.plan92Palette.primaryAccent,
    ) {
        prompts.forEach { prompt ->
            SingleLinePrompt(title = prompt)
        }
    }
}

@Composable
private fun RoutineTimeCard(
    title: String,
    accent: Color,
) {
    Surface(
        shape = DailyCardShape,
        color = accent.copy(alpha = 0.14f),
        border = androidx.compose.foundation.BorderStroke(1.dp, accent.copy(alpha = 0.3f)),
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = PlannerSheetMetrics.SectionPaddingHorizontal,
                vertical = PlannerSheetMetrics.SectionPaddingVertical,
            ),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge.copy(fontSize = 11.sp),
                color = MaterialTheme.plan92Palette.titleColor,
                fontWeight = FontWeight.Bold,
            )
            DailyScheduleCard(
                title = "Schedule",
                times = listOf("6:00 AM", "7:00 AM", "8:00 AM", "9:00 AM", "10:00 AM"),
            )
        }
    }
}

@Composable
private fun MoodAndReminderCard(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = DailyCardShape,
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = DailyCardBorder,
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = PlannerSheetMetrics.SectionPaddingHorizontal,
                vertical = PlannerSheetMetrics.SectionPaddingVertical,
            ),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = "Self-Care Reminder",
                style = MaterialTheme.typography.labelLarge.copy(fontSize = 11.sp),
                color = MaterialTheme.plan92Palette.titleColor,
                fontWeight = FontWeight.Bold,
            )
            FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                listOf("Sleep", "Water", "Food", "Walk", "Breathe", "Break").forEach { item ->
                    SelectionBubble(item)
                }
            }
        }
    }
}

@Composable
private fun FocusSelectorCard(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = DailyCardShape,
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = DailyCardBorder,
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = PlannerSheetMetrics.SectionPaddingHorizontal,
                vertical = PlannerSheetMetrics.SectionPaddingVertical,
            ),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = "Exercise Focus",
                style = MaterialTheme.typography.labelLarge.copy(fontSize = 11.sp),
                color = MaterialTheme.plan92Palette.titleColor,
                fontWeight = FontWeight.Bold,
            )
            FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                listOf("Cardio", "Stretch", "Arms", "Legs", "Core", "Back", "Rest").forEach { focus ->
                    SelectionBubble(label = focus)
                }
            }
        }
    }
}

@Composable
private fun MetricFieldGroup(
    title: String,
    labels: List<String>,
    modifier: Modifier = Modifier,
) {
    DailyCardFrame(
        title = title,
        modifier = modifier,
    ) {
        labels.forEach { label ->
            SingleLinePrompt(title = label)
        }
    }
}

@Composable
private fun MiniTrackerCard(
    title: String,
    entries: Int,
    modifier: Modifier = Modifier,
) {
    DailyCardFrame(
        title = title,
        modifier = modifier,
        tone = DailyCardTone.Secondary,
    ) {
        WaterTrackerBar(title = title, count = entries, tone = DailyCardTone.Secondary)
    }
}

@Composable
private fun ChoiceGroupCard(
    title: String,
    options: List<String>,
) {
    DailyCardFrame(
        title = title,
        tone = DailyCardTone.Secondary,
    ) {
        FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            options.forEach { option -> SelectionBubble(label = option) }
        }
    }
}

@Composable
private fun TrackerTableCard(
    title: String,
    columns: List<String>,
) {
    var rowIds by rememberSaveable(title) { mutableStateOf(List(5) { it }) }
    var nextRowId by rememberSaveable("${title}_next") { mutableStateOf(5) }

    DailyCardFrame(title = title) {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            columns.forEach { column ->
                Text(
                    text = column,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                    color = MaterialTheme.plan92Palette.bodyColor,
                    textAlign = TextAlign.Center,
                )
            }
        }
        rowIds.forEach { rowId ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                columns.forEachIndexed { index, _ ->
                    BasicLineField(
                        key = "${title}_${rowId}_$index",
                        modifier = Modifier.weight(1f),
                    )
                }
                if (rowIds.size > 1) {
                    PlannerInlineRemoveAction(onClick = { rowIds = rowIds - rowId })
                }
            }
        }
        PlannerSectionAddAction(
            label = "Add row",
            onClick = {
                rowIds = rowIds + nextRowId
                nextRowId += 1
            },
            modifier = Modifier.align(Alignment.End),
        )
    }
}

@Composable
private fun QuadrantChecklist(
    title: String,
    lines: Int,
    modifier: Modifier = Modifier,
) {
    var rowIds by rememberSaveable(title) { mutableStateOf(List(lines) { it }) }
    var nextRowId by rememberSaveable("${title}_next") { mutableStateOf(lines) }

    DailyCardFrame(
        title = title,
        modifier = modifier,
        titleColor = MaterialTheme.plan92Palette.primaryAccent,
    ) {
        rowIds.forEach { rowId ->
            ChecklistRow(
                key = "${title}_$rowId",
                onRemove = if (rowIds.size > 1) {
                    { rowIds = rowIds - rowId }
                } else {
                    null
                },
            )
        }
        PlannerSectionAddAction(
            label = "Add row",
            onClick = {
                rowIds = rowIds + nextRowId
                nextRowId += 1
            },
            modifier = Modifier.align(Alignment.End),
        )
    }
}

@Composable
private fun NumberedPriorityCard(
    title: String,
    count: Int,
    modifier: Modifier = Modifier,
    tone: DailyCardTone = DailyCardTone.Primary,
) {
    var rowIds by rememberSaveable(title) { mutableStateOf(List(count) { it }) }
    var nextRowId by rememberSaveable("${title}_next") { mutableStateOf(count) }

    DailyCardFrame(
        title = title,
        modifier = modifier,
        tone = tone,
    ) {
        rowIds.forEachIndexed { index, rowId ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Box(
                    modifier = Modifier
                        .width(18.dp)
                        .height(18.dp)
                        .background(MaterialTheme.plan92Palette.secondaryAccent.copy(alpha = 0.16f), CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "${index + 1}",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                        color = MaterialTheme.plan92Palette.secondaryAccent,
                        fontWeight = FontWeight.Bold,
                    )
                }
                BasicLineField(
                    key = "${title}_$rowId",
                    modifier = Modifier.weight(1f),
                )
                if (rowIds.size > 1) {
                    PlannerInlineRemoveAction(onClick = { rowIds = rowIds - rowId })
                }
            }
        }
        PlannerSectionAddAction(
            label = "Add priority",
            onClick = {
                rowIds = rowIds + nextRowId
                nextRowId += 1
            },
            modifier = Modifier.align(Alignment.End),
        )
    }
}

@Composable
private fun PomodoroTrackerCard(
    title: String,
    modifier: Modifier = Modifier,
    tone: DailyCardTone = DailyCardTone.Primary,
) {
    DailyCardFrame(
        title = title,
        modifier = modifier,
        tone = tone,
    ) {
        SingleLinePrompt(title = "Target")
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            repeat(5) { index ->
                SelectionBubble(label = "${index + 1}")
            }
        }
    }
}

@Composable
private fun DailyChecklistCard(
    title: String,
    lines: Int,
    modifier: Modifier = Modifier,
    tone: DailyCardTone = DailyCardTone.Primary,
) {
    var rowIds by rememberSaveable(title) { mutableStateOf(List(lines) { it }) }
    var nextRowId by rememberSaveable("${title}_next") { mutableStateOf(lines) }

    DailyCardFrame(
        title = title,
        modifier = modifier,
        tone = tone,
    ) {
        rowIds.forEach { rowId ->
            ChecklistRow(
                key = "${title}_$rowId",
                onRemove = if (rowIds.size > 1) {
                    { rowIds = rowIds - rowId }
                } else {
                    null
                },
            )
        }
        PlannerSectionAddAction(
            label = "Add task",
            onClick = {
                rowIds = rowIds + nextRowId
                nextRowId += 1
            },
            modifier = Modifier.align(Alignment.End),
        )
    }
}

@Composable
private fun DailyNotesCard(
    title: String,
    lines: Int,
    modifier: Modifier = Modifier,
    tone: DailyCardTone = DailyCardTone.Primary,
) {
    var rowIds by rememberSaveable(title) { mutableStateOf(List(lines) { it }) }
    var nextRowId by rememberSaveable("${title}_next") { mutableStateOf(lines) }

    DailyCardFrame(
        title = title,
        modifier = modifier,
        tone = tone,
    ) {
        rowIds.forEach { rowId ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BasicLineField(
                    key = "${title}_$rowId",
                    modifier = Modifier.weight(1f),
                )
                if (rowIds.size > 1) {
                    PlannerInlineRemoveAction(onClick = { rowIds = rowIds - rowId })
                }
            }
        }
        PlannerSectionAddAction(
            label = "Add line",
            onClick = {
                rowIds = rowIds + nextRowId
                nextRowId += 1
            },
            modifier = Modifier.align(Alignment.End),
        )
    }
}

@Composable
private fun DailyScheduleCard(
    title: String,
    times: List<String>,
    modifier: Modifier = Modifier,
    tone: DailyCardTone = DailyCardTone.Primary,
) {
    DailyCardFrame(
        title = title,
        modifier = modifier,
        tone = tone,
    ) {
        times.forEachIndexed { index, time ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(
                    text = time,
                    modifier = Modifier.width(PlannerSheetMetrics.TimeLabelWidth),
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                    color = MaterialTheme.plan92Palette.bodyColor,
                )
                BasicLineField(
                    key = "${title}_$index",
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun WaterTrackerBar(
    title: String,
    modifier: Modifier = Modifier,
    count: Int = 8,
    tone: DailyCardTone = DailyCardTone.Primary,
) {
    DailyCardFrame(
        title = title,
        modifier = modifier,
        tone = tone,
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
            repeat(count) { index ->
                SelectionBubble(label = "${index + 1}")
            }
        }
    }
}

@Composable
private fun MoodTrackerBar(
    title: String,
    modifier: Modifier = Modifier,
    count: Int = 6,
    tone: DailyCardTone = DailyCardTone.Primary,
) {
    val moods = listOf("Low", "Sad", "Okay", "Good", "Happy", "Great").take(count)
    DailyCardFrame(
        title = title,
        modifier = modifier,
        tone = tone,
    ) {
        FlowRow(horizontalArrangement = Arrangement.spacedBy(3.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            moods.forEach { mood -> SelectionBubble(label = mood) }
        }
    }
}

@Composable
private fun MiniTextCard(
    title: String,
    modifier: Modifier = Modifier,
    tone: DailyCardTone = DailyCardTone.Primary,
) {
    DailyCardFrame(
        title = title,
        modifier = modifier,
        tone = tone,
    ) {
        BasicLineField(
            key = title,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun HeaderLineRow(
    leftLabel: String,
    rightLabel: String,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.RowGap)) {
        if (leftLabel.isNotBlank()) {
            SingleLinePrompt(title = leftLabel, modifier = Modifier.weight(0.84f))
        }
        if (rightLabel.isNotBlank()) {
            SingleLinePrompt(title = rightLabel, modifier = Modifier.weight(1.16f))
        }
    }
}

@Composable
private fun SingleHeaderField(
    label: String,
) {
    SingleLinePrompt(title = label, modifier = Modifier.fillMaxWidth())
}

@Composable
private fun SingleLinePrompt(
    title: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall.copy(fontSize = PlannerSheetMetrics.FieldLabelSize),
            color = MaterialTheme.plan92Palette.titleColor,
            fontWeight = FontWeight.SemiBold,
        )
        BasicLineField(
            key = title,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun ChecklistRow(
    key: String,
    onRemove: (() -> Unit)? = null,
) {
    var checked by rememberSaveable("${key}_check") { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Box(
            modifier = Modifier
                .width(PlannerSheetMetrics.CheckboxSize)
                .height(PlannerSheetMetrics.CheckboxSize)
                .clickable { checked = !checked }
                .border(
                    width = 1.dp,
                    color = if (checked) MaterialTheme.plan92Palette.primaryAccent else MaterialTheme.plan92Palette.lineColor,
                    shape = RoundedCornerShape(3.dp),
                )
                .background(
                    if (checked) MaterialTheme.plan92Palette.primaryAccent.copy(alpha = 0.15f) else Color.Transparent,
                    RoundedCornerShape(3.dp),
                ),
            contentAlignment = Alignment.Center,
        ) {
            if (checked) {
                Text(
                    text = "✓",
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                    color = MaterialTheme.plan92Palette.primaryAccent,
                )
            }
        }
        BasicLineField(
            key = key,
            modifier = Modifier.weight(1f),
        )
        if (onRemove != null) {
            PlannerInlineRemoveAction(onClick = onRemove)
        }
    }
}

@Composable
private fun SelectionBubble(
    label: String,
) {
    var selected by rememberSaveable(label) { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .width(if (label.length <= 3) PlannerSheetMetrics.BubbleNarrowWidth else PlannerSheetMetrics.BubbleWideWidth)
            .height(PlannerSheetMetrics.BubbleHeight)
            .clickable { selected = !selected }
            .border(
                1.dp,
                if (selected) MaterialTheme.plan92Palette.primaryAccent else MaterialTheme.plan92Palette.lineColor,
                CircleShape,
            )
            .background(
                if (selected) MaterialTheme.plan92Palette.primaryAccent.copy(alpha = 0.18f) else Color.Transparent,
                CircleShape,
            )
            .padding(4.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = if (label.length <= 3) label else label,
            style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, lineHeight = 10.sp),
            color = MaterialTheme.plan92Palette.bodyColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
private fun BasicLineField(
    key: String,
    modifier: Modifier = Modifier,
) {
    var text by rememberSaveable(key) { mutableStateOf("") }
    BasicTextField(
        value = text,
        onValueChange = { text = it },
        textStyle = TextStyle(
            color = MaterialTheme.plan92Palette.titleColor,
            fontSize = PlannerSheetMetrics.FieldTextSize,
            lineHeight = PlannerSheetMetrics.FieldLineHeight,
        ),
        modifier = modifier,
        decorationBox = { innerTextField ->
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
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
