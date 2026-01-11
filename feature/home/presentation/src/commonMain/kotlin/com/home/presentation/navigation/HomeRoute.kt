package com.home.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute: NavKey {
    @Serializable
    data object Garages: NavKey

    @Serializable
    data class GarageDetail(val garageId: String): NavKey

    @Serializable
    data object Map: NavKey

    @Serializable
    data object History: NavKey
}