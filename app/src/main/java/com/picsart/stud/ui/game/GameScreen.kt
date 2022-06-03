package com.picsart.stud.ui.game

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.picsart.stud.R
import com.picsart.stud.ui.game.composables.MenuTextButton
import com.picsart.stud.ui.game.composables.Wheel
import com.picsart.stud.ui.navigation.NavKeys
import com.picsart.stud.utils.game.MAX_LEVEL

@Composable
fun GameScreen(
    navController: NavHostController,
    viewModel: GameViewModel,
    singleplayer: Boolean
) {
    val wheelAnim = remember { Animatable(0F) }

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.game_bg_2),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .weight(0.2F)
                .fillMaxSize()
                .padding(16.dp),
            backgroundColor = Color.DarkGray,
            shape = RoundedCornerShape(32.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (singleplayer) {
                    Text(
                        text = "Score - ${viewModel.firstScore}",
                        fontSize = 32.sp,
                        color = Color.White
                    )
                } else {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Score 1: ${viewModel.firstScore}",
                            fontSize = 20.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Score 2: ${viewModel.secondScore}",
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    }
                }
                Text(
                    text = "Level - ${viewModel.level}",
                    fontSize = 22.sp,
                    color = Color.White
                )
                if (!singleplayer) {
                    val turn = if (viewModel.playerTurn) 1 else 2
                    Text(
                        text = "Turn $turn",
                        fontSize = 22.sp,
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Wheel(
            modifier = Modifier.weight(0.6F),
            wheelAnim.value,
            viewModel.sectorValue
        )
        Spacer(modifier = Modifier.height(16.dp))
        val roll = if (singleplayer) -1 else if (viewModel.playerTurn) 1 else 2

        MenuTextButton(
            modifier = Modifier
                .weight(0.2F)
                .padding(vertical = 32.dp),
            text = "ROLL${if (roll != -1) roll else ""}"
        ) {
            viewModel.rollWheel()
        }
    }

    LaunchedEffect(viewModel.wheel) {
        if (viewModel.wheel.isRoll) {
            wheelAnim.snapTo(targetValue = wheelAnim.value - 1800F)
            wheelAnim.animateTo(
                targetValue = 360F,
                animationSpec = tween(600, easing = FastOutLinearInEasing),
            ).apply {
                when (endReason) {
                    AnimationEndReason.BoundReached -> {}
                    AnimationEndReason.Finished -> {
                        wheelAnim.snapTo(0F)
                        wheelAnim.animateTo(
                            targetValue = 360F,
                            animationSpec = repeatable(
                                20,
                                animation = tween(150)
                            )
                        ).apply {
                            when (endReason) {
                                AnimationEndReason.BoundReached -> {}
                                AnimationEndReason.Finished -> {
                                    wheelAnim.animateTo(
                                        targetValue = (-1 * viewModel.wheel.degrees) + 1440F,
                                        animationSpec = tween(1000, easing = FastOutSlowInEasing),
                                    ).apply {
                                        when (endReason) {
                                            AnimationEndReason.BoundReached -> {}
                                            AnimationEndReason.Finished -> {
                                                viewModel.stopWheel()
                                                if (!singleplayer) {
                                                    viewModel.playerTurn = !viewModel.playerTurn
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(viewModel.level) {
        val level = viewModel.level

        if (level > MAX_LEVEL) {
            if (singleplayer) {
                navController.navigate(
                    NavKeys.Score(
                        firstScore = viewModel.firstScore.toString(),
                        level = level.toString(),
                    ).route
                ) {
                    popUpTo(NavKeys.Game().route) { inclusive = true }
                }
            } else {
                Log.d("TAG", "GameScreen: multi")
                navController.navigate(
                    NavKeys.Score(
                        firstScore = viewModel.firstScore.toString(),
                        secondScore = viewModel.secondScore.toString(),
                        level = level.toString()
                    ).route
                ) {
                    popUpTo(NavKeys.Game().route) { inclusive = true }
                }
            }
            viewModel.saveResult()
        }
    }
}

@Preview
@Composable
fun GameScreenPreview() {
    GameScreen(navController = rememberNavController(), viewModel = viewModel(), true)
}