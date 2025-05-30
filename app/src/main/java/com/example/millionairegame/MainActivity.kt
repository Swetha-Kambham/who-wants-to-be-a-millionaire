package com.example.millionairegame
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import com.example.millionairegame.ui.theme.MillionaireGameTheme

class MainActivity : ComponentActivity() {
    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MillionaireGameTheme {
                var currentScreen by remember { mutableStateOf(Screen.Splash) } // Start with SplashScreen

                when (currentScreen) {
                    Screen.Splash -> SplashScreen(onTimeout = { currentScreen = Screen.Game })
                    Screen.Game -> GameScreen(viewModel)
                }
            }
        }
    }
}

enum class Screen {
    Splash,
    Game
}