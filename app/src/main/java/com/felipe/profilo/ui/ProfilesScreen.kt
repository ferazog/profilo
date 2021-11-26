package com.felipe.profilo.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.felipe.profilo.model.MOCK_USER_PROFILES
import com.felipe.profilo.model.UserProfile
import com.felipe.profilo.ui.theme.ActiveColor
import com.felipe.profilo.ui.theme.ProfiloTheme

@Composable
fun ProfilesScreen(
    userProfiles: List<UserProfile>,
    navController: NavHostController?
) {
    Scaffold(
        topBar = {
            ProfiloAppBar(
                title = "User Profiles"
            ) {}
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                contentPadding = PaddingValues(8.dp)
            ) {
                items(userProfiles) { userProfile ->
                    ProfileCard(userProfile = userProfile) {
                        navController?.navigate("$ROUTE_USER_DETAILS/${userProfile.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun ProfiloAppBar(
    title: String,
    icon: ImageVector? = null,
    integrateWithContentFlag: Boolean = false,
    onIconClick: () -> Unit
) {
    TopAppBar(
        elevation = if (integrateWithContentFlag) {
            0.dp
        } else {
            AppBarDefaults.TopAppBarElevation
        },
        navigationIcon = if (icon != null) {
            {
                Icon(
                    imageVector = icon,
                    contentDescription = "Navigate home",
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable { onIconClick() }
                )
            }
        } else {
            null
        },
        title = { Text(text = title) }
    )
}

@Composable
fun ProfileCard(userProfile: UserProfile, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(24.dp),
        elevation = 8.dp,
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfilePicture(userProfile.pictureUrl, userProfile.status, 72.dp)
            ProfileContent(userProfile.name, userProfile.status, Alignment.Start)
        }
    }
}

@Composable
fun ProfilePicture(
    pictureUrl: String,
    status: Boolean,
    size: Dp
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(
            width = 2.dp,
            color = if (status) ActiveColor else Color.Gray
        ),
        modifier = Modifier.padding(8.dp),
        elevation = 4.dp
    ) {
        Image(
            painter = rememberImagePainter(
                data = pictureUrl
            ),
            contentDescription = "Profile picture",
            modifier = Modifier.size(size)
        )
    }
}

@Composable
fun ProfileContent(
    name: String,
    status: Boolean,
    alignment: Alignment.Horizontal
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        if (status) {
            Text(text = name, style = MaterialTheme.typography.h6)
        } else {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = name, style = MaterialTheme.typography.h6)
            }
        }
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = if (status) "Active now" else "Offline",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.align(alignment)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProfiloTheme {
        ProfilesScreen(MOCK_USER_PROFILES, null)
    }
}