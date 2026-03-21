package com.example.plan92.ui.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.unit.dp
import com.example.plan92.ui.theme.plan92Palette

@Composable
fun FavoritesDialog(
    onDismiss: () -> Unit,
    onAddFavorites: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(0.92f),
            shape = RoundedCornerShape(30.dp),
            color = MaterialTheme.plan92Palette.pageSurface,
            shadowElevation = 18.dp,
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(18.dp),
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "Favourites",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.plan92Palette.titleColor,
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.plan92Palette.fieldSurface)
                            .clickable(onClick = onDismiss),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "Close",
                            tint = MaterialTheme.plan92Palette.titleColor,
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .size(156.dp)
                        .clip(RoundedCornerShape(28.dp))
                        .background(MaterialTheme.plan92Palette.primaryAccent.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = null,
                        tint = MaterialTheme.plan92Palette.primaryAccent,
                        modifier = Modifier.size(74.dp),
                    )
                }

                Text(
                    text = "Your Favorites Await",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.plan92Palette.titleColor,
                )
                Text(
                    text = "Your favorites will appear here. Long press on any template and tap the heart to add it to this collection.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.plan92Palette.bodyColor,
                )

                Button(
                    onClick = onAddFavorites,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Add Favorites")
                }
            }
        }
    }
}
