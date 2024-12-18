package com.test.shopping_lists.features.keys_management_feature.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.shopping_lists.datas.settings_data.MODEL.SettingsUI
import com.test.shopping_lists.datas.settings_data.SettingsDataSourceDataStoreImpl
import com.test.shopping_lists.datas.settings_data.SettingsRepository
import com.test.shopping_lists.datas.shopping_actual_data.ShoppingActualDataRepository
import com.test.shopping_lists.datas.shopping_saved_data.MODEL.Key
import com.test.shopping_lists.datas.shopping_saved_data.ShoppingSavedDataRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class KeysManagementViewModel(
    val actualDataRepository: ShoppingActualDataRepository,
    val savedDataRepository: ShoppingSavedDataRepository,
    val settingsRepository: SettingsRepository
) : ViewModel() {
    val keys = savedDataRepository.getSavedKeysFlow()
    val selectedKey = settingsRepository.getSelectedKeyAsFlow()
        .stateIn(viewModelScope, started = SharingStarted.Eagerly, initialValue = null)
    val settingsUI = settingsRepository.getSettingsUIAsFlow()
        .stateIn(viewModelScope, started = SharingStarted.Eagerly, initialValue = SettingsUI.DefaultSettings)

    val inputNameStateFlow = MutableStateFlow("")
    val inputKeyStateFlow = MutableStateFlow("")

    fun updateInputName(str: String){
        inputNameStateFlow.value = str
    }
    fun updateInputKey(str: String){
        if (str.length < 7){
            inputKeyStateFlow.value = str
        }
    }

    fun selectKey(k: Key){
        viewModelScope.launch {
            settingsRepository.updateSelectedKey(k)
        }
    }
    fun removeKey(k: Key){
        viewModelScope.launch {
            if (selectedKey.value == k) settingsRepository.updateSelectedKey(null)
            savedDataRepository.removeKey(k)
        }
    }
    fun updateKeys(vararg keys: Key){
        viewModelScope.launch {
            savedDataRepository.updateSavedKeys(keys.toList())
        }
    }
    fun generateKey(name: String){
        viewModelScope.launch {
            savedDataRepository.addKey(
                Key(
                    actualDataRepository.createKey(),
                    name
                )
            )
        }
    }
    fun addKey(key: Key) : Deferred<Boolean>{
        return viewModelScope.async {
            val isOk = actualDataRepository.authorize(key.key)
            if (isOk) {
                savedDataRepository.addKey(key)
            }
            isOk
        }
    }
}