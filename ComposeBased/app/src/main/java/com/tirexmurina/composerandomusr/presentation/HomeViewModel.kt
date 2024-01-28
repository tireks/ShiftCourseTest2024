package com.tirexmurina.composerandomusr.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tirexmurina.composerandomusr.domain.entity.User
import com.tirexmurina.composerandomusr.domain.usecase.IGetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val useCase: IGetUsersUseCase
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
        /*val usersList = useCase()
        _state.value = usersList*/
    }
}

/*private val _state: MutableState<HomeViewState> = mutableStateOf(HomeViewState.Initial)
val state: State<HomeViewState> = _state*/

fun getUsers(){
    viewModelScope.launch {
        try {
            val listUsers = useCase()
            _state.value = HomeViewState.Content(listUsers)
        } catch (e: Exception){
            Log.d("BL", "EXCEPTION ${e.message}")
            _state.value = HomeViewState.Error(e.message ?: "Unknown error")
        }
    }
}
}