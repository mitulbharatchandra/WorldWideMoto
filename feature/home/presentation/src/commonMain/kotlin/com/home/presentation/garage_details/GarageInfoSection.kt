package com.home.presentation.garage_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.home.presentation.garage_list.ServiceItemUi

@Composable
fun GarageInfoSection(garage: ServiceItemUi) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        InfoRow("Address", "MG Road, Bangalore")
        InfoRow("Phone", "+91 98765 43210")
        InfoRow("Working Hours", "9:00 AM – 7:00 PM")
        InfoRow("Next Available", garage.nextSlotLabel)
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}