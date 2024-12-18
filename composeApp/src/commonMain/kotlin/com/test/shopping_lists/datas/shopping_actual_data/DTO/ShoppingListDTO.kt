package com.test.shopping_lists.datas.shopping_actual_data.DTO


import kotlinx.serialization.Serializable

@Serializable
data class ShoppingListDTO(
    val created: String,
    val name: String,
    val id: Int
)