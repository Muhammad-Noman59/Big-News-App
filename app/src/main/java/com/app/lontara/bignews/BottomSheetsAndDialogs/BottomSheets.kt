package com.app.lontara.bignews.BottomSheetsAndDialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.lontara.bignews.R
import com.app.lontara.bignews.ui.theme.PrimaryColor


// Open About Me Bottom Sheet
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun OpenAboutMeBottomSheet(onDismissRequest: () -> Unit) {

    // Local UriHandler For Open Url
    val uriHandler = LocalUriHandler.current

    // Modal Bottom Sheet
    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        containerColor = Color.White,
        modifier = Modifier.padding()
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.my_image),
                contentDescription = "My Image",

                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)

            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Muhammad Noman", style = TextStyle(
                    fontSize = 24.sp, fontWeight = FontWeight.Medium
                ), modifier = Modifier.align(Alignment.CenterHorizontally)

            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.facebook_logo),
                    contentDescription = "Facebook Logo",
                    modifier = Modifier.clickable {
                        uriHandler.openUri("https://www.facebook.com/muhammad.noman59")
                    }
                )

                Image(
                    painter = painterResource(id = R.drawable.github_logo),
                    contentDescription = "GitHub Logo",
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .clickable {
                            uriHandler.openUri("https://github.com/Muhammad-Noman59")
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.linkedin_logo),
                    contentDescription = "LinkedIn Logo",
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .clickable {
                            uriHandler.openUri("https://www.linkedin.com/in/muhammad-noman59/")
                        }
                )

                Image(
                    painter = painterResource(id = R.drawable.whatsapp_logo),
                    contentDescription = "Whatsapp Logo",
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .clickable {
                            uriHandler.openUri("https://wa.me/+923104881573")
                        }
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "About Me", style = TextStyle(
                    fontSize = 24.sp, fontWeight = FontWeight.SemiBold
                ), modifier = Modifier.padding(start = 16.dp)
            )

            Text(
                text = "I am a passionate mobile app developer and designer with experience in Android development and UI/UX design.  I have a strong foundation in Kotlin, XML, Android Studio, and Jetpack Compose, constantly learning new technologies to enhance my skills. Starting my career as a full-stack developer in January 2024,  I am learning full-stack mobile application development and frameworks to enhance my skills, including KMP Jetpack Compose for desktop applications and websites. Ready to assist you around the clock with any questions or inquiries about my services, dedicated to delivering high-quality solutions and continuously improving my skills to provide the best possible service to my clients.",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                ),
                color = Color.Gray,
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 24.dp
                )
            )

        }

    }


}

// Open Upcoming Features Bottom Sheet
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun OpenUpcomingFeaturesBottomSheet(onDismissRequest: () -> Unit) {

  val  height = Modifier.height(12.dp)

    // Suggestions Dialog State Manage
    var suggestionsDialog by remember { mutableStateOf(false) }

    // Modal Bottom Sheet
    ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = {
        onDismissRequest()
    }) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = "Filter News by country Icon",
                    modifier = Modifier
                        .rotate(180f)
                        .size(18.dp)
                        .padding(start = 8.dp)
                )

                Text(
                    text = "Filter news by country",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    )
            }

            Spacer(height)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = "Dynamic Theme Icon",
                    modifier = Modifier
                        .rotate(180f)
                        .size(18.dp)
                        .padding(start = 8.dp)
                )

                Text(
                    text = "Dynamic theme",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = "Download News Image Icon",
                    modifier = Modifier
                        .rotate(180f)
                        .size(18.dp)
                        .padding(start = 8.dp)
                )

                Text(
                    text = "Download news image",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }

            Spacer(height)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = "Zoom News Image Icon",
                    modifier = Modifier
                        .rotate(180f)
                        .size(18.dp)
                        .padding(start = 8.dp)
                )

                Text(
                    text = "Zoom news image",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }

            Spacer(height)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = "Fix Bugs Icon",
                    modifier = Modifier
                        .rotate(180f)
                        .size(18.dp)
                        .padding(start = 8.dp)
                )

                Text(
                    text = "Fix bugs",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    suggestionsDialog = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Text(text = "Give more suggestions")
            }
        }

    }


    if (suggestionsDialog) {
        suggestionsDialog(
            onDismissRequest = {
                suggestionsDialog = false
            }
        )
    }

}