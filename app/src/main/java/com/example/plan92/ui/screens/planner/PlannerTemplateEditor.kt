package com.example.plan92.ui.screens.planner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.plan92.planner.engine.PlannerSectionDefinition
import com.example.plan92.planner.engine.PlannerSectionKind
import com.example.plan92.planner.engine.PlannerTemplateDefinition
import com.example.plan92.ui.components.BudgetGridSection
import com.example.plan92.ui.components.ChecklistSection
import com.example.plan92.ui.components.CategoryGridSection
import com.example.plan92.ui.components.DailyAgendaBoardSection
import com.example.plan92.ui.components.DailyBrainDumpBoardSection
import com.example.plan92.ui.components.DailyClassicBoardSection
import com.example.plan92.ui.components.DailyDevotionalBoardSection
import com.example.plan92.ui.components.DailyGoalBoardSection
import com.example.plan92.ui.components.DailyReflectionBoardSection
import com.example.plan92.ui.components.DailyReflectionJournalBoardSection
import com.example.plan92.ui.components.DailyTaskBoardSection
import com.example.plan92.ui.components.DailyWorkBoardSection
import com.example.plan92.ui.components.ExerciseDailyBoardSection
import com.example.plan92.ui.components.DateFieldRow
import com.example.plan92.ui.components.DayColumnsSection
import com.example.plan92.ui.components.EditableTextSection
import com.example.plan92.ui.components.FullDayHourlyBoardSection
import com.example.plan92.ui.components.GoalsTrackerBoardSection
import com.example.plan92.ui.components.GridNotesSection
import com.example.plan92.ui.components.HabitTrackerSection
import com.example.plan92.ui.components.HourlyScheduleSection
import com.example.plan92.ui.components.JournalPromptSection
import com.example.plan92.ui.components.LinedNotesSection
import com.example.plan92.ui.components.MealPlannerSection
import com.example.plan92.ui.components.MeetingNotesBoardSection
import com.example.plan92.ui.components.MonthGridSection
import com.example.plan92.ui.components.MoodSelectorSection
import com.example.plan92.ui.components.NotesSection
import com.example.plan92.ui.components.PlannerPage
import com.example.plan92.ui.components.PlannerSheetMetrics
import com.example.plan92.ui.components.PlannerTitleBlock
import com.example.plan92.ui.components.ProductiveDayBoardSection
import com.example.plan92.ui.components.ProgressTimelineSection
import com.example.plan92.ui.components.QuadrantChecklistSection
import com.example.plan92.ui.components.ReflectionPromptSection
import com.example.plan92.ui.components.RoutineBoardSection
import com.example.plan92.ui.components.ScheduleSection
import com.example.plan92.ui.components.SelfCareDailyBoardSection
import com.example.plan92.ui.components.TaskBreakdownBoardSection
import com.example.plan92.ui.components.TaskManagementBoardSection
import com.example.plan92.ui.components.TrackerSection
import com.example.plan92.ui.components.WorkTasksBoardSection
import com.example.plan92.ui.components.WaterTrackerSection
import com.example.plan92.ui.components.WeekListWithSideChecklistSection
import com.example.plan92.ui.components.WeekGridSection
import com.example.plan92.ui.components.AdhdDailyBoardSection
import com.example.plan92.ui.components.DailyManifestBoardSection
import com.example.plan92.ui.components.DotGridWritingSection
import com.example.plan92.ui.components.MonthlyAppointmentBoardSection
import com.example.plan92.ui.components.MonthlyBudgetBoardSection
import com.example.plan92.ui.components.MonthlyPlannerBoardSection
import com.example.plan92.ui.components.MonthlyWeightLossBoardSection
import com.example.plan92.ui.components.MyDailyJournalBoardSection
import com.example.plan92.ui.components.SeasonalYearlyBoardSection
import com.example.plan92.ui.components.SelfCareJournalBoardSection
import com.example.plan92.ui.components.SoapBibleBoardSection
import com.example.plan92.ui.components.WeeklyBulletBoardSection
import com.example.plan92.ui.components.WeeklyDashboardBoardSection
import com.example.plan92.ui.components.WeeklyGoalsBoardSection
import com.example.plan92.ui.components.WeeklyHabitsBoardSection
import com.example.plan92.ui.components.WeeklyMealBoardSection
import com.example.plan92.ui.components.WeeklyPlannerBoardSection
import com.example.plan92.ui.components.WeeklyProjectsBoardSection
import com.example.plan92.ui.components.WeeklyScheduleBoardSection
import com.example.plan92.ui.components.WorkLifeBalanceBoardSection
import com.example.plan92.ui.components.YearlyCalendarBoardSection
import com.example.plan92.ui.components.YearlyPlannerBoardSection
import com.example.plan92.ui.components.BulletLifeJournalBoardSection
import com.example.plan92.ui.components.DailyBulletBoardSection
import com.example.plan92.ui.components.DailyJournalBoardSection
import com.example.plan92.ui.components.DearDiaryBoardSection
import com.example.plan92.ui.components.FeelingsJournalBoardSection
import com.example.plan92.ui.components.FindBalanceJournalBoardSection
import com.example.plan92.ui.components.JournalPromptsBoardSection
import com.example.plan92.ui.components.ReadingLogBoardSection
import com.example.plan92.ui.components.BillPaymentBoardSection
import com.example.plan92.ui.components.ClassScheduleBoardSection
import com.example.plan92.ui.components.DoctorListBoardSection
import com.example.plan92.ui.components.EventPlannerBoardSection
import com.example.plan92.ui.components.FamilyOrganizerBoardSection
import com.example.plan92.ui.components.MedicalNotesBoardSection
import com.example.plan92.ui.components.MomChoresBoardSection
import com.example.plan92.ui.components.MomPlannerBoardSection
import com.example.plan92.ui.components.NursePlannerBoardSection
import com.example.plan92.ui.components.OfficeOrganizerBoardSection
import com.example.plan92.ui.components.ProjectPlannerBoardSection
import com.example.plan92.ui.components.ProjectProgressBoardSection
import com.example.plan92.ui.components.StudyPlannerBoardSection
import com.example.plan92.ui.components.TeacherPlannerBoardSection
import com.example.plan92.ui.components.TravelPackingBoardSection
import com.example.plan92.ui.components.VacationBudgetBoardSection
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
            verticalArrangement = Arrangement.spacedBy(PlannerSheetMetrics.PageSpacing),
        ) {
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

        PlannerSectionKind.LINED_NOTES -> LinedNotesSection(
            title = section.title,
            dateLabel = section.fields.firstOrNull()?.label ?: "Date",
            lines = section.cellLines.takeIf { it > 0 } ?: 14,
        )

        PlannerSectionKind.GRID_NOTES -> GridNotesSection(
            title = section.title,
            dateLabel = section.fields.firstOrNull()?.label ?: "Date",
            lines = section.cellLines.takeIf { it > 0 } ?: 18,
        )

        PlannerSectionKind.DOT_GRID_NOTES -> DotGridWritingSection(
            title = section.title,
            dateLabel = section.fields.firstOrNull()?.label ?: "Date",
            lines = section.cellLines.takeIf { it > 0 } ?: 18,
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

        PlannerSectionKind.CATEGORY_GRID -> CategoryGridSection(
            title = section.title,
            categories = section.rowLabels,
            columns = section.columns.takeIf { it > 0 } ?: 2,
            checkable = section.checkable,
            cellLines = section.cellLines.takeIf { it > 0 } ?: 4,
        )

        PlannerSectionKind.WEEK_LIST_WITH_SIDE_CHECKLIST -> WeekListWithSideChecklistSection(
            title = section.title,
            headerLeftLabel = section.fields.getOrNull(0)?.label ?: "Week Of",
            headerRightLabel = section.fields.getOrNull(1)?.label ?: "List",
            weekdays = section.rowLabels.ifEmpty { listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday") },
            checkable = section.checkable,
            listLines = section.cellLines.takeIf { it > 0 } ?: 6,
        )

        PlannerSectionKind.QUADRANT_CHECKLIST -> QuadrantChecklistSection(
            title = section.title,
            quadrantTitles = section.rowLabels.ifEmpty { listOf("Category 1", "Category 2", "Category 3", "Category 4") },
            checkable = section.checkable,
            lines = section.cellLines.takeIf { it > 0 } ?: 6,
        )

        PlannerSectionKind.TASK_BREAKDOWN_BOARD -> TaskBreakdownBoardSection(
            title = section.title,
            blocks = section.rowLabels.ifEmpty { listOf("Task Identification", "Prioritization", "Estimation", "Task Assignment", "Conclusion") },
        )

        PlannerSectionKind.MEETING_NOTES_BOARD -> MeetingNotesBoardSection(
            title = section.title,
            headerFields = section.fields.map { it.label },
            topics = section.rowLabels.ifEmpty { listOf("Topic 1", "Topic 2") },
        )

        PlannerSectionKind.GOALS_TRACKER_BOARD -> GoalsTrackerBoardSection(
            title = section.title,
            monthLabel = section.fields.firstOrNull()?.label ?: "Month",
            stepLabels = section.columnLabels.ifEmpty { listOf("Step 1", "Step 2", "Step 3", "Step 4", "Step 5") },
            rewardLabels = section.stats.ifEmpty { listOf("Reward 1", "Reward 2", "Reward 3", "Reward 4") },
        )

        PlannerSectionKind.DAILY_CLASSIC_BOARD -> DailyClassicBoardSection(title = section.title)
        PlannerSectionKind.DAILY_AGENDA_BOARD -> DailyAgendaBoardSection(title = section.title)
        PlannerSectionKind.PRODUCTIVE_DAY_BOARD -> ProductiveDayBoardSection(title = section.title)
        PlannerSectionKind.DAILY_TASK_BOARD -> DailyTaskBoardSection(title = section.title)
        PlannerSectionKind.DAILY_WORK_BOARD -> DailyWorkBoardSection(title = section.title)
        PlannerSectionKind.FULL_DAY_HOURLY_BOARD -> FullDayHourlyBoardSection(title = section.title)
        PlannerSectionKind.DAILY_GOAL_BOARD -> DailyGoalBoardSection(title = section.title)
        PlannerSectionKind.ROUTINE_BOARD -> RoutineBoardSection(title = section.title)
        PlannerSectionKind.WORK_TASKS_BOARD -> WorkTasksBoardSection(title = section.title)
        PlannerSectionKind.TASK_MANAGEMENT_BOARD -> TaskManagementBoardSection(title = section.title)
        PlannerSectionKind.ADHD_DAILY_BOARD -> AdhdDailyBoardSection(title = section.title)
        PlannerSectionKind.EXERCISE_DAILY_BOARD -> ExerciseDailyBoardSection(title = section.title)
        PlannerSectionKind.SELF_CARE_DAILY_BOARD -> SelfCareDailyBoardSection(title = section.title)
        PlannerSectionKind.DAILY_REFLECTION_BOARD -> DailyReflectionBoardSection(title = section.title)
        PlannerSectionKind.DAILY_REFLECTION_JOURNAL_BOARD -> DailyReflectionJournalBoardSection(title = section.title)
        PlannerSectionKind.DAILY_DEVOTIONAL_BOARD -> DailyDevotionalBoardSection(title = section.title)
        PlannerSectionKind.DAILY_MANIFEST_BOARD -> DailyManifestBoardSection(title = section.title)
        PlannerSectionKind.DAILY_BRAIN_DUMP_BOARD -> DailyBrainDumpBoardSection(title = section.title)
        PlannerSectionKind.WEEKLY_PLANNER_BOARD -> WeeklyPlannerBoardSection(title = section.title)
        PlannerSectionKind.WEEKLY_SCHEDULE_BOARD -> WeeklyScheduleBoardSection(title = section.title)
        PlannerSectionKind.WEEKLY_DASHBOARD_BOARD -> WeeklyDashboardBoardSection(title = section.title)
        PlannerSectionKind.WEEKLY_HABITS_BOARD -> WeeklyHabitsBoardSection(title = section.title)
        PlannerSectionKind.WEEKLY_GOALS_BOARD -> WeeklyGoalsBoardSection(title = section.title)
        PlannerSectionKind.WEEKLY_MEAL_BOARD -> WeeklyMealBoardSection(title = section.title)
        PlannerSectionKind.WEEKLY_PROJECTS_BOARD -> WeeklyProjectsBoardSection(title = section.title)
        PlannerSectionKind.WEEKLY_BULLET_BOARD -> WeeklyBulletBoardSection(title = section.title)
        PlannerSectionKind.WORK_LIFE_BALANCE_BOARD -> WorkLifeBalanceBoardSection(title = section.title)
        PlannerSectionKind.MONTHLY_PLANNER_BOARD -> MonthlyPlannerBoardSection(title = section.title)
        PlannerSectionKind.MONTHLY_APPOINTMENT_BOARD -> MonthlyAppointmentBoardSection(title = section.title)
        PlannerSectionKind.MONTHLY_BUDGET_BOARD -> MonthlyBudgetBoardSection(title = section.title)
        PlannerSectionKind.MONTHLY_WEIGHT_LOSS_BOARD -> MonthlyWeightLossBoardSection(title = section.title)
        PlannerSectionKind.YEARLY_PLANNER_BOARD -> YearlyPlannerBoardSection(title = section.title)
        PlannerSectionKind.YEARLY_CALENDAR_BOARD -> YearlyCalendarBoardSection(title = section.title)
        PlannerSectionKind.SEASONAL_YEARLY_BOARD -> SeasonalYearlyBoardSection(title = section.title)
        PlannerSectionKind.DAILY_JOURNAL_BOARD -> DailyJournalBoardSection(title = section.title)
        PlannerSectionKind.MY_DAILY_JOURNAL_BOARD -> MyDailyJournalBoardSection(title = section.title)
        PlannerSectionKind.FEELINGS_JOURNAL_BOARD -> FeelingsJournalBoardSection(title = section.title)
        PlannerSectionKind.JOURNAL_PROMPTS_BOARD -> JournalPromptsBoardSection(title = section.title)
        PlannerSectionKind.SELF_CARE_JOURNAL_BOARD -> SelfCareJournalBoardSection(title = section.title)
        PlannerSectionKind.READING_LOG_BOARD -> ReadingLogBoardSection(title = section.title)
        PlannerSectionKind.SOAP_BIBLE_BOARD -> SoapBibleBoardSection(title = section.title)
        PlannerSectionKind.FIND_BALANCE_JOURNAL_BOARD -> FindBalanceJournalBoardSection(title = section.title)
        PlannerSectionKind.BULLET_LIFE_JOURNAL_BOARD -> BulletLifeJournalBoardSection(title = section.title)
        PlannerSectionKind.DEAR_DIARY_BOARD -> DearDiaryBoardSection(title = section.title)
        PlannerSectionKind.DAILY_BULLET_BOARD -> DailyBulletBoardSection(title = section.title)
        PlannerSectionKind.PROJECT_PLANNER_BOARD -> ProjectPlannerBoardSection(title = section.title)
        PlannerSectionKind.PROJECT_PROGRESS_BOARD -> ProjectProgressBoardSection(title = section.title)
        PlannerSectionKind.VACATION_BUDGET_BOARD -> VacationBudgetBoardSection(title = section.title)
        PlannerSectionKind.BILL_PAYMENT_BOARD -> BillPaymentBoardSection(title = section.title)
        PlannerSectionKind.TRAVEL_PACKING_BOARD -> TravelPackingBoardSection(title = section.title)
        PlannerSectionKind.FAMILY_ORGANIZER_BOARD -> FamilyOrganizerBoardSection(title = section.title)
        PlannerSectionKind.MOM_PLANNER_BOARD -> MomPlannerBoardSection(title = section.title)
        PlannerSectionKind.MOM_CHORES_BOARD -> MomChoresBoardSection(title = section.title)
        PlannerSectionKind.CLASS_SCHEDULE_BOARD -> ClassScheduleBoardSection(title = section.title)
        PlannerSectionKind.STUDY_PLANNER_BOARD -> StudyPlannerBoardSection(title = section.title)
        PlannerSectionKind.TEACHER_PLANNER_BOARD -> TeacherPlannerBoardSection(title = section.title)
        PlannerSectionKind.MEDICAL_NOTES_BOARD -> MedicalNotesBoardSection(title = section.title)
        PlannerSectionKind.NURSE_PLANNER_BOARD -> NursePlannerBoardSection(title = section.title)
        PlannerSectionKind.DOCTOR_LIST_BOARD -> DoctorListBoardSection(title = section.title)
        PlannerSectionKind.EVENT_PLANNER_BOARD -> EventPlannerBoardSection(title = section.title)
        PlannerSectionKind.OFFICE_ORGANIZER_BOARD -> OfficeOrganizerBoardSection(title = section.title)
    }
}
