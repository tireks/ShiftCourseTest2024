package com.tirexmurina.composerandomusr

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tirexmurina.composerandomusr.screen.HomeScreen
import com.tirexmurina.composerandomusr.screen.UserDetailsScreen
import com.tirexmurina.composerandomusr.ui.theme.ComposeRandomUsrTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRandomUsrTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RandomUsrApp()
                }
            }
        }
    }

}

@Composable
fun RandomUsrApp(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "users"){
        composable("users") {
            HomeScreen(
                onItemClick = { userId -> navController.navigate("user/${userId}") },
                onRefreshClick = { navController.navigate("users")}
            )
        }

        composable("user/{userId}", arguments = listOf(navArgument("userId"){
            type = NavType.StringType
        })) {

            val userId = remember {
                it.arguments?.getString("userId")
            }
            Log.d("BL", "CALLING-USER-SCREEn")
            UserDetailsScreen(id = userId)
        }
    }
}
