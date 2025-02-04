package com.example.avents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.avents.View.Auth.OnBoardingView
import com.example.avents.View.Profile.ProfileView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "onboarding") {
                composable("onboarding") {
                    OnBoardingView(navController = navController) // Your Home Screen
                }
                composable("profile") {
                    ProfileView(navController = navController)
                }
            }
        }
    }
}
