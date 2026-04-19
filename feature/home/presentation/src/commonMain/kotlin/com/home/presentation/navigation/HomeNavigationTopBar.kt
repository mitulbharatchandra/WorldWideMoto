package com.home.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.presentation.util.networkimage.NetworkImage
import com.home.presentation.home.HomeViewModel
import com.kmp.designsystem.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavigationTopBar(
    title: String,
    onProfileClick: () -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            if (state.userProfileUrl != null) {
                NetworkImage(
                    imageUrl = state.userProfileUrl ?: "",
                    contentDescription = "Profile Icon",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .border(1.dp, Color.White, CircleShape)
                        .clickable { onProfileClick() },
                    contentScale = ContentScale.Crop // IMPORTANT for proper avatar fill
                )
            } else {
                IconButton(onClick = onProfileClick) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Profile Icon"
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun DiscoverTopBarPreviewLight() {
    AppTheme {
        HomeNavigationTopBar(
            title = "Discover",
            onProfileClick = {}
        )
    }
}

@Preview
@Composable
fun DiscoverTopBarPreviewDark() {
    AppTheme(darkTheme = true) {
        HomeNavigationTopBar(
            title = "Discover",
            onProfileClick = {}
        )
    }
}
