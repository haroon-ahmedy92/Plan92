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
    WEEKLY_PLANNER_BOARD,
    WEEKLY_SCHEDULE_BOARD,
    WEEKLY_DASHBOARD_BOARD,
    WEEKLY_HABITS_BOARD,
    WEEKLY_GOALS_BOARD,
    WEEKLY_MEAL_BOARD,
    WEEKLY_PROJECTS_BOARD,
    WEEKLY_BULLET_BOARD,
    WORK_LIFE_BALANCE_BOARD,
    MONTHLY_PLANNER_BOARD,
    MONTHLY_APPOINTMENT_BOARD,
    MONTHLY_BUDGET_BOARD,
    MONTHLY_WEIGHT_LOSS_BOARD,
    YEARLY_PLANNER_BOARD,
    YEARLY_CALENDAR_BOARD,
    SEASONAL_YEARLY_BOARD,
    DAILY_JOURNAL_BOARD,
    MY_DAILY_JOURNAL_BOARD,
    FEELINGS_JOURNAL_BOARD,
    JOURNAL_PROMPTS_BOARD,
    SELF_CARE_JOURNAL_BOARD,
    READING_LOG_BOARD,
    SOAP_BIBLE_BOARD,
    FIND_BALANCE_JOURNAL_BOARD,
    BULLET_LIFE_JOURNAL_BOARD,
    DEAR_DIARY_BOARD,
    DAILY_BULLET_BOARD,
    PROJECT_PLANNER_BOARD,
    PROJECT_PROGRESS_BOARD,
    VACATION_BUDGET_BOARD,
    BILL_PAYMENT_BOARD,
    TRAVEL_PACKING_BOARD,
    FAMILY_ORGANIZER_BOARD,
    MOM_PLANNER_BOARD,
    MOM_CHORES_BOARD,
    CLASS_SCHEDULE_BOARD,
    STUDY_PLANNER_BOARD,
    TEACHER_PLANNER_BOARD,
    MEDICAL_NOTES_BOARD,
    NURSE_PLANNER_BOARD,
    DOCTOR_LIST_BOARD,
    EVENT_PLANNER_BOARD,
    OFFICE_ORGANIZER_BOARD,
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

        "daily_journal" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.JOURNAL,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.REFLECTION, PlannerCapability.MOOD),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Daily Journal",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A reflective daily writing page with priorities, prompts, and free writing.",
                    chips = listOf("Journal", "Daily", "Reflection"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Journal Layout",
                    kind = PlannerSectionKind.DAILY_JOURNAL_BOARD,
                ),
            ),
        )

        "my_daily_journal" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.JOURNAL,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.REFLECTION, PlannerCapability.MOOD),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "My Daily Journal",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A softer diary page with gratitude, affirmations, reflection, and writing space.",
                    chips = listOf("Journal", "Diary", "Daily"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Daily Diary Layout",
                    kind = PlannerSectionKind.MY_DAILY_JOURNAL_BOARD,
                ),
            ),
        )

        "feelings_journal" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.JOURNAL,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.REFLECTION, PlannerCapability.MOOD, PlannerCapability.WATER),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Feelings Journal",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "An emotional check-in journal with feelings, ratings, self-love, and reflection.",
                    chips = listOf("Feelings", "Journal", "Mood"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Feelings Layout",
                    kind = PlannerSectionKind.FEELINGS_JOURNAL_BOARD,
                ),
            ),
        )

        "journal_prompts" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.JOURNAL,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.REFLECTION),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Journal Prompts for Reflecting on Your Day",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A prompt-based journaling page with long-form writing space.",
                    chips = listOf("Prompts", "Reflection", "Journal"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Prompt Layout",
                    kind = PlannerSectionKind.JOURNAL_PROMPTS_BOARD,
                ),
            ),
        )

        "self_care_journal" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.JOURNAL,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.REFLECTION, PlannerCapability.MOOD),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Self-Care Journal",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A reflective self-care spread with weekly prompts and gentle check-ins.",
                    chips = listOf("Self Care", "Journal", "Reflection"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Self-Care Layout",
                    kind = PlannerSectionKind.SELF_CARE_JOURNAL_BOARD,
                ),
            ),
        )

        "reading_log" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.JOURNAL,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.REFLECTION, PlannerCapability.TRACKING),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Reading Log Journal",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Track what you're reading, favorite quotes, and reflections.",
                    chips = listOf("Reading", "Log", "Journal"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Reading Layout",
                    kind = PlannerSectionKind.READING_LOG_BOARD,
                ),
            ),
        )

        "soap_bible" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.JOURNAL,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.REFLECTION),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "SOAP Bible Study",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Scripture, observation, application, prayer, and notes in a guided study layout.",
                    chips = listOf("SOAP", "Bible", "Study"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "SOAP Layout",
                    kind = PlannerSectionKind.SOAP_BIBLE_BOARD,
                ),
            ),
        )

        "find_balance_journal" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.JOURNAL,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.REFLECTION, PlannerCapability.MOOD),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Find Your Balance Journal",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A balance-focused journal page with self-checks, resets, and room to write.",
                    chips = listOf("Balance", "Journal", "Growth"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Balance Layout",
                    kind = PlannerSectionKind.FIND_BALANCE_JOURNAL_BOARD,
                ),
            ),
        )

        "bullet_life_journal" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.JOURNAL,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.CHECKLISTS, PlannerCapability.TRACKING),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Organize Your Life with a Bullet Journal",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A guided bullet-journal spread with collections, key, migration, and open bullet notes.",
                    chips = listOf("Bullet", "Journal", "Collections"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Bullet Journal Layout",
                    kind = PlannerSectionKind.BULLET_LIFE_JOURNAL_BOARD,
                ),
            ),
        )

        "dear_diary" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.JOURNAL,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.REFLECTION, PlannerCapability.MOOD),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Daily Thoughts Dear Diary",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A decorative diary writing page with gentle prompts and long-form notes.",
                    chips = listOf("Diary", "Thoughts", "Writing"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Diary Layout",
                    kind = PlannerSectionKind.DEAR_DIARY_BOARD,
                ),
            ),
        )

        "daily_bullet" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.JOURNAL,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.CHECKLISTS),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Daily Bullet Journal",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A daily bullet spread with top tasks, quick notes, and open bullet writing space.",
                    chips = listOf("Bullet", "Daily", "Notes"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Daily Bullet Layout",
                    kind = PlannerSectionKind.DAILY_BULLET_BOARD,
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

        "weekly_planner" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_WEEK,
            capabilities = setOf(PlannerCapability.SCHEDULE, PlannerCapability.NOTES, PlannerCapability.HABITS),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Weekly Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A week-at-a-glance board with appointments, habits, daily plans, and notes.",
                    chips = listOf("Weekly", "Appointments", "Wins"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Weekly Layout",
                    kind = PlannerSectionKind.WEEKLY_PLANNER_BOARD,
                ),
            ),
        )

        "weekly_schedule" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_WEEK,
            capabilities = setOf(PlannerCapability.SCHEDULE, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Weekly Schedule Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A dense weekly schedule table inspired by the screenshot timetable layouts.",
                    chips = listOf("Weekly", "Schedule", "Timeline"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Week Timeline",
                    kind = PlannerSectionKind.WEEKLY_SCHEDULE_BOARD,
                ),
            ),
        )

        "weekly_dashboard" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_WEEK,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.TRACKING),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Weekly Dashboard",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Highlights, day goals, mood tracking, and notes in a dashboard-style spread.",
                    chips = listOf("Weekly", "Dashboard", "Highlights"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Dashboard Layout",
                    kind = PlannerSectionKind.WEEKLY_DASHBOARD_BOARD,
                ),
            ),
        )

        "weekly_goals" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_WEEK,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.SCHEDULE, PlannerCapability.TRACKING),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Weekly Goals Plan",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A goal-focused weekly spread with priorities, appointments, and notes.",
                    chips = listOf("Weekly", "Goals", "Priorities"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Goals Layout",
                    kind = PlannerSectionKind.WEEKLY_GOALS_BOARD,
                ),
            ),
        )

        "weekly_projects" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_WEEK,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.TRACKING),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Weekly Projects Work Journal",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Track goals, projects, activities, challenges, and next steps for the week.",
                    chips = listOf("Weekly", "Projects", "Work"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Project Journal Layout",
                    kind = PlannerSectionKind.WEEKLY_PROJECTS_BOARD,
                ),
            ),
        )

        "habit" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.TRACKER,
            capabilities = setOf(PlannerCapability.HABITS, PlannerCapability.NOTES, PlannerCapability.TRACKING),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Weekly Habits",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A weekly tracker page with goals, reminders, habits, and self-assessment.",
                    chips = listOf("Weekly", "Habits", "Tracker"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Habit Layout",
                    kind = PlannerSectionKind.WEEKLY_HABITS_BOARD,
                ),
            ),
        )

        "meal" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_WEEK,
            capabilities = setOf(PlannerCapability.MEALS, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Weekly Meal Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A weekly meal grid with grocery and prep sections.",
                    chips = listOf("Weekly", "Meals", "Prep"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Meal Layout",
                    kind = PlannerSectionKind.WEEKLY_MEAL_BOARD,
                ),
            ),
        )

        "bullet_weekly" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_WEEK,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.CHECKLISTS),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Organized Weekly Bullet Journal",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "An asymmetric weekly bullet spread with gratitude, favorites, and next-week blocks.",
                    chips = listOf("Weekly", "Bullet", "Journal"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Bullet Layout",
                    kind = PlannerSectionKind.WEEKLY_BULLET_BOARD,
                ),
            ),
        )

        "work_balance" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_WEEK,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.TRACKING),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Work Life Balance Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A weekly split planner for work obligations, personal reset, and notes.",
                    chips = listOf("Weekly", "Work", "Balance"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Balance Layout",
                    kind = PlannerSectionKind.WORK_LIFE_BALANCE_BOARD,
                ),
            ),
        )

        "monthly_planner" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.CALENDAR,
            capabilities = setOf(PlannerCapability.CALENDAR, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Monthly Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A clean month-at-a-glance calendar with editable cells, goals, and important dates.",
                    chips = listOf("Monthly", "Calendar", "Overview"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Month Layout",
                    kind = PlannerSectionKind.MONTHLY_PLANNER_BOARD,
                ),
            ),
        )

        "monthly_appointment",
        "appointments" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.CALENDAR,
            capabilities = setOf(PlannerCapability.CALENDAR, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Monthly Appointment Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A monthly appointment view with date-focused cells and follow-up areas.",
                    chips = listOf("Monthly", "Appointments", "Calendar"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Appointment Layout",
                    kind = PlannerSectionKind.MONTHLY_APPOINTMENT_BOARD,
                ),
            ),
        )

        "monthly_budget" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.BUDGET,
            capabilities = setOf(PlannerCapability.BUDGETING, PlannerCapability.NOTES, PlannerCapability.TRACKING),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Monthly Budget Overview",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A monthly finance spread with snapshot totals, category budgeting, and notes.",
                    chips = listOf("Monthly", "Budget", "Overview"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Budget Layout",
                    kind = PlannerSectionKind.MONTHLY_BUDGET_BOARD,
                ),
            ),
        )

        "weight_loss" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.TRACKER,
            capabilities = setOf(PlannerCapability.TRACKING, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Monthly Weight Loss Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A month-by-month progress tracker for goal, actual progress, and notes.",
                    chips = listOf("Monthly", "Progress", "Wellness"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Weight Loss Layout",
                    kind = PlannerSectionKind.MONTHLY_WEIGHT_LOSS_BOARD,
                ),
            ),
        )

        "yearly_planner" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.CALENDAR,
            capabilities = setOf(PlannerCapability.CALENDAR, PlannerCapability.NOTES, PlannerCapability.TRACKING),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "2026 Yearly Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A yearly planning spread with goals, highlights, and a month-by-month overview.",
                    chips = listOf("Yearly", "Goals", "Overview"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Year Layout",
                    kind = PlannerSectionKind.YEARLY_PLANNER_BOARD,
                ),
            ),
        )

        "yearly_calendar" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.CALENDAR,
            capabilities = setOf(PlannerCapability.CALENDAR, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "2026 Yearly Calendar Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A year-at-a-glance layout with editable monthly summary cards.",
                    chips = listOf("Yearly", "Calendar", "At a Glance"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Calendar Layout",
                    kind = PlannerSectionKind.YEARLY_CALENDAR_BOARD,
                ),
            ),
        )

        "seasonal_yearly" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.CALENDAR,
            capabilities = setOf(PlannerCapability.CALENDAR, PlannerCapability.NOTES, PlannerCapability.TRACKING),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Seasonal 2026 Yearly Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A seasonal yearly spread with quarterly focus cards and editable month summaries.",
                    chips = listOf("Yearly", "Seasonal", "Planner"),
                ),
                PlannerSectionDefinition(
                    id = "board",
                    title = "Seasonal Layout",
                    kind = PlannerSectionKind.SEASONAL_YEARLY_BOARD,
                ),
            ),
        )

        "project_planner" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.PROJECT,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.TRACKING, PlannerCapability.SCHEDULE),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Project Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A project form with objective, resources, dates, brainstorm, and progress fields.",
                    chips = listOf("Project", "Planner", "Workspace"),
                ),
                PlannerSectionDefinition(id = "board", title = "Project Layout", kind = PlannerSectionKind.PROJECT_PLANNER_BOARD),
            ),
        )

        "project_progress" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.PROJECT,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.TRACKING),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Project Progress Overview",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Track several active projects with priorities, status, and milestone notes.",
                    chips = listOf("Project", "Progress", "Overview"),
                ),
                PlannerSectionDefinition(id = "board", title = "Progress Layout", kind = PlannerSectionKind.PROJECT_PROGRESS_BOARD),
            ),
        )

        "vacation_budget" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.BUDGET,
            capabilities = setOf(PlannerCapability.BUDGETING, PlannerCapability.NOTES, PlannerCapability.TRACKING),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Vacation Budget Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Track trip categories, total budget, savings, and spending notes.",
                    chips = listOf("Travel", "Budget", "Vacation"),
                ),
                PlannerSectionDefinition(id = "board", title = "Vacation Budget Layout", kind = PlannerSectionKind.VACATION_BUDGET_BOARD),
            ),
        )

        "monthly_bill" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.BUDGET,
            capabilities = setOf(PlannerCapability.BUDGETING, PlannerCapability.NOTES, PlannerCapability.TRACKING),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Monthly Bill Payment Tracker",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A ledger-style bill tracker with due dates, paid dates, amounts, balance, and notes.",
                    chips = listOf("Bills", "Tracker", "Monthly"),
                ),
                PlannerSectionDefinition(id = "board", title = "Bill Tracker Layout", kind = PlannerSectionKind.BILL_PAYMENT_BOARD),
            ),
        )

        "travel" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.FLEXIBLE_PAGE,
            capabilities = setOf(PlannerCapability.CHECKLISTS, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Travel Packing List",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A categorized packing page for documents, clothes, toiletries, electronics, and notes.",
                    chips = listOf("Travel", "Packing", "Checklist"),
                ),
                PlannerSectionDefinition(id = "board", title = "Packing Layout", kind = PlannerSectionKind.TRAVEL_PACKING_BOARD),
            ),
        )

        "family" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_WEEK,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.SCHEDULE, PlannerCapability.MEALS),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Family Organizer Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A household weekly organizer for schedules, meals, and reminders.",
                    chips = listOf("Family", "Organizer", "Weekly"),
                ),
                PlannerSectionDefinition(id = "board", title = "Family Layout", kind = PlannerSectionKind.FAMILY_ORGANIZER_BOARD),
            ),
        )

        "mom_planner" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_DAY,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.TRACKING, PlannerCapability.MEALS, PlannerCapability.WATER, PlannerCapability.MOOD),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Mom Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A hybrid daily family planner with tasks, water, mood, meals, exercise, and savings.",
                    chips = listOf("Mom", "Daily", "Family"),
                ),
                PlannerSectionDefinition(id = "board", title = "Mom Layout", kind = PlannerSectionKind.MOM_PLANNER_BOARD),
            ),
        )

        "mom_chores" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_WEEK,
            capabilities = setOf(PlannerCapability.CHECKLISTS, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Mom Chores Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A weekly household spread with must-do and extra-time chores for each day.",
                    chips = listOf("Mom", "Chores", "Weekly"),
                ),
                PlannerSectionDefinition(id = "board", title = "Mom Chores Layout", kind = PlannerSectionKind.MOM_CHORES_BOARD),
            ),
        )

        "class_schedule" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_WEEK,
            capabilities = setOf(PlannerCapability.SCHEDULE, PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Class Schedule",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A weekly class timetable with editable periods and subjects.",
                    chips = listOf("Class", "Schedule", "School"),
                ),
                PlannerSectionDefinition(id = "board", title = "Class Layout", kind = PlannerSectionKind.CLASS_SCHEDULE_BOARD),
            ),
        )

        "student" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_WEEK,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.SCHEDULE, PlannerCapability.CHECKLISTS),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Study Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Track assignments, due dates, focus sessions, and study notes.",
                    chips = listOf("Study", "Planner", "Student"),
                ),
                PlannerSectionDefinition(id = "board", title = "Study Layout", kind = PlannerSectionKind.STUDY_PLANNER_BOARD),
            ),
        )

        "teacher" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_WEEK,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.SCHEDULE),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Teacher Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A weekly teaching planner for lesson plans, materials, and classroom notes.",
                    chips = listOf("Teacher", "Planner", "Weekly"),
                ),
                PlannerSectionDefinition(id = "board", title = "Teacher Layout", kind = PlannerSectionKind.TEACHER_PLANNER_BOARD),
            ),
        )

        "medical_notes" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.FLEXIBLE_PAGE,
            capabilities = setOf(PlannerCapability.NOTES),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Medical Notes",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Capture symptoms, medications, diagnosis, and follow-up notes.",
                    chips = listOf("Medical", "Notes", "Visit"),
                ),
                PlannerSectionDefinition(id = "board", title = "Medical Notes Layout", kind = PlannerSectionKind.MEDICAL_NOTES_BOARD),
            ),
        )

        "nurse_planner" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_DAY,
            capabilities = setOf(PlannerCapability.CHECKLISTS, PlannerCapability.NOTES, PlannerCapability.SCHEDULE),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Nurse Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A shift planner for medication tasks, patient checks, assessments, and hand-off notes.",
                    chips = listOf("Nurse", "Shift", "Planner"),
                ),
                PlannerSectionDefinition(id = "board", title = "Nurse Layout", kind = PlannerSectionKind.NURSE_PLANNER_BOARD),
            ),
        )

        "doctor_list" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.FLEXIBLE_PAGE,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.CALENDAR),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Doctors List Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Store doctor contacts, specialties, next visits, and notes.",
                    chips = listOf("Doctors", "Contacts", "Appointments"),
                ),
                PlannerSectionDefinition(id = "board", title = "Doctors Layout", kind = PlannerSectionKind.DOCTOR_LIST_BOARD),
            ),
        )

        "event_planner" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.PROJECT,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.CHECKLISTS, PlannerCapability.SCHEDULE, PlannerCapability.BUDGETING),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Event Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "Organize the event overview, checklist, vendors, timeline, and notes.",
                    chips = listOf("Event", "Planner", "Checklist"),
                ),
                PlannerSectionDefinition(id = "board", title = "Event Layout", kind = PlannerSectionKind.EVENT_PLANNER_BOARD),
            ),
        )

        "office_organizer" -> structuredDefinition(
            template = template,
            editorType = PlannerEditorKind.STRUCTURED_DAY,
            capabilities = setOf(PlannerCapability.NOTES, PlannerCapability.SCHEDULE, PlannerCapability.CHECKLISTS),
            sections = listOf(
                PlannerSectionDefinition(
                    id = "title",
                    title = "Office Organizer Planner",
                    kind = PlannerSectionKind.TITLE_BLOCK,
                    subtitle = "A workday layout with goals, calls, emails, meetings, and a time schedule.",
                    chips = listOf("Office", "Organizer", "Workday"),
                ),
                PlannerSectionDefinition(id = "board", title = "Office Layout", kind = PlannerSectionKind.OFFICE_ORGANIZER_BOARD),
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
