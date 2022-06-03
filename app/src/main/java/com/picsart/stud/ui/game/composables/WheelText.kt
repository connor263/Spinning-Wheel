package com.picsart.stud.ui.game.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun WheelText(modifier: Modifier = Modifier, wheelValue: Int, wheelPosition: Int) {
    Text(
        modifier = modifier.rotate((wheelPosition - 1) * 45F),
        text = (wheelValue * wheelPosition).toString(),
        style = TextStyle(
            fontSize = 22.sp, color = Color.White, shadow = Shadow(Color.Black, blurRadius = 20F)
        )
    )
}