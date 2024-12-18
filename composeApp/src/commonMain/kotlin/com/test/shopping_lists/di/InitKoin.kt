package com.test.shopping_lists.di

import com.test.shopping_lists.datas.settings_data.koin_modules.settingsDataPlatformModule
import com.test.shopping_lists.datas.settings_data.koin_modules.settingsDataSharedModule
import com.test.shopping_lists.datas.shopping_actual_data.koin_modules.shoppingActualDataPlatformModule
import com.test.shopping_lists.datas.shopping_actual_data.koin_modules.shoppingActualDataSharedModule
import com.test.shopping_lists.datas.shopping_saved_data.koin_modules.shoppingSavedDataPlatformModule
import com.test.shopping_lists.datas.shopping_saved_data.koin_modules.shoppingSavedDataSharedModule
import com.test.shopping_lists.features.keys_management_feature.koin_modules.keysManagementFeatureSharedModule
import com.test.shopping_lists.features.lists_usage_feature.koin_modules.listUsageFeatureSharedModule
import com.test.shopping_lists.features.settings_feature.koin_modules.settingsFeatureSharedModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(cfg: KoinAppDeclaration? = null){
    startKoin{
        cfg?.invoke(this)
        this.modules(
            //data modules
            shoppingActualDataSharedModule,
            shoppingActualDataPlatformModule,

            shoppingSavedDataSharedModule,
            shoppingSavedDataPlatformModule,

            settingsDataSharedModule,
            settingsDataPlatformModule,

            //feature modules
            listUsageFeatureSharedModule,
            keysManagementFeatureSharedModule,
            settingsFeatureSharedModule
        )
    }
}