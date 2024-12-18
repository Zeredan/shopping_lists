package com.test.shopping_lists.datas.shopping_saved_data.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.shopping_lists.datas.shopping_saved_data.MODEL.Key

@Database(entities = [Key::class], version = 1)
abstract class ShoppingSavedDatabase : RoomDatabase(){
    abstract fun getDao() : RoomShoppingDAO
}