package com.chtan.miniworld.presentation.user.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRightUI() {
    //Events
    HomeRCEventPager(
        listOf(
            HomeRCEventPagerDetails(
                "RC CHAMPIONSHIP", "", "You are the best"

            ), HomeRCEventPagerDetails(
                "RC CHAMPIONSHIP", "", "You are the worst"
            )
        ), Modifier.fillMaxWidth().shadow(elevation = 5.dp)
    )
    HomeRCTopRankedMaps(
        rankedMaps = listOf(
            MapInfo(
                mapName = "Erangel",
                ranked = 1,
                vehicles = 24,
                available = 1,
                mapImg = "https://picsum.photos/seed/erangel/300/200"
            ), MapInfo(
                mapName = "Miramar",
                ranked = 2,
                vehicles = 18,
                available = 1,
                mapImg = "https://picsum.photos/seed/miramar/300/200"
            ), MapInfo(
                mapName = "Sanhok",
                ranked = 3,
                vehicles = 15,
                available = 1,
                mapImg = "https://picsum.photos/seed/sanhok/300/200"
            ), MapInfo(
                mapName = "Vikendi",
                ranked = 4,
                vehicles = 20,
                available = 0,
                mapImg = "https://picsum.photos/seed/vikendi/300/200"
            ), MapInfo(
                mapName = "Deston",
                ranked = 5,
                vehicles = 22,
                available = 1,
                mapImg = "https://picsum.photos/seed/deston/300/200"
            ), MapInfo(
                mapName = "Karakin",
                ranked = 6,
                vehicles = 10,
                available = 1,
                mapImg = "https://picsum.photos/seed/karakin/300/200"
            ), MapInfo(
                mapName = "Taego",
                ranked = 7,
                vehicles = 19,
                available = 0,
                mapImg = "https://picsum.photos/seed/taego/300/200"
            ), MapInfo(
                mapName = "Paramo",
                ranked = 8,
                vehicles = 8,
                available = 1,
                mapImg = "https://picsum.photos/seed/paramo/300/200"
            ), MapInfo(
                mapName = "Haven",
                ranked = 9,
                vehicles = 6,
                available = 0,
                mapImg = "https://picsum.photos/seed/haven/300/200"
            ), MapInfo(
                mapName = "Nusa",
                ranked = 10,
                vehicles = 12,
                available = 1,
                mapImg = "https://picsum.photos/seed/nusa/300/200"
            )
        )

    )
}

