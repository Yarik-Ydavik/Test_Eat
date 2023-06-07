package com.example.test_eat.data.Kitchens_data

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class categor(
    val id: Int,
    @SerialName ("image_url")
    val image_url: String,
    val name: String
)