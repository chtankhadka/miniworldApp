package com.chtan.miniworld.data.datasource.network.model.admin.clan


import com.chtan.miniworld.data.datasource.network.model.BaseResponse
import com.chtan.miniworld.data.datasource.network.model.authorization.SignInData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import miniworld.composeapp.generated.resources.Res


typealias GetCreatedClansResponseModel = BaseResponse<List<CreatedClans>>

@Serializable
data class CreatedClans(
    @SerialName("admin_id")
    val adminId: String,
    @SerialName("clan_details")
    val clanDetails: ClanDetails,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("id")
    val id: String,
    @SerialName("updated_at")
    val updatedAt: String
)


@Serializable
data class ClanDetails(
    @SerialName("description")
    val description: String,
    @SerialName("name")
    val name: String,
    @SerialName("tag")
    val tag: String
)