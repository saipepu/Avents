package com.example.avents.view.event

import android.provider.CalendarContract.Attendees
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.example.avents.R
import com.example.avents.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventAttendees(
    navController: NavHostController,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Attendees",
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
        ) {
            AttendeesComponent(modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}

@Composable
fun AttendeesComponent(
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (attendeeCard, searchBar, searchButton, participantCard) = createRefs()
        var searchValue by remember { mutableStateOf("") }
        var numberOfAttendees by remember { mutableStateOf("2") }
        val focusRequester = remember { FocusRequester() }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .constrainAs(attendeeCard) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start)
                }
        ) {
            Icon(
                imageVector = Icons.Filled.People,
                contentDescription = "Location Icon",
                modifier = Modifier.size(20.dp),
                tint = Primary
            )
            Text(
                text = numberOfAttendees,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = Primary,
                modifier = Modifier
                    .padding(end = 4.dp)
                    .padding(start = 8.dp)
            )
            Text(
                text = "Attending",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = Primary
            )
        }

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
                    top.linkTo(attendeeCard.bottom, margin = 12.dp)
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
                    top.linkTo(attendeeCard.bottom, margin = 12.dp)
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

        LazyColumn(
            modifier = Modifier
                .constrainAs(participantCard) {
                    top.linkTo(searchButton.bottom, margin = 12.dp)
                }
        ) {
            items(3) { _ ->
                AttendeeCard(modifier = Modifier.padding(top = 8.dp))
            }
        }
    }
}

@Composable
fun AttendeeCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .border(
                width = 0.5.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.profilepic), // Replace with your image
                contentDescription = "Attendee Image",
                modifier = Modifier
                    .size(50.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp)) // Space between image and text

            Column {
                Text(
                    text = "Attendee Name",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Attendee Role",
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    color = Color.Gray
                )
            }
        }
    }
}