package com.example.millionairegame
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Fullscreen Background Image
        Image(
            painter = painterResource(id = R.drawable.millionaire_image), // Replace with your image resource
            contentDescription = "Millionaire Game Splash Screen",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        // You can add other elements on top of the image if needed
    }

    LaunchedEffect(key1 = true) {
        delay(2000) // Simulate splash screen duration
        onTimeout()
    }
}