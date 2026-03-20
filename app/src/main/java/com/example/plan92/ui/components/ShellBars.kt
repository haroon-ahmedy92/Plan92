package com.example.plan92.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.plan92.R
import com.example.plan92.navigation.MainTab
import com.example.plan92.ui.theme.ApricotGlow
import com.example.plan92.ui.theme.BurntOrange
import com.example.plan92.ui.theme.DividerSoft
import com.example.plan92.ui.theme.InkBlack
import com.example.plan92.ui.theme.OrchidBurst
import com.example.plan92.ui.theme.ShellWhite

@Composable
fun Plan92TopBar(
    currentTab: MainTab,
    onProTap: () -> Unit,
    onSearchTap: () -> Unit,
    onFavoritesTap: () -> Unit,
    onSettingsTap: () -> Unit,
    onReminderTap: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.app_name),
            color = InkBlack,
            style = androidx.compose.material3.MaterialTheme.typography.displayMedium,
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(BurntOrange.copy(alpha = 0.16f), OrchidBurst.copy(alpha = 0.16f)),
                        ),
                    )
                    .border(
                        width = 1.2.dp,
                        brush = Brush.horizontalGradient(listOf(BurntOrange, OrchidBurst)),
                        shape = RoundedCornerShape(14.dp),
                    ),
            ) {
                Surface(
                    onClick = onProTap,
                    shape = RoundedCornerShape(14.dp),
                    color = Color.Transparent,
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AutoAwesome,
                            contentDescription = null,
                            tint = ApricotGlow,
                            modifier = Modifier.size(16.dp),
                        )
                        Text(
                            text = "PRO",
                            color = InkBlack,
                            style = androidx.compose.material3.MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }

            AppIconAction(icon = Icons.Outlined.Search, onClick = onSearchTap)
            AppIconAction(icon = Icons.Outlined.FavoriteBorder, onClick = onFavoritesTap)
            AppIconAction(icon = Icons.Outlined.Settings, onClick = onSettingsTap)
        }
    }
}

@Composable
private fun AppIconAction(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(32.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF6E6877),
            modifier = Modifier.size(28.dp),
        )
    }
}

@Composable
fun Plan92BottomBar(
    currentTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    onCreateTap: () -> Unit,
) {
    NavigationBar(
        windowInsets = WindowInsets(0.dp),
        containerColor = Color.White,
        tonalElevation = 2.dp,
        modifier = Modifier.navigationBarsPadding(),
    ) {
        listOf(
            MainTab.Planners to Icons.Outlined.Description,
            MainTab.Calendar to Icons.Outlined.CalendarMonth,
        ).forEach { (tab, icon) ->
            NavigationBarItem(
                selected = currentTab == tab,
                onClick = { onTabSelected(tab) },
                icon = { Icon(icon, contentDescription = tab.title) },
                label = { Text(tab.title) },
                colors = navigationColors(),
            )
        }

        NavigationBarItem(
            selected = false,
            onClick = onCreateTap,
            icon = {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, Color(0xFF8F879A), CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Outlined.Add, contentDescription = "Create New", tint = Color(0xFF7E7887))
                }
            },
            label = { Text("Create New") },
            colors = navigationColors(),
        )

        listOf(
            MainTab.Search to Icons.Outlined.Search,
            MainTab.Templates to Icons.Outlined.Description,
        ).forEach { (tab, icon) ->
            NavigationBarItem(
                selected = currentTab == tab,
                onClick = { onTabSelected(tab) },
                icon = { Icon(icon, contentDescription = tab.title) },
                label = { Text(tab.title) },
                colors = navigationColors(),
            )
        }
    }
}

@Composable
private fun navigationColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = BurntOrange,
    selectedTextColor = BurntOrange,
    indicatorColor = Color.Transparent,
    unselectedIconColor = Color(0xFF7E7887),
    unselectedTextColor = Color(0xFF7E7887),
)
