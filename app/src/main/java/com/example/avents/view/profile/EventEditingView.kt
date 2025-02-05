package com.example.avents.view.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.avents.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventEditingView(
    navController: NavHostController,
    eventName: String
) {
    var isEditing by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                title = { androidx.compose.material3.Text( text = "Event Detail" ) },
                actions = {
                    IconButton(onClick = { isEditing = !isEditing }) {
                        Icon(
                            imageVector = if (isEditing) Icons.Default.Check else Icons.Default.Edit,
                            contentDescription = "Edit Profile"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            val (eventCoverPhoto, formFields) = createRefs()
            var eventName by remember { mutableStateOf(eventName) }
            var faculty by remember { mutableStateOf("VMS") }
            var startDate by remember { mutableStateOf("2023-08-12") }
            var startTime by remember { mutableStateOf("10:00") }
            var endDate by remember { mutableStateOf("2023-08-12") }
            var endTime by remember { mutableStateOf("12:00") }
            var description by remember { mutableStateOf("This is a sample event description.") }

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
            Column(
                modifier = Modifier
                    .constrainAs(formFields) {
                        top.linkTo(eventCoverPhoto.bottom, margin = 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
            ) {
                TextFieldRow(label = "Name", value = eventName, isEditing = isEditing) { eventName = it }
                TextFieldRow(label = "Faculty", value = faculty, isEditing = isEditing) { faculty = it }
                DateRow(label = "Start Date", value = startDate, isEditing = isEditing) { startDate = it }
                DateRow(label = "End Date", value = endDate, isEditing = isEditing) { endDate = it }
                TimeRow(label = "From", value = startTime, isEditing = isEditing) { startTime = it }
                TimeRow(label = "To", value = endTime, isEditing = isEditing) { endTime = it }
                TextFieldRow(label = "Description", value = description, isEditing = isEditing) { description = it }
            }
        }
    }
}