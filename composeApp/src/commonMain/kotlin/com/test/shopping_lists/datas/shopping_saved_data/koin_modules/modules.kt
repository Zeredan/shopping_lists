package com.test.shopping_lists.datas.shopping_saved_data.koin_modules

import androidx.room.RoomDatabase
import com.test.shopping_lists.datas.shopping_actual_data.HttpClientCreation.createHttpClient
import com.test.shopping_lists.datas.shopping_actual_data.ShoppingActualDataRepository
import com.test.shopping_lists.datas.shopping_actual_data.ShoppingActualDataSource
import com.test.shopping_lists.datas.shopping_actual_data.ShoppingActualDataSourceRemoteImpl
import com.test.shopping_lists.datas.shopping_saved_data.Room.RoomShoppingDAO
import com.test.shopping_lists.datas.shopping_saved_data.Room.ShoppingSavedDatabase
import com.test.shopping_lists.datas.shopping_saved_data.ShoppingSavedDataRepository
import com.test.shopping_lists.datas.shopping_saved_data.ShoppingSavedDataSource
import com.test.shopping_lists.datas.shopping_saved_data.ShoppingSavedDataSourceDbImpl
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val shoppingSavedDataSharedModule = module {
    single<ShoppingSavedDataRepository>{ ShoppingSavedDataRepository(get()) }
    single<ShoppingSavedDataSource>{ ShoppingSavedDataSourceDbImpl(get()) }
    single<RoomShoppingDAO>{
        val db = get<ShoppingSavedDatabase>()
        db.getDao()
    }
    single<ShoppingSavedDatabase>{
        val builder = get<RoomDatabase.Builder<ShoppingSavedDatabase>>()
        builder.build()
    }
    single<String>(named("db_name")){
        "myStorage_db"
    }
}

expect val shoppingSavedDataPlatformModule : Module