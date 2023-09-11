package ru.yotfr.alarm.ui.common

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class Shape {
    Circle,
    Rectangle,
    RoundedRect
}

fun Modifier.pressedShadow(
    shape: Shape = Shape.RoundedRect,
    darkColor: Color,
    lightColor: Color,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
    cornerRadius: Dp = 0.dp,
    backgroundColor: Color
) = then(
    drawBehind {
        drawIntoCanvas { canvas ->
            // Blur and shadow color
            val darkPaint = Paint()
            val darkFrameworkPaint = darkPaint.asFrameworkPaint()
            if (blurRadius != 0.dp) {
                darkFrameworkPaint.maskFilter = (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }
            darkFrameworkPaint.color = darkColor.toArgb()

            val lightPaint = Paint()
            val lightFrameworkPaint = lightPaint.asFrameworkPaint()
            if (blurRadius != 0.dp) {
                lightFrameworkPaint.maskFilter = (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }
            lightFrameworkPaint.color = lightColor.toArgb()

            val backgroundPaint = Paint()
            val backgroundFrameworkPaint = backgroundPaint.asFrameworkPaint()
            backgroundFrameworkPaint.color = backgroundColor.toArgb()

            when(shape) {
                Shape.Circle -> {
                    val centerX = size.width / 2
                    val centerY = size.height / 2

                    val darkCenterOffset = Offset(centerX - offsetX.toPx(), centerY - offsetY.toPx())
                    val lightCenterOffset = Offset(centerX + offsetX.toPx(), centerY + offsetY.toPx())

                    canvas.drawCircle(
                        center = darkCenterOffset,
                        radius = size.width / 2,
                        paint = darkPaint
                    )
                    canvas.drawCircle(
                        center = lightCenterOffset,
                        radius = size.width / 2,
                        paint = lightPaint
                    )
                }
                Shape.Rectangle -> {

                    canvas.drawRect(
                        left = 0f,
                        top = 0f,
                        right = size.width,
                        bottom = size.height,
                        paint = backgroundPaint
                    )

                    val darkLeftPixel = 0f
                    val darkTopPixel = 0f
                    val darkRightPixel = size.width - offsetX.toPx()
                    val darkBottomPixel = size.height - offsetY.toPx()

                    canvas.drawRect(
                        left = darkLeftPixel,
                        top = darkTopPixel,
                        right = darkRightPixel,
                        bottom = darkBottomPixel,
                        paint = darkPaint
                    )

                    val lightLeftPixel = offsetX.toPx()
                    val lightTopPixel = offsetY.toPx()
                    val lightRightPixel = size.width
                    val lightBottomPixel = size.height

                    canvas.drawRect(
                        left = lightLeftPixel,
                        top = lightTopPixel,
                        right = lightRightPixel,
                        bottom = lightBottomPixel,
                        paint = lightPaint
                    )

                    val backgroundLeftPixel = offsetX.toPx()
                    val backgroundTopPixel = offsetY.toPx()
                    val backgroundRightPixel = size.width - offsetX.toPx()
                    val backgroundBottomPixel = size.height - offsetY.toPx()

                    canvas.drawRect(
                        left = backgroundLeftPixel,
                        top = backgroundTopPixel,
                        right = backgroundRightPixel,
                        bottom = backgroundBottomPixel,
                        paint = backgroundPaint
                    )
                }
                Shape.RoundedRect -> {

                    val radius = cornerRadius.toPx()

                    canvas.drawRoundRect(
                        left = 0f,
                        top = 0f,
                        right = size.width,
                        bottom = size.height,
                        paint = backgroundPaint,
                        radiusX = radius,
                        radiusY = radius
                    )

                    val darkLeftPixel = 0f
                    val darkTopPixel = 0f
                    val darkRightPixel = size.width - offsetX.toPx()
                    val darkBottomPixel = size.height - offsetY.toPx()

                    canvas.drawRoundRect(
                        left = darkLeftPixel,
                        top = darkTopPixel,
                        right = darkRightPixel,
                        bottom = darkBottomPixel,
                        paint = darkPaint,
                        radiusX = radius,
                        radiusY = radius
                    )

                    val lightLeftPixel = offsetX.toPx()
                    val lightTopPixel = offsetY.toPx()
                    val lightRightPixel = size.width
                    val lightBottomPixel = size.height

                    canvas.drawRoundRect(
                        left = lightLeftPixel,
                        top = lightTopPixel,
                        right = lightRightPixel,
                        bottom = lightBottomPixel,
                        paint = lightPaint,
                        radiusX = radius,
                        radiusY = radius
                    )

                    val backgroundLeftPixel = offsetX.toPx()
                    val backgroundTopPixel = offsetY.toPx()
                    val backgroundRightPixel = size.width - offsetX.toPx()
                    val backgroundBottomPixel = size.height - offsetY.toPx()

                    canvas.drawRoundRect(
                        left = backgroundLeftPixel,
                        top = backgroundTopPixel,
                        right = backgroundRightPixel,
                        bottom = backgroundBottomPixel,
                        paint = backgroundPaint,
                        radiusX = radius,
                        radiusY = radius
                    )
                }
            }
        }
    }
)

fun Modifier.punchedShadow(
    shape: Shape = Shape.RoundedRect,
    darkColor: Color,
    lightColor: Color,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
    cornerRadius: Dp = 0.dp
) = then(
    drawBehind {
        drawIntoCanvas { canvas ->
            // Blur and shadow color
            val darkPaint = Paint()
            val darkFrameworkPaint = darkPaint.asFrameworkPaint()
            if (blurRadius != 0.dp) {
                darkFrameworkPaint.maskFilter = (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }
            darkFrameworkPaint.color = darkColor.toArgb()

            val lightPaint = Paint()
            val lightFrameworkPaint = lightPaint.asFrameworkPaint()
            if (blurRadius != 0.dp) {
                lightFrameworkPaint.maskFilter = (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }
            lightFrameworkPaint.color = lightColor.toArgb()

            when(shape) {
                Shape.Circle -> {
                    val centerX = size.width / 2
                    val centerY = size.height / 2

                    val darkCenterOffset = Offset(centerX + offsetX.toPx(), centerY + offsetY.toPx())
                    val lightCenterOffset = Offset(centerX - offsetX.toPx(), centerY - offsetY.toPx())

                    canvas.drawCircle(
                        center = darkCenterOffset,
                        radius = size.width / 2,
                        paint = darkPaint
                    )
                    canvas.drawCircle(
                        center = lightCenterOffset,
                        radius = size.width / 2,
                        paint = lightPaint
                    )
                }
                Shape.Rectangle -> {
                    val darkLeftPixel = offsetX.toPx()
                    val darkTopPixel = offsetY.toPx()
                    val darkRightPixel = size.width + offsetX.toPx()
                    val darkBottomPixel = size.height + offsetY.toPx()

                    canvas.drawRect(
                        left = darkLeftPixel,
                        top = darkTopPixel,
                        right = darkRightPixel,
                        bottom = darkBottomPixel,
                        paint = darkPaint
                    )

                    val lightLeftPixel = -offsetX.toPx()
                    val lightTopPixel = -offsetY.toPx()
                    val lightRightPixel = size.width - offsetX.toPx()
                    val lightBottomPixel = size.height - offsetY.toPx()

                    canvas.drawRect(
                        left = lightLeftPixel,
                        top = lightTopPixel,
                        right = lightRightPixel,
                        bottom = lightBottomPixel,
                        paint = lightPaint
                    )
                }
                Shape.RoundedRect -> {

                    val radius = cornerRadius.toPx()

                    val darkLeftPixel = offsetX.toPx()
                    val darkTopPixel = offsetY.toPx()
                    val darkRightPixel = size.width + offsetX.toPx()
                    val darkBottomPixel = size.height + offsetY.toPx()

                    canvas.drawRoundRect(
                        left = darkLeftPixel,
                        top = darkTopPixel,
                        right = darkRightPixel,
                        bottom = darkBottomPixel,
                        paint = darkPaint,
                        radiusX = radius,
                        radiusY = radius
                    )

                    val lightLeftPixel = -offsetX.toPx()
                    val lightTopPixel = -offsetY.toPx()
                    val lightRightPixel = size.width - offsetX.toPx()
                    val lightBottomPixel = size.height - offsetY.toPx()

                    canvas.drawRoundRect(
                        left = lightLeftPixel,
                        top = lightTopPixel,
                        right = lightRightPixel,
                        bottom = lightBottomPixel,
                        paint = lightPaint,
                        radiusX = radius,
                        radiusY = radius
                    )
                }
            }
        }
    }
)

