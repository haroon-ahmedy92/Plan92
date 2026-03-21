package com.example.plan92.ui.screens.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.CloudUpload
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.PictureAsPdf
import androidx.compose.material.icons.outlined.Print
import androidx.compose.material.icons.outlined.SaveAlt
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plan92.data.mock.MockPlannerRepository
import com.example.plan92.data.mock.OwnedPlanner
import com.example.plan92.data.mock.PlannerTemplate
import com.example.plan92.ui.theme.Plan92Theme
import com.example.plan92.ui.theme.plan92Palette

@Composable
fun PlannersHomeScreen(
    planners: List<OwnedPlanner>,
    featuredTemplates: List<PlannerTemplate>,
    readyToUseExpanded: Boolean,
    onReadyToUseExpandedChange: (Boolean) -> Unit,
    onCreateNew: () -> Unit,
    onOpenPlanner: (String) -> Unit,
    onUseTemplate: (String) -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.plan92Palette.appBackground,
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                CreatePlannerTile(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onCreateNew,
                )
            }

            items(
                items = planners,
                key = { it.id },
            ) { planner ->
                PlannerPageCard(
                    planner = planner,
                    modifier = Modifier.fillMaxWidth(),
                    onOpenPlanner = { onOpenPlanner(planner.templateId) },
                )
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                ReadyToUseRow(
                    expanded = readyToUseExpanded,
                    onToggle = { onReadyToUseExpandedChange(!readyToUseExpanded) },
                )
            }

            if (readyToUseExpanded) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Text(
                        text = "Choose any template to add it here and start planning.",
                        modifier = Modifier.padding(top = 4.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.plan92Palette.bodyColor,
                    )
                }

                items(
                    items = featuredTemplates,
                    key = { it.id },
                ) { template ->
                    ReadyTemplateCard(
                        template = template,
                        onClick = { onUseTemplate(template.id) },
                    )
                }
            }
        }
    }
}

@Composable
private fun CreatePlannerTile(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val lineColor = MaterialTheme.plan92Palette.lineColor
    Box(
        modifier = modifier
            .height(286.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.Transparent)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawRoundRect(
                color = lineColor,
                cornerRadius = CornerRadius(24.dp.toPx(), 24.dp.toPx()),
                style = Stroke(
                    width = 2.4.dp.toPx(),
                    cap = StrokeCap.Round,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(16.dp.toPx(), 12.dp.toPx())),
                ),
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.plan92Palette.fieldSurface),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = null,
                    tint = MaterialTheme.plan92Palette.bodyColor,
                )
            }
            Text(
                text = "Create New",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.plan92Palette.bodyColor,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun PlannerPageCard(
    planner: OwnedPlanner,
    modifier: Modifier = Modifier,
    onOpenPlanner: () -> Unit,
) {
    var menuExpanded by rememberSaveable { mutableStateOf(false) }

    Surface(
        modifier = modifier
            .height(286.dp)
            .clickable(onClick = onOpenPlanner),
        shape = RoundedCornerShape(18.dp),
        color = MaterialTheme.plan92Palette.pageSurface,
        shadowElevation = 3.dp,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(MaterialTheme.plan92Palette.plannerPaper),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(9.dp),
                ) {
                    repeat(18) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(MaterialTheme.plan92Palette.lineColor.copy(alpha = 0.65f)),
                        )
                    }
                }
            }

            Surface(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(18.dp),
                shape = RoundedCornerShape(12.dp),
                color = planner.accent.copy(alpha = 0.12f),
            ) {
                Text(
                    text = planner.title,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.plan92Palette.titleColor,
                    fontWeight = FontWeight.SemiBold,
                )
            }

            ActionMiniButton(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(14.dp),
                icon = Icons.Outlined.CloudUpload,
                onClick = { },
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(14.dp),
            ) {
                ActionMiniButton(
                    icon = Icons.Outlined.MoreHoriz,
                    onClick = { menuExpanded = true },
                )
                PlannerPageMenu(
                    expanded = menuExpanded,
                    onDismiss = { menuExpanded = false },
                    onEdit = {
                        menuExpanded = false
                        onOpenPlanner()
                    },
                )
            }
        }
    }
}

@Composable
private fun ReadyToUseRow(
    expanded: Boolean,
    onToggle: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        onClick = onToggle,
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.plan92Palette.pageSurface,
        shadowElevation = 2.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Ready to Use",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.plan92Palette.titleColor,
            )
            Icon(
                imageVector = if (expanded) Icons.Outlined.ExpandMore else Icons.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.plan92Palette.titleColor,
            )
        }
    }
}

@Composable
private fun ReadyTemplateCard(
    template: PlannerTemplate,
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(188.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(18.dp),
        color = MaterialTheme.plan92Palette.pageSurface,
        shadowElevation = 2.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(14.dp))
                    .background(template.secondaryAccent.copy(alpha = 0.12f)),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.58f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(template.accent.copy(alpha = 0.28f)),
                    )
                    repeat(4) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(template.accent.copy(alpha = 0.28f)),
                        )
                    }
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = template.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.plan92Palette.titleColor,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = template.subtitle,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.plan92Palette.bodyColor,
                )
            }
        }
    }
}

@Composable
private fun ActionMiniButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.plan92Palette.fieldSurface,
        shadowElevation = 4.dp,
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.plan92Palette.bodyColor,
                modifier = Modifier.size(18.dp),
            )
        }
    }
}

@Composable
private fun PlannerPageMenu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    onEdit: () -> Unit,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        modifier = Modifier
            .background(MaterialTheme.plan92Palette.pageSurface, RoundedCornerShape(20.dp))
            .width(260.dp),
    ) {
        val items = listOf(
            Triple("Edit", Icons.Outlined.Edit, onEdit),
            Triple("Save to Gallery", Icons.Outlined.SaveAlt, {}),
            Triple("Save PDF", Icons.Outlined.PictureAsPdf, {}),
            Triple("Print", Icons.Outlined.Print, {}),
            Triple("Share", Icons.Outlined.Share, {}),
            Triple("Export", Icons.Outlined.CloudUpload, {}),
            Triple("Duplicate", Icons.Outlined.Description, {}),
            Triple("Add to Book", Icons.Outlined.BookmarkAdd, {}),
            Triple("Add to Folder", Icons.Outlined.FolderOpen, {}),
        )
        items.forEach { (label, icon, action) ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.plan92Palette.titleColor,
                    )
                },
                leadingIcon = {
                    Icon(icon, contentDescription = null, tint = MaterialTheme.plan92Palette.titleColor)
                },
                onClick = action,
            )
        }
        DropdownMenuItem(
            text = {
                Text(
                    text = "Delete",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.plan92Palette.primaryAccent,
                    fontWeight = FontWeight.SemiBold,
                )
            },
            leadingIcon = {
                Icon(Icons.Outlined.DeleteOutline, contentDescription = null, tint = MaterialTheme.plan92Palette.primaryAccent)
            },
            onClick = onDismiss,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreviewCollapsed() {
    Plan92Theme {
        PlannersHomeScreen(
            planners = emptyList(),
            featuredTemplates = MockPlannerRepository.templates,
            readyToUseExpanded = false,
            onReadyToUseExpandedChange = {},
            onCreateNew = {},
            onOpenPlanner = {},
            onUseTemplate = {},
        )
    }
}

@Preview(showBackground = true, heightDp = 980)
@Composable
private fun HomePreviewExpanded() {
    Plan92Theme {
        PlannersHomeScreen(
            planners = listOf(
                MockPlannerRepository.createOwnedPlanner(MockPlannerRepository.templateById("notes_page"), 1),
            ),
            featuredTemplates = MockPlannerRepository.templates.take(6),
            readyToUseExpanded = true,
            onReadyToUseExpandedChange = {},
            onCreateNew = {},
            onOpenPlanner = {},
            onUseTemplate = {},
        )
    }
}
