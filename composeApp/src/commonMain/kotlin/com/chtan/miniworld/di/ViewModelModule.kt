package com.chtan.miniworld.di

import com.chtan.miniworld.MainViewModel
import com.chtan.miniworld.presentation.user.drive.DriveViewModel
import com.chtan.miniworld.presentation.login.SignInViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun viewModelModule() = module {
    viewModel { MainViewModel() }
    viewModel { DriveViewModel(get(),get()) }
    viewModel { SignInViewModel(get(), get()) }
}