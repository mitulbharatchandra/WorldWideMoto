package com.home.presentation.garage_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kmp.designsystem.theme.Spacing
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TagRow(tags: List<String>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
        modifier = Modifier.padding(top = Spacing.sm)
    ) {
        tags.forEach { tag ->
            AssistChip(
                onClick = {},
                label = {
                    Text(
                        text = tag,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun TagRowPreview() {
    TagRow(tags = listOf("Cruiser", "Adventure", "Touring"))
}