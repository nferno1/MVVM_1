package nferno1.mvvmpattern.ui.main

sealed class State {
    object Loading: State()
    object Succes: State()
    data class Error(
        val loginError: String?,
        val passwordError: String?
        ): State()
}
