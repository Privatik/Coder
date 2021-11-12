package com.io.coder.presentation.main_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.io.coder.R
import com.io.coder.presentation.main_screen.state.SortVariant
import com.io.coder.presentation.theme.*
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel
){
    val state = viewModel.state.value

    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    val radioOptions = listOf(
        stringResource(id = R.string.abc),
        stringResource(id = R.string.birth_day)
    )

    val coroutineScope = rememberCoroutineScope()

    BackHandler(bottomState.isVisible) {
        if (bottomState.isVisible){
            coroutineScope.launch {
                bottomState.animateTo(ModalBottomSheetValue.Hidden)
            }
        }
    }

    ModalBottomSheetLayout(
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetState= bottomState,
        sheetShape = BottomSheet,
        scrimColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f),
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = PaddingPostSmall),
                    text = stringResource(id = R.string.sort),
                    color = MaterialTheme.colors.onPrimary,
                    fontSize = TextMediumSize,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(SpacePostSmall))
                radioOptions.forEachIndexed{ index , title ->
                    Spacer(modifier = Modifier.height(SpacePostSmall))
                    Row(
                        modifier = Modifier.padding(start = PaddingSmall),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    bottomState.animateTo(ModalBottomSheetValue.Hidden)
                                }
                                viewModel.listener(
                                    MainAction.ChangeSortVariant(
                                        if (index == 0) SortVariant.ABC else SortVariant.BIRTHDAY
                                    )
                                )
                            }) {
                            Icon(
                                painter = if (state.sortVariant.index == index){
                                    painterResource(id = R.drawable.ic_selected)
                                } else {
                                    painterResource(id = R.drawable.ic_unselected)
                                },
                                tint = MaterialTheme.colors.onSecondary,
                                contentDescription = "RadioButton"
                            )
                        }
                        Spacer(modifier = Modifier.width(SpacePostSmall))
                        Text(
                            text = title,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
                Spacer(modifier = Modifier.height(SpaceLarge))
            }
        }
    ) {
        MainContent(
            navController = navController,
            viewModel = viewModel,
            sortVariant = state.sortVariant,
            onClickShowBottomSheet = {
                coroutineScope.launch {
                    bottomState.animateTo(ModalBottomSheetValue.Expanded)
                }
            }
        )
    }
}