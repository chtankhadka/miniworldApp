package com.chtan.miniworld.presentation.admin.clan.createclan

import com.chtan.miniworld.data.datasource.network.result.DataError

data class CreateAdminClanState(
    val test: String = "",

    val isClanCreated: Boolean = false,
    val error: DataError? = null
)
