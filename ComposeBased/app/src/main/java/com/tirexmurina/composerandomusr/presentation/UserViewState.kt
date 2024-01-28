package com.tirexmurina.composerandomusr.presentation

import com.tirexmurina.composerandomusr.domain.entity.User

sealed class UserViewState{
    object Initial : UserViewState()
    object Loading : UserViewState()
    data class Content(val data: User) : UserViewState()
    data class Error(val errorMsg: String) : UserViewState()
}
