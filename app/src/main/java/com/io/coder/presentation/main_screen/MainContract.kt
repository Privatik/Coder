package com.io.coder.presentation.main_screen

import com.io.coder.domain.model.Employee
import com.io.coder.domain.state.Department
import com.io.coder.domain.util.Resource
import com.io.coder.presentation.main_screen.state.SortVariant


data class MainState(
    val searchText: String,
    val isLoading: Boolean,
    val employees: List<Employee>,
    val errorType: Resource.Error.Type?,
    val sortVariant: SortVariant,
){
    companion object {
        fun initial() = MainState(
            searchText = "",
            isLoading = false,
            employees = emptyList(),
            errorType = null,
            sortVariant = SortVariant.ABC
        )
    }

}

sealed class MainAction{

    class ChangeText(val searchText: String): MainAction()

    class ChangeSortVariant(val sortVariant: SortVariant): MainAction()
}