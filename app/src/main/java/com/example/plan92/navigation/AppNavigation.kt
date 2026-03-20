package com.example.plan92.navigation

enum class MainTab(
    val title: String,
    val route: String,
) {
    Planners(title = "Your Planners", route = "planners"),
    Calendar(title = "Your Calendar", route = "calendar"),
    Search(title = "Search", route = "search"),
    Templates(title = "Templates", route = "templates"),
}

object AppRoute {
    const val Splash = "splash"
    const val Shell = "shell"
    const val Settings = "settings"
    const val ReminderOnboarding = "reminder-onboarding"
    const val ReminderSetup = "reminder-setup"
    const val WidgetPromo = "widget-promo"
    const val PageStyle = "page-style"
    const val ImportPdfStub = "import-pdf-stub"
    const val PlannerDetailBase = "planner-detail"

    fun plannerDetail(templateId: String): String = "$PlannerDetailBase/$templateId"
}
