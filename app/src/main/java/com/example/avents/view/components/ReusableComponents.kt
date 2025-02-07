package com.example.avents.view.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar


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
                imageVector = Icons.Default.Timer,
                contentDescription = "Select date",
            )
        }

    }
}