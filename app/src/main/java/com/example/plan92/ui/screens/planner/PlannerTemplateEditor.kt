package com.example.plan92.ui.screens.planner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.plan92.planner.engine.PlannerSectionDefinition
import com.example.plan92.planner.engine.PlannerSectionKind
import com.example.plan92.planner.engine.PlannerTemplateDefinition
import com.example.plan92.ui.components.BudgetGridSection
import com.example.plan92.ui.components.ChecklistSection
import com.example.plan92.ui.components.DateFieldRow
import com.example.plan92.ui.components.DayColumnsSection
import com.example.plan92.ui.components.EditableTextSection
import com.example.plan92.ui.components.HabitTrackerSection
import com.example.plan92.ui.components.HourlyScheduleSection
import com.example.plan92.ui.components.JournalPromptSection
import com.example.plan92.ui.components.MealPlannerSection
import com.example.plan92.ui.components.MonthGridSection
import com.example.plan92.ui.components.MoodSelectorSection
import com.example.plan92.ui.components.NotesSection
import com.example.plan92.ui.components.PlannerHeader
import com.example.plan92.ui.components.PlannerPage
import com.example.plan92.ui.components.PlannerTitleBlock
import com.example.plan92.ui.components.ProgressTimelineSection
import com.example.plan92.ui.components.ReflectionPromptSection
import com.example.plan92.ui.components.ScheduleSection
import com.example.plan92.ui.components.TrackerSection
import com.example.plan92.ui.components.WaterTrackerSection
import com.example.plan92.ui.components.WeekGridSection
import com.example.plan92.ui.theme.plan92Palette

@Composable
fun PlannerTemplateEditor(
    definition: PlannerTemplateDefinition,
    modifier: Modifier = Modifier,
) {
    PlannerPage(
        modifier = modifier,
        accent = MaterialTheme.plan92Palette.primaryAccent,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            PlannerHeader(
                label = definition.name,
                editorKind = definition.editorType.name,
                capabilities = definition.capabilities.map { it.name.replace('_', ' ') },
            )

            definition.sections.forEach { section ->
                PlannerSectionRenderer(section = section)
            }
        }
    }
}

@Composable
private fun PlannerSectionRenderer(
    section: PlannerSectionDefinition,
) {
    when (section.kind) {
        PlannerSectionKind.TITLE_BLOCK -> PlannerTitleBlock(
            title = section.title,
            subtitle = section.subtitle.orEmpty(),
            chips = section.chips,
        )

        PlannerSectionKind.DATE_ROW -> DateFieldRow(
            leftLabel = section.fields.getOrNull(0)?.label ?: "Date",
            rightLabel = section.fields.getOrNull(1)?.label ?: "Focus",
        )

        PlannerSectionKind.EDITABLE_TEXT -> EditableTextSection(
            title = section.title,
            subtitle = section.subtitle,
            fields = section.fields.map { it.label },
        )

        PlannerSectionKind.NOTES -> NotesSection(
            title = section.title,
            subtitle = section.subtitle,
        )

        PlannerSectionKind.CHECKLIST -> ChecklistSection(
            title = section.title,
            subtitle = section.subtitle,
            items = section.rowLabels.ifEmpty { listOf("Item 1", "Item 2", "Item 3") },
        )

        PlannerSectionKind.SCHEDULE -> ScheduleSection(
            title = section.title,
            rows = section.rowLabels.ifEmpty { listOf("Morning", "Midday", "Afternoon", "Evening") },
        )

        PlannerSectionKind.HOURLY_SCHEDULE -> HourlyScheduleSection(
            title = section.title,
            rows = section.rowLabels.ifEmpty { listOf("8 AM", "10 AM", "12 PM", "2 PM", "4 PM", "6 PM") },
        )

        PlannerSectionKind.DAY_COLUMNS -> DayColumnsSection(
            title = section.title,
            days = section.columnLabels.ifEmpty { listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun") },
        )

        PlannerSectionKind.WEEK_GRID -> WeekGridSection(
            title = section.title,
            days = section.columnLabels.ifEmpty { listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun") },
        )

        PlannerSectionKind.MONTH_GRID -> MonthGridSection(
            title = section.title,
            headers = section.columnLabels.ifEmpty { listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat") },
        )

        PlannerSectionKind.BUDGET_GRID -> BudgetGridSection(
            title = section.title,
            rows = section.rowLabels.ifEmpty { listOf("Housing", "Food", "Transport") },
            columns = section.columnLabels.ifEmpty { listOf("Planned", "Actual") },
        )

        PlannerSectionKind.TRACKER,
        PlannerSectionKind.STATS -> TrackerSection(
            title = section.title,
            stats = section.stats.ifEmpty { listOf("Metric 1", "Metric 2", "Metric 3", "Metric 4") },
        )

        PlannerSectionKind.WATER_TRACKER -> WaterTrackerSection(
            title = section.title,
        )

        PlannerSectionKind.MOOD_SELECTOR -> MoodSelectorSection(
            title = section.title,
            moods = section.chips.ifEmpty { listOf("Low", "Okay", "Good", "Great") },
        )

        PlannerSectionKind.HABIT_TRACKER -> HabitTrackerSection(
            title = section.title,
            habits = section.rowLabels.ifEmpty { listOf("Habit 1", "Habit 2", "Habit 3") },
            days = section.columnLabels.ifEmpty { listOf("M", "T", "W", "T", "F", "S", "S") },
        )

        PlannerSectionKind.MEAL_PLANNER -> MealPlannerSection(
            title = section.title,
            mealTypes = section.rowLabels.ifEmpty { listOf("Breakfast", "Lunch", "Dinner") },
            days = section.columnLabels.ifEmpty { listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun") },
        )

        PlannerSectionKind.REFLECTION_PROMPT -> ReflectionPromptSection(
            title = section.title,
            prompts = section.prompts.ifEmpty { listOf("What mattered most today?") },
        )

        PlannerSectionKind.JOURNAL_PROMPT -> JournalPromptSection(
            title = section.title,
            prompts = section.prompts.ifEmpty { listOf("What happened today?") },
        )

        PlannerSectionKind.PROGRESS_TIMELINE -> ProgressTimelineSection(
            title = section.title,
            milestones = section.rowLabels.ifEmpty { listOf("Milestone 1", "Milestone 2", "Milestone 3") },
        )
    }
}
