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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plan92.R
import com.example.plan92.ui.theme.Plan92Theme
import com.example.plan92.ui.theme.plan92Palette
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
                brush = MaterialTheme.plan92Palette.heroBrush,
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
                            .border(1.dp, MaterialTheme.plan92Palette.lineColor.copy(alpha = 0.4f), CircleShape),
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
                            .background(MaterialTheme.plan92Palette.pageSurface.copy(alpha = 0.92f)),
                    )
                }
                Box(
                    modifier = Modifier
                        .size(122.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.plan92Palette.pageSurface.copy(alpha = 0.96f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "P92",
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.plan92Palette.primaryAccent,
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    color = MaterialTheme.plan92Palette.titleColor,
                    style = MaterialTheme.typography.displayMedium,
                )
                Text(
                    text = "Your Growth Assistant",
                    color = MaterialTheme.plan92Palette.bodyColor,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.width(1.dp))
                Text(
                    text = "Elegant planning, journaling, and reminders in one place.",
                    color = MaterialTheme.plan92Palette.bodyColor.copy(alpha = 0.84f),
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
