package com.example.avents.View.Profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun ProfileView(
    navController: NavHostController,
) {
    Column {
        Text(text="Hello Profile View")
        Button(onClick = {
            navController.navigate("onboarding")
        }) {
            Text(text="Go to OnBoarding")
        }
    }
}
