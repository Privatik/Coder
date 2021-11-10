package com.io.coder.presentation.main_screen

import androidx.compose.foundation.layout.*
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
import com.io.coder.presentation.main_screen.components.SearchField


@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel
){
    val state = viewModel.state.value
    val focusManager = LocalFocusManager.current

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
        DepartmentTabs()
    }
}