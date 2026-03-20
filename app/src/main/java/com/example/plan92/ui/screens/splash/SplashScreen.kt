package com.example.plan92.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plan92.ui.theme.ApricotGlow
import com.example.plan92.ui.theme.BurntOrange
import com.example.plan92.ui.theme.CoralAccent
import com.example.plan92.ui.theme.Paper
import com.example.plan92.ui.theme.Plan92Theme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onFinished: () -> Unit,
) {
    LaunchedEffect(Unit) {
        delay(1600)
        onFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(CoralAccent, BurntOrange, ApricotGlow),
                ),
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(28.dp),
        ) {
            Box(
                modifier = Modifier.size(210.dp),
                contentAlignment = Alignment.Center,
            ) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .size((210 - index * 34).dp)
                            .border(1.dp, Paper.copy(alpha = 0.35f), CircleShape),
                    )
                }
                repeat(4) { index ->
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .align(
                                when (index) {
                                    0 -> Alignment.TopCenter
                                    1 -> Alignment.CenterEnd
                                    2 -> Alignment.BottomCenter
                                    else -> Alignment.CenterStart
                                },
                            )
                            .clip(CircleShape)
                            .background(Paper.copy(alpha = 0.92f)),
                    )
                }
                Box(
                    modifier = Modifier
                        .size(122.dp)
                        .clip(CircleShape)
                        .background(Paper.copy(alpha = 0.96f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "PW",
                        style = MaterialTheme.typography.displayMedium,
                        color = CoralAccent,
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "PlanWiz",
                    color = Paper,
                    style = MaterialTheme.typography.displayMedium,
                )
                Text(
                    text = "Your Growth Assistant",
                    color = Paper.copy(alpha = 0.9f),
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.width(1.dp))
                Text(
                    text = "Elegant planning, journaling, and reminders in one place.",
                    color = Paper.copy(alpha = 0.7f),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    Plan92Theme {
        SplashScreen(onFinished = {})
    }
}

