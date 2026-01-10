package com.home.presentation.garage_list

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.kmp.designsystem.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverTopBar(title: String) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            }
        },
        windowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp)
    )
}

@Preview
@Composable
fun DiscoverTopBarPreviewLight() {
    AppTheme {
        DiscoverTopBar(
            title = "Discover"
        )
    }
}

@Preview
@Composable
fun DiscoverTopBarPreviewDark() {
    AppTheme(darkTheme = true) {
        DiscoverTopBar(
            title = "Discover"
        )
    }
}
