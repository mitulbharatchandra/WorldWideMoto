package com.core.presentation.util.networkimage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun NetworkImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalPlatformContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant),
        loading = {
            ImageShimmerPlaceholder(
                modifier = Modifier.fillMaxSize()
            )
        },
        error = {
            ImageError(
                modifier = Modifier.fillMaxSize()
            )
        }
    )
}
