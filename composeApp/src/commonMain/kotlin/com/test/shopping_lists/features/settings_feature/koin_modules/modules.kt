package com.test.shopping_lists.features.settings_feature.koin_modules


import com.test.shopping_lists.features.settings_feature.domain.SettingsViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val settingsFeatureSharedModule = module {
    viewModelOf(::SettingsViewModel)
}