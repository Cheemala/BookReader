package com.cheemala.bookreader.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cheemala.bookreader.AppHeaderText
import com.cheemala.bookreader.R
import com.cheemala.bookreader.navigation.BookReaderScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(800, easing = { OvershootInterpolator(8f).getInterpolation(it) })
        )
        delay(2000L)
        navController.navigate(BookReaderScreens.LoginScreen.name)
    }

    Column(modifier = Modifier.padding(2.dp).fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            modifier = Modifier
                .padding(2.dp)
                .size(330.dp)
                .scale(scale.value),
            color = Color.White,
            border = BorderStroke(2.dp, Color.LightGray),
            shape = CircleShape,
            ) {
            AppHeaderText(title = R.string.app_name)
        }
    }

}