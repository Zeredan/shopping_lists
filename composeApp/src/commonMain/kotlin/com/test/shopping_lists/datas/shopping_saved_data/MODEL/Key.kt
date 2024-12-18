package com.test.shopping_lists.datas.shopping_saved_data.MODEL

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Key(
    @PrimaryKey val key: String,
    val name: String
)