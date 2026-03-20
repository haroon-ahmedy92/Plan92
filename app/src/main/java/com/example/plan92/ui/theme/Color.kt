package com.example.plan92.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp

val InkBlack = Color(0xFF000007)
val NightShade = Color(0xFF090117)
val RoyalIndigo = Color(0xFF130C5B)
val AzureDepth = Color(0xFF0C5D96)
val Amethyst = Color(0xFF60049E)
val OrchidBurst = Color(0xFF9D09DF)
val BrickRed = Color(0xFF9D2223)
val BurntOrange = Color(0xFFD16222)
val ApricotGlow = Color(0xFFF0A05C)

val Paper = lerp(ApricotGlow, Color.White, 0.84f)
val PaperTint = lerp(OrchidBurst, Color.White, 0.9f)
val ShellWhite = lerp(ApricotGlow, Color.White, 0.9f)
val Mist = lerp(Amethyst, Color.White, 0.62f)
val InkMuted = lerp(NightShade, ApricotGlow, 0.34f)
val DividerSoft = Amethyst.copy(alpha = 0.15f)
val DividerStrong = lerp(RoyalIndigo, Color.White, 0.8f).copy(alpha = 0.72f)
val PaperSection = lerp(Amethyst, Color.White, 0.95f)
val PaperField = Color.White.copy(alpha = 0.98f)
val SurfaceTone = lerp(InkBlack, RoyalIndigo, 0.48f)
val SurfaceToneHigh = lerp(NightShade, Amethyst, 0.32f)
val CoralAccent = BurntOrange
val AquaAccent = AzureDepth
val LemonAccent = ApricotGlow
val RoseAccent = OrchidBurst
val MintAccent = Amethyst

val AppBackgroundBrush: Brush
    get() = Brush.verticalGradient(
        colors = listOf(
            InkBlack,
            NightShade,
            RoyalIndigo,
        ),
    )

val HeroBrush: Brush
    get() = Brush.linearGradient(
        colors = listOf(
            RoyalIndigo,
            AzureDepth,
            Amethyst,
        ),
    )

val WarmAccentBrush: Brush
    get() = Brush.linearGradient(
        colors = listOf(
            BurntOrange,
            ApricotGlow,
        ),
    )
