package com.abhiram.thorak.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.abhiram.thorak.R

// Set of Material typography styles to start with
val Poppins = FontFamily(
    Font(R.font.poppins, FontWeight.Normal),
    Font(R.font.poppinssemibold,FontWeight.SemiBold),
    Font(R.font.poppinsmed,FontWeight.Medium)
)
val JosefinSans = FontFamily(
    Font(R.font.josefinsans,FontWeight.Medium),
    Font(R.font.josefinsb,FontWeight.SemiBold),
    Font(R.font.josefinsmedi,FontWeight.Normal)
)
val Jura = FontFamily(
    Font(R.font.jura,FontWeight.Normal)
)
val Lato = FontFamily(
    Font(R.font.lato,FontWeight.Normal)
)
val Papyrus = FontFamily(
    Font(R.font.papyrus,FontWeight.Normal)
)
val Phudu = FontFamily(
    Font(R.font.phudu,FontWeight.Normal)
)
val SquadaOne = FontFamily(
    Font(R.font.squadaone,FontWeight.Normal)
)

val Typography = Typography(
    headlineLarge = TextStyle(
        fontSize = 34.sp
    ),
    headlineMedium = TextStyle(
        fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
        fontSize = 20.sp
    ),
    bodyLarge = TextStyle(
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontSize = 0.sp
    )
)