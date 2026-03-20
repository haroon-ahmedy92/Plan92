package com.example.plan92.planner.engine

import com.example.plan92.data.mock.PlannerFamily
import com.example.plan92.data.mock.PlannerTemplate

enum class PlannerEditorKind {
    FLEXIBLE_PAGE,
    STRUCTURED_DAY,
    STRUCTURED_WEEK,
    CALENDAR,
    JOURNAL,
    TRACKER,
    BUDGET,
    PROJECT,
    NOTEBOOK,
}

enum class PlannerSectionKind {
    TITLE_BLOCK,
    DATE_ROW,
    EDITABLE_TEXT,
    NOTES,
    LINED_NOTES,
    CHECKLIST,
    SCHEDULE,
    HOURLY_SCHEDULE,
    DAY_COLUMNS,
    WEEK_GRID,
    MONTH_GRID,
    BUDGET_GRID,
    TRACKER,
    WATER_TRACKER,
    MOOD_SELECTOR,
    HABIT_TRACKER,
    MEAL_PLANNER,
    REFLECTION_PROMPT,
    JOURNAL_PROMPT,
    PROGRESS_TIMELINE,
    STATS,
    CATEGORY_GRID,
    WEEK_LIST_WITH_SIDE_CHECKLIST,
    QUADRANT_CHECKLIST,
    TASK_BREAKDOWN_BOARD,
    MEETING_NOTES_BOARD,
    GOALS_TRACKER_BOARD,
    DAILY_CLASSIC_BOARD,
    DAILY_AGENDA_BOARD,
    PRODUCTIVE_DAY_BOARD,
    DAILY_TASK_BOARD,
    DAILY_WORK_BOARD,
    FULL_DAY_HOURLY_BOARD,
    DAILY_GOAL_BOARD,
    ROUTINE_BOARD,
    WORK_TASKS_BOARD,
    TASK_MANAGEMENT_BOARD,
    ADHD_DAILY_BOARD,
    EXERCISE_DAILY_BOARD,
    SELF_CARE_DAILY_BOARD,
    DAILY_REFLECTION_BOARD,
    DAILY_REFLECTION_JOURNAL_BOARD,
    DAILY_DEVOTIONAL_BOARD,
    DAILY_MANIFEST_BOARD,
    DAILY_BRAIN_DUMP_BOARD,
}

enum class PlannerDecorationStyle {
    NONE,
    CORNER_GLOW,
    RIBBON,
    HIGHLIGHT_BAND,
}

enum class PlannerCapability {
    CHECKLISTS,
    SCHEDULE,
    NOTES,
    TRACKING,
    CALENDAR,
    BUDGETING,
    REFLECTION,
    MEALS,
    MOOD,
    WATER,
    HABITS,
}

data class PlannerFieldDefinition(
    val id: String,
    val label: String,
    val hint: String = "",
)

data class PlannerDecorationDefinition(
    val label: String? = null,
    val style: PlannerDecorationStyle = PlannerDecorationStyle.NONE,
)

data class PlannerSectionDefinition(
    val id: String,
    val title: String,
    val kind: PlannerSectionKind,
    val subtitle: String? = null,
    val fields: List<PlannerFieldDefinition> = emptyList(),
    val prompts: List<String> = emptyList(),
    val rowLabels: List<String> = emptyList(),
    val columnLabels: List<String> = emptyList(),
    val chips: List<String> = emptyList(),
    val stats: List<String> = emptyList(),
    val columns: Int = 0,
    val checkable: Boolean = false,
    val cellLines: Int = 0,
    val decoration: PlannerDecorationDefinition? = null,
)

data class PlannerTemplateDefinition(
    val id: String,
    val name: String,
    val family: PlannerFamily,
    val category: String,
    val layoutKind: String,
    val editorType: PlannerEditorKind,
    val capabilities: Set<PlannerCapability>,
    val sections: List<PlannerSectionDefinition>,
)

object PlannerTemplateSchema {
    fun definitionFor(template: PlannerTemplate): PlannerTemplateDefinition {
        exactStructuredTemplate(template)?.let { return it }

        val archetype = when (template.family) {
            PlannerFamily.BLANK_PAGE -> blankPageSections()
            PlannerFamily.BOOK_PLANNER -> bookSections()
            PlannerFamily.DAILY_PLANNER,
            PlannerFamily.DAILY_AGENDA,
            PlannerFamily.DAILY_TASK_PLANNER,
            PlannerFamily.PRODUCTIVITY_PLANNER,
            PlannerFamily.ADHD_DAILY_PLANNER -> dailySections()

            PlannerFamily.DAILY_JOURNAL,
            PlannerFamily.FEELINGS_JOURNAL,
            PlannerFamily.REFLECTION_JOURNAL,
            PlannerFamily.SOAP_BIBLE_STUDY,
            PlannerFamily.NOTES_PAGE,
            PlannerFamily.BULLET_JOURNAL -> journalSections()

            PlannerFamily.WEEKLY_PLANNER,
            PlannerFamily.WEEKLY_SCHEDULE,
            PlannerFamily.STUDENT_PLANNER,
            PlannerFamily.TEACHER_PLANNER,
            PlannerFamily.FAMILY_ORGANIZER -> weeklySections()

            PlannerFamily.MONTHLY_PLANNER,
            PlannerFamily.YEARLY_PLANNER,
            PlannerFamily.APPOINTMENT_PLANNER -> calendarSections()

            PlannerFamily.WORK_PLANNER,
            PlannerFamily.PROJECT_PLANNER,
            PlannerFamily.TASK_BREAKDOWN_PLANNER,
            PlannerFamily.TASK_BATCHING_PLANNER,
            PlannerFamily.TASK_MANAGEMENT_GUIDE,
            PlannerFamily.WORK_LIFE_BALANCE,
            PlannerFamily.TIME_BLOCKING_PLANNER,
            PlannerFamily.MEETING_NOTES -> projectSections()

            PlannerFamily.BUDGET_PLANNER -> budgetSections()
            PlannerFamily.EXERCISE_PLANNER,
            PlannerFamily.WEIGHT_LOSS_PLANNER,
            PlannerFamily.HABIT_TRACKER,
            PlannerFamily.SELF_CARE_PLANNER,
            PlannerFamily.ROUTINE_PLANNER -> trackerSections()

            PlannerFamily.GROCERY_PLANNER,
            PlannerFamily.CLEANING_PLANNER,
            PlannerFamily.CHORES_PLANNER,
            PlannerFamily.TRAVEL_PLANNER,
            PlannerFamily.READING_LOG,
            PlannerFamily.GOAL_PLANNER,
            PlannerFamily.MEAL_PLANNER -> lifestyleSections(template.family)
        }

        return PlannerTemplateDefinition(
            id = template.id,
            name = template.title,
            family = template.family,
            category = template.categoryId,
            layoutKind = template.layoutKind.name,
            editorType = archetype.first,
            capabilities = archetype.second,
            sections = archetype.third,
        )
    }

    private fun exactStructuredTemplate(
        template: PlannerTemplate,
    ): PlannerTemplateDefinition? = when (template.id) {
        "notes_page" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.FLEXIBLE_PAGE,
            capabilities = setOf(PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Notes",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A clean writing page based on the lined notes sheet from the screenshots.",
                    chips = listOf("Notes", "Lined Page"),
                    decoration = PlannerDecorationDefinition("Write Freely", PlannerDecorationStyle.CORNER_GLOW),
                ),
                PlannerSectionDefinition(
                    id = "lined_notes",
                    title = "Lined Notes",
                    kind = PlannerSectionKind.LINED_NOTES,
                    fields = listOf(PlannerFieldDefinition("date", "Date")),
                    cellLines = 16,
                ),
            ),
        )

        "shopping" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.FLEXIBLE_PAGE,
            capabilities = setOf(PlannerCapability.CHECKLISTS, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Shopping List",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A department-based shopping board with editable category blocks.",
                    chips = listOf("Shopping", "Departments"),
                ),
                PlannerSectionDefinition(
                    id = "shopping_grid",
                    title = "Departments",
                    kind = PlannerSectionKind.CATEGORY_GRID,
                    rowLabels = listOf(
                        "Fruit & Veg",
                        "Meat & Fish",
                        "Dairy",
                        "Pantry",
                        "Frozen",
                        "Toiletries",
                        "Beverages",
                        "Bakery",
                        "Other",
                    ),
                    columns = 3,
                    checkable = false,
                    cellLines = 4,
                ),
            ),
        )

        "shopping_todo" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.FLEXIBLE_PAGE,
            capabilities = setOf(PlannerCapability.CHECKLISTS),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Shopping To Do List",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A larger shopping board with many store sections and checklist rows.",
                    chips = listOf("Shopping", "Checklist"),
                ),
                PlannerSectionDefinition(
                    id = "week",
                    title = "Trip Header",
                    kind = PlannerSectionKind.DATE_ROW,
                    fields = listOf(
                        PlannerFieldDefinition("week", "Week Of"),
                        PlannerFieldDefinition("store", "Store / Plan"),
                    ),
                ),
                PlannerSectionDefinition(
                    id = "shopping_todo_grid",
                    title = "Store Sections",
                    kind = PlannerSectionKind.CATEGORY_GRID,
                    rowLabels = listOf(
                        "Groceries",
                        "Fashion & Accessories",
                        "Personal Care",
                        "Home Essentials",
                        "Electronics & Gadgets",
                        "Gift & Stationary",
                        "Fitness Gear",
                        "Kitchenware",
                        "Office & Study",
                        "Miscellaneous",
                    ),
                    columns = 2,
                    checkable = true,
                    cellLines = 5,
                ),
            ),
        )

        "grocery" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.FLEXIBLE_PAGE,
            capabilities = setOf(PlannerCapability.CHECKLISTS),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Grocery Checklist",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A categorized grocery sheet with editable check items.",
                    chips = listOf("Groceries", "Checklist"),
                ),
                PlannerSectionDefinition(
                    id = "grocery_categories",
                    title = "Categories",
                    kind = PlannerSectionKind.CATEGORY_GRID,
                    rowLabels = listOf(
                        "Produce",
                        "Breads & Grains",
                        "Meats & Seafood",
                        "Frozen Food",
                        "Condiments & Cans",
                        "Deli",
                        "Beverages",
                        "Spices & Baking",
                        "Dairy Protein",
                    ),
                    columns = 3,
                    checkable = true,
                    cellLines = 5,
                ),
            ),
        )

        "grocery_planner" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_WEEK,
            capabilities = setOf(PlannerCapability.CHECKLISTS, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Grocery Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A weekly grocery planning sheet with daily prompts and a running list.",
                    chips = listOf("Weekly", "Groceries"),
                ),
                PlannerSectionDefinition(
                    id = "grocery_board",
                    title = "Week Plan",
                    kind = PlannerSectionKind.WEEK_LIST_WITH_SIDE_CHECKLIST,
                    rowLabels = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"),
                    fields = listOf(
                        PlannerFieldDefinition("week", "Week Of"),
                        PlannerFieldDefinition("list", "Grocery List"),
                    ),
                    checkable = true,
                    cellLines = 6,
                ),
            ),
        )

        "cleaning" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.FLEXIBLE_PAGE,
            capabilities = setOf(PlannerCapability.CHECKLISTS),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Daily Cleaning List",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Room-based chores laid out as an editable checklist grid.",
                    chips = listOf("Cleaning", "Rooms"),
                ),
                PlannerSectionDefinition(
                    id = "date",
                    title = "Cleaning Day",
                    kind = PlannerSectionKind.DATE_ROW,
                    fields = listOf(
                        PlannerFieldDefinition("date", "Date"),
                        PlannerFieldDefinition("focus", "Main Focus"),
                    ),
                ),
                PlannerSectionDefinition(
                    id = "rooms",
                    title = "Room Checklists",
                    kind = PlannerSectionKind.CATEGORY_GRID,
                    rowLabels = listOf(
                        "Kitchen",
                        "Living Room",
                        "Bedrooms",
                        "Dining Room",
                        "Bathrooms",
                        "Laundry Area",
                        "Outside",
                        "Grocery",
                        "Misc",
                    ),
                    columns = 3,
                    checkable = true,
                    cellLines = 5,
                ),
            ),
        )

        "task_batching" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.PROJECT,
            capabilities = setOf(PlannerCapability.CHECKLISTS, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Task Batching Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Four editable batching categories modeled after the screenshot layout.",
                    chips = listOf("Tasks", "Batching"),
                ),
                PlannerSectionDefinition(
                    id = "date",
                    title = "Batch Header",
                    kind = PlannerSectionKind.DATE_ROW,
                    fields = listOf(
                        PlannerFieldDefinition("date", "Date"),
                        PlannerFieldDefinition("theme", "Theme"),
                    ),
                ),
                PlannerSectionDefinition(
                    id = "batching_grid",
                    title = "Batch Categories",
                    kind = PlannerSectionKind.QUADRANT_CHECKLIST,
                    rowLabels = listOf("Category 1", "Category 2", "Category 3", "Category 4"),
                    checkable = true,
                    cellLines = 8,
                ),
            ),
        )

        "task_breakdown" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.PROJECT,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.CHECKLISTS),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Task Breakdown Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A structured breakdown sheet with identification, planning, and conclusion blocks.",
                    chips = listOf("Breakdown", "Task Plan"),
                ),
                PlannerSectionDefinition(
                    id = "header",
                    title = "Task Header",
                    kind = PlannerSectionKind.DATE_ROW,
                    fields = listOf(
                        PlannerFieldDefinition("name", "Name"),
                        PlannerFieldDefinition("date", "Date"),
                    ),
                ),
                PlannerSectionDefinition(
                    id = "breakdown",
                    title = "Break It Down",
                    kind = PlannerSectionKind.TASK_BREAKDOWN_BOARD,
                    rowLabels = listOf("Task Identification", "Prioritization", "Estimation", "Task Assignment", "Conclusion"),
                ),
            ),
        )

        "meeting" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.PROJECT,
            capabilities = setOf(PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Meeting Note-Taking",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Meeting metadata followed by repeated topic note blocks.",
                    chips = listOf("Meeting", "Notes"),
                ),
                PlannerSectionDefinition(
                    id = "meeting_board",
                    title = "Meeting Notes",
                    kind = PlannerSectionKind.MEETING_NOTES_BOARD,
                    fields = listOf(
                        PlannerFieldDefinition("date", "Date"),
                        PlannerFieldDefinition("start", "Start Time"),
                        PlannerFieldDefinition("end", "End Time"),
                        PlannerFieldDefinition("purpose", "Purpose of Meeting"),
                        PlannerFieldDefinition("attendees", "Attendees"),
                        PlannerFieldDefinition("agenda", "Agenda & Topics"),
                    ),
                    rowLabels = listOf("Topic 1", "Topic 2"),
                ),
            ),
        )

        "goals_tracker" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.PROJECT,
            capabilities = setOf(PlannerCapability.CHECKLISTS, PlannerCapability.TRACKING, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Goals Tracker",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A top-goals worksheet with steps and rewards inspired by the screenshots.",
                    chips = listOf("Goals", "Tracker"),
                ),
                PlannerSectionDefinition(
                    id = "tracker",
                    title = "Goal Actions",
                    kind = PlannerSectionKind.GOALS_TRACKER_BOARD,
                    fields = listOf(PlannerFieldDefinition("month", "Month")),
                    rowLabels = listOf("My Top Five Goals", "To-Do List"),
                    columnLabels = listOf("Step 1", "Step 2", "Step 3", "Step 4", "Step 5"),
                    stats = listOf("Reward 1", "Reward 2", "Reward 3", "Reward 4"),
                ),
            ),
        )

        "daily_classic" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_DAY,
            capabilities = setOf(
                PlannerCapability.CHECKLISTS,
                PlannerCapability.SCHEDULE,
                PlannerCapability.NOTES,
                PlannerCapability.WATER,
                PlannerCapability.MOOD,
            ),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Daily Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Weather, priorities, schedule, money, water, and mood in one daily sheet.",
                    chips = listOf("Daily", "Classic"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Plan the Day",
                    kind = PlannerSectionKind.DAILY_CLASSIC_BOARD,
                ),
            ),
        )

        "daily_agenda" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_DAY,
            capabilities = setOf(
                PlannerCapability.CHECKLISTS,
                PlannerCapability.SCHEDULE,
                PlannerCapability.MEALS,
                PlannerCapability.WATER,
            ),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Today's Agenda",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Top priorities, agenda, to-do list, meals, and hydration.",
                    chips = listOf("Agenda", "Daily"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Agenda Layout",
                    kind = PlannerSectionKind.DAILY_AGENDA_BOARD,
                ),
            ),
        )

        "daily_focus" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_DAY,
            capabilities = setOf(
                PlannerCapability.CHECKLISTS,
                PlannerCapability.SCHEDULE,
                PlannerCapability.NOTES,
                PlannerCapability.TRACKING,
            ),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "The Productive Day Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Goal, main tasks, appointments, time trackers, and must-do buckets.",
                    chips = listOf("Productive", "Focus"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Productive Day",
                    kind = PlannerSectionKind.PRODUCTIVE_DAY_BOARD,
                ),
            ),
        )

        "daily_task" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_DAY,
            capabilities = setOf(PlannerCapability.CHECKLISTS, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Daily Task Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Task list, urgent box, priorities, expenses, and notes.",
                    chips = listOf("Tasks", "Daily"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Task Layout",
                    kind = PlannerSectionKind.DAILY_TASK_BOARD,
                ),
            ),
        )

        "daily_work" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_DAY,
            capabilities = setOf(PlannerCapability.CHECKLISTS, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Daily Work Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Priorities, to-do, meetings, reminders, and project notes.",
                    chips = listOf("Work", "Daily"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Work Day",
                    kind = PlannerSectionKind.DAILY_WORK_BOARD,
                ),
            ),
        )

        "hourly_day" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_DAY,
            capabilities = setOf(PlannerCapability.SCHEDULE),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Full-Day Hourly Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A long full-day timeline matching the hourly planner screenshot.",
                    chips = listOf("Hourly", "Timeline"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Hourly Schedule",
                    kind = PlannerSectionKind.FULL_DAY_HOURLY_BOARD,
                ),
            ),
        )

        "goal" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_DAY,
            capabilities = setOf(PlannerCapability.CHECKLISTS, PlannerCapability.SCHEDULE, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Daily Goal Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Today's goal, goal breakdown, to-do list, schedule, and notes.",
                    chips = listOf("Goal", "Daily"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Goal Layout",
                    kind = PlannerSectionKind.DAILY_GOAL_BOARD,
                ),
            ),
        )

        "routine" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_DAY,
            capabilities = setOf(PlannerCapability.CHECKLISTS, PlannerCapability.SCHEDULE),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Routine Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Morning, afternoon, and evening blocks with priorities and extras.",
                    chips = listOf("Routine", "Daily"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Routine Sections",
                    kind = PlannerSectionKind.ROUTINE_BOARD,
                ),
            ),
        )

        "work_tasks" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_DAY,
            capabilities = setOf(PlannerCapability.CHECKLISTS),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Work Tasks To Do List",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Must do, do if time, projects, meetings, morning, and afternoon task groups.",
                    chips = listOf("Work", "Buckets"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Task Buckets",
                    kind = PlannerSectionKind.WORK_TASKS_BOARD,
                ),
            ),
        )

        "task_management" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.PROJECT,
            capabilities = setOf(PlannerCapability.CHECKLISTS, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Daily Task Management Guide",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Important tasks with do first, plan, delegate, and eliminate quadrants.",
                    chips = listOf("Daily", "Matrix"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Task Management",
                    kind = PlannerSectionKind.TASK_MANAGEMENT_BOARD,
                ),
            ),
        )

        "adhd" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_DAY,
            capabilities = setOf(
                PlannerCapability.CHECKLISTS,
                PlannerCapability.SCHEDULE,
                PlannerCapability.NOTES,
                PlannerCapability.MOOD,
            ),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "ADHD Daily Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Focus, self-care reminders, do now/later/delegate, routines, and brain dump.",
                    chips = listOf("ADHD", "Support"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "ADHD Support Layout",
                    kind = PlannerSectionKind.ADHD_DAILY_BOARD,
                ),
            ),
        )

        "daily_exercise" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.TRACKER,
            capabilities = setOf(
                PlannerCapability.TRACKING,
                PlannerCapability.WATER,
                PlannerCapability.NOTES,
            ),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Daily Exercise Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Food, hydration, vitamins, sleep, exercise focus, and workout log.",
                    chips = listOf("Exercise", "Daily"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Exercise Board",
                    kind = PlannerSectionKind.EXERCISE_DAILY_BOARD,
                ),
            ),
        )

        "self_care" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.TRACKER,
            capabilities = setOf(
                PlannerCapability.CHECKLISTS,
                PlannerCapability.WATER,
                PlannerCapability.MOOD,
                PlannerCapability.TRACKING,
            ),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Self Care Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Checklist, workout, sleep, water, and mood laid out like the screenshot.",
                    chips = listOf("Self Care", "Daily"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Self Care Board",
                    kind = PlannerSectionKind.SELF_CARE_DAILY_BOARD,
                ),
            ),
        )

        "reflection" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.JOURNAL,
            capabilities = setOf(PlannerCapability.REFLECTION, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Daily Reflection",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Gratitude, affirmation, review, tomorrow planning, and checklist actions.",
                    chips = listOf("Reflection", "Daily"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Reflection Board",
                    kind = PlannerSectionKind.DAILY_REFLECTION_BOARD,
                ),
            ),
        )

        "reflection_journal" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.JOURNAL,
            capabilities = setOf(PlannerCapability.REFLECTION, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Daily Reflection Journal",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Mindset and growth check-ins with coaching prompts.",
                    chips = listOf("Reflection", "Coaching"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Coaching Reflection",
                    kind = PlannerSectionKind.DAILY_REFLECTION_JOURNAL_BOARD,
                ),
            ),
        )

        "devotional" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.JOURNAL,
            capabilities = setOf(PlannerCapability.REFLECTION, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Daily Devotional Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Prayer list, scripture, observation, application, and notes.",
                    chips = listOf("Devotional", "Daily"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Devotional Layout",
                    kind = PlannerSectionKind.DAILY_DEVOTIONAL_BOARD,
                ),
            ),
        )

        "manifest" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.JOURNAL,
            capabilities = setOf(PlannerCapability.REFLECTION, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Daily Manifest Journal",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Journaling, gratitude, affirmation, and visualization prompts.",
                    chips = listOf("Manifest", "Journal"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Manifest Layout",
                    kind = PlannerSectionKind.DAILY_MANIFEST_BOARD,
                ),
            ),
        )

        "brain_dump" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_DAY,
            capabilities = setOf(PlannerCapability.CHECKLISTS, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Daily Brain Dump",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "To-do list, priorities, big goals, and what can wait.",
                    chips = listOf("Brain Dump", "Daily"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Brain Dump Layout",
                    kind = PlannerSectionKind.DAILY_BRAIN_DUMP_BOARD,
                ),
            ),
        )

        else -> null
    }

    private fun structuredDefinition(
        template: PlannerTemplate,
        editorType: PlannerEditorKind,
        capabilities: Set<PlannerCapability>,
        sections: List<PlannerSectionDefinition>,
    ) = PlannerTemplateDefinition(
        id = template.id,
        name = template.title,
        family = template.family,
        category = template.categoryId,
        layoutKind = template.layoutKind.name,
        editorType = editorType,
        capabilities = capabilities,
        sections = sections,
    )

    private fun blankPageSections() = Triple(
        PlannerEditorKind.FLEXIBLE_PAGE,
        setOf(PlannerCapability.NOTES),
        listOf(
            PlannerSectionDefinition(
                id = "title",
                title = "Page Identity",
                kind = PlannerSectionKind.TITLE_BLOCK,
                subtitle = "Start with a clean page and shape it however you want.",
                chips = listOf("Blank", "Flexible"),
                decoration = PlannerDecorationDefinition("Canvas", PlannerDecorationStyle.CORNER_GLOW),
            ),
            PlannerSectionDefinition(
                id = "date",
                title = "Page Setup",
                kind = PlannerSectionKind.DATE_ROW,
                fields = listOf(
                    PlannerFieldDefinition("date", "Date"),
                    PlannerFieldDefinition("focus", "Main Focus"),
                ),
            ),
            PlannerSectionDefinition(
                id = "notes",
                title = "Open Writing Space",
                kind = PlannerSectionKind.NOTES,
                subtitle = "Use this page for ideas, plans, or freeform notes.",
            ),
        ),
    )

    private fun bookSections() = Triple(
        PlannerEditorKind.NOTEBOOK,
        setOf(PlannerCapability.NOTES, PlannerCapability.CHECKLISTS),
        listOf(
            PlannerSectionDefinition(
                id = "title",
                title = "Notebook Cover",
                kind = PlannerSectionKind.TITLE_BLOCK,
                subtitle = "A reusable book-style shell for collecting multiple planning pages.",
                chips = listOf("Book", "Notebook"),
                decoration = PlannerDecorationDefinition("Volume 01", PlannerDecorationStyle.RIBBON),
            ),
            PlannerSectionDefinition(
                id = "overview",
                title = "Book Overview",
                kind = PlannerSectionKind.EDITABLE_TEXT,
                fields = listOf(
                    PlannerFieldDefinition("name", "Planner Name"),
                    PlannerFieldDefinition("owner", "Owner"),
                ),
            ),
            PlannerSectionDefinition(
                id = "index",
                title = "Starter Index",
                kind = PlannerSectionKind.CHECKLIST,
                rowLabels = listOf("Goals", "Plans", "Reflections", "Ideas", "Notes"),
            ),
            PlannerSectionDefinition(
                id = "notes",
                title = "Opening Notes",
                kind = PlannerSectionKind.NOTES,
            ),
        ),
    )

    private fun dailySections() = Triple(
        PlannerEditorKind.STRUCTURED_DAY,
        setOf(
            PlannerCapability.CHECKLISTS,
            PlannerCapability.SCHEDULE,
            PlannerCapability.NOTES,
            PlannerCapability.WATER,
        ),
        listOf(
            PlannerSectionDefinition(
                id = "title",
                title = "Daily Focus",
                kind = PlannerSectionKind.TITLE_BLOCK,
                subtitle = "A structured daily page inspired by the planner screenshots.",
                chips = listOf("Daily", "Editable", "Focus"),
                decoration = PlannerDecorationDefinition("Today", PlannerDecorationStyle.HIGHLIGHT_BAND),
            ),
            PlannerSectionDefinition(
                id = "date",
                title = "Date & Intent",
                kind = PlannerSectionKind.DATE_ROW,
                fields = listOf(
                    PlannerFieldDefinition("date", "Date"),
                    PlannerFieldDefinition("theme", "Top Intent"),
                ),
            ),
            PlannerSectionDefinition(
                id = "priorities",
                title = "Top Priorities",
                kind = PlannerSectionKind.CHECKLIST,
                rowLabels = listOf("Priority 1", "Priority 2", "Priority 3", "Quick Win"),
            ),
            PlannerSectionDefinition(
                id = "schedule",
                title = "Plan the Day",
                kind = PlannerSectionKind.HOURLY_SCHEDULE,
                rowLabels = listOf("7 AM", "9 AM", "11 AM", "1 PM", "3 PM", "5 PM", "7 PM"),
            ),
            PlannerSectionDefinition(
                id = "water",
                title = "Hydration",
                kind = PlannerSectionKind.WATER_TRACKER,
            ),
            PlannerSectionDefinition(
                id = "notes",
                title = "Notes & Follow-ups",
                kind = PlannerSectionKind.NOTES,
            ),
        ),
    )

    private fun journalSections() = Triple(
        PlannerEditorKind.JOURNAL,
        setOf(
            PlannerCapability.NOTES,
            PlannerCapability.REFLECTION,
            PlannerCapability.MOOD,
        ),
        listOf(
            PlannerSectionDefinition(
                id = "title",
                title = "Journal Entry",
                kind = PlannerSectionKind.TITLE_BLOCK,
                subtitle = "A reflective page with prompts, notes, and room to write.",
                chips = listOf("Journal", "Reflection"),
                decoration = PlannerDecorationDefinition("Mindset", PlannerDecorationStyle.CORNER_GLOW),
            ),
            PlannerSectionDefinition(
                id = "date",
                title = "Entry Header",
                kind = PlannerSectionKind.DATE_ROW,
                fields = listOf(
                    PlannerFieldDefinition("date", "Date"),
                    PlannerFieldDefinition("topic", "Theme"),
                ),
            ),
            PlannerSectionDefinition(
                id = "mood",
                title = "Mood Check",
                kind = PlannerSectionKind.MOOD_SELECTOR,
                chips = listOf("Calm", "Focused", "Tired", "Grateful", "Excited"),
            ),
            PlannerSectionDefinition(
                id = "prompts",
                title = "Journal Prompts",
                kind = PlannerSectionKind.JOURNAL_PROMPT,
                prompts = listOf(
                    "What feels most important today?",
                    "What did I learn or notice?",
                    "What do I want to carry forward?",
                ),
            ),
            PlannerSectionDefinition(
                id = "notes",
                title = "Free Writing",
                kind = PlannerSectionKind.NOTES,
            ),
        ),
    )

    private fun weeklySections() = Triple(
        PlannerEditorKind.STRUCTURED_WEEK,
        setOf(
            PlannerCapability.SCHEDULE,
            PlannerCapability.NOTES,
            PlannerCapability.HABITS,
        ),
        listOf(
            PlannerSectionDefinition(
                id = "title",
                title = "Weekly Plan",
                kind = PlannerSectionKind.TITLE_BLOCK,
                subtitle = "A week-at-a-glance structure with editable daily columns.",
                chips = listOf("Weekly", "Week View"),
                decoration = PlannerDecorationDefinition("Week", PlannerDecorationStyle.RIBBON),
            ),
            PlannerSectionDefinition(
                id = "date",
                title = "Week Setup",
                kind = PlannerSectionKind.DATE_ROW,
                fields = listOf(
                    PlannerFieldDefinition("week", "Week Of"),
                    PlannerFieldDefinition("goal", "Main Goal"),
                ),
            ),
            PlannerSectionDefinition(
                id = "days",
                title = "Day Columns",
                kind = PlannerSectionKind.DAY_COLUMNS,
                columnLabels = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
            ),
            PlannerSectionDefinition(
                id = "habits",
                title = "Weekly Habits",
                kind = PlannerSectionKind.HABIT_TRACKER,
                rowLabels = listOf("Sleep", "Workout", "Reading", "No Spend"),
                columnLabels = listOf("M", "T", "W", "T", "F", "S", "S"),
            ),
            PlannerSectionDefinition(
                id = "notes",
                title = "Weekly Notes",
                kind = PlannerSectionKind.NOTES,
            ),
        ),
    )

    private fun calendarSections() = Triple(
        PlannerEditorKind.CALENDAR,
        setOf(
            PlannerCapability.CALENDAR,
            PlannerCapability.NOTES,
        ),
        listOf(
            PlannerSectionDefinition(
                id = "title",
                title = "Calendar View",
                kind = PlannerSectionKind.TITLE_BLOCK,
                subtitle = "A grid-based planner page for monthly and yearly planning.",
                chips = listOf("Calendar", "Overview"),
                decoration = PlannerDecorationDefinition("At a Glance", PlannerDecorationStyle.HIGHLIGHT_BAND),
            ),
            PlannerSectionDefinition(
                id = "date",
                title = "Calendar Header",
                kind = PlannerSectionKind.DATE_ROW,
                fields = listOf(
                    PlannerFieldDefinition("period", "Month / Year"),
                    PlannerFieldDefinition("focus", "Highlight"),
                ),
            ),
            PlannerSectionDefinition(
                id = "grid",
                title = "Calendar Grid",
                kind = PlannerSectionKind.MONTH_GRID,
                columnLabels = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"),
            ),
            PlannerSectionDefinition(
                id = "notes",
                title = "Important Dates",
                kind = PlannerSectionKind.NOTES,
            ),
        ),
    )

    private fun projectSections() = Triple(
        PlannerEditorKind.PROJECT,
        setOf(
            PlannerCapability.CHECKLISTS,
            PlannerCapability.SCHEDULE,
            PlannerCapability.NOTES,
            PlannerCapability.TRACKING,
        ),
        listOf(
            PlannerSectionDefinition(
                id = "title",
                title = "Project Workspace",
                kind = PlannerSectionKind.TITLE_BLOCK,
                subtitle = "A modular planning layout for work, meetings, and task systems.",
                chips = listOf("Project", "Work"),
                decoration = PlannerDecorationDefinition("Action Plan", PlannerDecorationStyle.CORNER_GLOW),
            ),
            PlannerSectionDefinition(
                id = "overview",
                title = "Project Overview",
                kind = PlannerSectionKind.EDITABLE_TEXT,
                fields = listOf(
                    PlannerFieldDefinition("name", "Project Name"),
                    PlannerFieldDefinition("owner", "Owner / Team"),
                ),
            ),
            PlannerSectionDefinition(
                id = "timeline",
                title = "Milestones",
                kind = PlannerSectionKind.PROGRESS_TIMELINE,
                rowLabels = listOf("Kickoff", "Build", "Review", "Launch"),
            ),
            PlannerSectionDefinition(
                id = "tasks",
                title = "Action Checklist",
                kind = PlannerSectionKind.CHECKLIST,
                rowLabels = listOf("Task 1", "Task 2", "Task 3", "Task 4", "Task 5"),
            ),
            PlannerSectionDefinition(
                id = "notes",
                title = "Supporting Notes",
                kind = PlannerSectionKind.NOTES,
            ),
        ),
    )

    private fun budgetSections() = Triple(
        PlannerEditorKind.BUDGET,
        setOf(
            PlannerCapability.BUDGETING,
            PlannerCapability.NOTES,
            PlannerCapability.TRACKING,
        ),
        listOf(
            PlannerSectionDefinition(
                id = "title",
                title = "Budget Planner",
                kind = PlannerSectionKind.TITLE_BLOCK,
                subtitle = "Track plans, actuals, and notes in a reusable finance layout.",
                chips = listOf("Budget", "Finance"),
                decoration = PlannerDecorationDefinition("Money Map", PlannerDecorationStyle.RIBBON),
            ),
            PlannerSectionDefinition(
                id = "date",
                title = "Budget Header",
                kind = PlannerSectionKind.DATE_ROW,
                fields = listOf(
                    PlannerFieldDefinition("period", "Month"),
                    PlannerFieldDefinition("goal", "Savings Goal"),
                ),
            ),
            PlannerSectionDefinition(
                id = "stats",
                title = "Snapshot",
                kind = PlannerSectionKind.STATS,
                stats = listOf("Income", "Bills", "Savings", "Balance"),
            ),
            PlannerSectionDefinition(
                id = "grid",
                title = "Budget Grid",
                kind = PlannerSectionKind.BUDGET_GRID,
                rowLabels = listOf("Housing", "Food", "Transport", "Health", "Fun"),
                columnLabels = listOf("Planned", "Actual"),
            ),
            PlannerSectionDefinition(
                id = "notes",
                title = "Budget Notes",
                kind = PlannerSectionKind.NOTES,
            ),
        ),
    )

    private fun trackerSections() = Triple(
        PlannerEditorKind.TRACKER,
        setOf(
            PlannerCapability.TRACKING,
            PlannerCapability.WATER,
            PlannerCapability.HABITS,
            PlannerCapability.MOOD,
            PlannerCapability.NOTES,
        ),
        listOf(
            PlannerSectionDefinition(
                id = "title",
                title = "Tracker Layout",
                kind = PlannerSectionKind.TITLE_BLOCK,
                subtitle = "A reusable tracker page for wellness, routines, and habits.",
                chips = listOf("Tracker", "Self Care"),
                decoration = PlannerDecorationDefinition("Progress", PlannerDecorationStyle.HIGHLIGHT_BAND),
            ),
            PlannerSectionDefinition(
                id = "date",
                title = "Tracker Header",
                kind = PlannerSectionKind.DATE_ROW,
                fields = listOf(
                    PlannerFieldDefinition("period", "Period"),
                    PlannerFieldDefinition("focus", "Health Focus"),
                ),
            ),
            PlannerSectionDefinition(
                id = "stats",
                title = "Daily Stats",
                kind = PlannerSectionKind.STATS,
                stats = listOf("Sleep", "Energy", "Steps", "Focus"),
            ),
            PlannerSectionDefinition(
                id = "habits",
                title = "Habit Grid",
                kind = PlannerSectionKind.HABIT_TRACKER,
                rowLabels = listOf("Stretch", "Workout", "Journal", "Meditate"),
                columnLabels = listOf("M", "T", "W", "T", "F", "S", "S"),
            ),
            PlannerSectionDefinition(
                id = "water",
                title = "Water Tracker",
                kind = PlannerSectionKind.WATER_TRACKER,
            ),
            PlannerSectionDefinition(
                id = "mood",
                title = "Mood Selector",
                kind = PlannerSectionKind.MOOD_SELECTOR,
                chips = listOf("Low", "Okay", "Good", "Great"),
            ),
            PlannerSectionDefinition(
                id = "notes",
                title = "Tracker Notes",
                kind = PlannerSectionKind.NOTES,
            ),
        ),
    )

    private fun lifestyleSections(
        family: PlannerFamily,
    ) = when (family) {
        PlannerFamily.MEAL_PLANNER -> Triple(
            PlannerEditorKind.STRUCTURED_WEEK,
            setOf(PlannerCapability.MEALS, PlannerCapability.NOTES),
            listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Meal Plan",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A weekly meal planning structure with editable slots.",
                    chips = listOf("Meal", "Prep"),
                    decoration = PlannerDecorationDefinition("Kitchen Plan", PlannerDecorationStyle.CORNER_GLOW),
                ),
                PlannerSectionDefinition(
                    id = "date",
                    title = "Meal Week",
                    kind = PlannerSectionKind.DATE_ROW,
                    fields = listOf(
                        PlannerFieldDefinition("week", "Week Of"),
                        PlannerFieldDefinition("theme", "Meal Focus"),
                    ),
                ),
                PlannerSectionDefinition(
                    id = "meals",
                    title = "Meals",
                    kind = PlannerSectionKind.MEAL_PLANNER,
                    rowLabels = listOf("Breakfast", "Lunch", "Dinner", "Snack"),
                    columnLabels = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
                ),
                PlannerSectionDefinition(
                    id = "notes",
                    title = "Prep Notes",
                    kind = PlannerSectionKind.NOTES,
                ),
            ),
        )

        PlannerFamily.GOAL_PLANNER -> Triple(
            PlannerEditorKind.PROJECT,
            setOf(PlannerCapability.TRACKING, PlannerCapability.NOTES, PlannerCapability.REFLECTION),
            listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Goal Tracker",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Clarify one important goal and break it into action steps.",
                    chips = listOf("Goal", "Action"),
                    decoration = PlannerDecorationDefinition("Milestone", PlannerDecorationStyle.RIBBON),
                ),
                PlannerSectionDefinition(
                    id = "goal",
                    title = "Goal Statement",
                    kind = PlannerSectionKind.EDITABLE_TEXT,
                    fields = listOf(
                        PlannerFieldDefinition("goal", "Main Goal"),
                        PlannerFieldDefinition("deadline", "Deadline"),
                    ),
                ),
                PlannerSectionDefinition(
                    id = "steps",
                    title = "Goal Steps",
                    kind = PlannerSectionKind.CHECKLIST,
                    rowLabels = listOf("Step 1", "Step 2", "Step 3", "Step 4"),
                ),
                PlannerSectionDefinition(
                    id = "reflection",
                    title = "Reflection",
                    kind = PlannerSectionKind.REFLECTION_PROMPT,
                    prompts = listOf(
                        "Why does this goal matter?",
                        "What support do I need?",
                        "How will I measure progress?",
                    ),
                ),
            ),
        )

        else -> Triple(
            PlannerEditorKind.FLEXIBLE_PAGE,
            setOf(PlannerCapability.CHECKLISTS, PlannerCapability.NOTES),
            listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Lifestyle Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A flexible planning page for lists, logs, and practical tracking.",
                    chips = listOf("List", "Planner"),
                    decoration = PlannerDecorationDefinition("Organize", PlannerDecorationStyle.HIGHLIGHT_BAND),
                ),
                PlannerSectionDefinition(
                    id = "date",
                    title = "Header",
                    kind = PlannerSectionKind.DATE_ROW,
                    fields = listOf(
                        PlannerFieldDefinition("date", "Date"),
                        PlannerFieldDefinition("focus", "Category"),
                    ),
                ),
                PlannerSectionDefinition(
                    id = "list",
                    title = "Checklist",
                    kind = PlannerSectionKind.CHECKLIST,
                    rowLabels = listOf("Entry 1", "Entry 2", "Entry 3", "Entry 4", "Entry 5", "Entry 6"),
                ),
                PlannerSectionDefinition(
                    id = "notes",
                    title = "Notes",
                    kind = PlannerSectionKind.NOTES,
                ),
            ),
        )
    }
}
