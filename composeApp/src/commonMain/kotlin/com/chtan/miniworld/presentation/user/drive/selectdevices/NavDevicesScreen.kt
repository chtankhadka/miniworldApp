package com.chtan.miniworld.presentation.user.drive.selectdevices

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
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
fun NavDevicesScreen(drawerState: DrawerState, coroutineScope: CoroutineScope) {
    var selectedDevice  by remember { mutableStateOf(NavDevicesDetails(carName = "BMW", carImage = Res.drawable.car))}
    val deviceList = listOf(
        NavDevicesDetails(carName = "BMW", carImage = Res.drawable.car),
        NavDevicesDetails(carName = "BMW", carImage = Res.drawable.car1),
        NavDevicesDetails(carName = "BMW", carImage = Res.drawable.car2),
        )
    Box(modifier = Modifier.fillMaxSize()) {
        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = {
                coroutineScope.launch {
                    if (drawerState.isOpen) drawerState.close() else drawerState.open()
                }
            },
            content = {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Change Car"
                )
            }
        )
        Column(modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
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




}