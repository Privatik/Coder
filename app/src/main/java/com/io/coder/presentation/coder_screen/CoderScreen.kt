package com.io.coder.presentation.coder_screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.io.coder.R
import com.io.coder.domain.util.getAge
import com.io.coder.domain.util.getStringFullDate
import com.io.coder.domain.util.getYearTitle
import com.io.coder.presentation.error_screen.model_parcelize.EmployeeParcelize
import com.io.coder.presentation.theme.*
import com.io.coder.presentation.util.Screen

@Composable
fun CoderScreen(
    navController: NavController,
    employee: EmployeeParcelize
){
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.secondary.copy(alpha = 0.3f))
        ){
            IconButton(
                onClick = { navController.popBackStack(
                        Screen.MainScreen.route, false ) }
            ) {
                Icon(
                    modifier = Modifier.size(SizePicturePreSmall),
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back"
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(SizePicturePreLarge)
                        .clip(CircleShape)
                    ,
                    painter = rememberImagePainter(employee.avatarUrl),
                    contentDescription = "Image_employee"
                )
                Spacer(modifier = Modifier.height(SpacePostSmall))
                Text(
                    text = buildAnnotatedString {
                        append("${employee.firstName} ${employee.lastName}")
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colors.secondaryVariant,
                                fontSize = TextPostSmallSize
                            )
                        ) {
                            append(" ")
                            append(employee.userTag)
                        }
                    },
                    fontSize = TextMediumSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onPrimary
                )
                Spacer(modifier = Modifier.height(SpacePostSmall))
                Text(
                    text = employee.department,
                    fontSize = TextSmallSize,
                    color = MaterialTheme.colors.secondaryVariant,
                )
                Spacer(modifier = Modifier.height(SpacePostSmall))
            }
        }
        Row(
            modifier = Modifier.padding(PaddingPostSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(SizePicturePreSmall),
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = "Star")
            Spacer(modifier = Modifier.width(SpacePostSmall))
            Text(
                text = employee.birthday.getStringFullDate(),
                fontSize = TextPreMediumSize
            )
            Spacer(modifier = Modifier.weight(1f))
            val age = employee.birthday.getAge()
            Text(
                text = "$age ${getYearTitle(
                    stringResource(id = R.string.one_year),
                    stringResource(id = R.string.from_two_to_four_year),
                    stringResource(id = R.string.from_five_to_ten_year),
                    age
                )}",
                color = MaterialTheme.colors.secondaryVariant,
                fontSize = TextPreMediumSize
            )
        }
        Spacer(modifier = Modifier.height(SpacePostSmall))
        Row(
            modifier = Modifier
                .padding(PaddingPostSmall)
                .clickable {
                    val dialIntent = Intent(Intent.ACTION_DIAL)
                    dialIntent.data = Uri.parse("tel:+${employee.phone}")
                    context.startActivity(dialIntent)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(SizePicturePreSmall),
                painter = painterResource(id = R.drawable.ic_phone),
                contentDescription = "Phone")
            Spacer(modifier = Modifier.width(SpacePostSmall))
            Text(
                text = "+${employee.phone}" ,
                fontSize = TextPreMediumSize
            )
        }
    }

}