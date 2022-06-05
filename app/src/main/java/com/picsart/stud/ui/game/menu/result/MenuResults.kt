package com.picsart.stud.ui.game.menu.result

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.picsart.stud.R
import com.picsart.stud.ui.game.composables.BackTextButton

@Composable
fun MenuResults(navController: NavController, viewModel: MenuResultsViewModel) {
    val results = viewModel.results.collectAsState(initial = listOf())

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.game_bg),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )

    BackTextButton(text = "Back") {
        navController.navigateUp()
    }

    Card(
        Modifier
            .fillMaxSize()
            .padding(64.dp),
        backgroundColor = Color.DarkGray,
        shape = RoundedCornerShape(32.dp)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            items(results.value) {
                val isMultiplayer = it.secondScore != -1
                Card(
                    Modifier.padding(8.dp),
                    shape = RoundedCornerShape(32.dp),
                    backgroundColor = Color.Black.copy(alpha = 0.8F)
                ) {
                    Column(
                        Modifier.padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (isMultiplayer) {

                            Text(text = "Player 1: ${it.firstScore}", color = Color.White)
                            Text(text = "Player 2: ${it.secondScore}", color = Color.White)
                            Text(text = "Level: ${it.level}", color = Color.White)
                        } else {
                            Text(text = "Player: ${it.firstScore}", color = Color.White)
                            Text(text = "Level: ${it.level}", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}