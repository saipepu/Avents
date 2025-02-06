package com.example.avents.view.event

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.avents.R
import com.example.avents.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailView(
    navController: NavHostController,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Event Details",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { paddingValues ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            EventDetails(modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}

@Composable
fun EventDetails(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (eventCoverPhoto, eventTitle, eventType, eventFacultyName, eventDescription, dateBox, location, time, registerButton) = createRefs()

        Image(
            painter = painterResource(R.drawable.eventphoto1),
            contentDescription = "Profile Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(8.dp))
                .constrainAs(eventCoverPhoto) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            contentScale = ContentScale.Crop,
        )

        Text(
            text = "Event Title",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 16.dp)
                .constrainAs(eventTitle) {
                    top.linkTo(eventCoverPhoto.bottom)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = "Type of event",
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            modifier = Modifier
                .padding(top = 10.dp)
                .constrainAs(eventType) {
                    top.linkTo(eventTitle.bottom)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = "by faculty name",
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            color = Primary,
            modifier = Modifier
                .padding(top = 10.dp)
                .constrainAs(eventFacultyName) {
                    top.linkTo(eventType.bottom)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = "Lorem ipsum dolor sit amet. Qui quam voluptatem quo accusamus facere eos voluptatem error ex consequatur deleniti ut accusamus neque ab vitae laudantium. Qui repudiandae voluptates a provident deleniti et optio delectus ut omnis architecto qui quas beatae nam harum ",
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            modifier = Modifier
                .padding(top = 10.dp)
                .constrainAs(eventDescription) {
                    top.linkTo(eventFacultyName.bottom)
                    start.linkTo(parent.start)
                }
        )

        Card(
            modifier = Modifier
                .constrainAs(dateBox) {
                    top.linkTo(eventDescription.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                }
                .size(width = 80.dp, height = 60.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "14-15",
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Primary
                    )
                    Text(
                        text = "JUN",
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .constrainAs(location) {
                    top.linkTo(eventDescription.bottom, margin = 16.dp)
                    start.linkTo(dateBox.end, margin = 30.dp)
                }
        ) {
            Icon(
                imageVector = Icons.Default.Place,
                contentDescription = "Location Icon",
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )

            Text(
                text = "Location place",
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .constrainAs(time) {
                    top.linkTo(location.bottom, margin = 16.dp)
                    start.linkTo(dateBox.end, margin = 30.dp)
                }
        ) {
            Icon(
                imageVector = Icons.Filled.AccessTime,
                contentDescription = "Location Icon",
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )

            Text(
                text = "09:00 - 12:00",
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Button(
            onClick = { /* Handle Submit */ },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(registerButton) {
                    top.linkTo(dateBox.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Register Now",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}