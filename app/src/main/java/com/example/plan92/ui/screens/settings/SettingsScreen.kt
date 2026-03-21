package com.example.plan92.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material.icons.outlined.Widgets
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plan92.ui.theme.plan92Palette

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    onOpenReminderSetup: () -> Unit,
    onOpenWidgetPromo: () -> Unit,
) {
    var notificationsEnabled by rememberSaveable { mutableStateOf(true) }
    var appLockEnabled by rememberSaveable { mutableStateOf(false) }
    var watermarkRemoved by rememberSaveable { mutableStateOf(false) }
    var snappingEnabled by rememberSaveable { mutableStateOf(true) }
    var hapticsEnabled by rememberSaveable { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.plan92Palette.appBackground,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                contentDescription = "Back",
                            )
                        }
                        Text(
                            text = "Settings",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.plan92Palette.titleColor,
                        )
                    }
                }
            }

            item {
                Surface(
                    shape = RoundedCornerShape(28.dp),
                    color = Color.Transparent,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        MaterialTheme.plan92Palette.primaryAccent,
                                        MaterialTheme.plan92Palette.secondaryAccent,
                                    ),
                                ),
                            )
                            .padding(18.dp),
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            Text(
                                text = "Unlock Premium Features!",
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onPrimary,
                            )
                            Text(
                                text = "Unlock the full planner experience with premium covers, templates, and polished export tools later on.",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.78f),
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                            ) {
                                PromoBadge("50% OFF")
                                PromoBadge("Gift a discount")
                            }
                        }
                    }
                }
            }

            item {
                SettingsSection(title = "App Settings") {
                    SettingsArrowRow(icon = Icons.Outlined.Grade, title = "Restore Purchased")
                    SettingsArrowRow(icon = Icons.Outlined.Sync, title = "Sync Your Planners", badge = "NEW")
                    SettingsToggleRow(icon = Icons.Outlined.Notifications, title = "Enable Notification", checked = notificationsEnabled) {
                        notificationsEnabled = it
                    }
                    SettingsToggleRow(icon = Icons.Outlined.Lock, title = "App Lock", checked = appLockEnabled) {
                        appLockEnabled = it
                    }
                    SettingsArrowRow(icon = Icons.Outlined.Notifications, title = "Schedule Reminders", onClick = onOpenReminderSetup)
                }
            }

            item {
                SettingsSection(title = "Import & Integration") {
                    SettingsArrowRow(icon = Icons.Outlined.Share, title = "Import Planner")
                    SettingsArrowRow(icon = Icons.Outlined.Sync, title = "Calendar Sync")
                }
            }

            item {
                SettingsSection(title = "Editor Settings") {
                    SettingsToggleRow(icon = Icons.Outlined.Info, title = "Remove Watermark", checked = watermarkRemoved) {
                        watermarkRemoved = it
                    }
                    SettingsToggleRow(icon = Icons.Outlined.Info, title = "Snapping", checked = snappingEnabled) {
                        snappingEnabled = it
                    }
                    SettingsToggleRow(icon = Icons.Outlined.Info, title = "Haptic", checked = hapticsEnabled) {
                        hapticsEnabled = it
                    }
                }
            }

            item {
                SettingsSection(title = "Support & Feedback") {
                    SettingsArrowRow(icon = Icons.Outlined.Widgets, title = "How to Add Widget?", onClick = onOpenWidgetPromo)
                    SettingsArrowRow(icon = Icons.Outlined.Grade, title = "Rate Us")
                    SettingsArrowRow(icon = Icons.Outlined.Info, title = "Help Center")
                }
            }

            item {
                SettingsSection(title = "More About Us") {
                    SettingsArrowRow(icon = Icons.Outlined.Info, title = "About Us")
                    SettingsArrowRow(icon = Icons.Outlined.Share, title = "Share")
                    SettingsArrowRow(icon = Icons.Outlined.Info, title = "More apps")
                    SettingsArrowRow(icon = Icons.Outlined.Info, title = "Privacy Policy")
                }
            }

            item {
                SettingsSection(title = "Follow Us") {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                    ) {
                        listOf(
                            "f" to MaterialTheme.plan92Palette.primaryAccent,
                            "ig" to MaterialTheme.plan92Palette.secondaryAccent,
                            "x" to MaterialTheme.plan92Palette.surfaceMuted,
                            "in" to MaterialTheme.plan92Palette.tertiaryAccent,
                        ).forEach { (label, color) ->
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(CircleShape)
                                    .background(color),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = label,
                                    color = Color.White,
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingsSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.plan92Palette.bodyColor,
        )
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.plan92Palette.pageSurface,
        ) {
            Column(
                modifier = Modifier.padding(vertical = 6.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                content = content,
            )
        }
    }
}

@Composable
private fun SettingsArrowRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    badge: String? = null,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.plan92Palette.primaryAccent.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(icon, contentDescription = null, tint = MaterialTheme.plan92Palette.primaryAccent)
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.plan92Palette.titleColor,
                )
                if (badge != null) {
                    Surface(
                        shape = RoundedCornerShape(999.dp),
                        color = MaterialTheme.plan92Palette.secondaryAccent.copy(alpha = 0.18f),
                    ) {
                        Text(
                            text = badge,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.plan92Palette.titleColor,
                        )
                    }
                }
            }
        }
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.plan92Palette.bodyColor,
        )
    }
}

@Composable
private fun SettingsToggleRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.plan92Palette.primaryAccent.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(icon, contentDescription = null, tint = MaterialTheme.plan92Palette.primaryAccent)
            }
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.plan92Palette.titleColor,
            )
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
        )
    }
}

@Composable
private fun PromoBadge(text: String) {
    Surface(
        shape = RoundedCornerShape(999.dp),
        color = MaterialTheme.plan92Palette.pageSurface.copy(alpha = 0.48f),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.plan92Palette.titleColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsPreview() {
    SettingsScreen(
        onBack = {},
        onOpenReminderSetup = {},
        onOpenWidgetPromo = {},
    )
}
