package com.home.presentation.garage_list

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kmp.designsystem.theme.AppTheme
import com.kmp.designsystem.theme.Spacing
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun FilterChips() {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = Spacing.md),
        horizontalArrangement = Arrangement.spacedBy(Spacing.sm)
    ) {
        FilterChip("Distance")
        FilterChip("Price")
        FilterChip("Rating")
        FilterChip("Service Type")
    }
}

@Composable
private fun FilterChip(label: String) {
    AssistChip(
        onClick = {},
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun FilterChipsPreviewLight() {
    AppTheme {
        FilterChips()
    }
}

@Preview(showBackground = true)
@Composable
fun FilterChipsPreviewDark() {
    AppTheme(darkTheme = true) {
        FilterChips()
    }
}
