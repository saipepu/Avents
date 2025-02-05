package com.example.avents.view.event

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil3.Uri
import coil3.toAndroidUri
import coil3.toCoilUri
import com.example.avents.ui.theme.Primary
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventCreationForm(navController: NavHostController) {
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
            FormView(modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}

@Composable
fun FormView(modifier: Modifier = Modifier) {
    var eventName by remember { mutableStateOf("") }
    var eventDetails by remember { mutableStateOf("") }
    var eventDescription by remember { mutableStateOf("") }
    var selectedUnit by remember { mutableStateOf("Hours") }
    var eventLocation by remember { mutableStateOf("") }
    var eventDate by remember { mutableStateOf("") }
    var eventTime by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // Date and Time Picker State
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // Date Picker
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            eventDate = "${monthOfYear + 1}/$dayOfMonth/$year" // Update the selected date
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    // Time Picker
    val timePickerDialog = TimePickerDialog(
        context,
        { _: TimePicker, hourOfDay: Int, minute: Int ->
            eventTime = "$hourOfDay:$minute" // Update the selected time
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    )

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: android.net.Uri? ->
            imageUri = uri?.toCoilUri()
            uri?.let {
                println("Selected image URI: $it")
            }
        }
    )

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Event Name Label and Field
        val (eventNameLabel, eventNameField, eventImageLabel, eventImageButton, imagePreview, eventDetailsLabel, roleDropdown, eventLocationLabel, eventLocationField, eventDateLabel, eventDateField, eventTimeLabel, eventTimeField, descriptionLabel, eventDescriptionField, submitButton) = createRefs()

        Text(
            text = "Name",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(eventNameLabel) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )

        OutlinedTextField(
            value = eventName,
            onValueChange = { eventName = it },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(eventNameField) {
                    top.linkTo(eventNameLabel.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            placeholder = { Text("Name your event") },
            singleLine = true
        )

        // Image Label and Button
        Text(
            text = "Image",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(eventImageLabel) {
                top.linkTo(eventNameField.bottom, margin = 16.dp)
                start.linkTo(parent.start)
            }
        )

        Button(
            onClick = { launcher.launch("image/*") },
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(eventImageButton) {
                    top.linkTo(eventImageLabel.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(
                text = "Upload Image",
                color = Color.White
            )
        }

        // Display Image Preview (optional)
        imageUri?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = "Event Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(top = 10.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .constrainAs(imagePreview) {
                        top.linkTo(eventImageButton.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }

        // Event Location
        Text(
            text = "Location",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(eventLocationLabel) {
                top.linkTo(eventImageButton.bottom, margin = 16.dp)
                start.linkTo(parent.start)
            }
        )

        OutlinedTextField(
            value = eventLocation,
            onValueChange = { eventLocation = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(eventLocationField) {
                    top.linkTo(eventLocationLabel.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            placeholder = { Text("Add location") },
            trailingIcon = {
                IconButton(onClick = { /* Handle icon click if needed */ }) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Location icon",
                        tint = Color.Gray
                    )
                }
            }
        )

        // Event Date
        Text(
            text = "Date",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(eventDateLabel) {
                top.linkTo(eventLocationField.bottom, margin = 16.dp)
                start.linkTo(parent.start)
            }
        )

        OutlinedTextField(
            value = eventDate,
            onValueChange = { eventDate = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(eventDateField) {
                    top.linkTo(eventDateLabel.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            placeholder = { Text("Select date") },
            trailingIcon = {
                IconButton(onClick = { datePickerDialog.show() }) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "Date icon",
                        tint = Color.Gray
                    )
                }
            },
            enabled = false
        )

        // Event Time
        Text(
            text = "Time",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(eventTimeLabel) {
                top.linkTo(eventDateField.bottom, margin = 16.dp)
                start.linkTo(parent.start)
            }
        )

        OutlinedTextField(
            value = eventTime,
            onValueChange = { eventTime = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(eventTimeField) {
                    top.linkTo(eventTimeLabel.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            placeholder = { Text("Select time") },
            trailingIcon = {
                IconButton(onClick = { timePickerDialog.show() }) {
                    Icon(
                        imageVector = Icons.Filled.AccessTime,
                        contentDescription = "Time icon",
                        tint = Color.Gray
                    )
                }
            },
            enabled = false
        )

        // Description Field
        Text(
            text = "Description",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(descriptionLabel) {
                top.linkTo(eventTimeField.bottom, margin = 16.dp)
                start.linkTo(parent.start)
            }
        )

        OutlinedTextField(
            value = eventDescription,
            onValueChange = { eventDescription = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .constrainAs(eventDescriptionField) {
                    top.linkTo(descriptionLabel.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            placeholder = { Text("Describe your event ...") },
            maxLines = 4
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Submit Button
        Button(
            onClick = { /* Handle Submit */ },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(submitButton) {
                    top.linkTo(eventDescriptionField.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Next",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun UnitDropdown(
    selectedRole: String = "Audience",
    onRoleSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val role = listOf("Audience", "Organizer", "Admin")
    val oppositeRole = if (selectedRole == "Audience") "Organizer" else "Audience"

    Box(modifier = modifier) {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            androidx.compose.material3.Text(
                text = selectedRole,
                color = Primary,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Dropdown Icon"
            )
        }

        // Dropdown Menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            role.forEach { role ->
                if (role != selectedRole) {
                    DropdownMenuItem(
                        text = { androidx.compose.material3.Text(text = role) },
                        onClick = {
                            onRoleSelected(role)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}