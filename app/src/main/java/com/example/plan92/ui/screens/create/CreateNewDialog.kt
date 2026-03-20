@file:OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)

package com.example.plan92.ui.screens.create

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.GridOn
import androidx.compose.material.icons.outlined.PictureAsPdf
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.plan92.data.mock.PlannerTemplate
import com.example.plan92.ui.theme.plan92Palette

private enum class CreateStep {
    Root,
    ReadyToUse,
    BookSetup,
}

private data class CreateOption(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val onClick: () -> Unit,
    val badge: String? = null,
)

@Composable
fun CreateNewDialog(
    templates: List<PlannerTemplate>,
    onDismiss: () -> Unit,
    onBlankPage: () -> Unit,
    onCreateBook: (String, String) -> Unit,
    onUseTemplate: (String) -> Unit,
    onBrowseTemplates: () -> Unit,
    onImportPdf: () -> Unit,
) {
    var step by rememberSaveable { mutableStateOf(CreateStep.Root) }
    var bookTitle by rememberSaveable { mutableStateOf("My Book Planner") }
    var starterIndex by rememberSaveable { mutableIntStateOf(0) }
    val starterModes = remember {
        listOf(
            "Notebook Starter" to "Blank and open writing pages",
            "Journal Starter" to "Reflective prompts and notes",
            "Planner Starter" to "General planning sections",
        )
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(0.94f),
            shape = RoundedCornerShape(30.dp),
            color = MaterialTheme.plan92Palette.pageSurface,
            shadowElevation = 18.dp,
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                CreateHeader(
                    title = when (step) {
                        CreateStep.Root -> "Create New Planner"
                        CreateStep.ReadyToUse -> "Choose a Template"
                        CreateStep.BookSetup -> "Set Up Your Book"
                    },
                    subtitle = when (step) {
                        CreateStep.Root -> "Start from scratch, pick a template, or create a book-style planner."
                        CreateStep.ReadyToUse -> "Pick a ready-made template and jump straight into the editor."
                        CreateStep.BookSetup -> "Give your planner a name and choose a simple starter style."
                    },
                    showBack = step != CreateStep.Root,
                    onBack = { step = CreateStep.Root },
                    onDismiss = onDismiss,
                )

                when (step) {
                    CreateStep.Root -> {
                        listOf(
                            CreateOption(
                                title = "Blank Page",
                                description = "Choose blank or lined and open a clean editable page.",
                                icon = Icons.Outlined.GridOn,
                                onClick = onBlankPage,
                            ),
                            CreateOption(
                                title = "Ready-to-Use",
                                description = "Pick from your template library and open it immediately.",
                                icon = Icons.Outlined.Description,
                                onClick = { step = CreateStep.ReadyToUse },
                            ),
                            CreateOption(
                                title = "Book",
                                description = "Set up a named book-style planner shell and begin writing.",
                                icon = Icons.Outlined.Book,
                                onClick = { step = CreateStep.BookSetup },
                            ),
                            CreateOption(
                                title = "Import PDF",
                                description = "This flow is still a placeholder for now.",
                                icon = Icons.Outlined.PictureAsPdf,
                                onClick = onImportPdf,
                                badge = "Coming Soon",
                            ),
                        ).forEach { option ->
                            CreateOptionRow(
                                option = option,
                                onClick = option.onClick,
                            )
                        }
                    }

                    CreateStep.ReadyToUse -> {
                        ReadyToUsePicker(
                            templates = templates,
                            onUseTemplate = onUseTemplate,
                            onBrowseTemplates = onBrowseTemplates,
                        )
                    }

                    CreateStep.BookSetup -> {
                        BookSetupStep(
                            title = bookTitle,
                            onTitleChange = { bookTitle = it },
                            starterModes = starterModes,
                            selectedStarterIndex = starterIndex,
                            onStarterSelected = { starterIndex = it },
                            onCreateBook = {
                                val selectedStarter = starterModes[starterIndex]
                                onCreateBook(
                                    bookTitle.ifBlank { "My Book Planner" },
                                    selectedStarter.second,
                                )
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CreateHeader(
    title: String,
    subtitle: String,
    showBack: Boolean,
    onBack: () -> Unit,
    onDismiss: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (showBack) {
                HeaderCircleButton(
                    icon = Icons.AutoMirrored.Outlined.ArrowBack,
                    onClick = onBack,
                )
            } else {
                Box(modifier = Modifier.size(40.dp))
            }
            HeaderCircleButton(
                icon = Icons.Outlined.Close,
                onClick = onDismiss,
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.plan92Palette.titleColor,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.plan92Palette.bodyColor,
        )
    }
}

@Composable
private fun HeaderCircleButton(
    icon: ImageVector,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(MaterialTheme.plan92Palette.fieldSurface)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.plan92Palette.titleColor,
        )
    }
}

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
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
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
                    .background(MaterialTheme.plan92Palette.primaryAccent.copy(alpha = 0.14f)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = option.icon,
                    contentDescription = null,
                    tint = MaterialTheme.plan92Palette.primaryAccent,
                    modifier = Modifier.size(34.dp),
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = option.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.plan92Palette.titleColor,
                    )
                    option.badge?.let {
                        Surface(
                            shape = RoundedCornerShape(999.dp),
                            color = MaterialTheme.plan92Palette.secondaryAccent.copy(alpha = 0.14f),
                        ) {
                            Text(
                                text = it,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.plan92Palette.secondaryAccent,
                            )
                        }
                    }
                }
                Text(
                    text = option.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.plan92Palette.bodyColor,
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.plan92Palette.primaryAccent,
            )
        }
    }
}

@Composable
private fun ReadyToUsePicker(
    templates: List<PlannerTemplate>,
    onUseTemplate: (String) -> Unit,
    onBrowseTemplates: () -> Unit,
) {
    val featured = remember(templates) { templates.take(10) }

    Column(
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        FlowRow(
            modifier = Modifier.heightIn(max = 420.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            featured.forEach { template ->
                ReadyTemplateOptionCard(
                    template = template,
                    onClick = { onUseTemplate(template.id) },
                )
            }
        }
        TextButton(onClick = onBrowseTemplates) {
            Text("Browse Full Template Library")
        }
    }
}

@Composable
private fun ReadyTemplateOptionCard(
    template: PlannerTemplate,
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .width(152.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(22.dp),
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = androidx.compose.foundation.BorderStroke(1.dp, template.accent.copy(alpha = 0.2f)),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(template.accent.copy(alpha = 0.14f)),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = template.title,
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.plan92Palette.titleColor,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Text(
                text = template.subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.plan92Palette.bodyColor,
                maxLines = 2,
            )
        }
    }
}

@Composable
private fun BookSetupStep(
    title: String,
    onTitleChange: (String) -> Unit,
    starterModes: List<Pair<String, String>>,
    selectedStarterIndex: Int,
    onStarterSelected: (Int) -> Unit,
    onCreateBook: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = onTitleChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Book Title") },
            shape = RoundedCornerShape(18.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.plan92Palette.pageSurface,
                unfocusedContainerColor = MaterialTheme.plan92Palette.pageSurface,
                focusedIndicatorColor = MaterialTheme.plan92Palette.primaryAccent,
                unfocusedIndicatorColor = MaterialTheme.plan92Palette.lineColor,
            ),
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(
                text = "Starter Style",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.plan92Palette.titleColor,
                fontWeight = FontWeight.SemiBold,
            )
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                starterModes.forEachIndexed { index, (label, description) ->
                    Surface(
                        modifier = Modifier
                            .width(180.dp)
                            .clickable { onStarterSelected(index) },
                        shape = RoundedCornerShape(20.dp),
                        color = if (selectedStarterIndex == index) {
                            MaterialTheme.plan92Palette.primaryAccent.copy(alpha = 0.14f)
                        } else {
                            MaterialTheme.plan92Palette.fieldSurface
                        },
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (selectedStarterIndex == index) MaterialTheme.plan92Palette.primaryAccent
                            else MaterialTheme.plan92Palette.lineColor,
                        ),
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.plan92Palette.titleColor,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Text(
                                text = description,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.plan92Palette.bodyColor,
                            )
                        }
                    }
                }
            }
        }

        Surface(
            shape = RoundedCornerShape(22.dp),
            color = MaterialTheme.plan92Palette.fieldSurface,
            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "What happens next",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.plan92Palette.primaryAccent,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "We’ll create a local book-style planner, add it to Your Planners, and open it immediately so you can start editing.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.plan92Palette.bodyColor,
                )
            }
        }

        Button(
            onClick = onCreateBook,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Create Book Planner")
        }
    }
}
