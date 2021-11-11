package com.io.coder.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.io.coder.presentation.theme.*
import com.io.coder.presentation.theme.PaddingMedium

@Composable
fun ItemYear(
    year: Int
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = PaddingPostMedium)
                .weight(1f)
                .background(MaterialTheme.colors.primaryVariant)
                .clip(MaterialTheme.shapes.medium),
            text = "",
            fontSize = 2.sp
        )
        Text(
            text = year.toString(),
            fontSize = TextPreMediumSize,
            color = MaterialTheme.colors.primaryVariant,
        )
        Text(
            modifier = Modifier
                .padding(horizontal = PaddingPostMedium)
                .weight(1f)
                .background(MaterialTheme.colors.primaryVariant)
                .clip(MaterialTheme.shapes.medium),
            text = "",
            fontSize = 2.sp
        )
    }
}

@Preview
@Composable
fun PreviewItemYear(){
    CoderTheme() {
        Surface(color = MaterialTheme.colors.background) {
            ItemYear(year = 2002)
        }
    }
}