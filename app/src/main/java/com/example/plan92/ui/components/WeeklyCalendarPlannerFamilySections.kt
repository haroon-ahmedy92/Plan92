package com.example.plan92.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plan92.ui.theme.plan92Palette

private val weeklyDays = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
private val yearlyMonths = listOf(
    "Jan", "Feb", "Mar", "Apr", "May", "Jun",
    "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
)

@Composable
fun WeeklyPlannerBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        CompactHeaderFields("Week Of", "Big Focus")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            MiniInfoCard(title = "Appointments", modifier = Modifier.weight(1f)) {
                repeat(4) { index ->
                    CompactEntryField(key = "weekly_planner_appointments_$index", placeholder = "Appointment ${index + 1}")
                }
            }
            MiniInfoCard(title = "This Week's Wins", modifier = Modifier.weight(1f)) {
                repeat(3) { index ->
                    CompactEntryField(key = "weekly_planner_wins_$index", placeholder = "Win ${index + 1}")
                }
            }
        }
        HorizontalWeekScroller {
            weeklyDays.forEach { day ->
                WeeklyDayCard(
                    title = day,
                    keyPrefix = "weekly_planner_$day",
                    modifier = Modifier.width(152.dp),
                    sections = listOf("Plans", "Tasks"),
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            WeekHabitMiniCard(
                title = "Mini Habits",
                habits = listOf("Read", "Move", "Stretch"),
                modifier = Modifier.weight(1f),
            )
            MiniInfoCard(title = "Weekly Notes", modifier = Modifier.weight(1f)) {
                CompactEntryField(
                    key = "weekly_planner_notes",
                    placeholder = "Notes, reminders, or reflections",
                    minLines = 5,
                )
            }
        }
    }
}

@Composable
fun WeeklyScheduleBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    val times = listOf("6 AM", "8 AM", "10 AM", "12 PM", "2 PM", "4 PM", "6 PM", "8 PM")
    SectionContainer(title = title, modifier = modifier) {
        CompactHeaderFields("Week Of", "Main Focus")
        ScheduleMatrixCard(
            keyPrefix = "weekly_schedule",
            columns = weeklyDays,
            rows = times,
        )
        MiniInfoCard(title = "Notes Strip") {
            CompactEntryField(
                key = "weekly_schedule_notes",
                placeholder = "Important reminders, deadlines, and follow-ups",
                minLines = 4,
            )
        }
    }
}

@Composable
fun WeeklyDashboardBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        CompactHeaderFields("Week Of", "Weekly Intention")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            MiniInfoCard(title = "Weekly Highlights", modifier = Modifier.weight(1f)) {
                repeat(3) { index ->
                    CompactEntryField(key = "weekly_dashboard_highlight_$index", placeholder = "Highlight ${index + 1}")
                }
            }
            MiniInfoCard(title = "Top Goals", modifier = Modifier.weight(1f)) {
                repeat(3) { index ->
                    CompactEntryField(key = "weekly_dashboard_goal_$index", placeholder = "Goal ${index + 1}")
                }
            }
        }
        HorizontalWeekScroller {
            weeklyDays.forEach { day ->
                WeeklyDayCard(
                    title = day,
                    keyPrefix = "weekly_dashboard_$day",
                    modifier = Modifier.width(150.dp),
                    sections = listOf("Goal", "Priority"),
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            WeekMoodCard(modifier = Modifier.weight(1f))
            MiniInfoCard(title = "Notes", modifier = Modifier.weight(1f)) {
                CompactEntryField(
                    key = "weekly_dashboard_notes",
                    placeholder = "Wins, blockers, and next week ideas",
                    minLines = 5,
                )
            }
        }
    }
}

@Composable
fun WeeklyHabitsBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        CompactHeaderFields("Week Of", "Habit Goal")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            MiniInfoCard(title = "Goals", modifier = Modifier.weight(1f)) {
                repeat(3) { index ->
                    CompactEntryField(key = "weekly_habits_goal_$index", placeholder = "Goal ${index + 1}")
                }
            }
            MiniInfoCard(title = "Notes & Reminders", modifier = Modifier.weight(1f)) {
                CompactEntryField(
                    key = "weekly_habits_notes",
                    placeholder = "What will help me stay consistent?",
                    minLines = 5,
                )
            }
        }
        HabitMatrixCard(
            keyPrefix = "weekly_habits",
            habits = listOf("Workout", "Hydrate", "Read", "Journal", "Sleep Early"),
        )
        MiniInfoCard(title = "Weekly Self-Assessment") {
            CompactEntryField(
                key = "weekly_habits_assessment",
                placeholder = "How did the week go? What should improve next week?",
                minLines = 4,
            )
        }
    }
}

@Composable
fun WeeklyGoalsBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        CompactHeaderFields("Week Of", "Weekly Theme")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            MiniInfoCard(title = "Top Priorities", modifier = Modifier.weight(1f)) {
                repeat(3) { index ->
                    CompactEntryField(key = "weekly_goals_priority_$index", placeholder = "Priority ${index + 1}")
                }
            }
            MiniInfoCard(title = "Main Goals", modifier = Modifier.weight(1f)) {
                repeat(3) { index ->
                    CompactEntryField(key = "weekly_goals_goal_$index", placeholder = "Goal ${index + 1}")
                }
            }
        }
        HorizontalWeekScroller {
            weeklyDays.forEach { day ->
                WeeklyDayCard(
                    title = day,
                    keyPrefix = "weekly_goals_$day",
                    modifier = Modifier.width(150.dp),
                    sections = listOf("Appointments", "Notes"),
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            MiniInfoCard(title = "Weekly Notes", modifier = Modifier.weight(1f)) {
                CompactEntryField(key = "weekly_goals_notes", placeholder = "Keep track of ideas and follow-ups", minLines = 4)
            }
            MiniInfoCard(title = "Next Week Preview", modifier = Modifier.weight(1f)) {
                CompactEntryField(key = "weekly_goals_next_week", placeholder = "What should carry into next week?", minLines = 4)
            }
        }
    }
}

@Composable
fun WeeklyMealBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        CompactHeaderFields("Week Of", "Meal Focus")
        MealMatrixCard(
            keyPrefix = "weekly_meal",
            mealTypes = listOf("Breakfast", "Lunch", "Dinner", "Snack"),
        )
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            MiniInfoCard(title = "Grocery List", modifier = Modifier.weight(1f)) {
                repeat(8) { index ->
                    CompactEntryField(key = "weekly_meal_grocery_$index", placeholder = "Item ${index + 1}")
                }
            }
            MiniInfoCard(title = "Prep Notes", modifier = Modifier.weight(1f)) {
                CompactEntryField(
                    key = "weekly_meal_prep",
                    placeholder = "Prep, portions, leftovers, and reminders",
                    minLines = 8,
                )
            }
        }
    }
}

@Composable
fun WeeklyProjectsBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        CompactHeaderFields("Week Of", "Main Project")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            MiniInfoCard(title = "Weekly Goals", modifier = Modifier.weight(1f)) {
                repeat(3) { index ->
                    CompactEntryField(key = "weekly_projects_goal_$index", placeholder = "Goal ${index + 1}")
                }
            }
            MiniInfoCard(title = "Priority Projects", modifier = Modifier.weight(1f)) {
                repeat(3) { index ->
                    CompactEntryField(key = "weekly_projects_priority_$index", placeholder = "Project ${index + 1}")
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            MiniInfoCard(title = "Activities", modifier = Modifier.weight(1f)) {
                CompactEntryField(key = "weekly_projects_activities", placeholder = "What work moved forward this week?", minLines = 6)
            }
            MiniInfoCard(title = "Challenges", modifier = Modifier.weight(1f)) {
                CompactEntryField(key = "weekly_projects_challenges", placeholder = "Risks, blockers, or support needed", minLines = 6)
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            MiniInfoCard(title = "Highlights", modifier = Modifier.weight(1f)) {
                CompactEntryField(key = "weekly_projects_highlights", placeholder = "Wins and learning moments", minLines = 5)
            }
            MiniInfoCard(title = "Next Steps", modifier = Modifier.weight(1f)) {
                CompactEntryField(key = "weekly_projects_next_steps", placeholder = "What happens next?", minLines = 5)
            }
        }
    }
}

@Composable
fun WeeklyBulletBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        CompactHeaderFields("Week Of", "Theme")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Column(
                modifier = Modifier.weight(1.4f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                HorizontalWeekScroller {
                    listOf("Mon", "Tue", "Wed").forEach { day ->
                        WeeklyDayCard(
                            title = day,
                            keyPrefix = "weekly_bullet_top_$day",
                            modifier = Modifier.width(145.dp),
                            sections = listOf("Tasks"),
                        )
                    }
                }
                HorizontalWeekScroller {
                    listOf("Thu", "Fri", "Sat", "Sun").forEach { day ->
                        WeeklyDayCard(
                            title = day,
                            keyPrefix = "weekly_bullet_bottom_$day",
                            modifier = Modifier.width(145.dp),
                            sections = listOf("Tasks"),
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.weight(0.9f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                MiniInfoCard(title = "Gratitude") {
                    CompactEntryField(key = "weekly_bullet_gratitude", placeholder = "What felt good this week?", minLines = 3)
                }
                MiniInfoCard(title = "Favorites") {
                    CompactEntryField(key = "weekly_bullet_favorites", placeholder = "Best moments or memories", minLines = 3)
                }
                MiniInfoCard(title = "Notes") {
                    CompactEntryField(key = "weekly_bullet_notes", placeholder = "Loose thoughts", minLines = 3)
                }
                MiniInfoCard(title = "Next Week") {
                    CompactEntryField(key = "weekly_bullet_next", placeholder = "Carry-over tasks", minLines = 3)
                }
            }
        }
    }
}

@Composable
fun WorkLifeBalanceBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    val workDays = listOf("Mon", "Tue", "Wed", "Thu", "Fri")
    SectionContainer(title = title, modifier = modifier) {
        CompactHeaderFields("Week Of", "Balance Goal")
        Surface(
            shape = RoundedCornerShape(18.dp),
            color = MaterialTheme.plan92Palette.fieldSurface,
            border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    HeaderCell(text = "Day", width = 64.dp)
                    HeaderCell(text = "Work", width = 132.dp)
                    HeaderCell(text = "Personal", width = 132.dp)
                }
                workDays.forEach { day ->
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        HeaderCell(text = day, width = 64.dp)
                        CompactEntryField(
                            key = "work_life_${day}_work",
                            placeholder = "Work",
                            modifier = Modifier.width(132.dp),
                            minLines = 2,
                            fillWidth = false,
                        )
                        CompactEntryField(
                            key = "work_life_${day}_personal",
                            placeholder = "Personal",
                            modifier = Modifier.width(132.dp),
                            minLines = 2,
                            fillWidth = false,
                        )
                    }
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            MiniInfoCard(title = "Weekly Wins", modifier = Modifier.weight(1f)) {
                CompactEntryField(key = "work_life_wins", placeholder = "What felt balanced or successful?", minLines = 4)
            }
            MiniInfoCard(title = "Reset Notes", modifier = Modifier.weight(1f)) {
                CompactEntryField(key = "work_life_notes", placeholder = "Where do I need more recovery?", minLines = 4)
            }
        }
    }
}

@Composable
fun MonthlyPlannerBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        CompactHeaderFields("Month / Year", "Monthly Focus")
        MonthCalendarCard(
            keyPrefix = "monthly_planner",
            showDateCircle = false,
        )
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            MiniInfoCard(title = "Goals", modifier = Modifier.weight(1f)) {
                repeat(4) { index ->
                    CompactEntryField(key = "monthly_planner_goal_$index", placeholder = "Goal ${index + 1}")
                }
            }
            MiniInfoCard(title = "Important Dates", modifier = Modifier.weight(1f)) {
                repeat(4) { index ->
                    CompactEntryField(key = "monthly_planner_date_$index", placeholder = "Date ${index + 1}")
                }
            }
        }
    }
}

@Composable
fun MonthlyAppointmentBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        CompactHeaderFields("Month / Year", "Main Highlight")
        MonthCalendarCard(
            keyPrefix = "monthly_appointment",
            showDateCircle = true,
        )
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            MiniInfoCard(title = "Appointments", modifier = Modifier.weight(1f)) {
                repeat(5) { index ->
                    CompactEntryField(key = "monthly_appointment_item_$index", placeholder = "Appointment ${index + 1}")
                }
            }
            MiniInfoCard(title = "Follow Ups", modifier = Modifier.weight(1f)) {
                CompactEntryField(key = "monthly_appointment_notes", placeholder = "Calls, follow-ups, and deadlines", minLines = 7)
            }
        }
    }
}

@Composable
fun MonthlyBudgetBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    val categories = listOf("Income", "Housing", "Food", "Transport", "Utilities", "Savings", "Fun")
    SectionContainer(title = title, modifier = modifier) {
        CompactHeaderFields("Month", "Savings Goal")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SummaryFieldCard("Income", "monthly_budget_income", modifier = Modifier.weight(1f))
            SummaryFieldCard("Bills", "monthly_budget_bills", modifier = Modifier.weight(1f))
            SummaryFieldCard("Savings", "monthly_budget_savings", modifier = Modifier.weight(1f))
        }
        BudgetTableCard(
            keyPrefix = "monthly_budget",
            rows = categories,
            columns = listOf("Planned", "Actual", "Diff"),
        )
        MiniInfoCard(title = "Budget Notes") {
            CompactEntryField(key = "monthly_budget_notes", placeholder = "Spending observations and next-month changes", minLines = 5)
        }
    }
}

@Composable
fun MonthlyWeightLossBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        CompactHeaderFields("Year", "Wellness Goal")
        TwoColumnMonthCards(
            months = yearlyMonths,
            keyPrefix = "weight_loss",
            fields = listOf("Goal", "Actual", "Notes"),
        )
    }
}

@Composable
fun YearlyPlannerBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        CompactHeaderFields("Year", "Theme")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            MiniInfoCard(title = "Top Goals", modifier = Modifier.weight(1f)) {
                repeat(3) { index ->
                    CompactEntryField(key = "yearly_goal_$index", placeholder = "Goal ${index + 1}")
                }
            }
            MiniInfoCard(title = "Yearly Highlights", modifier = Modifier.weight(1f)) {
                CompactEntryField(key = "yearly_highlights", placeholder = "What matters most this year?", minLines = 5)
            }
        }
        TwoColumnMonthCards(
            months = yearlyMonths,
            keyPrefix = "yearly_planner",
            fields = listOf("Focus", "Key Date"),
        )
    }
}

@Composable
fun YearlyCalendarBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        CompactHeaderFields("Year", "Highlight")
        TwoColumnMonthCards(
            months = yearlyMonths,
            keyPrefix = "yearly_calendar",
            fields = listOf("Events", "Notes"),
        )
    }
}

@Composable
fun SeasonalYearlyBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    val seasons = listOf("Spring", "Summer", "Autumn", "Winter")
    SectionContainer(title = title, modifier = modifier) {
        CompactHeaderFields("Year", "Seasonal Vision")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            seasons.take(2).forEach { season ->
                MiniInfoCard(title = season, modifier = Modifier.weight(1f)) {
                    CompactEntryField(key = "seasonal_$season", placeholder = "$season focus", minLines = 4)
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            seasons.drop(2).forEach { season ->
                MiniInfoCard(title = season, modifier = Modifier.weight(1f)) {
                    CompactEntryField(key = "seasonal_$season", placeholder = "$season focus", minLines = 4)
                }
            }
        }
        TwoColumnMonthCards(
            months = yearlyMonths,
            keyPrefix = "seasonal_yearly",
            fields = listOf("Priority", "Note"),
        )
    }
}

@Composable
private fun CompactHeaderFields(
    leftLabel: String,
    rightLabel: String,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        CompactEntryField(
            key = "header_$leftLabel",
            placeholder = leftLabel,
            modifier = Modifier.weight(1f),
        )
        CompactEntryField(
            key = "header_$rightLabel",
            placeholder = rightLabel,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun HorizontalWeekScroller(
    content: @Composable () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        content()
    }
}

@Composable
private fun WeeklyDayCard(
    title: String,
    keyPrefix: String,
    sections: List<String>,
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
            sections.forEachIndexed { index, section ->
                CompactEntryField(
                    key = "${keyPrefix}_$index",
                    placeholder = section,
                    minLines = 3,
                )
            }
        }
    }
}

@Composable
private fun MiniInfoCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
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
                color = MaterialTheme.plan92Palette.primaryAccent,
                fontWeight = FontWeight.Bold,
            )
            content()
        }
    }
}

@Composable
private fun WeekHabitMiniCard(
    title: String,
    habits: List<String>,
    modifier: Modifier = Modifier,
) {
    MiniInfoCard(title = title, modifier = modifier) {
        habits.forEach { habit ->
            var checked by rememberSaveable("mini_habit_$title$habit") { mutableStateOf(false) }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it },
                )
                Text(
                    text = habit,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.plan92Palette.bodyColor,
                )
            }
        }
    }
}

@Composable
private fun WeekMoodCard(
    modifier: Modifier = Modifier,
) {
    val moods = listOf("Low", "Okay", "Good", "Great")
    MiniInfoCard(title = "Weekly Mood", modifier = modifier) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            moods.forEach { mood ->
                SelectablePill(
                    key = "week_mood_$mood",
                    label = mood,
                    accent = MaterialTheme.plan92Palette.secondaryAccent,
                )
            }
        }
        CompactEntryField(
            key = "week_mood_notes",
            placeholder = "What shaped the week's energy?",
            minLines = 3,
        )
    }
}

@Composable
private fun SelectablePill(
    key: String,
    label: String,
    accent: Color,
) {
    var selected by rememberSaveable(key) { mutableStateOf(false) }
    Surface(
        onClick = { selected = !selected },
        shape = RoundedCornerShape(999.dp),
        color = if (selected) accent.copy(alpha = 0.18f) else MaterialTheme.plan92Palette.pageSurface,
        border = BorderStroke(1.dp, if (selected) accent else MaterialTheme.plan92Palette.lineColor),
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelLarge,
            color = if (selected) accent else MaterialTheme.plan92Palette.bodyColor,
        )
    }
}

@Composable
private fun ScheduleMatrixCard(
    keyPrefix: String,
    columns: List<String>,
    rows: List<String>,
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        Column(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                HeaderCell(text = "Time", width = 68.dp)
                columns.forEach { column ->
                    HeaderCell(text = column, width = 96.dp)
                }
            }
            rows.forEachIndexed { rowIndex, row ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    HeaderCell(text = row, width = 72.dp)
                    columns.forEachIndexed { columnIndex, _ ->
                        CompactEntryField(
                            key = "${keyPrefix}_${rowIndex}_$columnIndex",
                            placeholder = "",
                            modifier = Modifier.width(96.dp),
                            minLines = 2,
                            fillWidth = false,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HabitMatrixCard(
    keyPrefix: String,
    habits: List<String>,
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        Column(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                HeaderCell(text = "Habit", width = 104.dp)
                weeklyDays.forEach { day ->
                    HeaderCell(text = day, width = 46.dp)
                }
            }
            habits.forEachIndexed { habitIndex, habit ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    HeaderCell(text = habit, width = 104.dp)
                    weeklyDays.forEachIndexed { dayIndex, _ ->
                        ToggleCell(key = "${keyPrefix}_${habitIndex}_$dayIndex")
                    }
                }
            }
        }
    }
}

@Composable
private fun MealMatrixCard(
    keyPrefix: String,
    mealTypes: List<String>,
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        Column(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                HeaderCell(text = "Meal", width = 82.dp)
                weeklyDays.forEach { day ->
                    HeaderCell(text = day, width = 104.dp)
                }
            }
            mealTypes.forEachIndexed { rowIndex, mealType ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    HeaderCell(text = mealType, width = 82.dp)
                    weeklyDays.forEachIndexed { dayIndex, _ ->
                        CompactEntryField(
                            key = "${keyPrefix}_${rowIndex}_$dayIndex",
                            placeholder = "",
                            modifier = Modifier.width(104.dp),
                            minLines = 2,
                            fillWidth = false,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MonthCalendarCard(
    keyPrefix: String,
    showDateCircle: Boolean,
) {
    val headers = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    val dates = (1..31).map { it.toString() }
    val paddedDates = List(4) { "" } + dates + List(42 - dates.size - 4) { "" }

    Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        Column(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                headers.forEach { header ->
                    HeaderCell(text = header, width = 84.dp)
                }
            }
            paddedDates.chunked(7).forEachIndexed { weekIndex, week ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    week.forEachIndexed { dayIndex, date ->
                        CalendarCell(
                            key = "${keyPrefix}_${weekIndex}_$dayIndex",
                            dateLabel = date,
                            showDateCircle = showDateCircle,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BudgetTableCard(
    keyPrefix: String,
    rows: List<String>,
    columns: List<String>,
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        Column(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                HeaderCell(text = "Category", width = 112.dp)
                columns.forEach { column ->
                    HeaderCell(text = column, width = 90.dp)
                }
            }
            rows.forEachIndexed { rowIndex, row ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    HeaderCell(text = row, width = 112.dp)
                    columns.forEachIndexed { columnIndex, _ ->
                        CompactEntryField(
                            key = "${keyPrefix}_${rowIndex}_$columnIndex",
                            placeholder = "",
                            modifier = Modifier.width(90.dp),
                            fillWidth = false,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SummaryFieldCard(
    title: String,
    key: String,
    modifier: Modifier = Modifier,
) {
    MiniInfoCard(title = title, modifier = modifier) {
        CompactEntryField(
            key = key,
            placeholder = title,
        )
    }
}

@Composable
private fun TwoColumnMonthCards(
    months: List<String>,
    keyPrefix: String,
    fields: List<String>,
) {
    months.chunked(2).forEachIndexed { rowIndex, rowMonths ->
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            rowMonths.forEachIndexed { columnIndex, month ->
                MiniInfoCard(title = month, modifier = Modifier.weight(1f)) {
                    fields.forEachIndexed { fieldIndex, field ->
                        CompactEntryField(
                            key = "${keyPrefix}_${rowIndex}_${columnIndex}_$fieldIndex",
                            placeholder = field,
                            minLines = if (field == "Notes") 3 else 1,
                        )
                    }
                }
            }
            if (rowMonths.size == 1) {
                Box(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun HeaderCell(
    text: String,
    width: androidx.compose.ui.unit.Dp,
) {
    Surface(
        modifier = Modifier.width(width),
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.plan92Palette.primaryAccent.copy(alpha = 0.12f),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.plan92Palette.primaryAccent,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun ToggleCell(
    key: String,
) {
    var checked by rememberSaveable(key) { mutableStateOf(false) }
    Surface(
        modifier = Modifier.width(46.dp),
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.plan92Palette.pageSurface,
        border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it },
            )
        }
    }
}

@Composable
private fun CalendarCell(
    key: String,
    dateLabel: String,
    showDateCircle: Boolean,
) {
    Surface(
        modifier = Modifier.width(84.dp),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.plan92Palette.pageSurface,
        border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        Column(
            modifier = Modifier.padding(6.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            if (showDateCircle) {
                Box(
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .background(
                            color = if (dateLabel.isBlank()) Color.Transparent else MaterialTheme.plan92Palette.secondaryAccent.copy(alpha = 0.16f),
                            shape = CircleShape,
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = dateLabel,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.plan92Palette.secondaryAccent,
                        fontWeight = FontWeight.Bold,
                    )
                }
            } else {
                Text(
                    text = dateLabel,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.plan92Palette.primaryAccent,
                    fontWeight = FontWeight.Bold,
                )
            }
            CompactEntryField(
                key = key,
                placeholder = if (dateLabel.isBlank()) "" else "Write",
                minLines = 3,
            )
        }
    }
}

@Composable
private fun CompactEntryField(
    key: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    minLines: Int = 1,
    fillWidth: Boolean = true,
) {
    var value by rememberSaveable(key) { mutableStateOf("") }
    Surface(
        modifier = if (fillWidth) modifier.fillMaxWidth() else modifier,
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
                .padding(horizontal = 8.dp, vertical = 7.dp),
            minLines = minLines,
            decorationBox = { innerTextField ->
                if (value.isBlank() && placeholder.isNotBlank()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.plan92Palette.bodyColor.copy(alpha = 0.7f),
                    )
                }
                innerTextField()
            },
        )
    }
}
