package com.test.shopping_lists.datas.shopping_saved_data.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.test.shopping_lists.datas.shopping_saved_data.MODEL.Key
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomShoppingDAO {
    @Query("SELECT * FROM Key")
    fun getSavedKeysFlow() : Flow<List<Key>>

    @Insert
    suspend fun updateSavedKeys(keys: List<Key>)

    @Query("DELETE FROM Key")
    suspend fun clearSavedKeys()

    @Insert
    suspend fun addKey(key: Key)

    @Delete
    suspend fun removeKey(key: Key)
}