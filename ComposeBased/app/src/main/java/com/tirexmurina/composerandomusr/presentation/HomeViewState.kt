package com.tirexmurina.composerandomusr.presentation

import com.tirexmurina.composerandomusr.domain.entity.User

sealed class HomeViewState{
    object Initial : HomeViewState()
    object Loading : HomeViewState()
    data class Content(val data: List<User>) : HomeViewState()
    data class Error(val errorMsg: String) : HomeViewState()
}
