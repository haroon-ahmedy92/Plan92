package com.example.plan92.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plan92.data.mock.MockPlannerRepository
import com.example.plan92.data.mock.OwnedPlanner
import com.example.plan92.data.mock.PlannerLayoutKind
import com.example.plan92.data.mock.PlannerTemplate
import com.example.plan92.ui.theme.ApricotGlow
import com.example.plan92.ui.theme.AquaAccent
import com.example.plan92.ui.theme.CoralAccent
import com.example.plan92.ui.theme.DividerSoft
import com.example.plan92.ui.theme.InkBlack
import com.example.plan92.ui.theme.MintAccent
import com.example.plan92.ui.theme.Paper
import com.example.plan92.ui.theme.PaperTint
import com.example.plan92.ui.theme.Plan92Theme
import com.example.plan92.ui.theme.RoseAccent
import com.example.plan92.ui.theme.ShellWhite

@Composable
fun OwnedPlannerCard(
    planner: OwnedPlanner,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(planner.accent.copy(alpha = 0.95f), InkBlack.copy(alpha = 0.9f)),
                    ),
                )
                .padding(18.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Surface(
                    color = Paper.copy(alpha = 0.92f),
                    shape = RoundedCornerShape(18.dp),
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text(
                            text = planner.title,
                            color = InkBlack,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            text = planner.tagline,
                            color = InkBlack.copy(alpha = 0.66f),
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(planner.secondaryAccent.copy(alpha = 0.45f), planner.accent, InkBlack),
                            ),
                        ),
                ) {
                    repeat(3) { index ->
                        Box(
                            modifier = Modifier
                                .size((92 - index * 14).dp)
                                .align(
                                    when (index) {
                                        0 -> Alignment.TopEnd
                                        1 -> Alignment.CenterStart
                                        else -> Alignment.BottomCenter
                                    },
                                )
                                .padding(12.dp)
                                .clip(CircleShape)
                                .background(Paper.copy(alpha = 0.08f)),
                        )
                    }
                    Text(
                        text = "2026",
                        color = ApricotGlow,
                        style = MaterialTheme.typography.displayMedium,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(20.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun PlannerTemplateCard(
    template: PlannerTemplate,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = ShellWhite.copy(alpha = 0.96f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Box {
                PlannerPreview(
                    template = template,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.76f),
                )
                FavoriteBadge(
                    accent = template.accent,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(10.dp),
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = template.title,
                    color = InkBlack,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = template.subtitle,
                    color = InkBlack.copy(alpha = 0.62f),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Composable
fun PlannerPreview(
    template: PlannerTemplate,
    modifier: Modifier = Modifier,
) {
    PlannerPaper(
        modifier = modifier,
        accent = template.accent,
        secondary = template.secondaryAccent,
        title = template.title,
    ) {
        when (template.layoutKind) {
            PlannerLayoutKind.DailyPlanner -> DailyPlannerLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.DailyJournal -> DailyJournalLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.FeelingsJournal -> FeelingsLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.WeeklyPlanner -> WeeklyPlannerLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.MonthlyPlanner -> MonthlyPlannerLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.YearlyPlanner -> YearlyPlannerLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.WorkPlanner -> WorkPlannerLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.HourlyPlanner -> HourlyPlannerLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.ProjectOverview -> ProjectOverviewLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.BudgetOverview -> BudgetLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.ExercisePlanner -> ExerciseLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.WeightLossPlanner -> WeightLossLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.SelfCarePlanner -> SelfCareLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.CleaningPlanner -> CleaningLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.GroceryPlanner -> GroceryLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.ReadingLog -> ReadingLogLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.MealPlanner -> MealLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.GoalPlanner -> GoalLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.ReflectionJournal -> ReflectionLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.AdhdPlanner -> AdhdLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.BulletJournal -> BulletJournalLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.MeetingNotes -> MeetingLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.HabitTracker -> HabitLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.TravelPacking -> TravelLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.FamilyOrganizer -> FamilyLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.StudentPlanner -> StudentLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.TeacherPlanner -> TeacherLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.AppointmentPlanner -> AppointmentLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.TimeBlocking -> TimeBlockLayout(template.accent, template.secondaryAccent)
            PlannerLayoutKind.ChoresPlanner -> ChoresLayout(template.accent, template.secondaryAccent)
        }
    }
}

@Composable
private fun PlannerPaper(
    modifier: Modifier,
    accent: Color,
    secondary: Color,
    title: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        color = Paper,
        shadowElevation = 6.dp,
        border = BorderStroke(1.dp, accent.copy(alpha = 0.28f)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(3.dp),
                ) {
                    Text(
                        text = title.uppercase(),
                        color = InkBlack,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Box(
                        modifier = Modifier
                            .width(62.dp)
                            .height(4.dp)
                            .clip(CircleShape)
                            .background(Brush.horizontalGradient(listOf(accent, secondary))),
                    )
                }
                FavoriteBadge(accent = accent)
            }

            content()
        }
    }
}

@Composable
private fun FavoriteBadge(
    accent: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(22.dp)
            .clip(CircleShape)
            .background(accent.copy(alpha = 0.18f))
            .border(1.dp, accent.copy(alpha = 0.3f), CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Outlined.Favorite,
            contentDescription = null,
            tint = accent,
            modifier = Modifier.size(12.dp),
        )
    }
}

@Composable
private fun PaperRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(8.dp),
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = horizontalArrangement,
        content = content,
    )
}

@Composable
private fun LabelPill(
    text: String,
    color: Color,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(color.copy(alpha = 0.2f))
            .padding(horizontal = 8.dp, vertical = 4.dp),
    ) {
        Text(
            text = text,
            color = InkBlack.copy(alpha = 0.75f),
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
private fun SectionBlock(
    title: String,
    modifier: Modifier = Modifier,
    accent: Color = AquaAccent,
    lines: Int = 4,
    bullets: Boolean = false,
) {
    Column(
        modifier = modifier
            .border(1.dp, DividerSoft, RoundedCornerShape(14.dp))
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        LabelPill(text = title, color = accent)
        repeat(lines) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (bullets) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .border(1.dp, DividerSoft, CircleShape),
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(DividerSoft, RectangleShape),
                )
            }
        }
    }
}

@Composable
private fun TrackerRow(
    modifier: Modifier = Modifier,
    count: Int = 6,
    accent: Color = MintAccent,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        repeat(count) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(12.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .border(1.dp, DividerSoft, RoundedCornerShape(4.dp))
                    .background(if (it < count / 2) accent.copy(alpha = 0.55f) else Color.Transparent),
            )
        }
    }
}

@Composable
private fun MoodRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        repeat(5) {
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .border(1.dp, DividerSoft, CircleShape),
            )
        }
    }
}

@Composable
private fun SimpleCalendarGrid(accent: Color) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        PaperRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            listOf("M", "T", "W", "T", "F", "S", "S").forEach {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(6.dp))
                        .background(accent.copy(alpha = 0.16f))
                        .padding(vertical = 4.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = InkBlack.copy(alpha = 0.72f),
                    )
                }
            }
        }
        repeat(5) {
            PaperRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                repeat(7) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .border(1.dp, DividerSoft, RoundedCornerShape(6.dp)),
                    )
                }
            }
        }
    }
}

@Composable
private fun MiniStatsGrid(
    accent: Color,
    rows: Int = 2,
    columns: Int = 2,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        repeat(rows) {
            PaperRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                repeat(columns) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(26.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(1.dp, DividerSoft, RoundedCornerShape(8.dp))
                            .background(accent.copy(alpha = 0.08f)),
                    )
                }
            }
        }
    }
}

@Composable
private fun DailyPlannerLayout(accent: Color, secondary: Color) {
    PaperRow {
        SectionBlock("Weather", Modifier.weight(0.85f), accent, lines = 1)
        SectionBlock("Date", Modifier.weight(1f), secondary, lines = 1)
    }
    PaperRow {
        SectionBlock("3 Priorities", Modifier.weight(1f), accent, lines = 3, bullets = true)
        SectionBlock("Plans & Schedule", Modifier.weight(1.1f), secondary, lines = 7)
    }
    PaperRow {
        SectionBlock("To Do List", Modifier.weight(1f), accent, lines = 5, bullets = true)
        SectionBlock("Things To Get Done", Modifier.weight(1f), secondary, lines = 5)
    }
    MiniStatsGrid(accent = secondary)
    TrackerRow(accent = accent)
    MoodRow()
}

@Composable
private fun DailyJournalLayout(accent: Color, secondary: Color) {
    PaperRow {
        SectionBlock("To-Do", Modifier.weight(0.9f), accent, lines = 5, bullets = true)
        SectionBlock("Reflection", Modifier.weight(1.1f), secondary, lines = 4)
    }
    PaperRow {
        SectionBlock("Priorities", Modifier.weight(1f), secondary, lines = 3)
        SectionBlock("Productivity", Modifier.weight(1f), accent, lines = 3)
    }
    SectionBlock("Today's Gratitude", accent = secondary, lines = 4)
    TrackerRow(accent = secondary)
}

@Composable
private fun FeelingsLayout(accent: Color, secondary: Color) {
    PaperRow {
        SectionBlock("Feeling", Modifier.weight(0.8f), accent, lines = 6)
        SectionBlock("Date", Modifier.weight(1.2f), secondary, lines = 1)
    }
    PaperRow {
        SectionBlock("Self-Love", Modifier.weight(1f), accent, lines = 3)
        SectionBlock("Gratitude", Modifier.weight(1f), secondary, lines = 3)
    }
    PaperRow {
        SectionBlock("Self-Care", Modifier.weight(0.85f), secondary, lines = 5)
        SectionBlock("On My Mind", Modifier.weight(1.15f), accent, lines = 3)
    }
    PaperRow {
        SectionBlock("Next Step", Modifier.weight(1f), secondary, lines = 2)
        SectionBlock("Rate Today", Modifier.weight(1f), accent, lines = 1)
    }
    TrackerRow(accent = secondary)
}

@Composable
private fun WeeklyPlannerLayout(accent: Color, secondary: Color) {
    PaperRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        repeat(7) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, DividerSoft, RoundedCornerShape(12.dp))
                    .padding(6.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(14.dp)
                        .clip(RoundedCornerShape(999.dp))
                        .background(if (it % 2 == 0) accent.copy(alpha = 0.22f) else secondary.copy(alpha = 0.18f)),
                )
                repeat(4) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(DividerSoft),
                    )
                }
            }
        }
    }
    SectionBlock("Notes", accent = secondary, lines = 4)
    TrackerRow(accent = accent)
}

@Composable
private fun MonthlyPlannerLayout(accent: Color, secondary: Color) {
    SimpleCalendarGrid(accent = accent)
    PaperRow {
        SectionBlock("Appointments", Modifier.weight(1f), accent, lines = 3)
        SectionBlock("Focus", Modifier.weight(1f), secondary, lines = 3)
    }
}

@Composable
private fun YearlyPlannerLayout(accent: Color, secondary: Color) {
    repeat(3) {
        PaperRow {
            repeat(4) { index ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .border(1.dp, DividerSoft, RoundedCornerShape(10.dp))
                        .padding(5.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(12.dp)
                            .clip(RoundedCornerShape(999.dp))
                            .background(if ((index + it) % 2 == 0) accent.copy(alpha = 0.22f) else secondary.copy(alpha = 0.22f)),
                    )
                    repeat(3) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(DividerSoft),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WorkPlannerLayout(accent: Color, secondary: Color) {
    PaperRow {
        SectionBlock("Top Priorities", Modifier.weight(1f), accent, lines = 4, bullets = true)
        SectionBlock("To-Do List", Modifier.weight(1f), secondary, lines = 4, bullets = true)
    }
    SectionBlock("Meetings & Deadlines", accent = secondary, lines = 5)
    PaperRow {
        SectionBlock("Reminders", Modifier.weight(1f), accent, lines = 3)
        SectionBlock("Upcoming Projects", Modifier.weight(1f), secondary, lines = 3)
    }
}

@Composable
private fun HourlyPlannerLayout(accent: Color, secondary: Color) {
    PaperRow {
        SectionBlock("Date", Modifier.weight(1f), accent, lines = 1)
        SectionBlock("Goals", Modifier.weight(1f), secondary, lines = 1)
    }
    PaperRow {
        SectionBlock("6AM - 12PM", Modifier.weight(1f), accent, lines = 6)
        SectionBlock("1PM - 10PM", Modifier.weight(1f), secondary, lines = 6)
    }
    TrackerRow(accent = secondary)
}

@Composable
private fun ProjectOverviewLayout(accent: Color, secondary: Color) {
    repeat(3) {
        PaperRow {
            SectionBlock("Project", Modifier.weight(1.1f), accent, lines = 2)
            SectionBlock("Priority", Modifier.weight(0.9f), secondary, lines = 2)
        }
        SectionBlock("Timeline", accent = if (it % 2 == 0) accent else secondary, lines = 1)
    }
}

@Composable
private fun BudgetLayout(accent: Color, secondary: Color) {
    repeat(3) {
        PaperRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            repeat(4) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(54.dp)
                        .border(1.dp, DividerSoft, RoundedCornerShape(10.dp))
                        .background(if (it % 2 == 0) accent.copy(alpha = 0.08f) else secondary.copy(alpha = 0.08f)),
                )
            }
        }
    }
}

@Composable
private fun ExerciseLayout(accent: Color, secondary: Color) {
    PaperRow {
        SectionBlock("Food", Modifier.weight(1f), accent, lines = 4)
        SectionBlock("Hydration", Modifier.weight(1f), secondary, lines = 3)
    }
    PaperRow {
        SectionBlock("Today's Goal", Modifier.weight(1f), secondary, lines = 3)
        SectionBlock("Exercise Focus", Modifier.weight(1f), accent, lines = 4)
    }
    SectionBlock("Workout Log", accent = secondary, lines = 4)
    TrackerRow(accent = accent)
}

@Composable
private fun WeightLossLayout(accent: Color, secondary: Color) {
    repeat(3) {
        PaperRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            repeat(4) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(42.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if ((it + 1) % 2 == 0) accent.copy(alpha = 0.16f) else secondary.copy(alpha = 0.16f))
                        .border(1.dp, DividerSoft, RoundedCornerShape(10.dp)),
                )
            }
        }
    }
}

@Composable
private fun SelfCareLayout(accent: Color, secondary: Color) {
    PaperRow {
        SectionBlock("Checklist", Modifier.weight(1.2f), accent, lines = 6, bullets = true)
        Column(
            modifier = Modifier.weight(0.8f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            SectionBlock("Sleep", accent = secondary, lines = 2)
            SectionBlock("Water", accent = accent, lines = 2)
            SectionBlock("Mood", accent = secondary, lines = 2)
        }
    }
}

@Composable
private fun CleaningLayout(accent: Color, secondary: Color) {
    repeat(2) {
        PaperRow {
            SectionBlock("Kitchen", Modifier.weight(1f), accent, lines = 4, bullets = true)
            SectionBlock("Living", Modifier.weight(1f), secondary, lines = 4, bullets = true)
            SectionBlock("Bed", Modifier.weight(1f), accent, lines = 4, bullets = true)
        }
    }
}

@Composable
private fun GroceryLayout(accent: Color, secondary: Color) {
    PaperRow {
        SectionBlock("Produce", Modifier.weight(1f), accent, lines = 6, bullets = true)
        SectionBlock("Pantry", Modifier.weight(1f), secondary, lines = 6, bullets = true)
    }
    PaperRow {
        SectionBlock("Household", Modifier.weight(1f), accent, lines = 4, bullets = true)
        SectionBlock("Notes", Modifier.weight(1f), secondary, lines = 4)
    }
}

@Composable
private fun ReadingLogLayout(accent: Color, secondary: Color) {
    SectionBlock("Book Details", accent = accent, lines = 3)
    PaperRow {
        SectionBlock("Notes", Modifier.weight(1.3f), secondary, lines = 6)
        SectionBlock("Rating", Modifier.weight(0.7f), accent, lines = 4)
    }
    TrackerRow(accent = secondary)
}

@Composable
private fun MealLayout(accent: Color, secondary: Color) {
    repeat(3) {
        PaperRow {
            SectionBlock("Day ${it + 1}", Modifier.weight(1f), accent, lines = 3)
            SectionBlock("Prep", Modifier.weight(1f), secondary, lines = 3)
        }
    }
    SectionBlock("Shopping Notes", accent = accent, lines = 2)
}

@Composable
private fun GoalLayout(accent: Color, secondary: Color) {
    SectionBlock("Today's Goal", accent = accent, lines = 2)
    PaperRow {
        SectionBlock("Action Steps", Modifier.weight(1f), secondary, lines = 5, bullets = true)
        SectionBlock("Win Tracker", Modifier.weight(1f), accent, lines = 5)
    }
    SectionBlock("Reflection", accent = secondary, lines = 4)
}

@Composable
private fun ReflectionLayout(accent: Color, secondary: Color) {
    SectionBlock("Reflection Prompt", accent = accent, lines = 3)
    SectionBlock("What Went Well", accent = secondary, lines = 3)
    SectionBlock("Tomorrow I Will", accent = accent, lines = 3)
    TrackerRow(accent = secondary)
}

@Composable
private fun AdhdLayout(accent: Color, secondary: Color) {
    PaperRow {
        SectionBlock("Focus", Modifier.weight(1f), accent, lines = 4)
        SectionBlock("Support", Modifier.weight(1f), secondary, lines = 4)
    }
    PaperRow {
        SectionBlock("Low Energy Tasks", Modifier.weight(1f), secondary, lines = 4, bullets = true)
        SectionBlock("High Priority", Modifier.weight(1f), accent, lines = 4, bullets = true)
    }
    TrackerRow(accent = accent)
}

@Composable
private fun BulletJournalLayout(accent: Color, secondary: Color) {
    PaperRow {
        repeat(3) {
            SectionBlock(
                title = listOf("Mon", "Wed", "Fri")[it],
                modifier = Modifier.weight(1f),
                accent = if (it % 2 == 0) accent else secondary,
                lines = 5,
            )
        }
    }
    PaperRow {
        repeat(4) {
            SectionBlock(
                title = listOf("Tue", "Thu", "Sat", "Sun")[it],
                modifier = Modifier.weight(1f),
                accent = if (it % 2 == 0) secondary else accent,
                lines = 4,
            )
        }
    }
}

@Composable
private fun MeetingLayout(accent: Color, secondary: Color) {
    SectionBlock("Agenda", accent = accent, lines = 3)
    SectionBlock("Notes", accent = secondary, lines = 6)
    PaperRow {
        SectionBlock("Action Items", Modifier.weight(1f), accent, lines = 3, bullets = true)
        SectionBlock("Owner", Modifier.weight(1f), secondary, lines = 3)
    }
}

@Composable
private fun HabitLayout(accent: Color, secondary: Color) {
    repeat(4) {
        PaperRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Box(
                modifier = Modifier
                    .weight(0.85f)
                    .height(18.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(if (it % 2 == 0) accent.copy(alpha = 0.2f) else secondary.copy(alpha = 0.2f)),
            )
            repeat(7) {
                Box(
                    modifier = Modifier
                        .weight(0.4f)
                        .aspectRatio(1f)
                        .border(1.dp, DividerSoft, RoundedCornerShape(5.dp)),
                )
            }
        }
    }
}

@Composable
private fun TravelLayout(accent: Color, secondary: Color) {
    PaperRow {
        SectionBlock("Documents", Modifier.weight(1f), accent, lines = 4, bullets = true)
        SectionBlock("Carry On", Modifier.weight(1f), secondary, lines = 4, bullets = true)
    }
    PaperRow {
        SectionBlock("Outfits", Modifier.weight(1f), accent, lines = 5, bullets = true)
        SectionBlock("To Buy", Modifier.weight(1f), secondary, lines = 5, bullets = true)
    }
}

@Composable
private fun FamilyLayout(accent: Color, secondary: Color) {
    PaperRow {
        SectionBlock("Weekly Plan", Modifier.weight(1f), accent, lines = 4)
        SectionBlock("Meal + Budget", Modifier.weight(1f), secondary, lines = 4)
    }
    PaperRow {
        SectionBlock("Kids Activities", Modifier.weight(1f), accent, lines = 4)
        SectionBlock("Errands", Modifier.weight(1f), secondary, lines = 4, bullets = true)
    }
}

@Composable
private fun StudentLayout(accent: Color, secondary: Color) {
    PaperRow {
        SectionBlock("Assignments", Modifier.weight(1f), accent, lines = 5)
        SectionBlock("Study Blocks", Modifier.weight(1f), secondary, lines = 5)
    }
    SectionBlock("Class Notes", accent = accent, lines = 5)
    TrackerRow(accent = secondary)
}

@Composable
private fun TeacherLayout(accent: Color, secondary: Color) {
    PaperRow {
        SectionBlock("Lesson Plan", Modifier.weight(1f), accent, lines = 4)
        SectionBlock("Class Notes", Modifier.weight(1f), secondary, lines = 4)
    }
    PaperRow {
        SectionBlock("Attendance", Modifier.weight(1f), accent, lines = 4)
        SectionBlock("Follow-up", Modifier.weight(1f), secondary, lines = 4)
    }
}

@Composable
private fun AppointmentLayout(accent: Color, secondary: Color) {
    SimpleCalendarGrid(accent = secondary)
    SectionBlock("Appointment Notes", accent = accent, lines = 4)
}

@Composable
private fun TimeBlockLayout(accent: Color, secondary: Color) {
    repeat(5) {
        PaperRow {
            Box(
                modifier = Modifier
                    .weight(0.35f)
                    .height(28.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (it % 2 == 0) accent.copy(alpha = 0.18f) else secondary.copy(alpha = 0.18f)),
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(28.dp)
                    .border(1.dp, DividerSoft, RoundedCornerShape(8.dp)),
            )
        }
    }
    SectionBlock("Notes", accent = secondary, lines = 3)
}

@Composable
private fun ChoresLayout(accent: Color, secondary: Color) {
    repeat(3) {
        PaperRow {
            SectionBlock("Morning", Modifier.weight(1f), accent, lines = 3, bullets = true)
            SectionBlock("Afternoon", Modifier.weight(1f), secondary, lines = 3, bullets = true)
            SectionBlock("Evening", Modifier.weight(1f), accent, lines = 3, bullets = true)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlannerTemplateCardPreview() {
    Plan92Theme {
        PlannerTemplateCard(
            template = remember { MockPlannerRepository.templates.first() },
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OwnedPlannerCardPreview() {
    Plan92Theme {
        OwnedPlannerCard(
            planner = remember { MockPlannerRepository.userPlanners.first() },
            onClick = {},
        )
    }
}
