package com.test.shopping_lists.datas.shopping_actual_data.MODEL

import kotlinx.serialization.Serializable
import org.koin.core.component.getScopeId


data class ShoppingList(
    val created: String,
    val name: String,
    val id: Int
)