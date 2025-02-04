package com.example.avents.View.Profile

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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.avents.R

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
        ) {
            val (profileImage, formFields) = createRefs()

            Image(
                painter = painterResource(R.drawable.profilepic),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .constrainAs(profileImage) {
                        top.linkTo(parent.top, margin = 80.dp)
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
                ProfileDetailRow(label = "First Name", value = firstName, isEditing = isEditing) { firstName = it }
                ProfileDetailRow(label = "Last Name", value = lastName, isEditing = isEditing) { lastName = it }
                ProfileDetailRow(label = "Email", value = email, isEditing = isEditing) { email = it }
                ProfileDetailRow(label = "Phone", value = phone, isEditing = isEditing) { phone = it }
                ProfileDetailRow(label = "Gender", value = gender, isEditing = isEditing) { gender = it }
                ProfileDetailRow(label = "Date of Birth", value = dob, isEditing = isEditing) { dob = it }
            }
        }
    }
}

@Composable
fun ProfileDetailRow(
    label: String,
    value: String,
    isEditing: Boolean,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        if (isEditing) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.weight(2f)
            )
        } else {
            Text(
                text = value,
                fontSize = 18.sp,
                modifier = Modifier.weight(2f)
            )
        }
    }
}