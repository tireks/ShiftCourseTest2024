package com.tirexmurina.viewxmlrandomusr.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tirexmurina.viewxmlrandomusr.domain.usecase.IClearDatabaseUseCase
import com.tirexmurina.viewxmlrandomusr.domain.usecase.IGetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getUsersUseCase: IGetUsersUseCase,
    val clearDatabaseUseCase: IClearDatabaseUseCase
) : ViewModel() {

    private val _state = MutableLiveData<HomeViewState>(HomeViewState.Initial)
    val state: LiveData<HomeViewState> = _state


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