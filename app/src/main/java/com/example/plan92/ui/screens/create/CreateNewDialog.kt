package com.example.plan92.ui.screens.create

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.GridOn
import androidx.compose.material.icons.outlined.PictureAsPdf
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.unit.dp
import com.example.plan92.ui.theme.CoralAccent
import com.example.plan92.ui.theme.InkBlack
import com.example.plan92.ui.theme.ShellWhite

@Composable
fun CreateNewDialog(
    onDismiss: () -> Unit,
    onBlankPage: () -> Unit,
    onBook: () -> Unit,
    onReadyToUse: () -> Unit,
    onImportPdf: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(0.94f),
            shape = RoundedCornerShape(30.dp),
            color = ShellWhite,
            shadowElevation = 18.dp,
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "How Would You Like to Start?",
                        style = MaterialTheme.typography.headlineMedium,
                        color = InkBlack,
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .clickable(onClick = onDismiss),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "Close",
                            tint = InkBlack,
                        )
                    }
                }

                listOf(
                    CreateOption("Start with a Blank Page", "One blank page for notes, lists, and daily plans.", Icons.Outlined.GridOn, onBlankPage),
                    CreateOption("Start with a Book", "For writing, planning, and capturing moments as your days unfold.", Icons.Outlined.Book, onBook),
                    CreateOption("Ready-to-Use", "Begin quickly, choose a template, and jump into the editor.", Icons.Outlined.Description, onReadyToUse),
                    CreateOption("Import PDF", "Coming soon. We'll route you to a placeholder screen for now.", Icons.Outlined.PictureAsPdf, onImportPdf),
                ).forEach { option ->
                    CreateOptionRow(
                        option = option,
                        onClick = option.onClick,
                    )
                }
            }
        }
    }
}

private data class CreateOption(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val onClick: () -> Unit,
)

@Composable
private fun CreateOptionRow(
    option: CreateOption,
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(22.dp),
        color = Color.White,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(CoralAccent.copy(alpha = 0.14f)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = option.icon,
                    contentDescription = null,
                    tint = CoralAccent,
                    modifier = Modifier.size(34.dp),
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = option.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = InkBlack,
                )
                Text(
                    text = option.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = InkBlack.copy(alpha = 0.66f),
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = CoralAccent,
            )
        }
    }
}
