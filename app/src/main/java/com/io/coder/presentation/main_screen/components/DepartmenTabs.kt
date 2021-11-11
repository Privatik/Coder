package com.io.coder.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.io.coder.domain.model.Employee
import com.io.coder.domain.state.Department
import com.io.coder.presentation.theme.CoderTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DepartmentTabs(
    tabsList: List<Department>,
    pagerState: PagerState,
    isRefreshing: Boolean,
    onRefresh:() -> Unit,
    content: LazyListScope.(Int) -> Unit
) {
    val scope = rememberCoroutineScope()

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    Column() {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .fillMaxWidth(),
            edgePadding = 20.dp,
            contentColor = MaterialTheme.colors.onSecondary,
            backgroundColor = Color.Transparent,
            indicator = { tabPosition ->
                Box(
                    Modifier
                        .tabIndicatorOffset(tabPosition[pagerState.currentPage])
                        .height(TabRowDefaults.IndicatorHeight)
                        .padding(start = 10.dp, end = 10.dp)
                        .background(color = MaterialTheme.colors.onSecondary)
                )
            },
            tabs = {
                tabsList.forEachIndexed { index, department ->
                    Tab(
                        selected = index == pagerState.currentPage,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        selectedContentColor = MaterialTheme.colors.onSecondary,
                        text = {
                            Text(
                                text = department.title,
                                color =
                                if (index == pagerState.currentPage)
                                    MaterialTheme.colors.onPrimary
                                else
                                    MaterialTheme.colors.secondaryVariant
                            )
                        }
                    )
                }
            }
        )
        HorizontalPager(
            state = pagerState,
            count = tabsList.size,
            verticalAlignment = Alignment.Top
        ) { page ->
            SwipeRefresh(
                state = swipeRefreshState,
                indicator = { state, trigger ->
                    SwipeRefreshIndicator(
                        state = state,
                        elevation = 0.dp,
                        refreshTriggerDistance = trigger,
                        scale = true,
                        backgroundColor = Color.Transparent,
                        contentColor = MaterialTheme.colors.onSecondary
                    )
                },
                refreshTriggerDistance = 25.dp,
                onRefresh = {
                    onRefresh()
                },
            ){
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .offset {
                            IntOffset(
                                x = 0,
                                y = swipeRefreshState.indicatorOffset.coerceIn(0f,35.dp.value).roundToInt())
                        }
                ){
                    content(page)
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreviewDepartmentTabs(){
    CoderTheme {
        DepartmentTabs(
            tabsList = emptyList(),
            pagerState = rememberPagerState(initialPage = 0),
            isRefreshing = false,
            onRefresh = {}
        ){

        }
    }
}