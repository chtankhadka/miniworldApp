package com.chtan.miniworld.presentation.user.selectVehicle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AreaChart
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Hardware
import androidx.compose.material.icons.filled.TempleHindu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.chtan.miniworld.Route
import com.chtan.miniworld.presentation.components.pager.MiniWorldPager
import com.chtan.miniworld.presentation.components.shapes.RoundedParallelogram
import com.chtan.miniworld.presentation.user.map.ObjectDetails

@Composable
fun UserSelectVehicleScreen(
    nav: NavHostController,
    event: (UserSelectVehicleEvent) -> Unit,
    state: UserSelectVehicleState
) {
    var selectedButton by remember {
        mutableStateOf<UserSelectVehicleButtonType>(UserSelectVehicleButtonType.MapOverview)
    }


    Column(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.outline).padding(2.dp)){
        Row(modifier = Modifier.weight(1f)) {
            // Pictures

            if (selectedButton == UserSelectVehicleButtonType.MapOverview)
                MiniWorldPager(modifier = Modifier.fillMaxWidth(0.7f), state.mapDetails.map { it.id to it.img })
            else
                TestDrawablePager(modifier = Modifier.fillMaxWidth(0.7f), state.vehicleDetails.map { it.id to it.img })
            //Details

            Column(
                modifier = Modifier.weight(1f).padding(start = 5.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)){
                    Text(
                        maxLines = 2,
                        text = buildAnnotatedString {
                            withStyle(style = ParagraphStyle(lineHeight = 12.sp)){

                                withStyle(style = SpanStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.LightGray)) {
                                    append("Brighton \n")
                                }
                                withStyle(style = SpanStyle(fontSize = 14.sp, color = Color.White)) {
                                    append("The Des Island")

                                }
                                withStyle(style = SpanStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.LightGray)) {
                                    append("  5m2")
                                }

                            }
                        })
                Card {
                    Text(modifier = Modifier.fillMaxWidth(),
                        text = "Objects",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround) {
                        IconButton(
                            shape = RoundedParallelogram(5.dp),
                            onClick = {

                            },
                            content = {
                                Icon(imageVector = Icons.Default.Details, contentDescription = "Details")
                            }

                        )
                        IconButton(
                            onClick = {

                            },
                            content = {
                                Icon(imageVector = Icons.Default.AreaChart, contentDescription = "Details")
                            }

                        )
                        IconButton(
                            onClick = {

                            },
                            content = {
                                Icon(imageVector = Icons.Default.TempleHindu, contentDescription = "Details")
                            }

                        )
                        IconButton(
                            onClick = {

                            },
                            content = {
                                Icon(imageVector = Icons.Default.Hardware, contentDescription = "Details")
                            }

                        )
                    }
                    //object items
                    Column(modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        ObjectDetails("Difficulty",20)
                        ObjectDetails("Adventure" ,70)
                        ObjectDetails("Busy" ,20)
                        ObjectDetails("Loved" ,80)
                    }


                }
            }

        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
            listOf(
                UserSelectVehicleButtonType.Back,
                UserSelectVehicleButtonType.MapOverview,
                UserSelectVehicleButtonType.SelectVehicle,
                UserSelectVehicleButtonType.Start,
            ).forEach { buttonType ->
                Button(
                    enabled = selectedButton != buttonType,
                    modifier = Modifier.weight(1f),
                    shape = RoundedParallelogram(5.dp),
                    onClick = {
                        selectedButton = buttonType
                        when (buttonType){
                            UserSelectVehicleButtonType.Back -> nav.popBackStack()
                            UserSelectVehicleButtonType.MapOverview -> {

                            }
                            UserSelectVehicleButtonType.SelectVehicle -> {

                            }
                            UserSelectVehicleButtonType.Start -> {
                                nav.navigate(Route.Derive)
                            }
                        }

                    },
                    content = {
                        Text(buttonType.label)
                    })
            }
        }
    }
}



