package com.tirexmurina.viewxmlrandomusr.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tirexmurina.viewxmlrandomusr.domain.usecase.IGetUserByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    val useCase: IGetUserByIdUseCase
) : ViewModel() {

    private val _state = MutableLiveData<UserViewState>(UserViewState.Initial)
    val state: LiveData<UserViewState> = _state

    fun getUserById(id: String){
        _state.value = UserViewState.Loading
        viewModelScope.launch {
            try{
                val user = useCase(id)
                _state.value = UserViewState.Content(user)
            }catch (e: Exception){
                _state.value = UserViewState.Error(e.message ?: "Unknown error")
            }
        }
    }
}