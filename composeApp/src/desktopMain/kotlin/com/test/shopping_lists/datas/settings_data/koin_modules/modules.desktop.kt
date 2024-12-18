package com.test.shopping_lists.datas.settings_data.koin_modules

import com.test.shopping_lists.datas.settings_data.DATA_STORE_PATH
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual val settingsDataPlatformModule: Module
    get() = module{
        single<String>(named("pref_path")){
            DATA_STORE_PATH
        }
    }