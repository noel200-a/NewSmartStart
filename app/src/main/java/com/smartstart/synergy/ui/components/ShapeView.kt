package com.smartstart.synergy.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.smartstart.synergy.data.content.ShapeKind
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

/** Draws any of the Phase 1 [ShapeKind]s procedurally so no image assets are needed. */
@Composable
fun ShapeView(kind: ShapeKind, color: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val s = min(size.width, size.height)
        val cx = size.width / 2f
        val cy = size.height / 2f
        val r = s / 2f * 0.9f
        when (kind) {
            ShapeKind.CIRCLE -> drawCircle(color = color, radius = r, center = Offset(cx, cy))
            ShapeKind.OVAL -> drawOval(
                color = color,
                topLeft = Offset(cx - r, cy - r * 0.65f),
                size = androidx.compose.ui.geometry.Size(r * 2, r * 1.3f),
            )
            ShapeKind.SQUARE -> drawRect(
                color = color,
                topLeft = Offset(cx - r, cy - r),
                size = androidx.compose.ui.geometry.Size(r * 2, r * 2),
            )
            ShapeKind.RECTANGLE -> drawRect(
                color = color,
                topLeft = Offset(cx - r, cy - r * 0.6f),
                size = androidx.compose.ui.geometry.Size(r * 2, r * 1.2f),
            )
            ShapeKind.TRIANGLE -> drawPolygonPath(color, cx, cy, r, 3, startAngle = -90.0)
            ShapeKind.PENTAGON -> drawPolygonPath(color, cx, cy, r, 5, startAngle = -90.0)
            ShapeKind.HEXAGON -> drawPolygonPath(color, cx, cy, r, 6, startAngle = -90.0)
            ShapeKind.DIAMOND -> drawPolygonPath(color, cx, cy, r, 4, startAngle = -90.0)
            ShapeKind.STAR -> drawStar(color, cx, cy, r)
            ShapeKind.HEART -> drawHeart(color, cx, cy, r)
        }
    }
}

private fun DrawScope.drawPolygonPath(
    color: Color,
    cx: Float,
    cy: Float,
    r: Float,
    sides: Int,
    startAngle: Double,
) {
    val path = Path()
    for (i in 0 until sides) {
        val angle = Math.toRadians(startAngle + i * 360.0 / sides)
        val x = cx + r * cos(angle).toFloat()
        val y = cy + r * sin(angle).toFloat()
        if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
    }
    path.close()
    drawPath(path, color)
}

private fun DrawScope.drawStar(color: Color, cx: Float, cy: Float, r: Float) {
    val path = Path()
    val points = 5
    val inner = r * 0.45f
    for (i in 0 until points * 2) {
        val radius = if (i % 2 == 0) r else inner
        val angle = Math.toRadians(-90.0 + i * 180.0 / points)
        val x = cx + radius * cos(angle).toFloat()
        val y = cy + radius * sin(angle).toFloat()
        if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
    }
    path.close()
    drawPath(path, color)
}

private fun DrawScope.drawHeart(color: Color, cx: Float, cy: Float, r: Float) {
    val path = Path()
    val rect = Rect(cx - r, cy - r, cx + r, cy + r)
    val top = rect.top + rect.height * 0.3f
    path.moveTo(cx, rect.bottom)
    path.cubicTo(rect.left, cy, rect.left, top - rect.height * 0.1f, cx, top)
    path.cubicTo(rect.right, top - rect.height * 0.1f, rect.right, cy, cx, rect.bottom)
    path.close()
    drawPath(path, color)
}
