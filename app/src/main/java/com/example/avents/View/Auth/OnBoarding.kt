package com.example.avents.View.Auth

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun OnBoardingView(
    navController: NavHostController,
) {
    Column {
        Text(text="Hello OnBoarding")
        Button(onClick = {
            navController.navigate("profile")
        }) {
            Text(text="Go to Profile")
        }
    }
}