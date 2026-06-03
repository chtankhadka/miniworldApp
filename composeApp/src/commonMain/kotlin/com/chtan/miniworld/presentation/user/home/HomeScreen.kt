package com.chtan.miniworld.presentation.user.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .fillMaxWidth(0.3f)
                .padding(horizontal = 2.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            HomeLeftUI()
        }
        Column(
            modifier = Modifier.align(Alignment.TopEnd)
                .fillMaxWidth(0.3f)
                .padding(horizontal = 2.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            HomeRightUI()
        }
    }



}



