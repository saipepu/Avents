package com.example.avents.view.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.example.avents.R
import com.example.avents.model.upComingEventList

@Composable
fun HomeView(
    navController: NavHostController,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(20.dp)
    ) {
        var searchValue by remember { mutableStateOf("") }
        val (profileImage, userName, searchBar, searchButton, upcomingEvents, upcomingEventsContainer, eventCard, currentEvents, pastEvents) = createRefs()
        val focusRequester = remember { FocusRequester() }

        Image(
            painter = painterResource(id = R.drawable.person1),
            contentDescription = "Profile Image",
            modifier = Modifier
                .clip(CircleShape)
                .constrainAs(profileImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    width = Dimension.value(32.dp)
                },
            contentScale = ContentScale.Fit
        )
        Text(
            text = "Sai Pe Pu",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.constrainAs(userName) {
                top.linkTo(parent.top)
                start.linkTo(profileImage.end, margin = 10.dp)
                centerVerticallyTo(profileImage)
            }
        )
        OutlinedTextField(
            value = searchValue,
            onValueChange = { searchValue = it },
            placeholder = { Text("Search") },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .focusRequester(focusRequester)
                .constrainAs(searchBar) {
                    top.linkTo(profileImage.bottom, margin = 12.dp)
                },
        )
        Button(
            onClick = { /* Handle search click */ },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color.LightGray),
            contentPadding = PaddingValues(0.dp), // Remove default padding for better icon fit
            modifier = Modifier
                .background(color = Color.White)
                .constrainAs(searchButton) {
                    top.linkTo(profileImage.bottom, margin = 12.dp)
                    bottom.linkTo(searchBar.bottom)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                }
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Black // Change color if needed
            )
        }
        Text(
            text = "Upcoming Events",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(upcomingEvents) {
                top.linkTo(searchBar.bottom, margin = 12.dp)
                start.linkTo(parent.start)
            }
        )

        EventList(
            events = upComingEventList,
            modifier = Modifier
                .constrainAs(upcomingEventsContainer) {
                top.linkTo(upcomingEvents.bottom, margin = 12.dp)
            },
            onEventClick = { eventName ->
                navController.navigate("eventEditingView/$eventName")
            }
        )

    }
}