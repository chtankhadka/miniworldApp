package com.chtan.miniworld.presentation.user.clanOverview.startOverview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chtan.miniworld.presentation.components.modifier.bottomBorder
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


data class UserStartOverviewTrackDetails(
    val id: String,
    val icon: ImageVector,
    val heading: String,
    val detail: String,
    val color: Color
)

data class UserStartOverviewVehicleDetails(
    val id: String,
    val detail: List<VehicleDetail>
)

data class VehicleDetail(
    val name: String,
    val value: String,
    val img: ImageVector
)
@Composable
fun TrackDetails(item: UserStartOverviewTrackDetails) {
    Row(modifier = Modifier.fillMaxWidth().bottomBorder(),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = item.icon, contentDescription = item.heading)
        Text(
            modifier = Modifier.weight(1f).padding(horizontal = 5.dp),
            maxLines = 2,
            text = buildAnnotatedString {
                withStyle(style = ParagraphStyle(lineHeight = 10.sp)){

                    withStyle(style = SpanStyle(fontSize = 10.sp, fontWeight = FontWeight.Thin, color = Color.Yellow)) {
                        append("${item.heading}\n")
                    }
                    withStyle(style = SpanStyle(fontSize = 12.sp, color = item.color)) {
                        append(item.detail)
                    }

                }
            })
    }
}

@Suppress("SuspiciousIndentation")
@Composable
fun UserStartOverviewVehicleDetailsDrawablePager(
    modifier: Modifier,
    map: List<Pair<String, DrawableResource>>,
    currentPagerId: (String) -> Unit
    ) {
    val pagerState = rememberPagerState(pageCount = {
        map.count()
    })

    val scope = rememberCoroutineScope()

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = {
            scope.launch {
                if (pagerState.canScrollBackward) pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                else pagerState.animateScrollToPage(pagerState.pageCount - 1)
            }
        }, content = {
            Icon(imageVector = Icons.Filled.ChevronLeft, contentDescription = "Left Arrow")
        })


        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxHeight().aspectRatio(1f),
        ) { page ->
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(map[page].second),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
            currentPagerId(map[pagerState.currentPage].first)



        }

        IconButton(onClick = {
            scope.launch {
                if (pagerState.canScrollForward) pagerState.animateScrollToPage(pagerState.currentPage + 1)
                else pagerState.animateScrollToPage(0)
            }
        }, content = {
            Icon(imageVector = Icons.Filled.ChevronRight, contentDescription = "Right Arrow")
        })


    }
}


