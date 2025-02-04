package com.example.avents.view.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.avents.R
import com.example.avents.ui.theme.Primary

@Composable
fun OnBoardingView(
    navController: NavHostController,
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        val (image, welcomeText, button) = createRefs()
        createVerticalChain(image, welcomeText, button, chainStyle = ChainStyle.Packed)

        Image(
            painter = painterResource(id = R.drawable.onboarding),
            contentDescription = "Onboarding Image",
            modifier = Modifier.constrainAs(image) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Welcome to Avents",
            modifier = Modifier.constrainAs(welcomeText) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Button(
            onClick = {
                navController.navigate("home")
            },
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = Primary,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .constrainAs(button) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text(text = "Next")
        }

    }
}