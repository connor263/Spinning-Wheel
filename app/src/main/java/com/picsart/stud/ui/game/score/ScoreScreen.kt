package com.picsart.stud.ui.game.score

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.picsart.stud.R
import com.picsart.stud.ui.game.composables.MenuTextButton
import com.picsart.stud.ui.navigation.NavKeys

@Composable
fun ScoreScreen(
    navController: NavHostController,
    firstScore: Int,
    secondScore: Int,
    level: Int
) {
    val multiplayer = remember { secondScore != -1 }
    val resultType = remember {
        when {
            firstScore > secondScore -> if (multiplayer) "1 player is a winner" else "You win!"
            firstScore < secondScore -> "2 player is a winner"
            firstScore == secondScore -> "Draw"
            else -> return
        }
    }

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.game_bg_4),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .weight(0.6F)
                .fillMaxSize()
                .padding(16.dp),
            backgroundColor = Color.DarkGray,
            shape = RoundedCornerShape(32.dp)
        ) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = resultType,
                    fontSize = 32.sp,
                    color = Color.White
                )
                Text(
                    text = "Level - $level",
                    fontSize = 26.sp,
                    color = Color.White
                )
                Text(
                    text = "Score ${if (multiplayer) "1" else ""} - $firstScore",
                    fontSize = 26.sp,
                    color = Color.White
                )
                if (multiplayer) {
                    Text(
                        text = "Score 2 - $secondScore",
                        fontSize = 26.sp,
                        color = Color.White
                    )
                }
            }
        }

        MenuTextButton(
            modifier = Modifier
                .weight(0.15F), text = "Play again?"
        ) {
            if (multiplayer) {
                navController.navigate(
                    NavKeys.Game(singleplayer = false.toString()).route
                ) {
                    popUpTo(NavKeys.Score().route) { inclusive = true }
                }
            } else {
                navController.navigate(NavKeys.Game().route) {
                    popUpTo(NavKeys.Score().route) { inclusive = true }
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        MenuTextButton(
            modifier = Modifier
                .weight(0.10F), text = "Menu"
        ) {
            navController.navigate(NavKeys.Menu.route) {
                popUpTo(NavKeys.Score().route) { inclusive = true }
            }
        }
    }
}