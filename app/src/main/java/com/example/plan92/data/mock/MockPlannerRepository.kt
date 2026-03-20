package com.example.plan92.data.mock

import com.example.plan92.ui.theme.ApricotGlow
import com.example.plan92.ui.theme.AzureDepth
import com.example.plan92.ui.theme.BrickRed
import com.example.plan92.ui.theme.BurntOrange
import com.example.plan92.ui.theme.CoralAccent
import com.example.plan92.ui.theme.LemonAccent
import com.example.plan92.ui.theme.MintAccent
import com.example.plan92.ui.theme.Amethyst
import com.example.plan92.ui.theme.NightShade
import com.example.plan92.ui.theme.OrchidBurst
import com.example.plan92.ui.theme.RoyalIndigo
import com.example.plan92.ui.theme.RoseAccent

object MockPlannerRepository {
    val categories = listOf(
        PlannerCategory("daily", "Daily Planners"),
        PlannerCategory("journal", "Daily Journal"),
        PlannerCategory("weekly", "Weekly Planners"),
        PlannerCategory("monthly", "Monthly Planners"),
        PlannerCategory("yearly", "Yearly Planners"),
        PlannerCategory("versatile", "Versatile Planners"),
        PlannerCategory("todo", "To Do List"),
        PlannerCategory("notes", "Notes"),
        PlannerCategory("diary", "Diary"),
        PlannerCategory("adhd", "ADHD Planners"),
        PlannerCategory("bullet", "Bullet Journal"),
        PlannerCategory("student", "Student Planners"),
        PlannerCategory("teacher", "Teacher Planners"),
        PlannerCategory("fitness", "Fitness"),
        PlannerCategory("selfcare", "Self Care"),
        PlannerCategory("work", "Work Planners"),
        PlannerCategory("budget", "Budget"),
    )

    val userPlanners = listOf(
        OwnedPlanner(
            id = "owned_productivity",
            title = "2026 All-in-One Productivity Planner",
            tagline = "Start your planning here",
            accent = AzureDepth,
            secondaryAccent = CoralAccent,
            templateId = "daily_classic",
        ),
        OwnedPlanner(
            id = "owned_balance",
            title = "Find Your Balance Journal",
            tagline = "Plan • Organize • Journal",
            accent = BrickRed,
            secondaryAccent = LemonAccent,
            templateId = "find_balance_journal",
        ),
        OwnedPlanner(
            id = "owned_bullet",
            title = "Organize Your Life with a Bullet Journal",
            tagline = "2026 Edition",
            accent = AzureDepth,
            secondaryAccent = ApricotGlow,
            templateId = "bullet_life_journal",
        ),
    )

    val templates = listOf(
        PlannerTemplate("blank_page", "Blank Page", "notes", "Start from a clean flexible page", PlannerFamily.BLANK_PAGE, NightShade, AzureDepth, PlannerLayoutKind.DailyJournal),
        PlannerTemplate("book_starter", "Book Planner", "versatile", "Notebook-style planner shell", PlannerFamily.BOOK_PLANNER, RoyalIndigo, Amethyst, PlannerLayoutKind.DailyJournal),
        PlannerTemplate("daily_classic", "Daily Planner", "daily", "Plans and schedules", PlannerFamily.DAILY_PLANNER, BurntOrange, AzureDepth, PlannerLayoutKind.DailyPlanner),
        PlannerTemplate("daily_focus", "Productive Day Planner", "daily", "Top priorities and schedule", PlannerFamily.PRODUCTIVITY_PLANNER, ApricotGlow, Amethyst, PlannerLayoutKind.DailyPlanner),
        PlannerTemplate("daily_agenda", "Daily Agenda", "daily", "Agenda, tasks, meals, water", PlannerFamily.DAILY_AGENDA, AzureDepth, BurntOrange, PlannerLayoutKind.DailyPlanner),
        PlannerTemplate("daily_task", "Daily Task Planner", "daily", "Urgent list, priorities, notes", PlannerFamily.DAILY_TASK_PLANNER, RoyalIndigo, ApricotGlow, PlannerLayoutKind.DailyPlanner),
        PlannerTemplate("daily_journal", "Daily Journal", "journal", "Thoughts, reflection, productivity", PlannerFamily.DAILY_JOURNAL, AzureDepth, RoyalIndigo, PlannerLayoutKind.DailyJournal),
        PlannerTemplate("my_daily_journal", "My Daily Journal", "diary", "A soft daily diary page with gratitude and reflection", PlannerFamily.DAILY_JOURNAL, ApricotGlow, RoyalIndigo, PlannerLayoutKind.DailyJournal),
        PlannerTemplate("feelings_journal", "Feelings Journal", "journal", "Self-love and gratitude", PlannerFamily.FEELINGS_JOURNAL, OrchidBurst, ApricotGlow, PlannerLayoutKind.FeelingsJournal),
        PlannerTemplate("journal_prompts", "Journal Prompts for Reflecting on Your Day", "journal", "Prompt-led reflection and writing", PlannerFamily.REFLECTION_JOURNAL, RoyalIndigo, ApricotGlow, PlannerLayoutKind.ReflectionJournal),
        PlannerTemplate("weekly_planner", "Weekly Planner", "weekly", "Appointments, habits, wins", PlannerFamily.WEEKLY_PLANNER, Amethyst, AzureDepth, PlannerLayoutKind.WeeklyPlanner),
        PlannerTemplate("weekly_schedule", "Weekly Schedule Planner", "weekly", "Full-week timeline", PlannerFamily.WEEKLY_SCHEDULE, AzureDepth, ApricotGlow, PlannerLayoutKind.WeeklyPlanner),
        PlannerTemplate("weekly_dashboard", "Weekly Dashboard", "weekly", "Highlights, goals, mood, and notes", PlannerFamily.WEEKLY_PLANNER, OrchidBurst, AzureDepth, PlannerLayoutKind.WeeklyPlanner),
        PlannerTemplate("weekly_goals", "Weekly Goals Plan", "weekly", "Goals, priorities, appointments, and notes", PlannerFamily.WEEKLY_PLANNER, BurntOrange, RoyalIndigo, PlannerLayoutKind.WeeklyPlanner),
        PlannerTemplate("weekly_projects", "Weekly Projects Work Journal", "weekly", "Weekly work goals, activities, and challenges", PlannerFamily.WEEKLY_PLANNER, AzureDepth, CoralAccent, PlannerLayoutKind.WeeklyPlanner),
        PlannerTemplate("monthly_planner", "Monthly Planner", "monthly", "Month-at-a-glance calendar", PlannerFamily.MONTHLY_PLANNER, AzureDepth, ApricotGlow, PlannerLayoutKind.MonthlyPlanner),
        PlannerTemplate("monthly_appointment", "Monthly Appointment Planner", "monthly", "Calendar overview", PlannerFamily.MONTHLY_PLANNER, AzureDepth, OrchidBurst, PlannerLayoutKind.MonthlyPlanner),
        PlannerTemplate("monthly_budget", "Monthly Budget Overview", "budget", "Income and savings", PlannerFamily.BUDGET_PLANNER, RoyalIndigo, ApricotGlow, PlannerLayoutKind.BudgetOverview),
        PlannerTemplate("vacation_budget", "Vacation Budget Planner", "budget", "Trip costs and savings", PlannerFamily.BUDGET_PLANNER, BrickRed, ApricotGlow, PlannerLayoutKind.BudgetOverview),
        PlannerTemplate("yearly_planner", "2026 Yearly Planner", "yearly", "Goals and yearly overview", PlannerFamily.YEARLY_PLANNER, CoralAccent, AzureDepth, PlannerLayoutKind.YearlyPlanner),
        PlannerTemplate("seasonal_yearly", "Seasonal 2026 Yearly Planner", "yearly", "Illustrated yearly view", PlannerFamily.YEARLY_PLANNER, ApricotGlow, AzureDepth, PlannerLayoutKind.YearlyPlanner),
        PlannerTemplate("yearly_calendar", "2026 Yearly Calendar Planner", "yearly", "Year at a glance", PlannerFamily.YEARLY_PLANNER, BurntOrange, RoyalIndigo, PlannerLayoutKind.YearlyPlanner),
        PlannerTemplate("daily_work", "Daily Work Planner", "work", "Meetings and deadlines", PlannerFamily.WORK_PLANNER, BrickRed, AzureDepth, PlannerLayoutKind.WorkPlanner),
        PlannerTemplate("work_tasks", "Work Tasks To Do List", "work", "Must do, if time, projects, meetings, morning and afternoon tasks", PlannerFamily.WORK_PLANNER, BurntOrange, ApricotGlow, PlannerLayoutKind.WorkPlanner),
        PlannerTemplate("work_balance", "Work Life Balance Planner", "work", "Focus, work wins, personal reset", PlannerFamily.WORK_LIFE_BALANCE, Amethyst, BurntOrange, PlannerLayoutKind.WorkPlanner),
        PlannerTemplate("hourly_day", "Full-Day Hourly Planner", "daily", "Structured day timeline", PlannerFamily.DAILY_PLANNER, ApricotGlow, BrickRed, PlannerLayoutKind.HourlyPlanner),
        PlannerTemplate("project_progress", "Project Progress Overview", "work", "Projects, priority, timeline", PlannerFamily.PROJECT_PLANNER, ApricotGlow, RoyalIndigo, PlannerLayoutKind.ProjectOverview),
        PlannerTemplate("task_breakdown", "Task Breakdown Planner", "work", "Break large work into pieces", PlannerFamily.TASK_BREAKDOWN_PLANNER, AzureDepth, BurntOrange, PlannerLayoutKind.ProjectOverview),
        PlannerTemplate("task_batching", "Task Batching Planner", "work", "Group similar work into blocks", PlannerFamily.TASK_BATCHING_PLANNER, Amethyst, AzureDepth, PlannerLayoutKind.ProjectOverview),
        PlannerTemplate("task_management", "Task Management Guide", "work", "Organize priorities and flow", PlannerFamily.TASK_MANAGEMENT_GUIDE, RoyalIndigo, ApricotGlow, PlannerLayoutKind.ProjectOverview),
        PlannerTemplate("daily_exercise", "Daily Exercise Planner", "fitness", "Hydration and calories", PlannerFamily.EXERCISE_PLANNER, AzureDepth, ApricotGlow, PlannerLayoutKind.ExercisePlanner),
        PlannerTemplate("weight_loss", "Monthly Weight Loss Planner", "fitness", "Progress by month", PlannerFamily.WEIGHT_LOSS_PLANNER, ApricotGlow, BrickRed, PlannerLayoutKind.WeightLossPlanner),
        PlannerTemplate("self_care", "Self Care Planner", "selfcare", "Mood, checklist, sleep", PlannerFamily.SELF_CARE_PLANNER, BurntOrange, OrchidBurst, PlannerLayoutKind.SelfCarePlanner),
        PlannerTemplate("self_care_journal", "Self-Care Journal", "selfcare", "Reflective care prompts by day", PlannerFamily.SELF_CARE_PLANNER, ApricotGlow, OrchidBurst, PlannerLayoutKind.SelfCarePlanner),
        PlannerTemplate("find_balance_journal", "Find Your Balance Journal", "selfcare", "Life-balance check-ins and journaling", PlannerFamily.SELF_CARE_PLANNER, BrickRed, LemonAccent, PlannerLayoutKind.SelfCarePlanner),
        PlannerTemplate("routine", "Routine Planner", "selfcare", "Morning, afternoon, evening", PlannerFamily.ROUTINE_PLANNER, AzureDepth, Amethyst, PlannerLayoutKind.SelfCarePlanner),
        PlannerTemplate("cleaning", "Daily Cleaning List", "selfcare", "Rooms and chores", PlannerFamily.CLEANING_PLANNER, ApricotGlow, AzureDepth, PlannerLayoutKind.CleaningPlanner),
        PlannerTemplate("grocery", "Grocery Checklist", "todo", "Categorized pantry and grocery checklist", PlannerFamily.GROCERY_PLANNER, AzureDepth, BurntOrange, PlannerLayoutKind.GroceryPlanner),
        PlannerTemplate("grocery_planner", "Grocery Planner", "todo", "Weekly groceries with day-by-day planning", PlannerFamily.GROCERY_PLANNER, ApricotGlow, AzureDepth, PlannerLayoutKind.GroceryPlanner),
        PlannerTemplate("shopping", "Shopping List", "todo", "Store sections and quick notes", PlannerFamily.GROCERY_PLANNER, ApricotGlow, RoyalIndigo, PlannerLayoutKind.GroceryPlanner),
        PlannerTemplate("shopping_todo", "Shopping To Do List", "todo", "Departments and checklist columns", PlannerFamily.GROCERY_PLANNER, BurntOrange, AzureDepth, PlannerLayoutKind.GroceryPlanner),
        PlannerTemplate("reading_log", "Reading Log Journal", "notes", "Books and notes", PlannerFamily.READING_LOG, RoyalIndigo, AzureDepth, PlannerLayoutKind.ReadingLog),
        PlannerTemplate("meal", "Weekly Meal Planner", "selfcare", "Weekly menu and prep", PlannerFamily.MEAL_PLANNER, ApricotGlow, AzureDepth, PlannerLayoutKind.MealPlanner),
        PlannerTemplate("goal", "Daily Goal Planner", "daily", "Goal, notes, wins", PlannerFamily.GOAL_PLANNER, OrchidBurst, ApricotGlow, PlannerLayoutKind.GoalPlanner),
        PlannerTemplate("goals_tracker", "Goals Tracker", "daily", "Track goals, steps, and rewards", PlannerFamily.GOAL_PLANNER, RoyalIndigo, ApricotGlow, PlannerLayoutKind.GoalPlanner),
        PlannerTemplate("reflection", "Daily Reflection", "journal", "Prompted reflection", PlannerFamily.REFLECTION_JOURNAL, BurntOrange, AzureDepth, PlannerLayoutKind.ReflectionJournal),
        PlannerTemplate("reflection_journal", "Daily Reflection Journal", "journal", "Coaching prompts and mindset check-ins", PlannerFamily.REFLECTION_JOURNAL, RoyalIndigo, ApricotGlow, PlannerLayoutKind.ReflectionJournal),
        PlannerTemplate("devotional", "Daily Devotional Planner", "journal", "Prayer list, scripture, observation, and application", PlannerFamily.SOAP_BIBLE_STUDY, BurntOrange, ApricotGlow, PlannerLayoutKind.ReflectionJournal),
        PlannerTemplate("manifest", "Daily Manifest Journal", "journal", "Journaling, gratitude, affirmation, and visualization", PlannerFamily.REFLECTION_JOURNAL, BrickRed, ApricotGlow, PlannerLayoutKind.ReflectionJournal),
        PlannerTemplate("soap_bible", "SOAP Bible Study", "journal", "Scripture, observation, application, prayer", PlannerFamily.SOAP_BIBLE_STUDY, RoyalIndigo, BurntOrange, PlannerLayoutKind.ReflectionJournal),
        PlannerTemplate("adhd", "ADHD Daily Planner", "adhd", "Focus and support blocks", PlannerFamily.ADHD_DAILY_PLANNER, Amethyst, BurntOrange, PlannerLayoutKind.AdhdPlanner),
        PlannerTemplate("brain_dump", "Daily Brain Dump", "daily", "To-do, top priorities, big goals, and what can wait", PlannerFamily.DAILY_TASK_PLANNER, AzureDepth, ApricotGlow, PlannerLayoutKind.DailyPlanner),
        PlannerTemplate("daily_bullet", "Daily Bullet Journal", "bullet", "Bullet-style writing spread", PlannerFamily.BULLET_JOURNAL, AzureDepth, OrchidBurst, PlannerLayoutKind.BulletJournal),
        PlannerTemplate("bullet_life_journal", "Organize Your Life with a Bullet Journal", "bullet", "Bullet journal setup and guided spread", PlannerFamily.BULLET_JOURNAL, RoyalIndigo, AzureDepth, PlannerLayoutKind.BulletJournal),
        PlannerTemplate("bullet_weekly", "Organized Weekly Bullet Journal", "bullet", "Weekly bullet layout", PlannerFamily.BULLET_JOURNAL, RoyalIndigo, OrchidBurst, PlannerLayoutKind.BulletJournal),
        PlannerTemplate("meeting", "Meeting Note-Taking", "notes", "Agenda and next steps", PlannerFamily.MEETING_NOTES, AzureDepth, ApricotGlow, PlannerLayoutKind.MeetingNotes),
        PlannerTemplate("notes_page", "Notes Page", "notes", "Open writing page", PlannerFamily.NOTES_PAGE, NightShade, Amethyst, PlannerLayoutKind.MeetingNotes),
        PlannerTemplate("dear_diary", "Daily Thoughts Dear Diary", "diary", "Decorative diary writing page", PlannerFamily.DAILY_JOURNAL, MintAccent, RoseAccent, PlannerLayoutKind.DailyJournal),
        PlannerTemplate("habit", "Weekly Habits", "versatile", "Streaks and routine", PlannerFamily.HABIT_TRACKER, Amethyst, AzureDepth, PlannerLayoutKind.HabitTracker),
        PlannerTemplate("travel", "Travel Packing List", "notes", "Trip essentials", PlannerFamily.TRAVEL_PLANNER, ApricotGlow, RoyalIndigo, PlannerLayoutKind.TravelPacking),
        PlannerTemplate("family", "Family Organizer Planner", "versatile", "Household overview", PlannerFamily.FAMILY_ORGANIZER, OrchidBurst, AzureDepth, PlannerLayoutKind.FamilyOrganizer),
        PlannerTemplate("student", "Study Planner", "student", "Assignments and focus", PlannerFamily.STUDENT_PLANNER, AzureDepth, Amethyst, PlannerLayoutKind.StudentPlanner),
        PlannerTemplate("teacher", "Teacher Planner", "teacher", "Class plans and notes", PlannerFamily.TEACHER_PLANNER, BurntOrange, RoyalIndigo, PlannerLayoutKind.TeacherPlanner),
        PlannerTemplate("appointments", "Appointment Planner", "monthly", "Dates and follow-ups", PlannerFamily.APPOINTMENT_PLANNER, Amethyst, ApricotGlow, PlannerLayoutKind.AppointmentPlanner),
        PlannerTemplate("time_block", "Time Blocking Planner", "work", "Deep work sessions", PlannerFamily.TIME_BLOCKING_PLANNER, AzureDepth, BurntOrange, PlannerLayoutKind.TimeBlocking),
        PlannerTemplate("chores", "Chores Planner", "todo", "Weekly home tasks", PlannerFamily.CHORES_PLANNER, ApricotGlow, Amethyst, PlannerLayoutKind.ChoresPlanner),
    )

    val popularTags = listOf(
        "2026 Yearly Planner",
        "February 2026 Planner",
        "Mom Planner",
        "Parenting Journal",
        "Grocery Checklist",
        "Class Schedule",
        "Study Planner",
        "Cleaning Checklist",
        "Class Notes",
        "Teacher Planner",
        "Medical Notes",
        "Nurse Planner",
        "Time Block",
        "Goal Setting Planner",
        "Notes Journal",
        "ADHD Planner",
        "Daily Notes",
        "Digital Diary",
        "Gardening Planner",
        "Productivity Planner",
        "Homework Planner",
        "Weight Loss Tracker",
        "Workout Planner",
        "Sleep Tracker",
        "Pregnancy Journal",
        "Notebook 2026",
        "Family Organizer",
        "Meal Planner",
        "Event Planner",
        "Quarterly Planner",
        "Meeting Notes",
        "Mindfulness Journal",
        "Gratitude Journal",
        "Journal for Self Care",
        "Wellness Journal",
        "Bullet Journal Templates",
        "Weekly Reflection",
        "Mood Tracker",
        "Therapy Journal",
        "Travel Journal",
        "Wedding Planner",
    )

    fun templateById(id: String): PlannerTemplate = templates.first { it.id == id }

    fun blankTemplate(): PlannerTemplate = templateById("blank_page")

    fun bookTemplate(): PlannerTemplate = templateById("book_starter")

    fun createOwnedPlanner(
        template: PlannerTemplate,
        ordinal: Int,
    ): OwnedPlanner = OwnedPlanner(
        id = "owned_${template.id}_$ordinal",
        title = template.title,
        tagline = template.subtitle,
        accent = template.accent,
        secondaryAccent = template.secondaryAccent,
        templateId = template.id,
    )
}
