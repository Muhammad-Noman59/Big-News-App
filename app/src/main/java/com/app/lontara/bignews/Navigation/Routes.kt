package com.app.lontara.bignews.Navigation


sealed class Routes (val routes: String){

    object Splash : Routes("splash")
    object Home : Routes("home")
    object Details : Routes("details/{articleJson}") {
        fun createRoute(articleJson: String): String {
            val encodedJson = java.net.URLEncoder.encode(articleJson, "UTF-8")
            return "details/$encodedJson"
        }
    }
}