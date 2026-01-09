package com.home.presentation.garage_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kmp.designsystem.theme.Spacing
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun ServiceList(
    services: List<ServiceItemUi>,
    onGarageClick: () -> Unit = {},
    imageContent: @Composable (serviceId: String) -> Unit
) {
    val scrollState = rememberLazyListState()
    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Spacing.md),
        verticalArrangement = Arrangement.spacedBy(Spacing.md)
    ) {
        items(services) { service ->
            ServiceCard(
                service = service,
                onGarageClick = onGarageClick,
                imageContent = imageContent
            )
        }
    }
}

@Preview
@Composable
internal fun ServiceListPreview() {
    val services = listOf(
        ServiceItemUi(
            id = "1",
            title = "Engine Overhaul",
            nextSlotLabel = "Next slot: Tomorrow, 10:00 AM",
            distanceLabel = "5 km away",
            ratingLabel = "4.8 (25 reviews)",
            priceLabel = "Starts from $500",
            tags = listOf("Engine", "Full Service")
        ),
        ServiceItemUi(
            id = "2",
            title = "Tire Change",
            nextSlotLabel = "Next slot: Today, 2:00 PM",
            distanceLabel = "2 km away",
            ratingLabel = "4.5 (15 reviews)",
            priceLabel = "Starts from $50",
            tags = listOf("Tires", "Quick Service")
        )
    )
    ServiceList(services = services) {
        Box(modifier = Modifier.fillMaxSize().background(Color.Gray))
    }
}