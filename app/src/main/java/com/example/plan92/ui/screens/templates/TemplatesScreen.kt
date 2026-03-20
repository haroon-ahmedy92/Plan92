package com.example.plan92.ui.screens.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material.icons.outlined.Widgets
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.unit.dp
import com.example.plan92.data.mock.MockPlannerRepository
import com.example.plan92.data.mock.PlannerCategory
import com.example.plan92.data.mock.PlannerTemplate
import com.example.plan92.ui.components.PlannerTemplateCard
import com.example.plan92.ui.theme.ApricotGlow
import com.example.plan92.ui.theme.AzureDepth
import com.example.plan92.ui.theme.CoralAccent
import com.example.plan92.ui.theme.InkBlack
import com.example.plan92.ui.theme.NightShade
import com.example.plan92.ui.theme.Paper
import com.example.plan92.ui.theme.ShellWhite

@Composable
fun TemplatesScreen(
    categories: List<PlannerCategory>,
    templates: List<PlannerTemplate>,
    onOpenPlanner: (String) -> Unit,
) {
    var selectedCategoryId by rememberSaveable { mutableStateOf(categories.firstOrNull()?.id.orEmpty()) }
    val visibleTemplates = templates.filter { it.categoryId == selectedCategoryId }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = ShellWhite.copy(alpha = 0.97f),
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    categories.forEach { category ->
                        CategoryChip(
                            text = category.label,
                            selected = selectedCategoryId == category.id,
                            onClick = { selectedCategoryId = category.id },
                        )
                    }
                }
            }

            items(visibleTemplates) { template ->
                PlannerTemplateCard(
                    template = template,
                    onClick = { onOpenPlanner(template.id) },
                )
            }
        }
    }
}

@Composable
private fun CategoryChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = if (selected) CoralAccent else Color.White,
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (selected) CoralAccent else Color(0xFFE5DAEC),
        ),
    ) {
        Text(
            text = text,
            color = if (selected) InkBlack else Color(0xFF363041),
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Composable
fun WidgetPromoDialog(
    onDismiss: () -> Unit,
    onLearnMore: () -> Unit = onDismiss,
    showAsScreen: Boolean = false,
) {
    if (showAsScreen) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(NightShade.copy(alpha = 0.92f)),
            contentAlignment = Alignment.Center,
        ) {
            WidgetPromoCard(
                onDismiss = onDismiss,
                onLearnMore = onLearnMore,
            )
        }
    } else {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(usePlatformDefaultWidth = false),
        ) {
            WidgetPromoCard(
                onDismiss = onDismiss,
                onLearnMore = onLearnMore,
            )
        }
    }
}

@Composable
private fun WidgetPromoCard(
    onDismiss: () -> Unit,
    onLearnMore: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(0.92f)
            .clip(RoundedCornerShape(30.dp)),
        color = ShellWhite,
        shadowElevation = 18.dp,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp),
            modifier = Modifier.padding(18.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .width(220.dp)
                        .height(190.dp)
                        .clip(RoundedCornerShape(26.dp))
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(AzureDepth, NightShade),
                            ),
                        ),
                ) {
                    listOf(
                        0.34f to "Small",
                        0.52f to "Large",
                        0.74f to "Medium",
                    ).forEach { (offset, label) ->
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(top = (offset * 120).dp)
                                .size(if (label == "Large") 92.dp else 64.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Paper.copy(alpha = 0.95f)),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = if (label == "Large") Icons.Outlined.PhoneAndroid else Icons.Outlined.Widgets,
                                contentDescription = null,
                                tint = CoralAccent,
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
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

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "Widgets Now Available for Home Screen!",
                    style = MaterialTheme.typography.headlineMedium,
                    color = InkBlack,
                )
                Text(
                    text = "Easily view and manage your tasks directly from the home screen with polished, planner-friendly widgets.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = InkBlack.copy(alpha = 0.68f),
                )
            }

            Button(
                onClick = onLearnMore,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Learn More")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TemplatesPreview() {
    TemplatesScreen(
        categories = MockPlannerRepository.categories,
        templates = MockPlannerRepository.templates,
        onOpenPlanner = {},
    )
}

