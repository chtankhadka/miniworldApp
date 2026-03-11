package com.chtan.miniworld.di

import com.chtan.miniworld.data.datasource.local.UserLocalDataSource
import com.chtan.miniworld.data.datasource.network.websockets.DriveWebSocketDataSource
import com.chtan.miniworld.data.datasource.network.UserRemoteDataSource
import com.chtan.miniworld.data.datasource.network.websockets.ImageWebSocketDataSource
import com.chtan.miniworld.data.repositoryImpl.SocketRepositoryImpl
import com.chtan.miniworld.data.repositoryImpl.UserRepositoryImpl
import com.chtan.miniworld.domain.repository.SocketRepository
import com.chtan.miniworld.domain.repository.UserRepository
import org.koin.dsl.module


fun dataModule() = module {
    single<UserRepository> {
        UserRepositoryImpl(
            get(), // userRemoteDataSource
            get(), // userLocalDataSource
            get()  // driveWebSocketDataSource
        )
    }
    single<SocketRepository> {
        SocketRepositoryImpl(
            get(), // userRemoteDataSource
            get(), // userLocalDataSource
            get()  // driveWebSocketDataSource
        )
    }

    // also define these if not already done:
    single { UserRemoteDataSource(get(),get()) }
    single { UserLocalDataSource() }
    single { DriveWebSocketDataSource(get(), get()) }
    single { ImageWebSocketDataSource(get(), get()) }
}