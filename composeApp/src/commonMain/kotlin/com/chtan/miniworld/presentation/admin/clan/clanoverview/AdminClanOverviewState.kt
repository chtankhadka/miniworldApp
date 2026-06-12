package com.chtan.miniworld.presentation.admin.clan.clanoverview

import com.chtan.miniworld.data.datasource.network.model.admin.clan.CreatedClans
import com.chtan.miniworld.data.datasource.network.result.DataError

data class AdminClanOverviewState(
    val clanList: List<CreatedClans> = emptyList(),
    val isClanCreated: Boolean = false,
    val error: DataError? = null
)
