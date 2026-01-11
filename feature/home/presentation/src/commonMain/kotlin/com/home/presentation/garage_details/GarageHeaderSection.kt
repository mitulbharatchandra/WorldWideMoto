package com.home.presentation.garage_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.home.presentation.garage_list.ServiceItemUi

@Composable
fun GarageHeaderSection(garage: ServiceItemUi) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = garage.title,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Trusted multi-brand car service center with certified mechanics.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("⭐ ${garage.ratingLabel}")
            Text(garage.distanceLabel)
            Text(garage.priceLabel)
        }
    }
}
