package com.test.shopping_lists.features.keys_management_feature.koin_modules

import com.test.shopping_lists.features.keys_management_feature.domain.KeysManagementViewModel
import com.test.shopping_lists.features.lists_usage_feature.domain.ListUsageViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val keysManagementFeatureSharedModule = module {
    viewModelOf(::KeysManagementViewModel)
}