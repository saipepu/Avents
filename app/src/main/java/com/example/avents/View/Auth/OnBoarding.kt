package com.example.avents.View.Auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
        Image(
            painter = painterResource(id = R.drawable.your_image), // Replace with your image resource
            contentDescription = "Onboarding Image", // Provide a content description for accessibility
            modifier = Modifier.padding(16.dp) // Add padding around the image
        )
    }
}