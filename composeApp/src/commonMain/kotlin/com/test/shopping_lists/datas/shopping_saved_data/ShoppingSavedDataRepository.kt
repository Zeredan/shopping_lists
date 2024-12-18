package com.test.shopping_lists.datas.shopping_saved_data

import com.test.shopping_lists.datas.shopping_saved_data.MODEL.Key
import kotlinx.coroutines.flow.Flow

class ShoppingSavedDataRepository(
    val dataSource: ShoppingSavedDataSource
){
    fun getSavedKeysFlow() : Flow<List<Key>> {
        return dataSource.getSavedKeysFlow()
    }
    suspend fun updateSavedKeys(keys: List<Key>){
        dataSource.updateSavedKeys(keys)
    }
    suspend fun removeKey(key: Key){
        dataSource.removeKey(key)
    }
    suspend fun addKey(key: Key){
        dataSource.addKey(key)
    }
}