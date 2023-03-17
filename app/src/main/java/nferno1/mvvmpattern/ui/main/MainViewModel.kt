package nferno1.mvvmpattern.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Succes)

    val state = _state.asStateFlow()

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
            _state.value = State.Loading
            delay(5_000)
            _state.value = State.Succes
        }

    }

}