package com.test.shopping_lists.features.lists_usage_feature.koin_modules

import com.test.shopping_lists.features.lists_usage_feature.domain.ListUsageViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val listUsageFeatureSharedModule = module {
    viewModelOf(::ListUsageViewModel)
}