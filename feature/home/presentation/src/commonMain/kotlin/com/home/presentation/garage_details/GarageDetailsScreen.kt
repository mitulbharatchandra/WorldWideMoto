package com.home.presentation.garage_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.core.presentation.util.networkimage.NetworkImage
import com.home.presentation.garage_list.GarageListProvider

@Composable
fun GarageDetailsScreen(
    garageId: String,
    modifier: Modifier = Modifier,
    onBookServiceClick: (String) -> Unit
) {
    val garage = remember(garageId) {
        GarageListProvider.getGarageById(garageId)
    }

    if (garage == null) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Garage not found",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        return
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {

        item {
            NetworkImage(
                imageUrl = garage.imageUrl,
                contentDescription = garage.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )
        }

        item {
            GarageHeaderSection(garage)
        }

        item {
            GarageInfoSection(garage)
        }

        item {
            GarageTagsSection(garage.tags)
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onBookServiceClick(garage.id) },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text("Book Service")
            }
        }
    }
}
