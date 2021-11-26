package com.felipe.profilo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.felipe.profilo.model.MOCK_USER_PROFILES
import com.felipe.profilo.model.UserProfile
import com.felipe.profilo.ui.theme.ProfiloTheme

const val ROUTE_USER_LIST = "user_list"
const val ROUTE_USER_DETAILS = "user_details"
const val ARGUMENT_USER_ID = "userId"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfiloTheme {
                ProfiloApplication()
            }
        }
    }
}

@Composable
fun ProfiloApplication(userProfiles: List<UserProfile> = MOCK_USER_PROFILES) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ROUTE_USER_LIST) {
        composable(ROUTE_USER_LIST) {
            ProfilesScreen(
                userProfiles = userProfiles,
                navController = navController
            )
        }
        composable(
            route = "$ROUTE_USER_DETAILS/{userId}",
            arguments = listOf(
                navArgument(name = ARGUMENT_USER_ID) {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt(ARGUMENT_USER_ID)?.let { userId ->
                DetailsScreen(
                    userProfile = MOCK_USER_PROFILES.first { it.id == userId },
                    navController = navController
                )
            }
        }
    }
}
