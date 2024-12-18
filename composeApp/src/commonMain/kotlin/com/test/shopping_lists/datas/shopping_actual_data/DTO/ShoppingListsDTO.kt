package com.test.shopping_lists.datas.shopping_actual_data.DTO

import kotlinx.serialization.Serializable

@Serializable
data class ShoppingListsDTO(
    val shop_list: List<ShoppingListDTO>
)
