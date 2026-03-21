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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.plan92.ui.theme.Amethyst
import com.example.plan92.ui.theme.ApricotGlow
import com.example.plan92.ui.theme.AzureDepth
import com.example.plan92.ui.theme.BurntOrange
import com.example.plan92.ui.theme.InkBlack
import com.example.plan92.ui.theme.MintAccent
import com.example.plan92.ui.theme.OrchidBurst
import com.example.plan92.ui.theme.Paper
import com.example.plan92.ui.theme.RoseAccent
import com.example.plan92.ui.theme.RoyalIndigo
import com.example.plan92.ui.theme.plan92Palette
import kotlin.math.roundToInt

data class PageStyleResult(
    val templateId: String,
    val title: String,
    val tagline: String,
)

private data class PageSizeOption(
    val name: String,
    val subtitle: String,
    val portraitRatio: Float,
)

private data class PageTypeOption(
    val title: String,
    val templateId: String,
)

private data class PageColorOption(
    val background: Color,
    val accent: Color,
)

private enum class PageOrientation(val label: String) {
    Landscape("Landscape"),
    Portrait("Portrait"),
}

private enum class PageSpread(val label: String) {
    Single("Single"),
    Spread("Spread"),
}

@Composable
fun PageStyleScreen(
    onBack: () -> Unit,
    onClose: () -> Unit = onBack,
    onBeginPlanner: (PageStyleResult) -> Unit,
) {
    val pageSizes = remember {
        listOf(
            PageSizeOption("Square", "1:1", 1f),
            PageSizeOption("iPhone", "9:16", 9f / 16f),
            PageSizeOption("Letter", "8.5x11", 8.5f / 11f),
            PageSizeOption("A4", "8.3 x 11.7", 8.3f / 11.7f),
            PageSizeOption("Standard", "8.5x11", 8.5f / 11f),
            PageSizeOption("Legal", "8.5x14", 8.5f / 14f),
            PageSizeOption("Tabloid", "11x17", 11f / 17f),
            PageSizeOption("Traveler's", "4.3x8.3", 4.3f / 8.3f),
        )
    }
    val pageTypes = remember {
        listOf(
            PageTypeOption("Blank", "blank_page"),
            PageTypeOption("Lined", "notes_page"),
            PageTypeOption("Grid", "grid_page"),
            PageTypeOption("Dot Grid", "dot_grid_page"),
        )
    }
    val presetColors = listOf(
        PageColorOption(Paper, MaterialTheme.plan92Palette.primaryAccent),
        PageColorOption(RoseAccent, RoseAccent),
        PageColorOption(OrchidBurst, OrchidBurst),
        PageColorOption(Amethyst, Amethyst),
        PageColorOption(RoyalIndigo, RoyalIndigo),
        PageColorOption(AzureDepth, AzureDepth),
        PageColorOption(MintAccent, MintAccent),
        PageColorOption(ApricotGlow, BurntOrange),
    )

    var selectedSizeIndex by rememberSaveable { mutableIntStateOf(1) }
    var selectedOrientation by rememberSaveable { mutableStateOf(PageOrientation.Portrait) }
    var selectedSpread by rememberSaveable { mutableStateOf(PageSpread.Single) }
    var selectedPageTypeIndex by rememberSaveable { mutableIntStateOf(0) }
    var selectedColorIndex by rememberSaveable { mutableIntStateOf(0) }
    var customColorArgb by rememberSaveable { mutableIntStateOf(Paper.toArgb()) }
    var showCustomColorPicker by rememberSaveable { mutableStateOf(false) }

    val selectedSize = pageSizes[selectedSizeIndex]
    val selectedPageType = pageTypes[selectedPageTypeIndex]
    val selectedBackground = if (selectedColorIndex == -1) Color(customColorArgb) else presetColors[selectedColorIndex].background
    val selectionAccent = if (selectedColorIndex == -1) lerp(Color(customColorArgb), InkBlack, 0.18f) else presetColors[selectedColorIndex].accent

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.plan92Palette.appBackground,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp, bottom = 18.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                HeaderButton(onClick = onBack, dark = false, text = "‹")
                Text(
                    text = "Choose a Page Style",
                    style = MaterialTheme.typography.headlineLarge,
                    color = InkBlack,
                )
                HeaderButton(onClick = onClose, dark = true, text = "×")
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
            ) {
                SectionLabel("Page Size")
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                ) {
                    items(pageSizes.indices.toList()) { index ->
                        val option = pageSizes[index]
                        PageStyleChoiceCard(
                            selected = selectedSizeIndex == index,
                            accent = selectionAccent,
                            onClick = { selectedSizeIndex = index },
                            width = 118.dp,
                            height = 126.dp,
                        ) {
                            Text(
                                text = option.name,
                                style = MaterialTheme.typography.titleMedium,
                                color = InkBlack,
                                textAlign = TextAlign.Center,
                            )
                            Text(
                                text = option.subtitle,
                                style = MaterialTheme.typography.titleMedium,
                                color = InkBlack,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }

                DividerLine()
                SectionLabel("Page Layout")
                PageLayoutSelector(
                    accent = selectionAccent,
                    selectedOrientation = selectedOrientation,
                    onOrientationSelected = { selectedOrientation = it },
                    selectedSpread = selectedSpread,
                    onSpreadSelected = { selectedSpread = it },
                )

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
                                Brush.sweepGradient(
                                    listOf(
                                        MaterialTheme.plan92Palette.primaryAccent,
                                        RoseAccent,
                                        OrchidBurst,
                                        RoyalIndigo,
                                        MintAccent,
                                        BurntOrange,
                                        MaterialTheme.plan92Palette.primaryAccent,
                                    ),
                                ),
                            )
                            .clickable { showCustomColorPicker = true },
                    )
                    presetColors.forEachIndexed { index, option ->
                        ColorSwatch(
                            color = option.background,
                            selected = selectedColorIndex == index,
                            accent = option.accent,
                            onClick = { selectedColorIndex = index },
                        )
                    }
                    ColorSwatch(
                        color = Color(customColorArgb),
                        selected = selectedColorIndex == -1,
                        accent = selectionAccent,
                        onClick = { showCustomColorPicker = true },
                    )
                }

                DividerLine()
                SectionLabel("Page Type")
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    pageTypes.chunked(2).forEach { row ->
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            row.forEachIndexed { index, option ->
                                val actualIndex = pageTypes.indexOf(option)
                                PageTypeCard(
                                    modifier = Modifier.weight(1f),
                                    title = option.title,
                                    selected = selectedPageTypeIndex == actualIndex,
                                    accent = selectionAccent,
                                    onClick = { selectedPageTypeIndex = actualIndex },
                                ) {
                                    ResponsivePagePreview(
                                        pageType = option.title,
                                        backgroundColor = selectedBackground,
                                        portraitRatio = selectedSize.portraitRatio,
                                        orientation = selectedOrientation,
                                        spread = selectedSpread,
                                    )
                                }
                            }
                            if (row.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }

            Button(
                onClick = {
                    onBeginPlanner(
                        PageStyleResult(
                            templateId = selectedPageType.templateId,
                            title = "${selectedSize.name} ${selectedPageType.title}",
                            tagline = "${selectedOrientation.label} • ${selectedSpread.label} • ${selectedSize.subtitle}",
                        ),
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 18.dp),
                colors = ButtonDefaults.buttonColors(containerColor = selectionAccent),
            ) {
                Text(
                    text = "Begin New Planner  →",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }

    if (showCustomColorPicker) {
        SolidColorPickerDialog(
            initialColor = selectedBackground,
            onDismiss = { showCustomColorPicker = false },
            onSave = { color ->
                customColorArgb = color.toArgb()
                selectedColorIndex = -1
                showCustomColorPicker = false
            },
        )
    }
}

@Composable
private fun HeaderButton(
    onClick: () -> Unit,
    dark: Boolean,
    text: String,
) {
    Box(
        modifier = Modifier
            .size(42.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(if (dark) MaterialTheme.plan92Palette.titleColor else MaterialTheme.plan92Palette.fieldSurface)
            .border(1.dp, if (dark) MaterialTheme.plan92Palette.titleColor else MaterialTheme.plan92Palette.lineColor, RoundedCornerShape(14.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = if (dark) Color.White else MaterialTheme.plan92Palette.titleColor,
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
            .padding(horizontal = 16.dp, vertical = 14.dp)
            .height(1.dp)
            .background(MaterialTheme.plan92Palette.lineColor, RectangleShape),
    )
}

@Composable
private fun PageLayoutSelector(
    accent: Color,
    selectedOrientation: PageOrientation,
    onOrientationSelected: (PageOrientation) -> Unit,
    selectedSpread: PageSpread,
    onSpreadSelected: (PageSpread) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            LayoutChoiceChip(
                modifier = Modifier.weight(1f),
                text = "Landscape",
                accent = accent,
                selected = selectedOrientation == PageOrientation.Landscape,
                onClick = { onOrientationSelected(PageOrientation.Landscape) },
            )
            LayoutChoiceChip(
                modifier = Modifier.weight(1f),
                text = "Portrait",
                accent = accent,
                selected = selectedOrientation == PageOrientation.Portrait,
                onClick = { onOrientationSelected(PageOrientation.Portrait) },
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            LayoutChoiceChip(
                modifier = Modifier.weight(1f),
                text = "Single",
                accent = accent,
                selected = selectedSpread == PageSpread.Single,
                onClick = { onSpreadSelected(PageSpread.Single) },
            )
            LayoutChoiceChip(
                modifier = Modifier.weight(1f),
                text = "Spread",
                accent = accent,
                selected = selectedSpread == PageSpread.Spread,
                onClick = { onSpreadSelected(PageSpread.Spread) },
            )
        }
    }
}

@Composable
private fun LayoutChoiceChip(
    modifier: Modifier = Modifier,
    text: String,
    accent: Color,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        color = if (selected) accent.copy(alpha = 0.18f) else MaterialTheme.plan92Palette.fieldSurface,
        border = androidx.compose.foundation.BorderStroke(
            1.5.dp,
            if (selected) accent else MaterialTheme.plan92Palette.lineColor,
        ),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = 12.dp),
            textAlign = TextAlign.Center,
            color = InkBlack,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
private fun PageStyleChoiceCard(
    selected: Boolean,
    accent: Color,
    onClick: () -> Unit,
    width: androidx.compose.ui.unit.Dp,
    height: androidx.compose.ui.unit.Dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        modifier = Modifier
            .width(width)
            .height(height),
        onClick = onClick,
        shape = RoundedCornerShape(18.dp),
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = androidx.compose.foundation.BorderStroke(
            2.dp,
            if (selected) accent else MaterialTheme.plan92Palette.lineColor,
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
private fun ColorSwatch(
    color: Color,
    selected: Boolean,
    accent: Color,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(54.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color)
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = if (selected) accent else MaterialTheme.plan92Palette.lineColor,
                shape = RoundedCornerShape(12.dp),
            )
            .clickable(onClick = onClick),
    )
}

@Composable
private fun PageTypeCard(
    modifier: Modifier = Modifier,
    title: String,
    selected: Boolean,
    accent: Color,
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
                .height(252.dp),
            onClick = onClick,
            shape = RoundedCornerShape(18.dp),
            color = MaterialTheme.plan92Palette.fieldSurface,
            border = androidx.compose.foundation.BorderStroke(
                2.dp,
                if (selected) accent else MaterialTheme.plan92Palette.lineColor,
            ),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp),
                contentAlignment = Alignment.Center,
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
private fun ResponsivePagePreview(
    pageType: String,
    backgroundColor: Color,
    portraitRatio: Float,
    orientation: PageOrientation,
    spread: PageSpread,
) {
    val ratio = if (orientation == PageOrientation.Portrait) portraitRatio else 1f / portraitRatio
    if (spread == PageSpread.Single) {
        PreviewSheet(
            modifier = Modifier.fillMaxWidth(0.75f),
            ratio = ratio,
            backgroundColor = backgroundColor,
            pageType = pageType,
        )
    } else {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PreviewSheet(
                modifier = Modifier.weight(1f),
                ratio = ratio,
                backgroundColor = backgroundColor,
                pageType = pageType,
            )
            PreviewSheet(
                modifier = Modifier.weight(1f),
                ratio = ratio,
                backgroundColor = backgroundColor,
                pageType = pageType,
            )
        }
    }
}

@Composable
private fun PreviewSheet(
    modifier: Modifier,
    ratio: Float,
    backgroundColor: Color,
    pageType: String,
) {
    Box(
        modifier = modifier
            .aspectRatio(ratio)
            .clip(RoundedCornerShape(14.dp))
            .background(backgroundColor)
            .border(1.dp, MaterialTheme.plan92Palette.lineColor, RoundedCornerShape(14.dp))
            .padding(10.dp),
    ) {
        when (pageType) {
            "Lined" -> PreviewLines()
            "Grid" -> PreviewGrid()
            "Dot Grid" -> PreviewDots()
            else -> Unit
        }
    }
}

@Composable
private fun PreviewLines() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        repeat(18) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.plan92Palette.lineColor.copy(alpha = 0.85f)),
            )
        }
    }
}

@Composable
private fun PreviewGrid() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            repeat(16) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.plan92Palette.lineColor.copy(alpha = 0.75f)),
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            repeat(8) {
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxSize()
                        .background(MaterialTheme.plan92Palette.lineColor.copy(alpha = 0.75f)),
                )
            }
        }
    }
}

@Composable
private fun PreviewDots() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        repeat(14) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                repeat(8) {
                    Box(
                        modifier = Modifier
                            .size(3.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.plan92Palette.bodyColor.copy(alpha = 0.72f)),
                    )
                }
            }
        }
    }
}

@Composable
private fun SolidColorPickerDialog(
    initialColor: Color,
    onDismiss: () -> Unit,
    onSave: (Color) -> Unit,
) {
    var red by remember { mutableFloatStateOf(initialColor.red * 255f) }
    var green by remember { mutableFloatStateOf(initialColor.green * 255f) }
    var blue by remember { mutableFloatStateOf(initialColor.blue * 255f) }
    val previewColor = Color(red.roundToInt(), green.roundToInt(), blue.roundToInt())
    val swatches = listOf(
        MaterialTheme.plan92Palette.appBackground,
        RoseAccent,
        OrchidBurst,
        Amethyst,
        RoyalIndigo,
        AzureDepth,
        MintAccent,
        ApricotGlow,
    )

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(0.9f),
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.plan92Palette.pageSurface,
            shadowElevation = 18.dp,
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    HeaderButton(onClick = onDismiss, dark = false, text = "‹")
                    Text(
                        text = "Solid",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.plan92Palette.titleColor,
                    )
                    Surface(
                        onClick = { onSave(previewColor) },
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.plan92Palette.fieldSurface,
                        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
                    ) {
                        Text(
                            text = "SAVE",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.plan92Palette.primaryAccent,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }

                Text("Preview", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.plan92Palette.titleColor)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    PreviewMiniSwatch("New", previewColor, Modifier.weight(1f))
                    PreviewMiniSwatch("Current", initialColor, Modifier.weight(1f))
                }

                Text("Select Color", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.plan92Palette.titleColor)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    MaterialTheme.plan92Palette.fieldSurface,
                                    previewColor,
                                    InkBlack.copy(alpha = 0.8f),
                                ),
                            ),
                        ),
                )

                Slider(value = red, onValueChange = { red = it }, valueRange = 0f..255f)
                Slider(value = green, onValueChange = { green = it }, valueRange = 0f..255f)
                Slider(value = blue, onValueChange = { blue = it }, valueRange = 0f..255f)

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    ColorInfoField("Hex", "%02x%02x%02x".format(red.roundToInt(), green.roundToInt(), blue.roundToInt()), Modifier.weight(1.4f))
                    ColorInfoField("R", red.roundToInt().toString(), Modifier.weight(0.8f))
                    ColorInfoField("G", green.roundToInt().toString(), Modifier.weight(0.8f))
                    ColorInfoField("B", blue.roundToInt().toString(), Modifier.weight(0.8f))
                }

                Text("Custom Colors", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.plan92Palette.titleColor)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    swatches.forEach { swatch ->
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(swatch)
                                .border(1.dp, MaterialTheme.plan92Palette.lineColor, RoundedCornerShape(10.dp))
                                .clickable {
                                    red = swatch.red * 255f
                                    green = swatch.green * 255f
                                    blue = swatch.blue * 255f
                                },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PreviewMiniSwatch(
    label: String,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color)
                .border(1.dp, MaterialTheme.plan92Palette.lineColor, RoundedCornerShape(10.dp)),
        )
        Text(label, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.plan92Palette.titleColor)
    }
}

@Composable
private fun ColorInfoField(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.plan92Palette.fieldSurface,
            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center,
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = {},
                    textStyle = TextStyle(
                        color = MaterialTheme.plan92Palette.titleColor,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                    ),
                    readOnly = true,
                )
            }
        }
        Text(label, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.plan92Palette.titleColor)
    }
}

private fun Color.toArgb(): Int {
    val a = (alpha * 255).roundToInt()
    val r = (red * 255).roundToInt()
    val g = (green * 255).roundToInt()
    val b = (blue * 255).roundToInt()
    return (a shl 24) or (r shl 16) or (g shl 8) or b
}
