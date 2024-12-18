package com.test.shopping_lists.datas.shopping_saved_data

import com.test.shopping_lists.datas.shopping_saved_data.MODEL.Key
import com.test.shopping_lists.datas.shopping_saved_data.Room.RoomShoppingDAO
import kotlinx.coroutines.flow.Flow

interface ShoppingSavedDataSource {
    fun getSavedKeysFlow() : Flow<List<Key>>
    suspend fun updateSavedKeys(keys: List<Key>)
    suspend fun removeKey(key: Key)
    suspend fun addKey(key: Key)
}

class ShoppingSavedDataSourceDbImpl(
    val dao: RoomShoppingDAO
) : ShoppingSavedDataSource{
    override fun getSavedKeysFlow(): Flow<List<Key>> {
        return dao.getSavedKeysFlow()
    }

    override suspend fun updateSavedKeys(keys: List<Key>) {
        dao.clearSavedKeys()
        dao.updateSavedKeys(keys)
    }

    override suspend fun removeKey(key: Key) {
        dao.removeKey(key)
    }

    override suspend fun addKey(key: Key) {
        dao.addKey(key)
    }
}