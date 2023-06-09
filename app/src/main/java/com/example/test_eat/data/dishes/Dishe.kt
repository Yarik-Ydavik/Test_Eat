package com.example.test_eat.data.dishes

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Dishe(
    val description: String,
    val id: Int,
    @SerialName ("image_url")
    val image_url: String = "",
    val name: String,
    val price: Int,
    val tegs: List<String>,
    val weight: Int,
    var count : Int = 1
)