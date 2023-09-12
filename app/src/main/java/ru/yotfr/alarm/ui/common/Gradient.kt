package ru.yotfr.alarm.ui.common

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.TileMode
import java.lang.Math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

fun Modifier.angledGradient(colors: List<Color>, angle: Float) = this.then(
    Modifier.drawBehind {
        val angleRad = angle / 180f * PI
        val x = cos(angleRad).toFloat() //Fractional x
        val y = sin(angleRad).toFloat() //Fractional y

        val radius = sqrt(size.width.pow(2) + size.height.pow(2)) / 2f
        val offset = center + Offset(x * radius, y * radius)

        val exactOffset = Offset(
            x = min(offset.x.coerceAtLeast(0f), size.width),
            y = size.height - min(offset.y.coerceAtLeast(0f), size.height)
        )

        drawRect(
            brush = Brush.linearGradient(
                colors = colors,
                start = Offset(size.width, size.height) - exactOffset,
                end = exactOffset
            ),
            size = size
        )
    }
)

//fun Modifier.sphereGradient(colors: List<Color>, angle: Float) = this.then(
//    Modifier.drawBehind {
//        val angleRad = angle / 180f * PI
//        val x = cos(angleRad).toFloat() //Fractional x
//        val y = sin(angleRad).toFloat() //Fractional y
//
//        val radius = sqrt(size.width.pow(2) + size.height.pow(2)) / 2f
//        val offset = center + Offset(x * radius, y * radius)
//
//        val exactOffset = Offset(
//            x = min(offset.x.coerceAtLeast(0f), size.width),
//            y = size.height - min(offset.y.coerceAtLeast(0f), size.height)
//        )
//        drawCircle(
//            brush = Brush.linearGradient(
//                colors,
//                startX = size.width / 2 - 64, startY = size.height / 2 - 64,
//                endX = size.width / 2 + 64, endY = size.height / 2 + 64,
//                tileMode = TileMode.Clamp
//            )
//            gradient, 64f,
//        )
//
//        drawRect(
//            brush = Brush.linearGradient(
//                colors = colors,
//                start = Offset(size.width, size.height) - exactOffset,
//                end = exactOffset
//            ),
//            size = size
//        )
//    }
//)