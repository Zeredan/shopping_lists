package com.test.shopping_lists.datas.settings_data.koin_modules

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.test.shopping_lists.datas.settings_data.SettingsDataSource
import com.test.shopping_lists.datas.settings_data.SettingsDataSourceDataStoreImpl
import com.test.shopping_lists.datas.settings_data.SettingsRepository
import com.test.shopping_lists.datas.settings_data.createDataStore
import kotlinx.coroutines.flow.combine
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val settingsDataSharedModule = module{
    singleOf(::SettingsRepository)
    single<SettingsDataSource>{ SettingsDataSourceDataStoreImpl(get()) }
    single<DataStore<Preferences>>{ createDataStore(get(named("pref_path"))) }
}

expect val settingsDataPlatformModule : Module