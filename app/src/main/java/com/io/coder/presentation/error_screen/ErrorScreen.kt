package com.io.coder.presentation.error_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.io.coder.R
import com.io.coder.presentation.theme.SizePicturePreMedium
import com.io.coder.presentation.theme.SpacePostSmall
import com.io.coder.presentation.theme.SpaceSmall

@Composable
fun ErrorScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(SizePicturePreMedium),
            painter = painterResource(id = R.drawable.flying_saucer),
            contentDescription = "Flying_Saucer"
        )
        Spacer(modifier = Modifier.height(SpacePostSmall))
        Text(
            text = stringResource(id = R.string.broke_everything),
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onPrimary
        )
        Spacer(modifier = Modifier.height(SpacePostSmall))
        Text(
            text = stringResource(id = R.string.try_to_fix_it),
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.primaryVariant
        )
        Spacer(modifier = Modifier.height(SpacePostSmall))
        Button(
            elevation = ButtonDefaults.elevation(
                0.dp,
                0.dp,
                0.dp
            ),
            onClick = { }) {
            Text(
                text = stringResource(id = R.string.try_again),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onSecondary
            )
        }

    }
}