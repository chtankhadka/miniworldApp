package com.chtan.miniworld.presentation.user.dashboard.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomeLeftUI() {

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer,shape = CutCornerShape(topEnd = 20.dp))
            .shadow(elevation = 2.dp, clip = true, shape = CutCornerShape(topEnd = 20.dp))
        ,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Card(
            modifier = Modifier.size(50.dp).padding(2.dp),
            shape = CutCornerShape(5.dp),
            colors = CardDefaults.cardColors(Color.Blue)
        ) {
            Text(
                text = "27",
                fontSize = 24.sp,
                modifier = Modifier.fillMaxWidth()
                    .defaultMinSize(minWidth = 0.dp, minHeight = 0.dp)
                    .padding(top = 2.dp, start = 5.dp, end = 5.dp),
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.fillMaxSize().offset(y = (-7).dp),
                text = "LVL",
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )
        }

        Column(
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(style = ParagraphStyle(lineHeight = 12.sp)){

                        withStyle(style = SpanStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)) {
                            append("RC GIVER \uD83C\uDF0D \n")
                        }
                        withStyle(style = SpanStyle(fontSize = 10.sp, color = Color.LightGray)) {
                            append("RACING SINCE 2024")

                        }
                    }


                }
            )

        }
    }


}

