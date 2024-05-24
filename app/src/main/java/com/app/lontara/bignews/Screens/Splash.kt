package com.app.lontara.bignews.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.lontara.bignews.Navigation.Routes
import com.app.lontara.bignews.R
import kotlinx.coroutines.delay

@Composable
fun Splash(navController: NavHostController) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

       Image(painter = painterResource(id = R.drawable.logo), contentDescription = "App Logo",

           modifier = Modifier.size(300.dp)

           )

      LaunchedEffect(key1 = true) {

          delay(3000)

          navController.navigate(Routes.Home.routes)

      }
    }

}