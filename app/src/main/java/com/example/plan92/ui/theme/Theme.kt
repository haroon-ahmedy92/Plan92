package com.example.plan92.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val DarkAppColorScheme = darkColorScheme(
    primary = ApricotGlow,
    secondary = AzureDepth,
    tertiary = OrchidBurst,
    background = InkBlack,
    surface = NightShade,
    surfaceVariant = RoyalIndigo,
    primaryContainer = BurntOrange,
    secondaryContainer = Amethyst,
    tertiaryContainer = BrickRed,
    onPrimary = InkBlack,
    onSecondary = Paper,
    onTertiary = Paper,
    onBackground = Paper,
    onSurface = Paper,
    onSurfaceVariant = Paper,
    onPrimaryContainer = Paper,
    onSecondaryContainer = Paper,
    onTertiaryContainer = Paper,
    outline = Mist.copy(alpha = 0.5f),
)

private val LightAppColorScheme = lightColorScheme(
    primary = BurntOrange,
    secondary = AzureDepth,
    tertiary = Amethyst,
    background = ShellWhite,
    surface = Paper,
    surfaceVariant = PaperTint,
    primaryContainer = ApricotGlow,
    secondaryContainer = AzureDepth.copy(alpha = 0.12f),
    tertiaryContainer = OrchidBurst.copy(alpha = 0.14f),
    onPrimary = InkBlack,
    onSecondary = Paper,
    onTertiary = Paper,
    onBackground = InkBlack,
    onSurface = InkBlack,
    onSurfaceVariant = InkMuted,
    onPrimaryContainer = InkBlack,
    onSecondaryContainer = InkBlack,
    onTertiaryContainer = InkBlack,
    outline = Amethyst.copy(alpha = 0.25f),
)

private val AppShapes = Shapes(
    small = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
    medium = androidx.compose.foundation.shape.RoundedCornerShape(18.dp),
    large = androidx.compose.foundation.shape.RoundedCornerShape(24.dp),
)

@Immutable
data class Plan92BrandPalette(
    val appBackground: Color,
    val pageSurface: Color,
    val sectionSurface: Color,
    val fieldSurface: Color,
    val lineColor: Color,
    val titleColor: Color,
    val bodyColor: Color,
    val primaryAccent: Color,
    val secondaryAccent: Color,
    val tertiaryAccent: Color,
    val warmAccent: Color,
    val heroBrush: Brush,
    val warmBrush: Brush,
)

private val LightBrandPalette = Plan92BrandPalette(
    appBackground = ShellWhite,
    pageSurface = Paper,
    sectionSurface = PaperSection,
    fieldSurface = PaperField,
    lineColor = DividerStrong,
    titleColor = InkBlack,
    bodyColor = InkMuted,
    primaryAccent = BurntOrange,
    secondaryAccent = AzureDepth,
    tertiaryAccent = Amethyst,
    warmAccent = ApricotGlow,
    heroBrush = HeroBrush,
    warmBrush = WarmAccentBrush,
)

private val DarkBrandPalette = Plan92BrandPalette(
    appBackground = InkBlack,
    pageSurface = SurfaceTone,
    sectionSurface = SurfaceToneHigh,
    fieldSurface = RoyalIndigo.copy(alpha = 0.66f),
    lineColor = Mist.copy(alpha = 0.22f),
    titleColor = Paper,
    bodyColor = Paper.copy(alpha = 0.78f),
    primaryAccent = ApricotGlow,
    secondaryAccent = AzureDepth,
    tertiaryAccent = OrchidBurst,
    warmAccent = BurntOrange,
    heroBrush = HeroBrush,
    warmBrush = WarmAccentBrush,
)

private val LocalPlan92BrandPalette = staticCompositionLocalOf { LightBrandPalette }

val MaterialTheme.plan92Palette: Plan92BrandPalette
    @Composable get() = LocalPlan92BrandPalette.current

@Composable
fun Plan92Theme(
    content: @Composable () -> Unit,
) {
    val isDark = isSystemInDarkTheme()
    val brandPalette = if (isDark) DarkBrandPalette else LightBrandPalette
    MaterialTheme(
        colorScheme = if (isDark) DarkAppColorScheme else LightAppColorScheme,
        typography = Plan92Typography,
        shapes = AppShapes,
    ) {
        androidx.compose.runtime.CompositionLocalProvider(
            LocalPlan92BrandPalette provides brandPalette,
            content = content,
        )
    }
}
