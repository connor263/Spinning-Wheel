package com.picsart.stud.ui.game.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.picsart.stud.R
import com.picsart.stud.utils.game.DEFAULT_WHEEL_SECTOR_VALUE
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Wheel(
    modifier: Modifier = Modifier,
    degrees: Float = 0F,
    wheelValue: Int
) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier.rotate(degrees),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.wheel),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Box(modifier = Modifier.rotate(292.2F)) {
                repeat(8) {
                    WheelText(
                        modifier = Modifier
                            .offset(
                                x = (130 * cos(degToRad(it * 45))).dp,
                                y = (130 * sin(degToRad(it * 45))).dp
                            ),
                        wheelValue = wheelValue,
                        wheelPosition = it + 1
                    )
                }
            }
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.wheel_button),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center
            )
        }
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.wheel_edging),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(group = "Wheel")
@Composable
fun WheelPreview() {
    Wheel(wheelValue = 100)
}

fun degToRad(angle: Int): Double {
    return (angle * Math.PI / 180)
}