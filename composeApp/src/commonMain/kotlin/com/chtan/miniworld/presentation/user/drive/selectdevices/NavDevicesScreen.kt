package com.chtan.miniworld.presentation.user.drive.selectdevices

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.chtan.miniworld.presentation.components.shapes.RoundedParallelogram
import io.ktor.websocket.Frame
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

sealed class UserDriveButton(
    val label: String,
    val icon: ImageVector
){
    data object BackToMenu: UserDriveButton("Menu", Icons.AutoMirrored.Default.ExitToApp)
    data object Close: UserDriveButton("Close", Icons.AutoMirrored.Default.ArrowBack)

}


@Composable
fun NavDevicesScreen(onClick: (UserDriveButton) -> Unit) {
    var selectedDevice  by remember { mutableStateOf(NavDevicesDetails(carName = "BMW", carImage = Res.drawable.car))}
    val deviceList = listOf(
        NavDevicesDetails(carName = "BMW", carImage = Res.drawable.car),
        NavDevicesDetails(carName = "BMW", carImage = Res.drawable.car1),
        NavDevicesDetails(carName = "BMW", carImage = Res.drawable.car2),
        )
    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                listOf(UserDriveButton.BackToMenu, UserDriveButton.Close).forEach {
                    Button(
                        modifier = Modifier.weight(1f),
                        shape = RoundedParallelogram(5.dp),
                        onClick = {
                            onClick(it)
                        },
                        content ={
                            Text(text = it.label)
                        }
                    )
//                    IconButton(
//                        modifier = Modifier,
//                        onClick = {
//                            onClick(it)
//                        },
//                        content = {
//                            Icon(
//                                imageVector = it.icon,
//                                contentDescription = it.label
//                            )
//                        }
//                    )
                }


            }
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