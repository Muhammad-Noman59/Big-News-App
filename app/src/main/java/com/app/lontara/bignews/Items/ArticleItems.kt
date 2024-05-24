package com.app.lontara.bignews.Items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.app.lontara.bignews.API_Service.Article
import com.app.lontara.bignews.BottomSheetsAndDialogs.openFullImageDialog
import com.app.lontara.bignews.Navigation.Routes
import com.app.lontara.bignews.Screens.TimeAgo
import com.app.lontara.bignews.Screens.ImageLoading
import com.app.lontara.bignews.ui.theme.PrimaryColor
import com.google.gson.Gson

@Composable
fun ArticleItems(
    image: String? = null,
    title: String? = null,
    date: String? = null,
    url: String? = null,
    article: Article,
    navController: NavHostController

) {

    // Open Full Image Dialog State Manage
    var openFullImageDialog by remember { mutableStateOf(false) }

    // Local UriHandler For Open Url
    val uriHandler = LocalUriHandler.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
            .shadow(
                elevation = 10.dp, spotColor = Color.Black, shape = RoundedCornerShape(10.dp)
            )
            .clip(
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                val articleJson = Gson().toJson(article)
                navController.navigate(Routes.Details.createRoute(articleJson))
            }, colors = CardDefaults.cardColors(
            containerColor = Color.White
        )

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {


            if (image != null) {
                SubcomposeAsyncImage(model = image, contentDescription = "News Image",

                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
                        )
                        .height(150.dp)
                        .fillMaxWidth()
                        .clickable {
                            openFullImageDialog = true
                        }, contentScale = ContentScale.FillBounds, loading = {
                        ImageLoading()
                    }

                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            if (title != null) {
                Text(
                    text = title, style = TextStyle(
                        fontSize = 16.sp, fontWeight = FontWeight.Normal
                    ), maxLines = 2, overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {


                if (date != null) {
                    date.let {
                        TimeAgo(timestamp = it)
                    }
                }

                if (url != null) {
                    Button(
                        onClick = {

                            // Open News Website
                            uriHandler.openUri(url)
                        },

                        shape = RoundedCornerShape(5.dp), colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryColor
                        )

                    ) {

                        Text(text = "Visit website")

                    }
                }

            }

        }

    }


    // Open Full Image Dialog
    if (openFullImageDialog) {
        openFullImageDialog(onDismissRequest = { openFullImageDialog = false }, image = image)
    }


}