package com.io.coder.presentation.main_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.rememberPagerState
import com.io.coder.R
import com.io.coder.presentation.theme.*

@Composable
fun NoFindEmployee(

){
    Column(
        modifier = Modifier
            .padding(
                top = PaddingLarge
            )
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.magnifying_glass),
            modifier = Modifier.size(SizePicturePreMedium),
            contentDescription = "No_Find"
        )
        Spacer(modifier = Modifier.height(SpaceSmall))
        Text(
            text = stringResource(id = R.string.no_find),
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onPrimary
        )
        Spacer(modifier = Modifier.height(SpaceSmall))
        Text(
            text = stringResource(id = R.string.try_to_adjust),
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.primaryVariant
        )
    }
}

@Preview
@Composable
fun PreviewNoFindEmployee(){
    CoderTheme {
        NoFindEmployee(

        )
    }
}