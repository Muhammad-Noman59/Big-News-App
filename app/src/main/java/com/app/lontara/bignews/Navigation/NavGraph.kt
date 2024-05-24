package com.app.lontara.bignews.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.lontara.bignews.Screens.Details
import com.app.lontara.bignews.Screens.Home
import com.app.lontara.bignews.Screens.Splash
import com.app.lontara.bignews.ViewModel.NewsViewModel
import java.net.URLDecoder

@Composable
fun NavGraph(navController: NavHostController,viewModel: NewsViewModel) {


    NavHost(navController = navController, startDestination = Routes.Splash.routes){

        composable(Routes.Splash.routes){
            Splash(navController)
        }

        composable(Routes.Home.routes){
            Home(viewModel,navController)
        }

        composable(
            route = Routes.Details.routes,
            arguments = listOf(navArgument("articleJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val encodedJson = backStackEntry.arguments?.getString("articleJson")!!
            val articleJson = URLDecoder.decode(encodedJson, "UTF-8")
            Details(articleJson,navController)
        }
    }

}