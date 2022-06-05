package com.picsart.stud.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.picsart.stud.MainActivity
import com.picsart.stud.MainViewModel

@Composable
fun InitScreen(viewModel: MainViewModel) {
    var isLoading by remember { mutableStateOf(true) }
    val activity = LocalContext.current as MainActivity

    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.size(120.dp), color = Color.White)
        } else {
            AlertDialog(
                title = {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("No internet connection", fontSize = 24.sp)
                },
                text = {
                    Text(
                        "Check your internet connection and try again later",
                        fontSize = 16.sp
                    )
                },
                onDismissRequest = {
                    activity.initApp()
                },
                shape = RoundedCornerShape(16.dp),
                confirmButton = {
                    TextButton(onClick = {
                        activity.initApp()
                    }) {
                        Text("Try again")
                    }
                }
            )
        }
    }

    LaunchedEffect(viewModel.isLoading) {
        isLoading = viewModel.isLoading
    }
}