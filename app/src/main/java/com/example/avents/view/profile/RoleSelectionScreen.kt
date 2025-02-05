package com.example.avents.view.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.avents.ui.theme.Primary

@Composable
fun RoleDropdown(
    selectedRole: String = "Audience",
    onRoleSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val oppositeRole = if (selectedRole == "Audience") "Organizer" else "Audience"

    Box(modifier = modifier) {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.width(160.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
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
                .width(160.dp)
        ) {
            DropdownMenuItem(
                text = { Text(text = oppositeRole) },
                onClick = {
                    onRoleSelected(oppositeRole)
                    expanded = false
                }
            )
        }
    }
}