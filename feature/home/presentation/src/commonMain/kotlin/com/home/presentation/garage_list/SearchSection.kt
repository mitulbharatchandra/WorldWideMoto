package com.home.presentation.garage_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kmp.designsystem.theme.AppTheme
import com.kmp.designsystem.theme.Spacing
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SearchSection(
    hint: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = "",
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.md, vertical = Spacing.sm)
    )
}

@Preview(showBackground = true)
@Composable
fun SearchSectionPreviewLight() {
    AppTheme {
        SearchSection(
            hint = "Search by Garage, Service or Location",
            onValueChange = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchSectionPreviewDark() {
    AppTheme(darkTheme = true) {
        SearchSection(
            hint = "Search by Garage, Service or Location",
            onValueChange = {}
        )
    }
}
