package com.test.shopping_lists.features.settings_feature.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.shopping_lists.datas.settings_data.MODEL.SettingsUI
import com.test.shopping_lists.datas.settings_data.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    val settingsRepository: SettingsRepository
) : ViewModel() {
    val settingsUI = settingsRepository.getSettingsUIAsFlow()
        .stateIn(viewModelScope, started = SharingStarted.Eagerly, SettingsUI.DefaultSettings)

    fun updateSettingsUI(settings: SettingsUI) {
        viewModelScope.launch {
            settingsRepository.updateSettingsUI(settings)
        }
    }
}