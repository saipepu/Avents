package com.example.avents.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.avents.ui.theme.Primary

@Composable
fun FeedbackView(
    feedbackImage: Painter,
    navController: NavHostController,
    message: String,
    navigateLink: String,
    buttonText: String
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        val (image, welcomeText, button) = createRefs()
        createVerticalChain(image, welcomeText, button, chainStyle = ChainStyle.Packed)
        Image(
            painter = feedbackImage,
            contentDescription = "Onboarding Image",
            modifier = Modifier.constrainAs(image) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            contentScale = ContentScale.Crop
        )
        Text(
            text = if(message != "") message else "Feedback Message",
            modifier = Modifier
                .padding(bottom = 12.dp)
                .constrainAs(welcomeText) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Button(
            onClick = {
                navController.navigate(navigateLink)
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
            Text(text = buttonText)
        }
    }
}