package com.test.shopping_lists.datas.settings_data.MODEL

import kotlinx.serialization.Serializable

@Serializable
data class SettingsUI(
    val autoExpandLists : Boolean,
    val askToDeleteKey: Boolean,
    val askToDeleteItem: Boolean,
    val askToDeleteList: Boolean
)
{
    companion object Companion {
        val DefaultSettings = SettingsUI(
            false,
            true,
            true,
            true
        )
    }
}
