package com.test.shopping_lists.datas.shopping_actual_data.koin_modules

import com.test.shopping_lists.datas.shopping_actual_data.HttpClientCreation.createHttpClient
import com.test.shopping_lists.datas.shopping_actual_data.ShoppingActualDataRepository
import com.test.shopping_lists.datas.shopping_actual_data.ShoppingActualDataSource
import com.test.shopping_lists.datas.shopping_actual_data.ShoppingActualDataSourceRemoteImpl
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val shoppingActualDataSharedModule = module {
    single<ShoppingActualDataRepository>{ ShoppingActualDataRepository(get()) }

    single<ShoppingActualDataSource> { ShoppingActualDataSourceRemoteImpl(get(), get(named("baseUrl"))) }

    single<HttpClient>{ createHttpClient(get()) }
    single<String>(named("baseUrl")){ "https://cyberprot.ru/shopping/v2" }
}

expect val shoppingActualDataPlatformModule : Module