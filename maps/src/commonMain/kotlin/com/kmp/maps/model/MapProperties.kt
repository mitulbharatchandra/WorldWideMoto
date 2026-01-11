package com.kmp.maps.model

data class MapProperties(
    val isMyLocationEnabled: Boolean = false,
    val mapType: MapType = MapType.NORMAL
)