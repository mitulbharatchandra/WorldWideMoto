package com.home.presentation.garage_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kmp.designsystem.theme.AppTheme
import com.kmp.designsystem.theme.Spacing
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ServiceCard(
    service: ServiceItemUi,
    onGarageClick: () -> Unit = {},
    imageContent: @Composable (serviceId: String) -> Unit

) {
    Card(
        shape = MaterialTheme.shapes.extraLarge,
        onClick = {
            onGarageClick()
        }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            ) {
                imageContent(service.id)
            }

            Column(
                modifier = Modifier.padding(Spacing.md),
                verticalArrangement = Arrangement.spacedBy(Spacing.xs)
            ) {
                Text(
                    text = service.nextSlotLabel,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = service.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = service.distanceLabel + " • " + service.ratingLabel,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = service.priceLabel,
                    style = MaterialTheme.typography.bodyMedium
                )
                TagRow(tags = service.tags)
            }
        }
    }
}

@Preview(name = "Light theme")
@Composable
private fun ServiceCardPreview() {
    AppTheme {
        ServiceCard(
            service = ServiceItemUi(
                id = "1",
                title = "Mobile mechanic",
                nextSlotLabel = "NEXT SLOT: 2:00 PM",
                distanceLabel = "5 miles away",
                ratingLabel = "4.9 (120)",
                priceLabel = "$50 - $100",
                tags = listOf("Mobile", "Mechanic", "On-demand")
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .background(Color.Gray)
            )
        }
    }
}