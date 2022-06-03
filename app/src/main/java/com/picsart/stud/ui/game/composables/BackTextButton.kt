package com.picsart.stud.ui.game.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BackTextButton(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = TextStyle(
        fontSize = 20.sp
    ),
    action: () -> Unit
) {
    TextButton(
        modifier = modifier.padding(4.dp),
        onClick = { action() },
        shape = RoundedCornerShape(32.dp),
        border = BorderStroke(5.dp, Color.Yellow.copy(green = 0.3F)),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green.copy(blue = 1F))
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 32.dp),
            text = text,
            style = style
        )
    }
}