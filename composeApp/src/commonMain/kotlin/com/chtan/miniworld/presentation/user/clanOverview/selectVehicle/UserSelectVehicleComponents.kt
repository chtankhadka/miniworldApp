package com.chtan.miniworld.presentation.user.clanOverview.selectVehicle

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.CurrencyPound
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.chtan.miniworld.presentation.components.shapes.RoundedParallelogram
import com.chtan.miniworld.presentation.user.dashboard.TitleParallelogramBox
import kotlinx.coroutines.launch
import miniworld.composeapp.generated.resources.Res
import miniworld.composeapp.generated.resources.car1
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class MapDetails(
    val id: String,
    val img: String
)

data class VehicleDetails(
    val id: String,
    val img: DrawableResource
)

sealed class UserSelectVehicleButtonType(
    val label: String,
){
    data object MapOverview: UserSelectVehicleButtonType("Map Overview")
    data object SelectVehicle: UserSelectVehicleButtonType("Select Cars")
    data object Continue: UserSelectVehicleButtonType("Continue")
}
@Suppress("SuspiciousIndentation")
@Composable
fun TestDrawablePager(modifier: Modifier, map: List<Pair<String, DrawableResource>>) {
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSelectVehicleTopAppBar(nav: NavHostController) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)
            .background(color = MaterialTheme.colorScheme.outline),
        expandedHeight = 0.dp,
        title = {
                Text(text = "Des Clan", fontWeight = FontWeight.Normal, fontSize = 16.sp)
        },
        navigationIcon = {
            Button(
                modifier = Modifier,
                shape = RoundedParallelogram(5.dp),
                onClick = {
                    nav.popBackStack()
            },
                content = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                        contentDescription = "Back"
                    )
                    Text("Back")
                })
        },
        actions = {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    TitleParallelogramBox(
                        number = "100",
                        currencyIcon = Icons.Default.CurrencyBitcoin,
                        type = "Credit",
                        onClickAdd = {

                        })
                    TitleParallelogramBox(
                        number = "1000",
                        currencyIcon = Icons.Default.CurrencyPound,
                        type = "Coins",
                        onClickAdd = {

                        })
                }
                Image(
                    modifier = Modifier.size(32.dp).border(
                        width = 1.dp,
                        color = Color.Blue,
                        shape = CircleShape
                    ).clip(CircleShape),
                    painter = painterResource(Res.drawable.car1),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )

            }


        })
}
