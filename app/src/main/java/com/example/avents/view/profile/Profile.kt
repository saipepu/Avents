package com.example.avents.view.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.avents.R
import com.example.avents.model.onGoingEventList
import com.example.avents.model.upComingEventList
import com.example.avents.ui.theme.Primary
import com.example.avents.view.home.EventList

@Composable
fun ProfileView(
    navController: NavHostController,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (profileBackground, profileImage, userName, roleDropdown, profileDetailButton, tabscreen, onGoingEventContainer, upComingEventContainer) = createRefs()

        Image(
            painter = painterResource(R.drawable.background_wallpaper),
            contentDescription = "Profile Background",
            modifier = Modifier
                .constrainAs(profileBackground) {
                    top.linkTo(parent.top, margin = -30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .height(180.dp)
        )

        Image(
            painter = painterResource(R.drawable.profilepic),
            contentDescription = "Profile Image",
            modifier = Modifier
                .constrainAs(profileImage) {
                top.linkTo(profileBackground.bottom, margin = -50.dp)
                start.linkTo(parent.start)
                }
                .width(100.dp)
                .height(100.dp)
                .padding(horizontal = 16.dp)
        )

        Text(
            text = "Justin Noodles",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(userName) {
                    top.linkTo(profileImage.bottom)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )

        var selectedRole by remember { mutableStateOf("Audience") }

        RoleDropdown(
            selectedRole = selectedRole,
            onRoleSelected = { role -> selectedRole = role },
            modifier = Modifier
                .constrainAs(roleDropdown) {
                    top.linkTo(userName.bottom)
                    start.linkTo(parent.start)
                }
                .padding(horizontal = 16.dp)
        )

        Button(
            onClick = {
                navController.navigate("profileDetail")
            },
            modifier = Modifier
                .constrainAs(profileDetailButton) {
                    top.linkTo(userName.bottom)
                    start.linkTo(roleDropdown.end)
                    end.linkTo(parent.end)
                }
                .width(160.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Profile Detail"
            )
        }

        TabScreen(
            modifier = Modifier
                .constrainAs(tabscreen) {
                    top.linkTo(roleDropdown.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(),
            onNavigate = { eventRoute ->
                navController.navigate(eventRoute) // Navigate when an event is clicked
            }
        )

    }
}

@Composable
fun TabScreen(modifier: Modifier = Modifier, onNavigate: (String) -> Unit = {}) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Ongoing", "Upcoming")

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        TabRow(
            selectedTabIndex = tabIndex,
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.White,
            contentColor = Color.Black,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                    color = Primary
                )
            },
            divider = { }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> OngoingScreen(modifier, onNavigate)
            1 -> UpcomingScreen(modifier, onNavigate)
        }
    }
}

@Composable
fun OngoingScreen(
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit = {},
) {
    EventList(
        events = onGoingEventList,
        modifier = modifier.padding(top = 10.dp, bottom = 150.dp),
        onEventClick = { eventName ->
            onNavigate("eventEditingView/$eventName")
        }
    )
}

@Composable
fun UpcomingScreen(
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit = {}
) {
    EventList(
        events = upComingEventList,
        modifier = modifier.padding(top = 10.dp, bottom = 150.dp),
        onEventClick = { eventName ->
            onNavigate("eventEditingView/$eventName")
        }
    )
}
