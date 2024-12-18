package com.test.shopping_lists.datas.shopping_saved_data.koin_modules

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.test.shopping_lists.datas.shopping_saved_data.Room.ShoppingSavedDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module

actual val shoppingSavedDataPlatformModule: Module
    get() = module{
        /*single<RoomDatabase.Builder<ShoppingSavedDatabase>>{
            val dbFilePath = NSHomeDirectory() + "/my_room.db"
            Room.databaseBuilder<ShoppingSavedDatabase>(
                name = dbFilePath,
                factory =  { ShoppingSavedDatabase::class }
            )
                .setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.IO)
        }*/
    }