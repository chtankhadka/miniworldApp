package com.chtan.miniworld.presentation.user.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.CurrencyPound
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import miniworld.composeapp.generated.resources.Res
import miniworld.composeapp.generated.resources.car1
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniWorldTopAppBar() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)
            .background(color = MaterialTheme.colorScheme.outline),
        expandedHeight = 0.dp,
        title = {
            Text("Miniworld")
        }, actions = {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    TitleParallelogramBox(
                        number = "100",
                        currencyIcon = Icons.Default.CurrencyBitcoin,
                        type = "Credit",
                        onClickAdd = {

                        })
                    TitleParallelogramBox(
                        number = "1000",
                        currencyIcon = Icons.Default.CurrencyPound,
                        type = "Coins",
                        onClickAdd = {

                        })
                }
                Image(
                    modifier = Modifier.size(32.dp).border(
                        width = 1.dp,
                        color = Color.Blue,
                        shape = CircleShape
                    ).clip(CircleShape),
                    painter = painterResource(Res.drawable.car1),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )

            }


        })
}

