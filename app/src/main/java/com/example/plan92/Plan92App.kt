package com.example.plan92

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.plan92.data.mock.MockPlannerRepository
import com.example.plan92.data.mock.OwnedPlanner
import com.example.plan92.navigation.AppRoute
import com.example.plan92.navigation.MainTab
import com.example.plan92.ui.components.Plan92BottomBar
import com.example.plan92.ui.components.Plan92TopBar
import com.example.plan92.ui.screens.calendar.CalendarScreen
import com.example.plan92.ui.screens.calendar.ReminderOnboardingScreen
import com.example.plan92.ui.screens.calendar.ReminderScheduleScreen
import com.example.plan92.ui.screens.create.CreateNewDialog
import com.example.plan92.ui.screens.create.ImportPdfPlaceholderScreen
import com.example.plan92.ui.screens.create.PageStyleScreen
import com.example.plan92.ui.screens.favorites.FavoritesDialog
import com.example.plan92.ui.screens.home.PlannersHomeScreen
import com.example.plan92.ui.screens.planner.PlannerDetailScreen
import com.example.plan92.ui.screens.search.SearchScreen
import com.example.plan92.ui.screens.settings.SettingsScreen
import com.example.plan92.ui.screens.splash.SplashScreen
import com.example.plan92.ui.screens.templates.TemplatesScreen
import com.example.plan92.ui.screens.templates.WidgetPromoDialog
import com.example.plan92.ui.theme.Plan92Theme
import com.example.plan92.ui.theme.ShellWhite

private enum class ShellModal {
    Favorites,
    Create,
    WidgetPromo,
}

@Composable
fun Plan92App() {
    val navController = rememberNavController()
    val ownedPlanners = remember { mutableStateListOf<OwnedPlanner>() }
    var createdPlannerCount by rememberSaveable { mutableIntStateOf(0) }
    val templates = remember { MockPlannerRepository.templates }

    fun createPlanner(
        templateId: String,
        customTitle: String? = null,
        customTagline: String? = null,
    ) {
        val template = MockPlannerRepository.templateById(templateId)
        createdPlannerCount += 1
        ownedPlanners.add(
            index = 0,
            element = MockPlannerRepository.createOwnedPlanner(
                template = template,
                ordinal = createdPlannerCount,
                customTitle = customTitle,
                customTagline = customTagline,
            ),
        )
    }

    Plan92Theme {
        NavHost(
            navController = navController,
            startDestination = AppRoute.Splash,
        ) {
            composable(AppRoute.Splash) {
                SplashScreen(
                    onFinished = {
                        navController.navigate(AppRoute.Shell) {
                            popUpTo(AppRoute.Splash) {
                                inclusive = true
                            }
                        }
                    },
                )
            }
            composable(AppRoute.Shell) {
                ShellScreen(
                    planners = ownedPlanners,
                    templates = templates,
                    onOpenSettings = { navController.navigate(AppRoute.Settings) },
                    onOpenReminderOnboarding = { navController.navigate(AppRoute.ReminderOnboarding) },
                    onCreatePlannerFromTemplate = { templateId -> createPlanner(templateId) },
                    onCreateBookPlanner = { title, tagline ->
                        createPlanner(
                            templateId = MockPlannerRepository.bookTemplate().id,
                            customTitle = title,
                            customTagline = tagline,
                        )
                    },
                    onOpenPlanner = { templateId ->
                        navController.navigate(AppRoute.plannerDetail(templateId))
                    },
                    onOpenWidgetPromo = { navController.navigate(AppRoute.WidgetPromo) },
                    onOpenImportPdf = { navController.navigate(AppRoute.ImportPdfStub) },
                    onOpenPageStyle = { navController.navigate(AppRoute.PageStyle) },
                )
            }
            composable(AppRoute.Settings) {
                SettingsScreen(
                    onBack = { navController.popBackStack() },
                    onOpenReminderSetup = { navController.navigate(AppRoute.ReminderSetup) },
                    onOpenWidgetPromo = { navController.navigate(AppRoute.WidgetPromo) },
                )
            }
            composable(AppRoute.ReminderOnboarding) {
                ReminderOnboardingScreen(
                    onBack = { navController.popBackStack() },
                    onContinue = { navController.navigate(AppRoute.ReminderSetup) },
                )
            }
            composable(AppRoute.ReminderSetup) {
                ReminderScheduleScreen(
                    onBack = { navController.popBackStack() },
                )
            }
            composable(AppRoute.WidgetPromo) {
                WidgetPromoDialog(
                    showAsScreen = true,
                    onDismiss = { navController.popBackStack() },
                )
            }
            composable(AppRoute.ImportPdfStub) {
                ImportPdfPlaceholderScreen(
                    onBack = { navController.popBackStack() },
                )
            }
            composable(AppRoute.PageStyle) {
                PageStyleScreen(
                    onBack = { navController.popBackStack() },
                    onBeginBlank = {
                        createPlanner(MockPlannerRepository.blankTemplate().id)
                        navController.navigate(AppRoute.plannerDetail(MockPlannerRepository.blankTemplate().id)) {
                            popUpTo(AppRoute.PageStyle) { inclusive = true }
                        }
                    },
                    onBeginLined = {
                        createPlanner("notes_page")
                        navController.navigate(AppRoute.plannerDetail("notes_page")) {
                            popUpTo(AppRoute.PageStyle) { inclusive = true }
                        }
                    },
                )
            }
            composable(
                route = "${AppRoute.PlannerDetailBase}/{templateId}",
                arguments = listOf(navArgument("templateId") { type = NavType.StringType }),
            ) { backStackEntry ->
                val templateId = backStackEntry.arguments?.getString("templateId").orEmpty()
                PlannerDetailScreen(
                    template = MockPlannerRepository.templateById(templateId),
                    relatedTemplates = templates.filter { it.id != templateId }.take(6),
                    onBack = { navController.popBackStack() },
                    onOpenPlanner = { nextTemplateId ->
                        navController.navigate(AppRoute.plannerDetail(nextTemplateId))
                    },
                )
            }
        }
    }
}

@Composable
private fun ShellScreen(
    planners: List<OwnedPlanner>,
    templates: List<com.example.plan92.data.mock.PlannerTemplate>,
    onOpenSettings: () -> Unit,
    onOpenReminderOnboarding: () -> Unit,
    onOpenPlanner: (String) -> Unit,
    onCreatePlannerFromTemplate: (String) -> Unit = {},
    onCreateBookPlanner: (String, String) -> Unit,
    onOpenWidgetPromo: () -> Unit,
    onOpenImportPdf: () -> Unit,
    onOpenPageStyle: () -> Unit,
) {
    var selectedTab by rememberSaveable { mutableStateOf(MainTab.Planners) }
    var activeModal by rememberSaveable { mutableStateOf<ShellModal?>(null) }
    var homeReadyToUseExpanded by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ShellWhite),
    ) {
        Scaffold(
            containerColor = androidx.compose.ui.graphics.Color.Transparent,
            topBar = {
                Plan92TopBar(
                    currentTab = selectedTab,
                    onProTap = onOpenSettings,
                    onSearchTap = { selectedTab = MainTab.Search },
                    onFavoritesTap = { activeModal = ShellModal.Favorites },
                    onSettingsTap = onOpenSettings,
                    onReminderTap = onOpenReminderOnboarding,
                )
            },
            bottomBar = {
                Plan92BottomBar(
                    currentTab = selectedTab,
                    onTabSelected = { tab ->
                        selectedTab = tab
                    },
                    onCreateTap = { activeModal = ShellModal.Create },
                )
            },
        ) { innerPadding ->
            Crossfade(
                targetState = selectedTab,
                modifier = Modifier.padding(innerPadding),
                label = "main-tab-crossfade",
            ) { tab ->
                when (tab) {
                    MainTab.Planners -> PlannersHomeScreen(
                        planners = planners,
                        featuredTemplates = templates,
                        readyToUseExpanded = homeReadyToUseExpanded,
                        onReadyToUseExpandedChange = { homeReadyToUseExpanded = it },
                        onCreateNew = { activeModal = ShellModal.Create },
                        onOpenPlanner = onOpenPlanner,
                        onUseTemplate = { templateId ->
                            onCreatePlannerFromTemplate(templateId)
                            onOpenPlanner(templateId)
                        },
                    )

                    MainTab.Calendar -> CalendarScreen(
                        onAddReminder = onOpenReminderOnboarding,
                    )

                    MainTab.Search -> SearchScreen(
                        popularTags = MockPlannerRepository.popularTags,
                    )

                    MainTab.Templates -> TemplatesScreen(
                        categories = MockPlannerRepository.categories,
                        templates = templates,
                        onOpenPlanner = onOpenPlanner,
                    )
                }
            }
        }

        when (activeModal) {
            ShellModal.Favorites -> FavoritesDialog(
                onDismiss = { activeModal = null },
                onAddFavorites = {
                    activeModal = null
                    selectedTab = MainTab.Templates
                },
            )

            ShellModal.Create -> CreateNewDialog(
                templates = templates,
                onDismiss = { activeModal = null },
                onBlankPage = {
                    activeModal = null
                    onOpenPageStyle()
                },
                onCreateBook = { title, tagline ->
                    activeModal = null
                    onCreateBookPlanner(title, tagline)
                    onOpenPlanner(MockPlannerRepository.bookTemplate().id)
                },
                onUseTemplate = { templateId ->
                    activeModal = null
                    onCreatePlannerFromTemplate(templateId)
                    onOpenPlanner(templateId)
                },
                onBrowseTemplates = {
                    activeModal = null
                    selectedTab = MainTab.Templates
                },
                onImportPdf = {
                    activeModal = null
                    onOpenImportPdf()
                },
            )

            ShellModal.WidgetPromo -> WidgetPromoDialog(
                onDismiss = { activeModal = null },
                onLearnMore = {
                    activeModal = null
                    onOpenWidgetPromo()
                },
            )

            null -> Unit
        }
    }
}
