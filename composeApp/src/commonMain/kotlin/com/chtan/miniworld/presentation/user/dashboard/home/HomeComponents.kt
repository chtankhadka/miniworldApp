package com.chtan.miniworld.presentation.user.dashboard.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.chtan.miniworld.presentation.components.pager.GlowingPagerDot
import com.chtan.miniworld.presentation.components.shapes.RoundedParallelogram
import kotlinx.coroutines.launch
import miniworld.composeapp.generated.resources.Res
import miniworld.composeapp.generated.resources.car1
import org.jetbrains.compose.resources.painterResource


data class HomeRCEventPagerDetails(
    val eventName: String, val imgUrl: String, val eventDetails: String
)

@Composable
fun HomeRCEventPager(data: List<HomeRCEventPagerDetails>, boxCardModifier: Modifier) {
    val pagerState = rememberPagerState(pageCount = {
        data.size
    })

    val scope = rememberCoroutineScope()

    Column(
        modifier = boxCardModifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier,
        ) { page ->

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.LightGray)
            ) {
//                    AsyncImage(
//                        modifier = Modifier.size(400.dp).padding(10.dp)
//                            .clip(shape = RoundedCornerShape(5)),
//                        model = data[page].imgUrl,
//                        contentScale = ContentScale.Crop,
//                        contentDescription = null,
//                    )

                Image(
                    modifier = Modifier.matchParentSize().clip(shape = RoundedCornerShape(5)),
                    painter = painterResource(Res.drawable.car1),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 2.dp),


                    ) {
                    Text(
                        text = data[page].eventName,
                        lineHeight = 10.sp
                    )
                    Text(
                        text = data[page].eventDetails,
                        lineHeight = 10.sp
                    )
                    CompositionLocalProvider(
                        LocalMinimumInteractiveComponentSize provides Dp.Unspecified
                    ) {
                        Button(
                            onClick = {},
                            modifier = Modifier.defaultMinSize(
                                minWidth = 1.dp,
                                minHeight = 1.dp
                            ),
                            shape = RoundedParallelogram(5.dp),
                            contentPadding = PaddingValues(
                                horizontal = 5.dp,
                                vertical = 0.dp
                            )
                        ) {
                            Text("VIEW EVENT", fontSize = 10.sp)
                        }
                    }

                    Row(
                        Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(pagerState.pageCount) { iteration ->
                            GlowingPagerDot(
                                isSelected = iteration == pagerState.currentPage,
                                onClickPagerDot = {
                                    scope.launch {
                                        pagerState.animateScrollToPage(iteration)
                                    }

                                })
                        }
                    }


                }
            }

        }


    }
}

data class MapInfo(
    val mapName: String,
    val ranked: Int,
    val vehicles: Int,
    val available: Int,
    val mapImg: String
)

@Composable
fun HomeRCTopRankedMaps(rankedMaps: List<MapInfo>) {
    Card(
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Ranked maps")
                RadioButton(
                    selected = true,
                    onClick = {

                    }
                )


            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(rankedMaps) { mapInfo ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = mapInfo.ranked.toString().padStart(2, '0'),
                            modifier = Modifier.width(36.dp),
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily.Monospace
                        )
                        AsyncImage(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(shape = CircleShape),
                            model = mapInfo.mapImg,
                            contentScale = ContentScale.Crop,
                            contentDescription = "Map Image"
                        )
                        Text(text = mapInfo.mapName.take(5).padEnd(10, ' '))
                        Text(
                            text = mapInfo.vehicles.toString().padStart(2, '0'),
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = mapInfo.available.toString().padStart(2, '0'),
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily.Monospace
                        )

                    }


                }
            }
        }


    }

}