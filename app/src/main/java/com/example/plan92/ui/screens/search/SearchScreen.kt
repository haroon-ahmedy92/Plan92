package com.example.plan92.ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plan92.data.mock.MockPlannerRepository
import com.example.plan92.ui.theme.CoralAccent
import com.example.plan92.ui.theme.ShellWhite

@Composable
fun SearchScreen(
    popularTags: List<String>,
) {
    var query by rememberSaveable { mutableStateOf("") }
    val filteredTags = popularTags.filter { tag ->
        query.isBlank() || tag.contains(query, ignoreCase = true)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = ShellWhite.copy(alpha = 0.97f),
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(18.dp),
                    placeholder = {
                        Text("Search here...")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null,
                        )
                    },
                )
            }

            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        text = "Popular Tags",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color(0xFF171321),
                    )
                    Text(
                        text = "Fast-jump into the most common planner searches from the screenshots.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF5B5568),
                    )
                }
            }

            items(filteredTags) { tag ->
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = Color.White,
                    tonalElevation = 2.dp,
                ) {
                    RowItem(tag = tag)
                }
            }
        }
    }
}

@Composable
private fun RowItem(tag: String) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = null,
            tint = CoralAccent,
        )
        Text(
            text = tag,
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF171321),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchPreview() {
    SearchScreen(popularTags = MockPlannerRepository.popularTags)
}

