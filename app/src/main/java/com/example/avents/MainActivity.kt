package com.example.avents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.avents.view.auth.AuthView
import com.example.avents.view.auth.OnBoardingView
import com.example.avents.view.components.FeedbackView
import com.example.avents.view.event.EventAttendees
import com.example.avents.view.event.EventCreationForm
import com.example.avents.view.event.EventCreationScreen
import com.example.avents.view.event.EventDetailView
import com.example.avents.view.home.HomeView
import com.example.avents.view.profile.EventEditingView
import com.example.avents.view.profile.ProfileDetailView
import com.example.avents.view.profile.ProfileView
import com.example.avents.viewModel.ProfileViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val profileViewModel: ProfileViewModel by viewModels()

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "auth") {
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
                    ProfileView(navController = navController, profileViewModel)
                }
                composable("profileDetail") {
                    ProfileDetailView(navController = navController)
                }
                composable("event") {
                    EventCreationScreen(navController = navController)
                }
                composable("eventCreationForm") {
                    EventCreationForm(navController = navController)
                }
                composable("eventEditingView/{eventName}") { backStackEntry ->
                    val eventName = backStackEntry.arguments?.getString("eventName") ?: "Unknown"
                    EventEditingView(navController = navController, eventName)
                }
                composable("eventDetail/{eventName}") {
                    EventDetailView(navController = navController)
                }
                composable("eventAttendees") {
                    EventAttendees(navController = navController)
                }
                composable(
                    "feedbackView/{message}/{navigateLink}/{buttonText}/{imageResource}",
                    arguments = listOf(
                        navArgument("message") { type = NavType.StringType },
                        navArgument("navigateLink") { type = NavType.StringType },
                        navArgument("buttonText") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val message = backStackEntry.arguments?.getString("message") ?: ""
                    val navigateLink = backStackEntry.arguments?.getString("navigateLink") ?: ""
                    val buttonText = backStackEntry.arguments?.getString("buttonText") ?: ""
                    val imageResource = backStackEntry.arguments?.getString("imageResource")?.toIntOrNull() ?: R.drawable.onboarding

                    FeedbackView(
                        navController = navController,
                        message = message,
                        navigateLink = navigateLink,
                        buttonText = buttonText,
                        feedbackImage = painterResource(id = imageResource)
                    )
                }
            }
        }
    }
}
