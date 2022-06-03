package com.picsart.stud.ui.game.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.picsart.stud.MainActivity
import com.picsart.stud.R
import com.picsart.stud.ui.game.composables.MenuTextButton
import com.picsart.stud.ui.navigation.NavKeys

@Composable
fun MenuScreen(navController: NavHostController = rememberNavController()) {
    val context = LocalContext.current
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.game_bg_4),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MenuTextButton(text = "Single") {
            navController.navigate(NavKeys.Game().route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        MenuTextButton(text = "With Friend") {
            navController.navigate(
                NavKeys.Game(singleplayer = false.toString()).route
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        MenuTextButton(text = "Results") {
            navController.navigate(NavKeys.Results.route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        MenuTextButton(text = "Exit") {
            (context as MainActivity).finish()
        }
    }
}

@Preview(
    group = "Menu",
    showBackground = true
)
@Composable
fun MenuScreenPreview(navController: NavHostController = rememberNavController()) {
    MenuScreen(navController)
}