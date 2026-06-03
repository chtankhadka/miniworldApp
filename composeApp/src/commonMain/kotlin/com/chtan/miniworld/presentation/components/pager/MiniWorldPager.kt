package com.chtan.miniworld.presentation.components.pager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch

@Suppress("SuspiciousIndentation")
@Composable
fun MiniWorldPager(modifier: Modifier, map: List<Pair<String, String>>) {
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
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = map[page].second,
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )


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


