package com.io.coder.presentation.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.io.coder.presentation.main_screen.components.MainAction.ChangeText
import com.io.coder.presentation.main_screen.components.SearchField


@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel
){
    val state = viewModel.state.value

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchField(
            searchText = state.searchtext,
            onChangeSearchText = {
                 viewModel.listener(ChangeText(it))
            },
            onClickSortButton = {

                //viewModel.listener(ChangeText(it))
            }
        )
    }
}