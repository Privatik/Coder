package com.io.coder.presentation.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.io.coder.domain.model.Employee
import com.io.coder.domain.state.Department
import com.io.coder.domain.util.filterByDepartment
import com.io.coder.domain.util.filterBySearch
import com.io.coder.domain.util.tripleSortBirthDayAndNextYear
import com.io.coder.presentation.error_screen.ErrorScreen
import com.io.coder.presentation.error_screen.model_parcelize.toParcelize
import com.io.coder.presentation.main_screen.components.*
import com.io.coder.presentation.main_screen.state.SortVariant
import com.io.coder.presentation.util.Screen
import com.io.coder.presentation.util.navigateC

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainContent(
    navController: NavController,
    viewModel: MainViewModel,
    sortVariant: SortVariant,
    onClickShowBottomSheet:() -> Unit
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

    val isRefreshing = remember { mutableStateOf(false) }
    val pagerState = rememberPagerState(initialPage = 0)

    if (state.isError && state.employees.isNotEmpty()){
        ErrorScreen(navController = navController)
    } else {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SearchField(
                searchText = state.searchText,
                onChangeSearchText = {
                    viewModel.listener(MainAction.ChangeText(it))
                },
                onClickSortButton = {
                    onClickShowBottomSheet()
                },
                onClickCancelButton = {
                    focusManager.clearFocus()
                },
                onClickCancelButtonInSearchField = {
                    viewModel.listener(MainAction.ChangeText(""))
                }
            )
            DepartmentTabs(
                tabsList = tabsList,
                pagerState = pagerState,
                isRefreshing = isRefreshing.value,
                onRefresh = {
                    isRefreshing.value = true
                }
            ) { page ->
                if (state.isLoading){
                    items(30){
                        ItemEmployee()
                    }
                } else {
                    val listEmployee = state.employees
                        .filterByDepartment(tabsList[page])
                        .filterBySearch(state.searchText)

                    if (listEmployee.isEmpty()){
                        item { NoFindEmployee() }
                    } else {
                        if (sortVariant == SortVariant.ABC) {
                            items(
                                listEmployee
                                    .sortedBy { it.firstName })
                            { employee ->
                                ItemsEmployee(
                                    employee = employee,
                                    onClick = {
                                        navController.navigate(employee)
                                    }
                                )
                            }
                        } else {
                            val triple = listEmployee.tripleSortBirthDayAndNextYear()
                            items(triple.first) { employee ->
                                ItemsEmployee(
                                    employee = employee,
                                    isVisibleBirthDay = true,
                                    onClick = {
                                        navController.navigate(employee)
                                    }
                                )
                            }
                            if (triple.third != null){
                                item {
                                    ItemYear(year = triple.third!!)
                                }
                                items(triple.second){ employee ->
                                    ItemsEmployee(
                                        employee = employee,
                                        isVisibleBirthDay = true,
                                        onClick = {
                                            navController.navigate(employee)
                                        }
                                    )
                                }
                            }
                        }
                    }

                }
            }
        }

        LaunchedEffect(isRefreshing.value) {
            if (isRefreshing.value) {
                viewModel.loadEmployee()
                isRefreshing.value = false
            }
        }
    }
}

fun NavController.navigate(employee: Employee){
    navigateC(
        route = Screen.CoderScreen.route,
        arg = employee.toParcelize()
    )
}

@Composable
fun ItemsEmployee(
    employee: Employee,
    isVisibleBirthDay: Boolean = false,
    onClick: () -> Unit
){
    ItemEmployee(
        name = "${employee.firstName} ${employee.lastName}",
        department = employee.department,
        urlImageEmployee = employee.avatarUrl,
        userTag = employee.userTag,
        birthDay = if (isVisibleBirthDay) employee.birthday else null,
        isVisibleBirthDay = isVisibleBirthDay,
        onClick = {onClick()}
    )
}