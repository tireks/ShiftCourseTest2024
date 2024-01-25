package com.tirexmurina.composerandomusr.presentation

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
    useCase: IGetUsersUseCase
) : ViewModel() {

    private val _listOfUsers: MutableState<List<User>> = mutableStateOf(emptyList())
    val listOfUsers: State<List<User>> = _listOfUsers

    init {
        viewModelScope.launch {
            val usersList = useCase()
            _listOfUsers.value = usersList
        }
    }
}