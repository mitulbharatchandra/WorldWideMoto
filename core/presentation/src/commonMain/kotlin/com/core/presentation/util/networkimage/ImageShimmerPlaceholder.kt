package com.core.presentation.util.networkimage

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ImageShimmerPlaceholder(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .shimmer()
    )
}