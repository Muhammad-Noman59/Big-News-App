package com.app.lontara.bignews.Screens

import android.text.format.DateUtils
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.time.Instant

@Composable
fun TimeAgo(timestamp: String) {
    val timeAgo = remember(timestamp) {
        val instant = Instant.parse(timestamp)
        val now = System.currentTimeMillis()
        val time = instant.toEpochMilli()
        val relativeTime = DateUtils.getRelativeTimeSpanString(
            time,
            now,
            DateUtils.MINUTE_IN_MILLIS,
            DateUtils.FORMAT_ABBREV_RELATIVE
        ).toString()
        relativeTime

    }

    Text(
        text =timeAgo ,
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        ),
        color = Color.Gray
    )
}
