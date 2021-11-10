package com.io.coder.presentation.main_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.io.coder.util.HandlerAction

class MainViewModel(

): ViewModel(), HandlerAction<MainAction> {

    private val _state = mutableStateOf(MainState.initial())
    val state: State<MainState> = _state


    override fun listener(action: MainAction) {
        when (action){
            is MainAction.ChangeText -> {
                _state.value = state.value.copy(searchtext = action.searchText)
            }
        }
    }
}