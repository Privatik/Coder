package com.io.coder.presentation.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.io.coder.R
import com.io.coder.domain.state.Department
import com.io.coder.presentation.error_screen.ErrorScreen
import com.io.coder.presentation.main_screen.MainAction.ChangeText
import com.io.coder.presentation.main_screen.components.DepartmentTabs
import com.io.coder.presentation.main_screen.components.ItemEmployee
import com.io.coder.presentation.main_screen.components.SearchField
import com.io.coder.presentation.main_screen.state.SortVariant
import com.io.coder.presentation.theme.*
import com.io.coder.presentation.util.Screen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel
){

    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    val radioOptions = listOf(
        stringResource(id = R.string.abc),
        stringResource(id = R.string.birth_day)
    )


    val selectedOption = remember { mutableStateOf(0) }

    val coroutineScope = rememberCoroutineScope()


    ModalBottomSheetLayout(
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetState= bottomState,
        sheetShape = BottomSheet,
        scrimColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f),
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
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
                        IconButton(onClick = {  selectedOption.value = index}) {
                            Icon(
                                painter = if (selectedOption.value == index){
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
            }
        }
    ) {
        MainContent(
            navController = navController,
            viewModel = viewModel,
            onClickShowBottomSheet = {
                coroutineScope.launch {
                    if (!bottomState.isVisible) {
                        bottomState.animateTo(ModalBottomSheetValue.HalfExpanded)
                    } else {
                        bottomState.hide()
                    }
                }
            }
        )
    }
}