package com.chtan.miniworld.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import miniworld.composeapp.generated.resources.Aloevera_OVoWO
import miniworld.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font



// Set of Material typography styles to start with
@Composable
fun AppTypography() = Typography().run {

    val aloeveraFont = FontFamily(Font(Res.font.Aloevera_OVoWO))
    copy(
        bodySmall = TextStyle(
            fontFamily =  aloeveraFont,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
        ),
        bodyMedium = TextStyle(
            fontFamily = aloeveraFont,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = aloeveraFont,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = aloeveraFont,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,

        ),
        headlineLarge = TextStyle(
            fontFamily = aloeveraFont,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        ),

        headlineSmall = TextStyle(
            fontFamily = aloeveraFont,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
        ),
        titleLarge = TextStyle(
            fontFamily = aloeveraFont,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 26.sp,

            ),
        titleMedium = TextStyle(
            fontFamily = aloeveraFont,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
        ),
        titleSmall = TextStyle(
            fontFamily = aloeveraFont,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
        ),
        labelLarge = TextStyle(
            fontFamily = aloeveraFont,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        labelSmall = TextStyle(
            fontFamily = aloeveraFont,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        labelMedium = TextStyle(
            fontFamily = aloeveraFont,
            fontWeight = FontWeight.Normal,
        )
    )}