package com.tirexmurina.composerandomusr.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tirexmurina.composerandomusr.domain.usecase.ClearDatabaseUseCase
import com.tirexmurina.composerandomusr.domain.usecase.IClearDatabaseUseCase
import com.tirexmurina.composerandomusr.domain.usecase.IGetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getUsersUseCase: IGetUsersUseCase,
    val clearDatabaseUseCase: IClearDatabaseUseCase
) : ViewModel() {

    /*private val _listOfUsers: MutableState<List<User>> = mutableStateOf(emptyList())
    val listOfUsers: State<List<User>> = _listOfUsers*/
    private val _state: MutableState<HomeViewState> = mutableStateOf(HomeViewState.Initial)
    val state: State<HomeViewState> = _state


    init {
        Log.d("BL", "init goes brrrrrrrrr")
        viewModelScope.launch {
            _state.value = HomeViewState.Loading
            getUsers()
            /*val usersList = getUsersUseCase()
            _state.value = usersList*/
        }
    }

    /*private val _state: MutableState<HomeViewState> = mutableStateOf(HomeViewState.Initial)
    val state: State<HomeViewState> = _state*/

    private fun getUsers() {
        viewModelScope.launch {
            try {
                val listUsers = getUsersUseCase()
                _state.value = HomeViewState.Content(listUsers)
            } catch (e: Exception) {
                Log.d("BL", "EXCEPTION ${e.message}")
                _state.value = HomeViewState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun clearDb(){
        viewModelScope.launch{
            try {
                clearDatabaseUseCase()
            } catch (e: Exception){
                Log.d("BL", "EXCEPTION ${e.message}")
                _state.value = HomeViewState.Error(e.message ?: "Unknown error")
            }
        }
    }
}