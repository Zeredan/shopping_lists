package com.test.shopping_lists.datas.shopping_saved_data.koin_modules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.test.shopping_lists.datas.shopping_saved_data.Room.ShoppingSavedDatabase
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual val shoppingSavedDataPlatformModule: Module
    get() = module{
        single<RoomDatabase.Builder<ShoppingSavedDatabase>>{
            val context = get<Context>()
            val dbName = get<String>(named("db_name"))
            Room.databaseBuilder<ShoppingSavedDatabase>(
                context = context,
                name = dbName
            )
                //.setDriver(BundledSQLiteDriver())
                //.setQueryCoroutineContext(Dispatchers.IO)
        }
    }