package com.tirexmurina.composerandomusr.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tirexmurina.composerandomusr.domain.usecase.IGetUserByIdUseCase
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
    val useCase: IGetUserByIdUseCase
) : ViewModel() {

    private val _state: MutableState<UserViewState> = mutableStateOf(UserViewState.Initial)
    val state: State<UserViewState> = _state

    fun getUserById(id: String){
        _state.value = UserViewState.Loading
        viewModelScope.launch {
            try{
                val user = useCase(id)
                _state.value = UserViewState.Content(user)
            }catch (e: Exception){
                Log.d("BL", "EXCEPTION ${e.message}")
                _state.value = UserViewState.Error(e.message ?: "Unknown error")
            }
        }
    }



    fun showMap(geoLocationStr: String){
        /*val geoLocationUri = Uri.parse(geoLocationStr)
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = geoLocationUri
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            requireActivity().startActivity(intent)
        }*/

    }


}