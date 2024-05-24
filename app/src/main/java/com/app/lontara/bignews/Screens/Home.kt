package com.app.lontara.bignews.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.lontara.bignews.BottomSheetsAndDialogs.OpenAboutMeBottomSheet
import com.app.lontara.bignews.BottomSheetsAndDialogs.OpenUpcomingFeaturesBottomSheet
import com.app.lontara.bignews.Items.ArticleItems
import com.app.lontara.bignews.R
import com.app.lontara.bignews.ViewModel.NewsViewModel
import com.app.lontara.bignews.ui.theme.PrimaryColor
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch


@Composable
fun Home(viewModel: NewsViewModel, navController: NavHostController) {

    // Get Articles
    val res = viewModel.res.value?.articles ?: emptyList()

    // Get Number Of Articles
    val noOfNews = viewModel.res.value?.totalResults ?: "0"

    // Text State Manage For Query
    val queryText = remember { mutableStateOf("") }

    // Refresh Articles
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    // Drawer State Manage
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val interactionSource = remember { MutableInteractionSource() }

    // Open Bottom Sheets
    var openAboutMeBottomSheet by remember { mutableStateOf(false) }
    var openUpcomingFeaturesBottomSheet by remember { mutableStateOf(false) }


    // Modal Navigation Drawer
    ModalNavigationDrawer(drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = @Composable {
            ModalDrawerSheet(
                drawerContainerColor = Color.White
            ) {

                // Design Navigation Drawer
                Column(
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "App Logo",

                        modifier = Modifier
                            .size(70.dp)
                            .align(Alignment.CenterHorizontally),


                        )

                    Divider(modifier = Modifier
                        .width(200.dp)
                        .padding(vertical = 20.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.clickable {
                            scope.launch { drawerState.close() }
                            openUpcomingFeaturesBottomSheet = true

                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.upcoming_icon),
                            contentDescription = "Upcoming Features Icon"
                        )
                        Text(
                            text = "Upcoming Features", modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.github_icon),
                            contentDescription = "GitHub Icon"
                        )
                        Text(
                            text = "Source Code", modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.bug_icon),
                            contentDescription = "Bugs Icon"
                        )
                        Text(
                            text = "Bugs Reports", modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Divider(modifier = Modifier
                        .width(200.dp)
                        .padding(vertical = 20.dp))

                    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.clickable {
                        scope.launch { drawerState.close() }
                        openAboutMeBottomSheet = true
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.info_me_icon),
                            contentDescription = "About Me Icon"
                        )
                        Text(
                            text = "About Me", modifier = Modifier.padding(start = 8.dp)
                        )
                    }


                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.info_app_icon),
                            contentDescription = "About App Icon"
                        )
                        Text(
                            text = "Version 1.0", modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }

            }


        }

    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding()
        ) {
            Row(
                modifier = Modifier.padding(top = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Menu Icon
                Icon(painter = painterResource(id = R.drawable.menu_icon),
                    contentDescription = "Menu Icon",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            scope.launch { drawerState.open() }
                        }
                        .padding(start = 8.dp))

                // Search Bard
                OutlinedTextField(
                    value = queryText.value,
                    onValueChange = {
                        queryText.value = it
                    },
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.search_icon),
                            contentDescription = "Search Icon",
                            modifier = Modifier
                                .size(32.dp)
                                .padding(start = 8.dp)

                        )
                    },

                    trailingIcon = {
                        if (queryText.value.isNotEmpty()) {
                            Image(
                                painter = painterResource(id = R.drawable.go_icon),
                                contentDescription = " GO Icon",
                                modifier = Modifier
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = null
                                    ) {
                                        viewModel.updateQuery(queryText.value)
                                    }
                                    .size(48.dp)
                                    .padding(end = 16.dp)

                            )

                        }
                    },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Start, fontSize = 14.sp
                    ),
                    placeholder = {
                        Text(
                            text = "Search everything...", fontSize = 14.sp
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(100),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Black,
                        focusedIndicatorColor = PrimaryColor,
                        cursorColor = PrimaryColor,
                        focusedLeadingIconColor = PrimaryColor,
                        focusedTextColor = PrimaryColor,
                        selectionColors = TextSelectionColors(
                            handleColor = PrimaryColor, backgroundColor = PrimaryColor
                        )
                    ),
                    modifier = Modifier
                        .padding(end = 8.dp, start = 8.dp)
                        .fillMaxWidth()

                )

            }
            Spacer(modifier = Modifier.height(12.dp))

            // Check If Query Null Or Empty, If Yes Then Show Category Tab
            if (queryText.value.isNullOrEmpty()) {
                categoryTap(viewModel = viewModel)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 12.dp, bottom = 12.dp, start = 8.dp)

            ) {
                Text(
                    text = "Available news:", style = TextStyle(
                        fontSize = 12.sp, fontWeight = FontWeight.Normal
                    ), color = Color.Gray
                )
                Text(
                    text = noOfNews.toString(), style = TextStyle(
                        fontSize = 16.sp, fontWeight = FontWeight.Medium
                    ), color = Color.Black, modifier = Modifier.padding(start = 4.dp)
                )
            }

            // Refresh Manage
            SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing),
                onRefresh = { viewModel.refreshNews() }) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                        .padding(start = 8.dp, end = 8.dp)
                ) {

                    // LazyColumn For Show All Articles
                    LazyColumn(
                        modifier = Modifier
                            .background(color = Color.White)
                            .fillMaxSize()
                    ) {
                        items(res) {
                            ArticleItems(
                                image = it.urlToImage,
                                title = it.title,
                                date = it.publishedAt,
                                url = it.url,
                                article = it,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }

    // Open About Me Bottom Sheet

    if (openAboutMeBottomSheet) {
        OpenAboutMeBottomSheet(
            onDismissRequest = {
                openAboutMeBottomSheet = false
            })
    }

    // Open Upcoming Features BottomSheet

    if (openUpcomingFeaturesBottomSheet) {
        OpenUpcomingFeaturesBottomSheet(
            onDismissRequest = {
                openUpcomingFeaturesBottomSheet = false
            }
        )


    }

}


