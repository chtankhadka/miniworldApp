package com.chtan.miniworld.di

import com.chtan.miniworld.data.datasource.network.httpclient.httpClient
import io.ktor.client.HttpClient
import org.koin.dsl.module

fun networkModule() = module {
    single<HttpClient> {
        httpClient
    }
}