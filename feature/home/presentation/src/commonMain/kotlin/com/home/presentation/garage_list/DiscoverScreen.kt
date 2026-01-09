package com.home.presentation.garage_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DiscoverScreen(
    modifier: Modifier = Modifier,
    title: String,
    searchHint: String,
    services: List<ServiceItemUi>,
    isListSelected: Boolean,
    onGarageClick: () -> Unit = {},
    onSearchChange: (String) -> Unit,
    onViewToggle: (Boolean) -> Unit,
    imageContent: @Composable (serviceId: String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        DiscoverTopBar(title = title)
        SearchSection(
            hint = searchHint,
            onValueChange = onSearchChange
        )
        FilterChips()
        ServiceList(
            services = services,
            onGarageClick = onGarageClick,
            imageContent = imageContent
        )
    }
}

@Preview
@Composable
private fun DiscoverScreenPreview() {
    DiscoverScreen(
        title = "Discover Garages",
        searchHint = "Search for garages",
        services = GarageListProvider.garageList,
        isListSelected = true,
        onSearchChange = {},
        onViewToggle = {},
        imageContent = {}
    )
}