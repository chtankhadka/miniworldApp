package com.chtan.miniworld.presentation.admin.clan.clanoverview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.outlined.Camera
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Sensors
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.chtan.miniworld.data.datasource.network.model.admin.clan.AddDeviceToClanRequest
import com.chtan.miniworld.data.datasource.network.result.DataError
import com.chtan.miniworld.presentation.admin.clan.createclan.AdminClanTopAppBar
import com.chtan.miniworld.presentation.admin.clan.createclan.CreateAdminClanEvent
import com.chtan.miniworld.presentation.components.modifier.bottomBorder
import com.chtan.miniworld.presentation.components.shapes.RoundedParallelogram
import miniworld.composeapp.generated.resources.Res
import miniworld.composeapp.generated.resources.car1
import miniworld.composeapp.generated.resources.car2
import miniworld.composeapp.generated.resources.car3
import miniworld.composeapp.generated.resources.car4
import miniworld.composeapp.generated.resources.car5
import miniworld.composeapp.generated.resources.testCountryMap
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AdminClanOverviewScreen(
    nav: NavHostController,
    event: (AdminClanOverviewEvent) -> Unit,
    state: AdminClanOverviewState
) {



    // dialog
    val dialogPasswordState = rememberTextFieldState()
    val dialogNameState = rememberTextFieldState()
    val showDialog = remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(state.error) {
        state.error?.let { error ->
            val message = when(error) {
                is DataError.Remote.BackendError -> error.message
                else -> error.toString()
            }
            // We clear the error in the ViewModel so it doesn't trigger again,
            // but we use the captured 'message' for the snackbar.

            snackbarHostState.showSnackbar(message)
            event(AdminClanOverviewEvent.DismissError)
        }
    }




    if (showDialog.value){
        Dialog(
            onDismissRequest = {

            }, content = {
                Card {
                    Column(modifier = Modifier.padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Device Type")
                            DropdownMenuWithDetails(
                                modifier = Modifier.weight(1f),
                                dropdownItems = listOf(
                                    Pair(Icons.Filled.Sensors, "ESP32"),
                                    Pair(Icons.Filled.Camera, "ESP32Cam")),
                                onSelected = {

                                }
                            )
                        }
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Vehicle Type")
                            DropdownMenuWithDetails(
                                modifier = Modifier.weight(1f),
                                dropdownItems = listOf(
                                    Pair(Icons.Filled.Sensors, "Crane"),
                                    Pair(Icons.Filled.Camera, "Excavator")),
                                onSelected = {

                                }
                            )
                        }

                        OutlinedTextField(
                            modifier = Modifier.defaultMinSize(minHeight = 1.dp),
                            state = dialogNameState,
                            label = { Text("Name") },
                            textStyle = TextStyle.Default.copy()
                        )
                        OutlinedTextField(
                            modifier = Modifier.defaultMinSize(minHeight = 1.dp),
                            state = dialogPasswordState,
                            label = { Text("Password") },
                            textStyle = TextStyle.Default.copy()
                        )


                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                            Button(
                                colors = ButtonDefaults.buttonColors(Color.Red),
                                modifier = Modifier.weight(1f),
                                shape = RoundedParallelogram(10.dp),
                                onClick = {
                                    showDialog.value = false

                                },
                                content = {
                                    Text("Cancel")

                                })


                            Button(
                                modifier = Modifier.weight(1f),
                                shape = RoundedParallelogram(10.dp),
                                onClick = {

                                },
                                content = {
                                    Text("Add")

                                })
                        }




                    }
                }
            })
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
            AdminClanTopAppBar("Clan Overview",nav)
        }) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 5.dp)) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(.7f),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Row(modifier = Modifier.weight(1f)) {
                        Image(
                            modifier = Modifier.fillMaxHeight().aspectRatio(1f),
                            painter = painterResource(Res.drawable.testCountryMap),
                            contentDescription = "Clan Image",
                            contentScale = ContentScale.FillWidth
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(5.dp),
                            verticalArrangement = Arrangement.SpaceAround

                        ) {
                            Text(
                                maxLines = 4, text = buildAnnotatedString {
                                    withStyle(style = ParagraphStyle(lineHeight = 12.sp)) {
                                        withStyle(
                                            style = SpanStyle(
                                                fontSize = 14.sp, color = Color.Green
                                            )
                                        ) {
                                            append(if (state.clanList.isNotEmpty()) state.clanList.first().clanDetails.name else "Did not get")

                                        }
                                        append("\uD83D\uDFE2\n")
                                        withStyle(
                                            style = SpanStyle(
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Thin,
                                                color = Color.LightGray
                                            )
                                        ) {
                                            append("Created on May 15, 2026\nUnited by Des shadow, we rise as one Strategy, skill, and strength lead us to victory")
                                        }
                                    }
                                })

                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                items(
                                    listOf(
                                        Pair("Members", "10"),
                                        Pair("Region", "Brighton"),
                                        Pair("Clan Type", "Open")
                                    )
                                ) { item ->
                                    Card(
                                        shape = RoundedParallelogram(5.dp)
                                    ) {
                                        Text(
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.padding(5.dp),
                                            maxLines = 2, text = buildAnnotatedString {
                                                withStyle(style = ParagraphStyle(lineHeight = 10.sp)) {
                                                    withStyle(
                                                        style = SpanStyle(
                                                            fontSize = 10.sp, color = Color.Green
                                                        )
                                                    ) {
                                                        append(item.first + "\n")

                                                    }
                                                    withStyle(
                                                        style = SpanStyle(
                                                            fontSize = 10.sp,
                                                            fontWeight = FontWeight.Thin,
                                                            color = Color.LightGray
                                                        )
                                                    ) {
                                                        append(item.second)

                                                    }
                                                }
                                            })
                                    }
                                }


                            }

                        }
                    }
                    Row(modifier = Modifier.weight(1f)) {
                        Card(modifier = Modifier.weight(1f)) {
                            Column(
                                modifier = Modifier.fillMaxSize().padding(5.dp),
                                verticalArrangement = Arrangement.SpaceAround
                            ) {
                                listOf(
                                    "CLAN TAG" to "DES",
                                    "CLAN TYPE" to "Open",
                                    "REGION" to "Brighton",
                                    "Player" to "10"
                                ).forEach { item ->
                                    ClanDetailItem(item)
                                }
                            }
                        }
                        Card(modifier = Modifier.weight(1f)) {

                        }


                    }


                }
                Card(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(5.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        )
                        {
                            Text(
                                maxLines = 1, text = buildAnnotatedString {
                                    withStyle(style = ParagraphStyle(lineHeight = 12.sp)) {
                                        withStyle(
                                            style = SpanStyle(
                                                fontSize = 14.sp, color = Color.Green
                                            )
                                        ) {
                                            append("\uD83C\uDFCE")
                                            append(" MEMBERS")

                                        }
                                        withStyle(
                                            style = SpanStyle(
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Thin,
                                                color = Color.LightGray
                                            )
                                        ) {
                                            append("(10)")
                                        }
                                    }
                                })
                            Button(
                                modifier = Modifier
                                    .defaultMinSize(
                                        minHeight = 1.dp
                                    ),
                                shape = RoundedParallelogram(5.dp),
                                onClick = {
                                    showDialog.value = true
                                },
                                contentPadding = PaddingValues(0.dp)

                            ) {
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Add"
                                )
                                Text("Add")

                            }
                        }
                        HorizontalDivider()

                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            items(
                                listOf(
                                    VehicleDetails(
                                        id = "1",
                                        img = Res.drawable.car1,
                                        isOnline = true,
                                        isInMatch = true,
                                        name = "2x Off-Road"
                                    ),
                                    VehicleDetails(
                                        id = "2",
                                        img = Res.drawable.car2,
                                        isOnline = true,
                                        isInMatch = true,
                                        name = "2x Excavator"
                                    ), VehicleDetails(
                                        id = "3",
                                        img = Res.drawable.car3,
                                        isOnline = false,
                                        isInMatch = false,
                                        name = "2x Road Roller"
                                    ), VehicleDetails(
                                        id = "4",
                                        img = Res.drawable.car4,
                                        isOnline = true,
                                        isInMatch = false,
                                        name = "2x Crane"
                                    ), VehicleDetails(
                                        id = "5",
                                        img = Res.drawable.car5,
                                        isOnline = true,
                                        isInMatch = true,
                                        name = "2x Off-Road"
                                    )
                                )
                            ) { item ->
                                VehicleDetailItem(item)

                            }
                        }


                    }
                }

            }
        }
    }

}

@Composable
private fun DropdownMenuWithDetails(
    modifier: Modifier = Modifier,
    onSelected: (String) -> Unit,
    dropdownItems: List<Pair<ImageVector, String>>
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf(dropdownItems.first().second) }
    var buttonWidth by remember { mutableIntStateOf(0) }

    Box(modifier = modifier.fillMaxWidth()) {

        Button(
            modifier = Modifier.fillMaxWidth().onSizeChanged {
                buttonWidth = it.width
            },
            shape = RoundedParallelogram(10.dp),
            onClick = { expanded = !expanded }
        ) {
            Text(selectedType)

            Icon(
                imageVector = if (expanded)
                    Icons.Default.ArrowDropUp
                else
                    Icons.Default.ArrowDropDown,
                contentDescription = null
            )
        }

        DropdownMenu(
            modifier = Modifier.width(
                with(LocalDensity.current) { buttonWidth.toDp() }
            ),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            dropdownItems.forEach {
                DropdownMenuItem(
                    text = { Text(it.second) },
                    leadingIcon = {
                        Icon(
                            imageVector = it.first,
                            contentDescription = null
                        )
                    },
                    onClick = {
                        selectedType = it.second
                        onSelected(it.second)
                        expanded = false
                    }
                )
                HorizontalDivider()
            }

        }
    }
}
@Composable
private fun ClanDetailItem(item: Pair<String, String>) {
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

private data class VehicleDetails(
    val id: String,
    val img: DrawableResource,
    val isOnline: Boolean,
    val isInMatch: Boolean,
    val name: String
)

@Composable
private fun VehicleDetailItem(item: VehicleDetails) {
    Row(
        modifier = Modifier.fillMaxWidth().bottomBorder(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.padding(bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(32.dp).clip(CircleShape),
                painter = painterResource(item.img),
                contentDescription = item.name,
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(start = 2.dp),
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace,
                text = item.name
            )
        }

        Text(
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace,
            text = if (item.isInMatch) "In Match" else if (item.isOnline) "Online" else "Offline",
            color = if (item.isInMatch) Color.Yellow else if (item.isOnline) Color.Green else Color.LightGray
        )
    }
}
