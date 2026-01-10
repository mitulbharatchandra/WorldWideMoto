package com.home.presentation.garage_list

data class ServiceItemUi(
    val id: String,
    val title: String,
    val imageUrl: String,
    val nextSlotLabel: String,
    val distanceLabel: String,
    val ratingLabel: String,
    val priceLabel: String,
    val tags: List<String>
)