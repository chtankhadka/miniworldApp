package com.chtan.miniworld.data.repositoryImpl

import com.chtan.miniworld.data.datasource.network.AdminRemoteDataSource
import com.chtan.miniworld.data.datasource.network.model.CommonResponse
import com.chtan.miniworld.data.datasource.network.model.admin.clan.AddDeviceToClanRequest
import com.chtan.miniworld.data.datasource.network.model.admin.clan.CreateClanRequest
import com.chtan.miniworld.data.datasource.network.model.admin.clan.GetCreatedClansResponseModel
import com.chtan.miniworld.domain.repository.AdminRepository
import com.chtan.miniworld.data.datasource.network.result.RemoteResult

class AdminRepositoryImpl(
    private val adminRemoteDataSource: AdminRemoteDataSource
): AdminRepository {
    override suspend fun createClan(data: CreateClanRequest): RemoteResult<CommonResponse> {
        return adminRemoteDataSource.createClan(data)
    }

    override suspend fun addDeviceInClan(data: AddDeviceToClanRequest): RemoteResult<CommonResponse> {
        return adminRemoteDataSource.addDeviceInClan(data)
    }

    override suspend fun getCreatedClans(): RemoteResult<GetCreatedClansResponseModel> {
        return adminRemoteDataSource.getCreatedClans()
    }
}
