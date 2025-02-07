package com.example.avents.view.event

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.avents.R
import com.example.avents.ui.theme.Primary

@Composable
fun EventCreationScreen(
    navController: NavHostController,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (aventImage, eventPeople, description, hostEventButton) = createRefs()
        val verticalCenter = createGuidelineFromTop(0.2f)

        Image(
            painter = painterResource(R.drawable.avents_create_logo),
            contentDescription = "Avent Image",
            modifier = Modifier
                .constrainAs(aventImage) {
                    top.linkTo(verticalCenter)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .width(250.dp)
                .height(100.dp)
        )

        Image(
            painter = painterResource(R.drawable.eventpeople),
            contentDescription = "Event People",
            modifier = Modifier
                .constrainAs(eventPeople) {
                    top.linkTo(aventImage.bottom)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                }
                .fillMaxWidth()
                .height(250.dp)
        )

        Text(
            text = "Simply submit your event for approval and watch as your idea transform into a memorable experience for attendees.",
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            modifier = Modifier
                .constrainAs(description) {
                    top.linkTo(eventPeople.bottom, margin = 15.dp)
                    start.linkTo(parent.start)
                }
                .padding(horizontal = 16.dp)
        )

        Button(
            onClick = {
                navController.navigate("eventCreationForm")
            },
            modifier = Modifier
                .constrainAs(hostEventButton) {
                    top.linkTo(description.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .width(150.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Host an Event"
            )
        }
    }
}