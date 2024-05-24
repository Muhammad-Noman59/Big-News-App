package com.app.lontara.bignews.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.app.lontara.bignews.API_Service.Article
import com.app.lontara.bignews.Navigation.Routes
import com.app.lontara.bignews.R
import com.app.lontara.bignews.ui.theme.PrimaryColor
import com.google.gson.Gson

@Composable
fun Details(articleJson: String, navController: NavHostController) {

    val res = Gson().fromJson(articleJson, Article::class.java)
    val interactionSource = remember { MutableInteractionSource() }

    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    navController.navigate(route = Routes.Home.routes)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_icon),
                contentDescription = "Back btn",
                modifier = Modifier.size(24.dp)
            )


            if (res.title != null) {
                Text(
                    text = res.title,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    maxLines = 1,
                    modifier = Modifier.padding(start = 10.dp),
                    overflow = TextOverflow.Ellipsis
                )
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
        ) {


            Spacer(modifier = Modifier.height(10.dp))

            if (res.urlToImage != null) {

                AsyncImage(
                    model = res.urlToImage,
                    contentDescription = "New Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(
                            shape = RoundedCornerShape(20.dp)
                        ),
                    contentScale = ContentScale.Crop

                )
                Spacer(modifier = Modifier.height(8.dp))
            }


            if (res.title != null) {
                Text(
                    text = res.title,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))
            }



            if (res.description != null) {
                Text(
                    text = "Description:",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                )
                Spacer(modifier = Modifier.height(4.dp))


                Text(
                    text = res.description,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            if (res.content != null) {
                Text(
                    text = "Content:",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                )
                Spacer(modifier = Modifier.height(4.dp))


                Text(
                    text = res.content,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    if (res.author != null) {
                        Text(
                            text = "Author: ",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal
                            ),
                            color = Color.Gray
                        )

                        Text(
                            text = res.author,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }


                if (res.publishedAt != null) {
                    TimeAgo(timestamp = res.publishedAt)
                }

            }


            if (res.url != null) {
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        uriHandler.openUri(res.url)
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            shape = RoundedCornerShape(100)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryColor
                    )

                ) {

                    Text(
                        text = "Visit website",
                        color = Color.White
                    )
                }
            }
        }


    }


}