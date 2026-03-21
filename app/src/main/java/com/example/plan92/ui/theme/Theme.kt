package com.example.plan92.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val AppColorScheme = lightColorScheme(
    primary = CoralAccent,
    secondary = Gray600,
    tertiary = Gray300,
    background = ShellWhite,
    surface = Paper,
    surfaceVariant = PaperSection,
    primaryContainer = Gray300,
    secondaryContainer = Gray200,
    tertiaryContainer = Gray100,
    onPrimary = Color.White,
    onSecondary = InkBlack,
    onTertiary = InkBlack,
    onBackground = InkBlack,
    onSurface = InkBlack,
    onSurfaceVariant = InkMuted,
    onPrimaryContainer = InkBlack,
    onSecondaryContainer = InkBlack,
    onTertiaryContainer = InkBlack,
    outline = DividerStrong,
)

private val AppShapes = Shapes(
    small = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
    medium = androidx.compose.foundation.shape.RoundedCornerShape(18.dp),
    large = androidx.compose.foundation.shape.RoundedCornerShape(24.dp),
)

@Immutable
data class Plan92BrandPalette(
    val appBackground: Color,
    val barSurface: Color,
    val pageSurface: Color,
    val sectionSurface: Color,
    val surfaceMuted: Color,
    val fieldSurface: Color,
    val plannerPaper: Color,
    val lineColor: Color,
    val titleColor: Color,
    val bodyColor: Color,
    val primaryAccent: Color,
    val secondaryAccent: Color,
    val tertiaryAccent: Color,
    val warmAccent: Color,
    val chipSelected: Color,
    val chipUnselected: Color,
    val heroBrush: Brush,
    val warmBrush: Brush,
)

private val LightBrandPalette = Plan92BrandPalette(
    appBackground = ShellWhite,
    barSurface = Color.White.copy(alpha = 0.84f),
    pageSurface = Paper,
    sectionSurface = PaperSection,
    surfaceMuted = SurfaceTone,
    fieldSurface = PaperField,
    plannerPaper = Paper,
    lineColor = DividerStrong,
    titleColor = InkBlack,
    bodyColor = InkMuted,
    primaryAccent = CoralAccent,
    secondaryAccent = Gray600,
    tertiaryAccent = Gray300,
    warmAccent = ApricotGlow,
    chipSelected = Gray200,
    chipUnselected = Paper,
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
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Plan92Typography,
        shapes = AppShapes,
    ) {
        androidx.compose.runtime.CompositionLocalProvider(
            LocalPlan92BrandPalette provides LightBrandPalette,
            content = content,
        )
    }
}
