package com.test.shopping_lists.datas.settings_data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.test.shopping_lists.datas.settings_data.MODEL.SettingsUI
import com.test.shopping_lists.datas.shopping_saved_data.MODEL.Key
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

interface SettingsDataSource {
    suspend fun updateSelectedKey(key: Key?)
    suspend fun updateSettingsUI(settingsUI: SettingsUI)

    fun getSelectedKeyAsFlow() : Flow<Key?>
    fun getSettingsUIAsFlow() : Flow<SettingsUI>
}

class SettingsDataSourceDataStoreImpl(
    val dataStore: DataStore<Preferences>
) : SettingsDataSource {
    override suspend fun updateSelectedKey(key: Key?) {
        dataStore.edit { mPref ->
            val prefKey = stringPreferencesKey("selectedKey")
            mPref[prefKey] = Json.encodeToString(key)
        }
    }

    override suspend fun updateSettingsUI(settingsUI: SettingsUI) {
        dataStore.edit { mPref ->
            val prefKey = stringPreferencesKey("settingsUI")
            mPref[prefKey] = Json.encodeToString(settingsUI)
        }
    }

    override fun getSelectedKeyAsFlow(): Flow<Key?> {
        return dataStore.data
            .map {
                val prefKey = stringPreferencesKey("selectedKey")
                try{
                    Json.decodeFromString(it[prefKey] ?: "{}")
                }
                catch (e: Exception){
                    null
                }
            }
    }

    override fun getSettingsUIAsFlow(): Flow<SettingsUI> {
        return dataStore.data
            .map {
                val prefKey = stringPreferencesKey("settingsUI")
                try{
                    Json.decodeFromString(it[prefKey] ?: "{}")
                }
                catch (e: Exception){
                    SettingsUI.DefaultSettings
                }
            }
    }
}

class SettingsDataSourceStubImpl : SettingsDataSource{
    override suspend fun updateSelectedKey(key: Key?) {
        TODO("Not yet implemented")
    }

    override suspend fun updateSettingsUI(settingsUI: SettingsUI) {
        TODO("Not yet implemented")
    }

    override fun getSelectedKeyAsFlow(): Flow<Key?> {
        TODO("Not yet implemented")
    }

    override fun getSettingsUIAsFlow(): Flow<SettingsUI> {
        TODO("Not yet implemented")
    }

}