package com.example.plan92.ui.screens.create

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.plan92.ui.theme.Amethyst
import com.example.plan92.ui.theme.ApricotGlow
import com.example.plan92.ui.theme.AzureDepth
import com.example.plan92.ui.theme.BurntOrange
import com.example.plan92.ui.theme.DividerSoft
import com.example.plan92.ui.theme.InkBlack
import com.example.plan92.ui.theme.OrchidBurst
import com.example.plan92.ui.theme.Paper
import com.example.plan92.ui.theme.PaperTint
import com.example.plan92.ui.theme.ShellWhite

private data class PageSizeOption(val name: String, val subtitle: String)

@Composable
fun PageStyleScreen(
    onBack: () -> Unit,
    onClose: () -> Unit = onBack,
    onBeginBlank: () -> Unit,
    onBeginLined: () -> Unit,
) {
    val pageSizes = remember {
        listOf(
            PageSizeOption("Square", "1:1"),
            PageSizeOption("iPhone", "9:16"),
            PageSizeOption("Letter", "8.5x11"),
            PageSizeOption("A4", "8.3 x 11.7"),
            PageSizeOption("Legal", "8.5x14"),
        )
    }
    val bgOptions = remember {
        listOf(
            Paper,
            PaperTint,
            AzureDepth.copy(alpha = 0.14f),
            BurntOrange.copy(alpha = 0.14f),
            Amethyst.copy(alpha = 0.14f),
            OrchidBurst.copy(alpha = 0.14f),
            ApricotGlow.copy(alpha = 0.2f),
        )
    }

    var selectedSize by rememberSaveable { mutableIntStateOf(1) }
    var selectedLayout by rememberSaveable { mutableIntStateOf(1) }
    var selectedBackground by rememberSaveable { mutableIntStateOf(0) }
    var selectedPageType by rememberSaveable { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = ShellWhite,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp, bottom = 20.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ActionCircle(onClick = onBack, text = "‹")
                Text(
                    text = "Choose a Page Style",
                    style = MaterialTheme.typography.headlineLarge,
                    color = InkBlack,
                )
                ActionCircle(onClick = onClose, text = "×")
            }

            SectionLabel("Page Size")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {
                items(pageSizes.indices.toList()) { index ->
                    val option = pageSizes[index]
                    PageStyleChoiceCard(
                        selected = selectedSize == index,
                        onClick = { selectedSize = index },
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(option.name, style = MaterialTheme.typography.titleLarge, color = InkBlack)
                            Text(option.subtitle, style = MaterialTheme.typography.titleMedium, color = InkBlack, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            DividerLine()
            SectionLabel("Page Layout")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                listOf("Landscape", "Portrait", "Single", "Spread").forEachIndexed { index, label ->
                    Surface(
                        modifier = Modifier.weight(1f),
                        onClick = { selectedLayout = index },
                        shape = RoundedCornerShape(12.dp),
                        color = if (selectedLayout == index) Paper else Color.White,
                        border = androidx.compose.foundation.BorderStroke(
                            1.5.dp,
                            if (selectedLayout == index) BurntOrange.copy(alpha = 0.55f) else DividerSoft,
                        ),
                    ) {
                        Text(
                            text = label,
                            modifier = Modifier.padding(vertical = 12.dp),
                            textAlign = TextAlign.Center,
                            color = InkBlack,
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                }
            }

            DividerLine()
            SectionLabel("Page background Color")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            androidx.compose.ui.graphics.Brush.sweepGradient(
                                listOf(BurntOrange, ApricotGlow, AzureDepth, Amethyst, OrchidBurst, BurntOrange),
                            ),
                        ),
                )
                bgOptions.forEachIndexed { index, color ->
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(color)
                            .border(
                                width = if (selectedBackground == index) 2.dp else 1.dp,
                                color = if (selectedBackground == index) BurntOrange else DividerSoft,
                                shape = RoundedCornerShape(12.dp),
                            )
                            .clickable { selectedBackground = index },
                    )
                }
            }

            DividerLine()
            SectionLabel("Page Type")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(18.dp),
            ) {
                PageTypeCard(
                    modifier = Modifier.weight(1f),
                    title = "Blank",
                    selected = selectedPageType == 0,
                    onClick = { selectedPageType = 0 },
                    preview = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(bgOptions[selectedBackground], RoundedCornerShape(16.dp)),
                        )
                    },
                )
                PageTypeCard(
                    modifier = Modifier.weight(1f),
                    title = "Lined",
                    selected = selectedPageType == 1,
                    onClick = { selectedPageType = 1 },
                    preview = {
                        LinedPreviewPage(backgroundColor = bgOptions[selectedBackground])
                    },
                )
            }

            Button(
                onClick = {
                    if (selectedPageType == 0) onBeginBlank() else onBeginLined()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
            ) {
                Text("Begin New Planner")
            }
        }
    }
}

@Composable
private fun ActionCircle(
    onClick: () -> Unit,
    text: String,
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, DividerSoft, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = InkBlack,
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
        style = MaterialTheme.typography.headlineMedium,
        color = InkBlack,
    )
}

@Composable
private fun DividerLine() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .height(1.dp)
            .background(DividerSoft, RectangleShape),
    )
}

@Composable
private fun PageStyleChoiceCard(
    selected: Boolean,
    onClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        modifier = Modifier
            .width(142.dp)
            .height(142.dp),
        onClick = onClick,
        shape = RoundedCornerShape(18.dp),
        color = Color.White,
        border = androidx.compose.foundation.BorderStroke(
            2.dp,
            if (selected) BurntOrange.copy(alpha = 0.7f) else DividerSoft,
        ),
        shadowElevation = 3.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            content = content,
        )
    }
}

@Composable
private fun PageTypeCard(
    modifier: Modifier = Modifier,
    title: String,
    selected: Boolean,
    onClick: () -> Unit,
    preview: @Composable BoxScope.() -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp),
            onClick = onClick,
            shape = RoundedCornerShape(18.dp),
            color = Color.White,
            border = androidx.compose.foundation.BorderStroke(
                2.dp,
                if (selected) BurntOrange.copy(alpha = 0.7f) else DividerSoft,
            ),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp),
                content = preview,
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = InkBlack,
        )
    }
}

@Composable
private fun LinedPreviewPage(
    backgroundColor: Color,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor, RoundedCornerShape(14.dp)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            repeat(21) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFFB7B3C2)),
                )
            }
        }
    }
}
