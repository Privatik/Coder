package com.io.coder.presentation.main_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.io.coder.domain.usecase.EmployeeUseCase
import com.io.coder.domain.util.Resource
import com.io.coder.util.HandlerAction
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(
    private val useCase: EmployeeUseCase = EmployeeUseCase()
): ViewModel(), HandlerAction<MainAction> {

    private val _state = mutableStateOf(MainState.initial())
    val state: State<MainState> = _state

    init {
        loadEmployee()
    }

    private fun loadEmployee(){
        useCase().onEach { resourse ->
            when(resourse){
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        isError = false,
                        isLoading = false,
                        employees = resourse.data!!
                    )
                }
                is Resource.Error -> {
                    _state.value = MainState.initial().copy(isError = true)
                }
                is Resource.Loading -> {
                    _state.value = MainState.initial().copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun listener(action: MainAction) {
        when (action){
            is MainAction.ChangeText -> {
                _state.value = state.value.copy(searchtext = action.searchText)
            }
        }
    }
}