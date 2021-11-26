package com.felipe.profilo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.felipe.profilo.model.MOCK_USER_PROFILES
import com.felipe.profilo.model.UserProfile
import com.felipe.profilo.ui.theme.PrimaryColor
import com.felipe.profilo.ui.theme.ProfiloTheme

@Composable
fun DetailsScreen(userProfile: UserProfile, navController: NavController?) {
    Scaffold(
        topBar = {
            ProfiloAppBar(
                title = "User Details",
                icon = Icons.Default.ArrowBack,
                integrateWithContentFlag = true
            ) {
                navController?.popBackStack()
            }
        }
    ) {
        Surface(
            color = PrimaryColor,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfilePicture(
                    pictureUrl = userProfile.pictureUrl,
                    status = userProfile.status,
                    size = 144.dp
                )
                ProfileContent(userProfile.name, userProfile.status, Alignment.CenterHorizontally)
                ProfileDetailBody()
            }
        }
    }
}

@Composable
fun ProfileDetailBody() {
    Card(
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(top = 40.dp)
            .fillMaxHeight(),
        elevation = 16.dp
    ) {
        Text(
            text = "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"",
            fontSize = 14.sp,
            lineHeight = 32.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(40.dp)
        )
    }
}

@Preview
@Composable
fun DetailsPreview() {
    ProfiloTheme {
        DetailsScreen(userProfile = MOCK_USER_PROFILES[0], null)
    }
}
