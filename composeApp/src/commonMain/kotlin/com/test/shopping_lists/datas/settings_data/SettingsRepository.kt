package com.test.shopping_lists.datas.settings_data

import com.test.shopping_lists.datas.settings_data.MODEL.SettingsUI
import com.test.shopping_lists.datas.shopping_saved_data.MODEL.Key
import kotlinx.coroutines.flow.Flow

class SettingsRepository(
    val dataSource: SettingsDataSource
) {
    suspend fun updateSelectedKey(key: Key?) {
        dataSource.updateSelectedKey(key)
    }
    fun getSelectedKeyAsFlow() : Flow<Key?>
    {
        return dataSource.getSelectedKeyAsFlow()
    }
    suspend fun updateSettingsUI(settingsUI: SettingsUI){
        dataSource.updateSettingsUI(settingsUI)
    }
    fun getSettingsUIAsFlow() : Flow<SettingsUI>{
        return dataSource.getSettingsUIAsFlow()
    }
}