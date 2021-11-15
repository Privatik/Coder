package com.io.coder.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.io.coder.R
import com.io.coder.presentation.main_screen.state.SortVariant
import com.io.coder.presentation.theme.CoderTheme
import com.io.coder.presentation.theme.PaddingMedium
import com.io.coder.presentation.theme.PaddingPostSmall
import com.io.coder.presentation.theme.SpacePostSmall

@Composable
fun SearchField(
    searchText: String,
    hint: String,
    sortVariant: SortVariant,
    onChangeSearchText: (String) -> Unit,
    onClickSortButton: () -> Unit,
    onClickCancelButtonInSearchField: () -> Unit,
    onClickCancelButton: () -> Unit
){

    val searchMode = searchText.isNotBlank()
    val focusMode = remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .padding(
                top = PaddingMedium,
                start = PaddingMedium,
                end = PaddingMedium,
                bottom = PaddingMedium / 2
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(if (focusMode.value) 0.7f else 1f)
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colors.secondary)
                .onFocusChanged {
                    focusMode.value = it.isFocused
                },
            placeholder = {
                if (!focusMode.value) {
                    Text(
                        text = hint,
                        color = MaterialTheme.colors.primaryVariant
                    )
                }
            },
            value = searchText,
            onValueChange = {
                onChangeSearchText(it)
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search",
                    tint =
                    if (focusMode.value) MaterialTheme.colors.onPrimary
                    else MaterialTheme.colors.primaryVariant
                )
            },
            trailingIcon = {
                if (!focusMode.value){
                    IconButton(onClick = { onClickSortButton() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_list_ui_alt),
                            contentDescription = "Sort",
                            tint = if (sortVariant == SortVariant.ABC)
                                MaterialTheme.colors.primaryVariant
                                    else
                                MaterialTheme.colors.onSecondary
                        )
                    }
                } else if (searchMode){
                    IconButton(onClick = { onClickCancelButtonInSearchField() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cancel),
                            contentDescription = "Cancel",
                            tint = MaterialTheme.colors.primaryVariant
                        )
                    }
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colors.onSecondary,
                focusedIndicatorColor = Color.Transparent
            )
        )
        if (focusMode.value){
            Spacer(modifier = Modifier.weight(SpacePostSmall.value))
            Button(
                onClick = { onClickCancelButton() },
                elevation = ButtonDefaults.elevation(
                    0.dp,
                    0.dp,
                    0.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.background
                )
            ) {
                Text(
                    text = stringResource(id = R.string.cancel),
                    color = MaterialTheme.colors.onSecondary
                )
            }
        }

    }
}

@Preview
@Composable
fun PreviewSearchField(){
    CoderTheme {
        SearchField(
            searchText = "",
            hint = "Введи имя, тег, почту...",
            sortVariant = SortVariant.ABC,
            onChangeSearchText = {},
            onClickSortButton = {},
            onClickCancelButtonInSearchField = {},
            onClickCancelButton = {}
        )
    }
}