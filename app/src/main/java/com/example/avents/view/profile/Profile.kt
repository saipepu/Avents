package com.example.avents.view.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
