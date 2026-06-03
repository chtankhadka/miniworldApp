package com.chtan.miniworld.presentation.user.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chtan.miniworld.presentation.components.shapes.RoundedParallelogram
import com.chtan.miniworld.presentation.user.home.HomeScreen
import com.chtan.miniworld.presentation.user.map.UserMapScreen
import com.chtan.miniworld.presentation.user.map.UserMapScreenPointsGenerator
import miniworld.composeapp.generated.resources.Res
import miniworld.composeapp.generated.resources.raceFlag
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDashboardScreen(
    nav: NavHostController,
    event: (UserDashboardEvent) -> Unit,
    state: UserDashboardState,
) {

    val bottomNavController = rememberNavController()
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()


    Scaffold(
        topBar = {
        MiniWorldTopAppBar()
    }, bottomBar = {
        MiniWorldBottomAppBar()
    },
        floatingActionButton = {

                Column(modifier = Modifier.fillMaxWidth(.2f).fillMaxHeight().offset((-16).dp).padding(top =125.dp )) {
                    listOf(
                        UserDashboardNavBarItem.Home,
                        UserDashboardNavBarItem.Maps,
                        UserDashboardNavBarItem.Events,
                        UserDashboardNavBarItem.Leaderboard,
                        UserDashboardNavBarItem.Settings,
                        UserDashboardNavBarItem.EditMap,

                    ).forEach { item ->

                        val isSelected =
                            navBackStackEntry?.destination?.hierarchy?.any { it.hasRoute(item.route::class) } == true

                        Box(
                            modifier = Modifier.height(30.dp).background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color.Black,
                                        Color.White,
                                    )
                                )
                            )
                        ) {
                            if (isSelected) {
                                Image(
                                    modifier = Modifier.fillMaxHeight().align(Alignment.CenterEnd),
                                    painter = painterResource(Res.drawable.raceFlag),
                                    contentDescription = "Race Flag"
                                )
                            }

                            NavigationDrawerItem(
                                modifier = Modifier.background(Color.Transparent),
                                shape = RoundedParallelogram(2.dp),
                                icon = {
                                    Icon(
                                        modifier = Modifier.size(24.dp).defaultMinSize(0.dp),
                                        imageVector = item.icon,
                                        contentDescription = item.title
                                    )
                                },
                                label = {
                                    Text(
                                        text = item.title,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                },
                                selected = isSelected,
                                colors = NavigationDrawerItemDefaults.colors(
                                    selectedIconColor = Color.White,
                                    selectedTextColor = Color.White,
                                    selectedContainerColor = Color.Transparent,
                                    unselectedIconColor = Color.White,
                                    unselectedTextColor = Color.White,
                                ),
                                onClick = {
                                    bottomNavController.navigate(item.route)
                                },
                            )
                            HorizontalDivider()
                        }
                    }
                }
        },
        floatingActionButtonPosition = FabPosition.Start,
        content = { innerPadding ->

        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {


            NavHost(
                modifier = Modifier,
                navController = bottomNavController, startDestination = UserDashboardRoute.Home
            ) {
                composable<UserDashboardRoute.Home> {
                    HomeScreen()
                }
                composable<UserDashboardRoute.Map> {
                    UserMapScreen(state,nav)
                }
                composable<UserDashboardRoute.Events> {}
                composable<UserDashboardRoute.Leaderboard> {}
                composable<UserDashboardRoute.Setting> {}
                composable<UserDashboardRoute.EditMap> {
                    UserMapScreenPointsGenerator()
                }
            }
        }
    })
}
