package com.tirexmurina.viewxmlrandomusr.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tirexmurina.viewxmlrandomusr.domain.usecase.IClearDatabaseUseCase
import com.tirexmurina.viewxmlrandomusr.domain.usecase.IGetUsersUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(
    val getUsersUseCase: IGetUsersUseCase,
    val clearDatabaseUseCase: IClearDatabaseUseCase
) : ViewModel() {

    private val _state: MutableState<HomeViewState> = mutableStateOf(HomeViewState.Initial)
    val state: State<HomeViewState> = _state


    init {
        viewModelScope.launch {
            _state.value = HomeViewState.Loading
            getUsers()
        }
    }

    private fun getUsers() {
        viewModelScope.launch {
            try {
                val listUsers = getUsersUseCase()
                _state.value = HomeViewState.Content(listUsers)
            } catch (e: Exception) {
                _state.value = HomeViewState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun clearDb(){
        viewModelScope.launch{
            try {
                clearDatabaseUseCase()
            } catch (e: Exception){
                _state.value = HomeViewState.Error(e.message ?: "Unknown error")
            }
        }
    }
}