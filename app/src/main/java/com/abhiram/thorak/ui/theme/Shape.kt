
package com.abhiram.thorak.ui.theme

import android.media.RouteDiscoveryPreference
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val ThorakShapes = Shapes(
    small = RoundedCornerShape(percent = 50),
    medium = RoundedCornerShape(size = 8.dp),
    large = RoundedCornerShape(size = 10.dp)
)
val IconShapes = Shapes(
    extraSmall = RoundedCornerShape(percent = 0),
    small = RoundedCornerShape(percent = 25),
    medium = RoundedCornerShape(percent = 40),
    large = RoundedCornerShape(percent = 75)
)

val BottomSheetsShapes = Shapes(
    medium = RoundedCornerShape(topStart = 24.dp, topEnd = 224.dp, bottomEnd = 0.dp, bottomStart = 0.dp)
)
