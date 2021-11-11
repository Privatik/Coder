package com.io.coder.presentation.main_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.io.coder.domain.state.Department
import com.io.coder.presentation.theme.*
import com.io.coder.util.extends.gradientBackground

@Composable
fun ItemEmployee(
    name:String?,
    department: Department?,
    urlImageEmployee: String?
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = PaddingMedium,
                start = PaddingPostSmall,
                end = PaddingPostSmall,
                bottom = PaddingSmall
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .size(SizePictureMedium)
                .gradientBackground(
                    colors = listOf(
                        GradientStart,
                        GradientEnd
                    ),
                    angle = -45f
                ),
            painter = urlImageEmployee?.let {
                rememberImagePainter(it)
            } ?: run {
                rememberImagePainter(data = null)
            },
            contentDescription = "ImageEmployee")
        Spacer(modifier = Modifier.width(SpacePostSmall))
        Column {
            Text(
                modifier =
                if (name == null) {
                    Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.large)
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    GradientStart,
                                    GradientEnd
                                )
                            )
                        )
                } else Modifier,
                text = name ?: "",
                fontSize = 16.sp,
                color = MaterialTheme.colors.onPrimary,
            )
            Spacer(modifier = Modifier.height(SpacePostSmall))
            Text(
                modifier =
                if (department == null) {
                    Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.large)
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    GradientStart,
                                    GradientEnd
                                )
                            )
                        )
                } else Modifier,
                text = department?.title ?: "",
                fontSize = 12.sp,
                color = MaterialTheme.colors.onPrimary,
            )
        }
    }
}

@Preview
@Composable
fun PreviewItemEmployee(){
    CoderTheme {
        ItemEmployee(
            name = null,
            department = null,
            urlImageEmployee = null)
    }
}