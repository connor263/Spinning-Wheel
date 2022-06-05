package com.picsart.stud.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.picsart.stud.MainViewModel
import com.picsart.stud.ui.InitScreen
import com.picsart.stud.ui.game.GameScreen
import com.picsart.stud.ui.game.GameViewModel
import com.picsart.stud.ui.game.menu.MenuScreen
import com.picsart.stud.ui.game.menu.result.MenuResults
import com.picsart.stud.ui.game.menu.result.MenuResultsViewModel
import com.picsart.stud.ui.game.score.ScoreScreen
import com.picsart.stud.ui.web.WebScreen

@Composable
fun SpinningWheelContent(navController: NavHostController, mainViewModel: MainViewModel) {

    NavHost(navController = navController, startDestination = NavKeys.Init.route) {
        // Menu
        composable(NavKeys.Menu.route) {
            MenuScreen(navController)
        }
        composable(NavKeys.Results.route) {
            val viewModel: MenuResultsViewModel = hiltViewModel()
            MenuResults(navController, viewModel)
        }

        // Game
        composable(NavKeys.Game().route, listOf(
            navArgument("singleplayer") {
                type = NavType.BoolType
                defaultValue = true
            }
        )) {
            it.arguments?.getBoolean("singleplayer")?.let { singleplayer ->
                val viewModel: GameViewModel = hiltViewModel()
                GameScreen(navController, viewModel, singleplayer)
            }
        }

        // Score
        composable(NavKeys.Score().route, listOf(
            navArgument("first_score") {
                type = NavType.IntType
                defaultValue = 0
            },
            navArgument("second_score") {
                type = NavType.IntType
                defaultValue = -1
            },
            navArgument("level") {
                type = NavType.IntType
                defaultValue = 1
            }
        )) {
            val firstScore = it.arguments?.getInt("first_score") ?: 0
            val secondScore = it.arguments?.getInt("second_score") ?: -1
            val level = it.arguments?.getInt("level") ?: 1
            ScoreScreen(
                navController,
                firstScore,
                secondScore,
                level
            )
        }

        // Init
        composable(NavKeys.Init.route) {
            InitScreen(mainViewModel)
        }

        // Web
        composable(NavKeys.Web().route) {
            it.arguments?.getString("link")?.let { link ->
                WebScreen(navController, link)
            }
        }
    }
    LaunchedEffect(mainViewModel.route) {
        mainViewModel.route.let {
            if (it.isNotBlank()) {
                navController.navigate(it) {
                    popUpTo(NavKeys.Init.route) { inclusive = true }
                }
            }
        }
    }
}

sealed class NavKeys(val route: String) {
    object Init : NavKeys("init")
    object Menu : NavKeys("menu")
    object Results : NavKeys("results")

    data class Web(val link: String = "{link}") : NavKeys("web/$link")

    data class Game(val singleplayer: String = "{singleplayer}") :
        NavKeys("game?singleplayer=$singleplayer")

    data class Score(
        val firstScore: String = "{first_score}",
        val secondScore: String = "{second_score}",
        val level: String = "{level}",
    ) :
        NavKeys("score?first_score=$firstScore&second_score=$secondScore&level=$level")
}