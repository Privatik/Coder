package com.io.coder.presentation.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavController
import com.io.coder.domain.state.Department
import com.io.coder.presentation.main_screen.MainAction.ChangeText
import com.io.coder.presentation.main_screen.components.DepartmentTabs
import com.io.coder.presentation.main_screen.components.ItemEmployee
import com.io.coder.presentation.main_screen.components.SearchField
import com.io.coder.presentation.util.Screen


@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel
){
    val state = viewModel.state.value
    val focusManager = LocalFocusManager.current

    val tabsList = listOf(
        Department.ALL,
        Department.ANDROID,
        Department.IOS,
        Department.FRONTEND,
        Department.BACKEND,
        Department.BACK_OFFICE,
        Department.DESIGN,
        Department.HR,
        Department.PR,
        Department.MANAGEMENT,
        Department.QA,
        Department.SUPPORT,
        Department.ANALYTICS
    )

    if (state.isError){
        navController.navigate(Screen.ErrorScreen.route)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchField(
            searchText = state.searchtext,
            onChangeSearchText = {
                 viewModel.listener(ChangeText(it))
            },
            onClickSortButton = {
                //to do
            },
            onClickCancelButton = {
                focusManager.clearFocus()
            },
            onClickCancelButtonInSearchField = {
                viewModel.listener(ChangeText(""))
            }
        )
        DepartmentTabs(
            tabsList = tabsList
        ) { page ->
            if (state.employees.isEmpty()){
                items(30){
                    ItemEmployee(
                        name = null,
                        department = null,
                        urlImageEmployee = null)
                }
            } else {
                items(state.employees){ employee ->
                    ItemEmployee(
                        name = "${employee.firstName} ${employee.lastName}",
                        department = employee.department,
                        urlImageEmployee = employee.avatarUrl)
                }
            }
        }
    }
}