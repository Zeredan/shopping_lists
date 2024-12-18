package com.test.shopping_lists.datas.shopping_saved_data.koin_modules

import androidx.room.RoomDatabase
import com.test.shopping_lists.datas.shopping_saved_data.Room.ShoppingSavedDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val shoppingSavedDataPlatformModule: Module
    get() = module{
        /*single<RoomDatabase.Builder<ShoppingSavedDatabase>>{

        }*/
    }