package com.test.shopping_lists.datas.settings_data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

fun createDataStore(path: String) : DataStore<Preferences>{
    return PreferenceDataStoreFactory.createWithPath {
        path.toPath()
    }
}

const val DATA_STORE_PATH = "dataStore.preferences_pb"