package com.example.library.ui.theme.screens

import android.window.SplashScreen
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.library.R


@Composable
fun SplashScreen(onNavigateToNext:()-> Unit) {
    val splashScreenDuration = 3000L
    LaunchedEffect (Unit){
        kotlinx.coroutines.delay(splashScreenDuration)
        onNavigateToNext()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(300.dp)

            )

            Spacer(modifier = Modifier.height(16.dp)) // Space between the logo and the text

            Text(
                text = "OlexMart",
                color = Color.Black,
                fontSize = 28.sp,

            )
        }
    }
}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun SplashScreenPreview() {
//    SplashScreen()
//}
