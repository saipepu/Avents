package com.example.avents.view.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.example.avents.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.avents.ui.theme.Primary

@Composable
fun AuthView(
    navController: NavHostController,
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        val (logo, emailLabel, emailInput, passwordLabel, passwordInput, signInButton) = createRefs()
        var password by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        val focusRequester = remember { FocusRequester() }

        Image(
            painter = painterResource(id = R.drawable.aventslogo),
            contentDescription = "Avents Logo",
            modifier = Modifier.constrainAs(logo) {
                top.linkTo(parent.top, margin = 100.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.value(100.dp)
            },
            contentScale = ContentScale.Fit
        )
        Text(
            text = "Email",
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold),
            modifier = Modifier.constrainAs(emailLabel) {
                top.linkTo(logo.bottom, margin = 100.dp)
            }
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Enter your email") },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .constrainAs(emailInput) {
                    top.linkTo(emailLabel.bottom, margin = 12.dp)
                },
        )
        Text(
            text = "Password",
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold),
            modifier = Modifier.constrainAs(passwordLabel) {
                top.linkTo(emailInput.bottom, margin = 16.dp)
            }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Enter your password") },
            shape = RoundedCornerShape(8.dp),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .constrainAs(passwordInput) {
                    top.linkTo(passwordLabel.bottom, margin = 12.dp)
                },
        )
        Button(
            onClick = {
                navController.navigate("onboarding")
            },
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = Primary,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .constrainAs(signInButton) {
                    top.linkTo(passwordInput.bottom, margin = 16.dp)
                    end.linkTo(parent.end)
                }
        ) {
            Text(text = "Sign In")
        }


    }
}