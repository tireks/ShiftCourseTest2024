package com.tirexmurina.composerandomusr.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tirexmurina.composerandomusr.domain.usecase.IGetUsersBySeedUseCase
import com.tirexmurina.composerandomusr.domain.usecase.IGetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// эта вьюмодель пока носит исключительно демонстративный характер,
// так как никакого юзкейса здесь не нужно - запроса нет.
// Мне в ответном письме разрешили использовать простую передачу данных между экранами
// Поэтому здесь оставлю какую-нибудь заглушку в виде инишиал стейта и все,
// пока что более здесь ничего не требуется

@HiltViewModel
class UserViewModel @Inject constructor(
    val useCase: IGetUsersBySeedUseCase
) : ViewModel() {

    private val _state: MutableState<UserViewState> = mutableStateOf(UserViewState.Initial)
    val state: State<UserViewState> = _state

    fun getUserById(id: String){
        _state.value = UserViewState.Loading
        val seed = parseSeedFromId(id)
        val num = parseNumFromId(id)
        viewModelScope.launch {
            try{
                val listUsers = useCase(getSeedMap(seed))
                _state.value = UserViewState.Content(listUsers[num-1])
            }catch (e: Exception){
                Log.d("BL", "EXCEPTION ${e.message}")
                _state.value = UserViewState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun getSeedMap(seed: String): Map<String, String> {
        return mapOf("results" to "20", "inc" to "gender,name,nat,email,cell,picture,nat,location","seed" to seed)
    }

    private fun parseSeedFromId(id: String): String {
        return id.substringBefore("|||")
    }

    private fun parseNumFromId(id: String): Int {
        return id.substringAfter("|||").toInt()
    }
}