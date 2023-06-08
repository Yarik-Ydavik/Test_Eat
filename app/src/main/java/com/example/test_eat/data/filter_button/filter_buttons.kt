package com.example.test_eat.data.filter_button


data class filter_button(
    val name: String,
    var enabled : Boolean,
)

val filter_buttons = listOf<filter_button>(
    filter_button(
        name = "Всё меню",
        enabled = false
    ),
    filter_button(
        name = "Салаты",
        enabled = false
    ),
    filter_button(
        name = "С рисом",
        enabled = false
    ),
    filter_button(
        name = "С рыбой",
        enabled = false
    ),
    filter_button(
        name = "Роллы",
        enabled = false
    )
)
