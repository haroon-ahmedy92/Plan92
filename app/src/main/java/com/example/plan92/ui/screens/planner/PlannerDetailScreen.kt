package com.example.plan92.ui.screens.planner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plan92.data.mock.MockPlannerRepository
import com.example.plan92.data.mock.PlannerTemplate
import com.example.plan92.planner.engine.PlannerTemplateSchema
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.plan92Palette.appBackground),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.plan92Palette.titleColor,
                    )
                }
                Text(
                    text = template.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 18.sp,
                        lineHeight = 22.sp,
                    ),
                    color = MaterialTheme.plan92Palette.titleColor,
                    maxLines = 1,
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                RoundAction(Icons.Outlined.FavoriteBorder)
                RoundAction(Icons.Outlined.Share)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 6.dp, vertical = 2.dp),
            contentAlignment = Alignment.TopCenter,
        ) {
            PlannerTemplateEditor(
                definition = definition,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun RoundAction(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
) {
    Box(
        modifier = Modifier
            .size(36.dp)
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
