package com.example.plan92.ui.screens.planner

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plan92.data.mock.MockPlannerRepository
import com.example.plan92.data.mock.PlannerTemplate
import com.example.plan92.planner.engine.PlannerTemplateSchema
import com.example.plan92.ui.components.LabelChip
import com.example.plan92.ui.components.PlannerCardSurface
import com.example.plan92.ui.theme.Plan92Theme
import com.example.plan92.ui.theme.plan92Palette

@Composable
fun PlannerDetailScreen(
    template: PlannerTemplate,
    relatedTemplates: List<PlannerTemplate>,
    onBack: () -> Unit,
    onOpenPlanner: (String) -> Unit = {},
) {
    val definition = PlannerTemplateSchema.definitionFor(template)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.plan92Palette.appBackground),
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
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.plan92Palette.titleColor,
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        RoundAction(Icons.Outlined.FavoriteBorder)
                        RoundAction(Icons.Outlined.Share)
                    }
                }
            }

            item {
                Surface(
                    shape = RoundedCornerShape(28.dp),
                    color = MaterialTheme.plan92Palette.sectionSurface,
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        MaterialTheme.plan92Palette.primaryAccent.copy(alpha = 0.12f),
                    ),
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        LabelChip(
                            text = template.categoryId.replaceFirstChar { it.uppercase() },
                            accent = MaterialTheme.plan92Palette.secondaryAccent,
                        )
                        Text(
                            text = template.title,
                            style = MaterialTheme.typography.displaySmall,
                            color = MaterialTheme.plan92Palette.titleColor,
                        )
                        Text(
                            text = template.subtitle,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.plan92Palette.bodyColor,
                        )
                        Text(
                            text = "This planner now renders through the reusable template engine, so its sections are editable Compose primitives instead of a static preview.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.plan92Palette.bodyColor,
                        )
                    }
                }
            }

            item {
                PlannerTemplateEditor(definition = definition)
            }

            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Text(
                        text = "Related templates",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.plan92Palette.titleColor,
                    )
                    Text(
                        text = "These also route through the same engine and schema system.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.plan92Palette.bodyColor,
                    )
                }
            }

            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                ) {
                    items(relatedTemplates) { related ->
                        PlannerCardSurface(
                            modifier = Modifier
                                .size(width = 220.dp, height = 182.dp)
                                .clickable { onOpenPlanner(related.id) },
                            accent = related.accent,
                        ) {
                            Column(
                                modifier = Modifier.padding(14.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                            ) {
                                Surface(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(20.dp),
                                    color = related.secondaryAccent.copy(alpha = 0.14f),
                                ) {
                                    Column(
                                        modifier = Modifier.padding(14.dp),
                                        verticalArrangement = Arrangement.spacedBy(8.dp),
                                    ) {
                                        LabelChip(
                                            text = related.family.name.replace('_', ' '),
                                            accent = related.accent,
                                        )
                                        Text(
                                            text = related.title,
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.plan92Palette.titleColor,
                                        )
                                        Text(
                                            text = related.subtitle,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.plan92Palette.bodyColor,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RoundAction(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(MaterialTheme.plan92Palette.sectionSurface),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.plan92Palette.titleColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PlannerDetailPreview() {
    Plan92Theme {
        PlannerDetailScreen(
            template = MockPlannerRepository.templateById("daily_classic"),
            relatedTemplates = MockPlannerRepository.templates.take(4),
            onBack = {},
        )
    }
}
