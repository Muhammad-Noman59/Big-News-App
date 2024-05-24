package com.app.lontara.bignews.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.lontara.bignews.ViewModel.NewsViewModel
import com.app.lontara.bignews.ui.theme.PrimaryColor

@Composable
fun categoryTap(viewModel: NewsViewModel) {
    val tabs = listOf("Top Headlines", "Tech", "Sports", "Business","Entertainment","Gaming","Health","Science")
    var selectedTabIndex by remember { mutableStateOf(0) }

    ScrollableTabRow(
        edgePadding = 8.dp,
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = Color.White,
        indicator = {tabPositions ->
            if (selectedTabIndex < tabPositions.size) {
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .clip(shape = CircleShape),
                    color = PrimaryColor
                )} },
        selectedTabIndex = selectedTabIndex
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selectedContentColor = PrimaryColor,
                unselectedContentColor = Color.Gray,
                selected = selectedTabIndex == index,
                onClick = {

                    selectedTabIndex = index
                    val category = when (title) {
                        "Tech" -> "technology"
                        "Sports" -> "sports"
                        "Business" -> "business"
                        "Entertainment" -> "entertainment"
                        "Gaming" -> "gaming"
                        "Health" -> "health"
                        "Science" -> "science"
                        else -> null
                    }
                    viewModel.updateCategory(category)
                },
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 5.dp)


            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(bottom = 5.dp)
                    )
            }
        }
    }
}
