package com.chtan.miniworld.di

import com.chtan.miniworld.MainViewModel
import com.chtan.miniworld.presentation.admin.clan.clanoverview.AdminClanOverviewViewModel
import com.chtan.miniworld.presentation.admin.clan.createclan.CreateAdminClanViewModel
import com.chtan.miniworld.presentation.user.dashboard.drive.DriveViewModel
import com.chtan.miniworld.presentation.login.SignInViewModel
import com.chtan.miniworld.presentation.user.clanOverview.UserClanOverviewViewModel
import com.chtan.miniworld.presentation.user.dashboard.UserDashboardViewModel
import com.chtan.miniworld.presentation.user.clanOverview.selectVehicle.UserSelectVehicleViewModel
import com.chtan.miniworld.presentation.user.clanOverview.startOverview.UserStartOverviewViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun viewModelModule() = module {
    viewModel { MainViewModel() }

    //UserDashboard

    viewModel { UserDashboardViewModel(get()) }



    // UserClan

    viewModel { UserClanOverviewViewModel(get())
    }
    viewModel { UserSelectVehicleViewModel(get()) }
    viewModel { UserStartOverviewViewModel(get()) }



    // Admin
    viewModel { CreateAdminClanViewModel(get()) }
    viewModel { AdminClanOverviewViewModel(get()) }



    //

    viewModel { DriveViewModel(get(),get()) }
    viewModel { SignInViewModel(get(), get()) }
}