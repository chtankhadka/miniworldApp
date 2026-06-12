package com.chtan.miniworld.presentation.user.clanOverview.startOverview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.CardTravel
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.chtan.miniworld.presentation.components.modifier.bottomBorder
import com.chtan.miniworld.presentation.components.shapes.RoundedParallelogram
import com.chtan.miniworld.presentation.user.clanOverview.selectVehicle.VehicleDetails
import miniworld.composeapp.generated.resources.Res
import miniworld.composeapp.generated.resources.car3
import miniworld.composeapp.generated.resources.car4
import miniworld.composeapp.generated.resources.car5
import miniworld.composeapp.generated.resources.car6
import miniworld.composeapp.generated.resources.testCountryMap
import org.jetbrains.compose.resources.painterResource

@Composable
fun StartOverviewScreen(
    nav: NavHostController,
    event: (UserStartOverviewEvent) -> Unit,
    state: UserStartOverviewState
) {
    //selected Vehicle
    var selectedVehicle by remember { mutableStateOf("1") }
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                // Map Descriptions,
                Card {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
                        maxLines = 3,
                        text = buildAnnotatedString {
                            withStyle(style = ParagraphStyle(lineHeight = 12.sp)) {

                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.LightGray
                                    )
                                ) {
                                    append("EVENT\n")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 14.sp, color = Color.Green
                                    )
                                ) {
                                    append("DESERT STORM \n")

                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Thin,
                                        color = Color.LightGray
                                    )
                                ) {
                                    append("SAND CANYON CIRCUIT")
                                }

                            }
                        })
                    Image(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f),
                        painter = painterResource(Res.drawable.testCountryMap),
                        contentDescription = "Map Descriptions",
                        contentScale = ContentScale.Crop,
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color(42, 69, 99))
                            .padding(5.dp)
                    ) {
                        Image(
                            modifier = Modifier.fillMaxHeight().aspectRatio(1f),
                            painter = painterResource(Res.drawable.testCountryMap),
                            contentDescription = "Map Descriptions",
                            contentScale = ContentScale.Crop,
                        )
                        Column(modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceAround) {
                            listOf(
                                UserStartOverviewTrackDetails(
                                    id = "1",
                                    icon = Icons.Default.Route,
                                    heading = "Track Length",
                                    detail = "12.5 M",
                                    color = Color.White
                                ), UserStartOverviewTrackDetails(
                                    id = "2",
                                    icon = Icons.Default.Timer,
                                    heading = "Time",
                                    detail = "60.00",
                                    color = Color.White
                                ), UserStartOverviewTrackDetails(
                                    id = "3",
                                    icon = Icons.Default.AutoGraph,
                                    heading = "Difficulty",
                                    detail = "Hard",
                                    color = Color.Red
                                )


                            ).forEach { item ->
                                TrackDetails(item)

                            }

                        }
                    }
                    Row {
                        // track image
                        Image(
                            modifier = Modifier.fillMaxWidth(),
                            painter = painterResource(Res.drawable.testCountryMap),
                            contentDescription = "Map Descriptions",
                            contentScale = ContentScale.Crop,

                            )

                        //track details
                        Column {
                            Text("Track Length")
                            Text("12.5 KM")
                        }
                    }
                    // some notes
                    Text("A challenging off-road track through the Sandy Canyon, Sharp turns, steep climbs and rough terrains will test you skills!")
                }
                Card {
                    Text("EVENT")
                    Text("DESERT STORM")
                    Text("SAND CANYON CIRCUIT")

                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(Res.drawable.testCountryMap),
                        contentDescription = "Map Descriptions",
                        contentScale = ContentScale.Crop,

                        )
                    Row {
                        // track image
                        Image(
                            modifier = Modifier.fillMaxWidth(),
                            painter = painterResource(Res.drawable.testCountryMap),
                            contentDescription = "Map Descriptions",
                            contentScale = ContentScale.Crop,

                            )

                        //track details
                        Column {
                            Text("Track Length")
                            Text("12.5 KM")
                        }
                    }
                    // some notes
                    Text("A challenging off-road track through the Sandy Canyon, Sharp turns, steep climbs and rough terrains will test you skills!")
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                Card {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(5.dp).drawBehind {
                            drawLine(
                                color = Color.White,
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = 2.dp.toPx()
                            )
                        }, maxLines = 1, text = "SELECTED VEHICLE"
                    )


                    //
                    UserStartOverviewVehicleDetailsDrawablePager(
                        modifier = Modifier.fillMaxWidth()
                            .fillMaxHeight(0.5f), map = listOf(
                            VehicleDetails("1", img = Res.drawable.car3),
                            VehicleDetails("2", img = Res.drawable.car4),
                            VehicleDetails("3", img = Res.drawable.car5),
                            VehicleDetails("4", img = Res.drawable.car6),

                            ).map { it.id to it.img }, currentPagerId = {
                            selectedVehicle = it
                        })
                    Column(modifier = Modifier.fillMaxSize().padding(5.dp),
                        verticalArrangement = Arrangement.SpaceAround,
                        ) {
                        state.vehicleDetails.find { it.id == selectedVehicle }.let {
                                it!!.detail.forEach {
                                    VehicleDetail(it)
                            }

                        }
                    }


                }


            }
            // Race objectives, Rewards, Event detaisl
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Card(
                    modifier = Modifier.weight(1f)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(5.dp),
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Text("RACE OBJECTIVES")
                        HorizontalDivider(modifier = Modifier.height(2.dp))
                        listOf(
                            Pair(Icons.Default.Timer, "FINISH BEFORE TIME EXPIRES"),
                            Pair(Icons.Default.Flag, "REACH ALL CHECKPOINTS"),
                            Pair(Icons.Default.Security, "AVOID SERVER VEHICLE DAMAGE")
                        ).forEach { item ->
                            RaceObjectiveItem(item)
                        }
                    }

                }

                Card(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        text = "EVENT DETAILS"
                    )
                    HorizontalDivider(modifier = Modifier.height(2.dp))
                    Column(
                        modifier = Modifier.fillMaxSize().padding(5.dp),
                        verticalArrangement = Arrangement.SpaceAround,
                    ) {
                        listOf(
                            Pair("WEATHER", "SUNNY"),
                            Pair("TIME OF DAY", "14:30"),
                            Pair("PLAYERS", "7"),
                            Pair("DAMAGE", "ENABLED")
                        ).forEach { item ->
                            EventDetailItem(item)
                        }


                    }

                }
                Card {

                }

            }
        }
        Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.2f).padding(bottom = 5.dp)) {
            // Players descriptions
            LazyRow(
                modifier = Modifier.weight(1f).padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.width(5.dp))
                }
                items(10) {
                    Box(
                        modifier = Modifier.clip(RoundedParallelogram(5.dp))
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.testCountryMap),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxHeight().aspectRatio(1f).graphicsLayer {
                                scaleX = 1.5f // adjust as needed
                            }.clip(RoundedParallelogram(5.dp))
                        )
                        Text(
                            modifier = Modifier.align(alignment = Alignment.BottomCenter),
                            text = "24lvl"
                        )

                    }
                }


            }
            Button(
                modifier = Modifier.fillMaxHeight(),
                shape = RoundedParallelogram(5.dp),
                onClick = {

                },
                content = {
                    Text("START RACE")
                })
        }
    }
}


@Composable
private fun EventDetailItem(item: Pair<String, String>) {
    Row(
        modifier = Modifier.bottomBorder(),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f).defaultMinSize(0.dp),
            lineHeight = 12.sp,
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraLight,
            textAlign = TextAlign.Start,
            fontFamily = FontFamily.Monospace,
            text = item.first,
        )
        Text(
            modifier = Modifier.weight(1f),
            lineHeight = 12.sp,
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraLight,
            textAlign = TextAlign.Start,
            fontFamily = FontFamily.Monospace,
            text = item.second,

            )
    }

}

@Composable
private fun RaceObjectiveItem(item: Pair<ImageVector, String>) {
    Row(
        modifier = Modifier.bottomBorder(),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = item.first,
            tint = Color.Yellow,
            contentDescription = null
        )
        Text(
            fontSize = 10.sp,
            lineHeight = 10.sp,
            fontWeight = FontWeight.ExtraLight,
            textAlign = TextAlign.Start,
            text = item.second,
        )
    }
}


@Composable
private fun VehicleDetail(details: VehicleDetail) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            tint = MaterialTheme.colorScheme.primary,
            imageVector = details.img,
            contentDescription = "Speed"
        )
        Text(
            modifier = Modifier,
            text = details.name.take(10).padEnd(10, ' '),
            fontWeight = FontWeight.Thin,
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace

        )
        Box(
            modifier = Modifier.height(5.dp).weight(1f).background(color = Color.White)
        ) {
            Card(
                modifier = Modifier.fillMaxSize(0.7f),
                colors = CardDefaults.cardColors(Color.Green)
            ) { }
        }

        Text(
            text = details.value.take(4).padEnd(4, ' '), fontWeight = FontWeight.Thin, fontSize = 10.sp
        )
    }
}
