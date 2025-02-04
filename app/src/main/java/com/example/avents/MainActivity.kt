package com.example.avents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.avents.view.auth.OnBoardingView
import com.example.avents.view.profile.ProfileView
import com.example.avents.view.auth.AuthView
import com.example.avents.view.home.HomeView
import com.example.avents.view.profile.ProfileDetailView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "profile") {
                composable("auth") {
                    AuthView(navController = navController)
                }
                composable("onboarding") {
                    OnBoardingView(navController = navController) // Your Home Screen
                }
                composable("home") {
                    HomeView(navController = navController) // Your Home Screen
                }
                composable("profile") {
                    ProfileView(navController = navController)
                }
                composable("profileDetail") {
                    ProfileDetailView(navController = navController)
                }
            }
        }
    }
}
