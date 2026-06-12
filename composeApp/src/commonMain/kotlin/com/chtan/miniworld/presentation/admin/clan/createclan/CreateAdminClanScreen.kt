package com.chtan.miniworld.presentation.admin.clan.createclan

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.AreaChart
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.chtan.miniworld.Route
import com.chtan.miniworld.data.datasource.network.result.DataError
import com.chtan.miniworld.presentation.components.shapes.RoundedParallelogram


@Composable
fun CreateAdminClanScreen(
    nav: NavHostController,
    event: (CreateAdminClanEvent) -> Unit,
    state: CreateAdminClanState
) {

    //navigation
    val bottomNavController = rememberNavController()
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()


    LaunchedEffect(state.isClanCreated){
        if (state.isClanCreated){
            nav.navigate(Route.AdminClanOverview)
        }
    }
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
            event(CreateAdminClanEvent.DismissError)
        }
    }



    // text
    val clanNameState = rememberTextFieldState()
    val clanTagState = rememberTextFieldState()
    var clanDetails by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(), 
        topBar = {
            AdminClanTopAppBar("Create Clan",nav)
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
        ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 5.dp)) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(.8f),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Card(modifier = Modifier.weight(1f)) {
                        Column(modifier = Modifier.padding(5.dp)) {
                            Row(
                                modifier = Modifier,
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Icon(imageVector = Icons.Filled.AreaChart, contentDescription = "Profile")
                                Text(
                                    maxLines = 2, text = buildAnnotatedString {
                                        withStyle(style = ParagraphStyle(lineHeight = 12.sp)) {
                                            withStyle(
                                                style = SpanStyle(
                                                    fontSize = 14.sp, color = Color.Green
                                                )
                                            ) {
                                                append("Set your clan identity\n")

                                            }
                                            withStyle(
                                                style = SpanStyle(
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.LightGray
                                                )
                                            ) {
                                                append("BASIC INFO")
                                            }
                                        }
                                    })
                            }
                            HorizontalDivider(modifier = Modifier.height(1.dp))

                            Row(modifier = Modifier.fillMaxSize()) {
                                Column {
                                    OutlinedTextField(
                                        state = clanNameState,
                                        label = { Text("Clan Name") },
                                        textStyle = TextStyle.Default.copy(

                                        )
                                    )

                                    OutlinedTextField(
                                        state = clanTagState,
                                        label = { Text("Clan Tag") },
                                        textStyle = TextStyle.Default.copy(

                                        )

                                    )
                                }


                                    Row(
                                        modifier = Modifier.fillMaxSize().padding(10.dp).border( width = 1.dp, color = Color.White, shape = RoundedCornerShape(5.dp)),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(48.dp),
                                            imageVector = Icons.Default.Security,
                                            contentDescription = "Security"
                                        )
                                        Text(
                                            maxLines = 2, text = buildAnnotatedString {
                                                withStyle(style = ParagraphStyle(lineHeight = 12.sp)) {
                                                    withStyle(
                                                        style = SpanStyle(
                                                            fontSize = 12.sp, color = Color.Green
                                                        )
                                                    ) {
                                                        append("Upload emblem\n")

                                                    }
                                                    withStyle(
                                                        style = SpanStyle(
                                                            fontSize = 10.sp,
                                                            fontWeight = FontWeight.Bold,
                                                            color = Color.LightGray
                                                        )
                                                    ) {
                                                        append("Recommended size: 512x512 \n PNG, JPG up to 1MB")
                                                    }
                                                }
                                            })
                                    }

                            }


                        }
                    }
                    Card(modifier = Modifier.weight(1f)) {
                        Column(modifier = Modifier.padding(5.dp)) {
                            Row(
                                modifier = Modifier,
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Details,
                                    contentDescription = "Details"
                                )
                                Text(
                                    maxLines = 2, text = buildAnnotatedString {
                                        withStyle(style = ParagraphStyle(lineHeight = 12.sp)) {
                                            withStyle(
                                                style = SpanStyle(
                                                    fontSize = 14.sp, color = Color.Green
                                                )
                                            ) {
                                                append("Details\n")

                                            }
                                            withStyle(
                                                style = SpanStyle(
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.LightGray
                                                )
                                            ) {
                                                append("Tell others about your clan")
                                            }
                                        }
                                    })
                            }
                            HorizontalDivider(modifier = Modifier.height(1.dp))
                            Row {
                                TextField(
                                    modifier = Modifier.border(
                                    1.dp, color = Color.White, shape = RoundedCornerShape(5.dp)
                                ),
                                    colors = TextFieldDefaults.colors(
                                        focusedIndicatorColor = Color.Transparent
                                    ),
                                    label = {
                                        Text(maxLines = 2, text = buildAnnotatedString {
                                            withStyle(style = ParagraphStyle(lineHeight = 12.sp)) {
                                                withStyle(style = SpanStyle(fontSize = 12.sp)) {
                                                    append("Clan Description...")

                                                }
                                                withStyle(
                                                    style = SpanStyle(
                                                        fontSize = 10.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color.LightGray
                                                    )
                                                ) {
                                                    append("(${clanDetails.length}/200)")
                                                }
                                            }
                                        })
                                    },
                                    value = clanDetails,
                                    onValueChange = {
                                        if (it.length <= 200) {
                                            clanDetails = it
                                        }
                                    },
                                    maxLines = 3,
                                    minLines = 3,
                                    textStyle = LocalTextStyle.current.copy(
                                        lineHeight = 12.sp,
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.Thin
                                    )

                                )
                            }
                        }
                    }
                }

                Card(modifier = Modifier.fillMaxSize()) {
                    Row(modifier = Modifier.padding(5.dp)) {
                        Icon(imageVector = Icons.Filled.Map, contentDescription = "Map")
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 2,
                            text = buildAnnotatedString {
                                withStyle(style = ParagraphStyle(lineHeight = 12.sp)) {
                                    withStyle(
                                        style = SpanStyle(
                                            fontSize = 14.sp, color = Color.Green
                                        )
                                    ) {
                                        append("Map\n")

                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.LightGray
                                        )
                                    ) {
                                        append("Add your map images")
                                    }
                                }
                            })
                    }
                    HorizontalDivider()
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                            onClick = {

                            }, content = {
                                Icon(

                                    imageVector = Icons.Filled.AddAPhoto,
                                    contentDescription = "Map"
                                )
                            })
                    }
                    HorizontalDivider()

                    Button(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 5.dp),
                        shape = RoundedParallelogram(5.dp),
                        onClick = {
                            event(CreateAdminClanEvent.CreateClan(clanNameState.text.toString(),clanTagState.text.toString(),clanDetails))
                        },
                        content = {
                            Text("Create")
                        }
                    )



                }


            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AdminClanTopAppBar(title: String, nav: NavHostController) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)
            .background(color = MaterialTheme.colorScheme.outline),
        expandedHeight = 0.dp,
        title = {
            Text(text = title, fontWeight = FontWeight.Normal, fontSize = 16.sp)
        },
        navigationIcon = {
            Button(modifier = Modifier, shape = RoundedParallelogram(5.dp), onClick = {
                nav.popBackStack()
            }, content = {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                    contentDescription = "Back"
                )
                Text("Back")
            })
        },
    )
}
