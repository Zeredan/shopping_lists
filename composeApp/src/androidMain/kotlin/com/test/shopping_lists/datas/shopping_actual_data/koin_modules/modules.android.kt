package com.test.shopping_lists.datas.shopping_actual_data.koin_modules

import io.ktor.client.engine.HttpClientEngine
import org.koin.core.module.Module
import org.koin.dsl.module
import io.ktor.client.engine.okhttp.OkHttp

actual val shoppingActualDataPlatformModule: Module
    get() = module{
        single<HttpClientEngine> { OkHttp.create() }
    }