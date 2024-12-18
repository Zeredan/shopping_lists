package com.test.shopping_lists.datas.shopping_actual_data.DTO

import kotlinx.serialization.Serializable

@Serializable
data class ItemListDTO(
    val success: Boolean,
    val item_list: List<ItemDTO>
)
