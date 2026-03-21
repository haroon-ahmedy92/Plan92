package com.example.plan92.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp

val Gray50 = Color(0xFFF8F9FA)
val Gray100 = Color(0xFFE9ECEF)
val Gray200 = Color(0xFFDEE2E6)
val Gray300 = Color(0xFFCED4DA)
val Gray400 = Color(0xFFADB5BD)
val Gray600 = Color(0xFF6C757D)
val Gray700 = Color(0xFF495057)
val Gray800 = Color(0xFF343A40)
val Gray900 = Color(0xFF212529)

val Porcelain = Gray50
val Linen = Gray100
val AlmondMilk = lerp(Gray50, Color.White, 0.2f)
val Oat = Gray200
val Sandstone = Gray300

val InkBlack = Gray900
val NightShade = Gray700
val RoyalIndigo = Color(0xFFC7D3EC)
val AzureDepth = Color(0xFFCFE2F3)
val Amethyst = Color(0xFFD8CDED)
val OrchidBurst = Color(0xFFE4CFDF)
val BrickRed = Color(0xFFE2CFC6)
val BurntOrange = Color(0xFFE9D8C8)
val ApricotGlow = Color(0xFFE8E0CA)

val Paper = Gray50
val PaperTint = Gray100
val ShellWhite = Gray50
val Mist = lerp(Gray200, Color.White, 0.24f)
val InkMuted = Gray600
val DividerSoft = Gray400.copy(alpha = 0.24f)
val DividerStrong = Gray400.copy(alpha = 0.52f)
val PaperSection = lerp(Gray100, Gray50, 0.42f)
val PaperField = Color.White.copy(alpha = 0.82f)
val SurfaceTone = lerp(Gray200, Gray100, 0.5f)
val SurfaceToneHigh = lerp(Gray100, Color.White, 0.36f)
val CoralAccent = Gray700
val AquaAccent = Color(0xFFD8E7F0)
val LemonAccent = Color(0xFFF0E6C8)
val RoseAccent = Color(0xFFEAD5DF)
val MintAccent = Color(0xFFD4E3D3)

val AppBackgroundBrush: Brush
    get() = Brush.verticalGradient(
        colors = listOf(
            Porcelain,
            Paper,
            PaperTint,
        ),
    )

val HeroBrush: Brush
    get() = Brush.linearGradient(
        colors = listOf(
            Gray50,
            Gray100,
            Gray200,
        ),
    )

val WarmAccentBrush: Brush
    get() = Brush.linearGradient(
        colors = listOf(
            Gray200,
            Gray300,
        ),
    )
