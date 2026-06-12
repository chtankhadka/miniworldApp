package com.chtan.miniworld.presentation.admin.clan.createclan

sealed interface CreateAdminClanEvent {
    data class CreateClan(val name: String, val tag: String, val details: String) : CreateAdminClanEvent
    data object DismissError : CreateAdminClanEvent
}
