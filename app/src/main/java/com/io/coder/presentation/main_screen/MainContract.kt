package com.io.coder.presentation.main_screen

import com.io.coder.domain.model.Employee


data class MainState(
    val searchtext: String,
    val isLoading: Boolean,
    val employees: List<Employee>,
    val isError: Boolean
){
    companion object {
        fun initial() = MainState(
            searchtext = "",
            isLoading = false,
            employees = emptyList(),
            isError = false
        )
    }

}

sealed class MainAction{

    class ChangeText(val searchText: String): MainAction()
}