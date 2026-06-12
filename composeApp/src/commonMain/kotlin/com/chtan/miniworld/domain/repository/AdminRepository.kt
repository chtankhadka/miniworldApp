package com.chtan.miniworld.domain.repository

import com.chtan.miniworld.data.datasource.network.model.CommonResponse
import com.chtan.miniworld.data.datasource.network.model.admin.clan.AddDeviceToClanRequest
import com.chtan.miniworld.data.datasource.network.model.admin.clan.CreateClanRequest
import com.chtan.miniworld.data.datasource.network.model.admin.clan.GetCreatedClansResponseModel
import com.chtan.miniworld.data.datasource.network.result.RemoteResult


interface AdminRepository {
    suspend fun createClan(data: CreateClanRequest): RemoteResult<CommonResponse>
    suspend fun addDeviceInClan(data: AddDeviceToClanRequest): RemoteResult<CommonResponse>
    suspend fun getCreatedClans(): RemoteResult<GetCreatedClansResponseModel>
}
