package com.chtan.miniworld.presentation.user.drive.selectdevices

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import miniworld.composeapp.generated.resources.Res
import miniworld.composeapp.generated.resources.car
import miniworld.composeapp.generated.resources.car1
import miniworld.composeapp.generated.resources.car2
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class NavDevicesDetails(
    val carName: String,
    var carDetails: String = "",
    var carImage: DrawableResource,
)


@Composable
fun NavDevicesScreen(){
    var selectedDevice  by remember { mutableStateOf(NavDevicesDetails(carName = "BMW", carImage = Res.drawable.car))}
    val deviceList = listOf(
        NavDevicesDetails(carName = "BMW", carImage = Res.drawable.car),
        NavDevicesDetails(carName = "BMW", carImage = Res.drawable.car1),
        NavDevicesDetails(carName = "BMW", carImage = Res.drawable.car2),
        )
    Column(modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        deviceList.forEach {
            Image(
                modifier = Modifier.size(if(selectedDevice == it) 100.dp else 70.dp).clickable{
                    selectedDevice = it
                },
                painter = painterResource( it.carImage),
                contentDescription = it.carName
            )

        }
    }



}