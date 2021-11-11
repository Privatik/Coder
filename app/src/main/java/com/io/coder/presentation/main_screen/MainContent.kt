package com.io.coder.presentation.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.io.coder.domain.state.Department
import com.io.coder.domain.util.filterByDepartment
import com.io.coder.domain.util.tripleSortBirthDayAndNextYear
import com.io.coder.presentation.error_screen.ErrorScreen
import com.io.coder.presentation.main_screen.components.DepartmentTabs
import com.io.coder.presentation.main_screen.components.ItemEmployee
import com.io.coder.presentation.main_screen.components.ItemYear
import com.io.coder.presentation.main_screen.components.SearchField
import com.io.coder.presentation.main_screen.state.SortVariant

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

    val pagerState = rememberPagerState(initialPage = 0)

    if (state.isError){
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
                pagerState = pagerState
            ) { page ->
                if (state.isLoading){
                    items(30){
                        ItemEmployee(
                            name = null,
                            department = null,
                            urlImageEmployee = null,
                            birthDay = null
                        )
                    }
                } else {
                    if (sortVariant == SortVariant.ABC) {
                        items(
                            state.employees
                                .filterByDepartment(tabsList[page])
                                .sortedBy { it.firstName })
                        { employee ->
                            ItemEmployee(
                                name = "${employee.firstName} ${employee.lastName}",
                                department = employee.department,
                                urlImageEmployee = employee.avatarUrl,
                                userTag = employee.userTag,
                                birthDay = null,
                            )
                        }
                    } else {
                        val triple = state.employees.tripleSortBirthDayAndNextYear()
                        items(triple.first.filterByDepartment(tabsList[page])) { employee ->
                            ItemEmployee(
                                name = "${employee.firstName} ${employee.lastName}",
                                department = employee.department,
                                urlImageEmployee = employee.avatarUrl,
                                userTag = employee.userTag,
                                birthDay = employee.birthday,
                                isVisibleBirthDay = true
                            )
                        }
                        if (triple.third != null){
                            item {
                                ItemYear(year = triple.third!!)
                            }
                            items(triple.second.filterByDepartment(tabsList[page])){ employee ->
                                ItemEmployee(
                                    name = "${employee.firstName} ${employee.lastName}",
                                    department = employee.department,
                                    urlImageEmployee = employee.avatarUrl,
                                    userTag = employee.userTag,
                                    birthDay = employee.birthday,
                                    isVisibleBirthDay = true
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}