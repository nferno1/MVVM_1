package nferno1.mvvmpattern.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Succes)

    val state = _state.asStateFlow()

    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()

    init {
        Log.d(TAG, "init: ")
    }

    fun onButtonClick() {
        Log.d(TAG, "onButtonClick")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared")
    }

    fun onSignInClick(login: String, password: String) {
        viewModelScope.launch {
            val isLoginEmpty = login.isBlank()
            val isPasswordEmpty = password.isBlank()
            if (isLoginEmpty || isPasswordEmpty) {
                var loginError: String? = null
                if (isLoginEmpty){
                    loginError = "Login is required"
                }
                var passwordError: String? = null
                if (isPasswordEmpty){
                    passwordError = "Password is required"
                }
                _state.value = State.Error(loginError, passwordError)
                _error.send("Login or Password not valid")

            } else {
                _state.value = State.Loading
                delay(5_000)
                _state.value = State.Succes
            }

        }
    }
}
