package com.tirexmurina.composerandomusr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tirexmurina.composerandomusr.screen.HomeScreen
import com.tirexmurina.composerandomusr.ui.theme.ComposeRandomUsrTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRandomUsrTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    RandomUsrApp()
                }
            }
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun RandomUsrApp(){
    HomeScreen()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeRandomUsrTheme {
        Greeting("Android")
    }
}

//todo новый экран и навигация на него

// todo решить как получать пользователя и сделать его получение при загрузке второго скрина