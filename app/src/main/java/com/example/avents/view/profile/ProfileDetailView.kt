package com.example.avents.view.profile

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.avents.R
import com.example.avents.ui.theme.Primary
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDetailView(
    navController: NavHostController,
) {
    var isEditing by remember { mutableStateOf(false) }

    var firstName by remember { mutableStateOf("Justin") }
    var lastName by remember { mutableStateOf("Noodles") }
    var email by remember { mutableStateOf("justin@example.com") }
    var phone by remember { mutableStateOf("+1234567890") }
    var gender by remember { mutableStateOf("Male") }
    var dob by remember { mutableStateOf("1995-08-12") }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                title = { Text( text = "Profile Detail" ) },
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
            val (profileImage, formFields) = createRefs()

            Image(
                painter = painterResource(R.drawable.profilepic),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .constrainAs(profileImage) {
                        top.linkTo(parent.top, margin = 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .width(130.dp)
                    .height(130.dp)
                    .padding(bottom = 16.dp)
            )

            Column(
                modifier = Modifier
                    .constrainAs(formFields) {
                        top.linkTo(profileImage.bottom, margin = 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
            ) {
                TextFieldRow(label = "First Name", value = firstName, isEditing = isEditing) { firstName = it }
                TextFieldRow(label = "Last Name", value = lastName, isEditing = isEditing) { lastName = it }
                TextFieldRow(label = "Email", value = email, isEditing = isEditing) { email = it }
                TextFieldRow(label = "Phone", value = phone, isEditing = isEditing) { phone = it }
                TextFieldRow(label = "Gender", value = gender, isEditing = isEditing) { gender = it }
//                TextFieldRow(label = "Date of Birth", value = dob, isEditing = isEditing) { dob = it }
                DateRow(label = "Date of Birth", value = dob, isEditing = isEditing) { dob = it }
            }
        }
    }
}

@Composable
fun TextFieldRow(
    label: String,
    value: String,
    isEditing: Boolean,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f).padding(top = 8.dp)
        )

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(fontSize = 16.sp), // Customize text style if needed
            singleLine = true,
            enabled = isEditing,
            modifier = Modifier
                .weight(2f)
                .border(
                    width = if (isEditing) 1.dp else 0.dp, // Toggle border width based on isEditing
                    color = if (isEditing) Color.Gray else Color.Transparent, // Toggle border color
                    shape = RoundedCornerShape(8.dp) // Optional: Add border radius
                )
                .padding(8.dp) // Adjust padding as needed (not inside the field)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRow(
    label: String,
    value: String,
    isEditing: Boolean,
    onValueChange: (String) -> Unit
) {

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            onValueChange("$year-${month + 1}-$dayOfMonth")
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f).padding(top = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(2f)
                .border(
                    width = if (isEditing) 1.dp else 0.dp, // Toggle border width based on isEditing
                    color = if (isEditing) Color.Gray else Color.Transparent, // Toggle border color
                    shape = RoundedCornerShape(8.dp) // Optional: Add border radius
                )
                .padding(8.dp)
                .clickable { if(isEditing) datePickerDialog.show() }
        ) {
            Text(
                text = if (value != "") value else "Select a date",
                fontSize = 16.sp,
            )
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Select date"
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeRow(
    label: String,
    value: String,
    isEditing: Boolean,
    onValueChange: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val timePickerDialog = TimePickerDialog(
        context,
        { _: TimePicker, hourOfDay: Int, minute: Int ->
            onValueChange("$hourOfDay:$minute")
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f).padding(top = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(2f)
                .border(
                    width = if (isEditing) 1.dp else 0.dp, // Toggle border width based on isEditing
                    color = if (isEditing) Color.Gray else Color.Transparent, // Toggle border color
                    shape = RoundedCornerShape(8.dp) // Optional: Add border radius
                )
                .padding(8.dp)
                .clickable { if(isEditing) timePickerDialog.show() }
        ) {
            Text(
                text = if (value != "") value else "Select a date",
                fontSize = 16.sp,
            )
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Select date",
            )
        }

    }
}