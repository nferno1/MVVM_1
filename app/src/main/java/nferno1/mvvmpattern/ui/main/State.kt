package nferno1.mvvmpattern.ui.main

sealed class State {
    object Loading: State()
    object Succes: State()
}
