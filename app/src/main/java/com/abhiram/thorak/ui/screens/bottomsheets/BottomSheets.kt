package com.abhiram.thorak.ui.screens.bottomsheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhiram.thorak.ui.screens.tour.getFont
import com.abhiram.thorak.ui.theme.BottomSheetsShapes
import com.abhiram.thorak.ui.theme.JosefinSans
import com.abhiram.thorak.ui.theme.Jura
import com.abhiram.thorak.ui.theme.Lato
import com.abhiram.thorak.ui.theme.NubBlu
import com.abhiram.thorak.ui.theme.NubYel
import com.abhiram.thorak.ui.theme.Phudu
import com.abhiram.thorak.ui.theme.Poppins
import com.abhiram.thorak.ui.theme.SquadaOne
import com.abhiram.thorak.ui.theme.ThorakShapes
import com.abhiram.thorak.ui.theme.Typography


@Composable
@ExperimentalMaterial3Api
fun FontsBottomSheet(){
    Box(
        modifier = Modifier
            .fillMaxWidth(1F)
            .clip(BottomSheetsShapes.medium)
            .background(NubBlu)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(1F)
                .padding(horizontal = 24.dp)
        ) {
            Text(text = "Choose",
                color = NubYel,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                style = Typography.headlineMedium)

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(1F)
                    .height(42.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NubYel, contentColor = NubBlu),
                shape = ThorakShapes.large
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ){
                    Text(text = "Poppins",
                        color = NubBlu,
                        style = Typography.bodyLarge,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(1F)
                    .height(42.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NubYel, contentColor = NubBlu),
                shape = ThorakShapes.large
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Josefin Sans",
                        color = NubBlu,
                        style = Typography.bodyLarge,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontFamily = JosefinSans,
                        modifier = Modifier
                            .fillMaxWidth(1F)
                            .clip(ThorakShapes.large)
                            .background(NubYel)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(1F)
                    .height(42.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NubYel, contentColor = NubBlu),
                shape = ThorakShapes.large
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Jura",
                        color = NubBlu,
                        style = Typography.bodyLarge,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontFamily = Jura
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(1F)
                    .height(42.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NubYel, contentColor = NubBlu),
                shape = ThorakShapes.large
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Lato",
                        color = NubBlu,
                        style = Typography.bodyLarge,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontFamily = Lato
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(1F)
                    .height(42.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NubYel, contentColor = NubBlu),
                shape = ThorakShapes.large
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Squada One",
                        color = NubBlu,
                        style = Typography.bodyLarge,
                        fontWeight = FontWeight.Normal,
                        fontFamily = SquadaOne,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(1F),
                horizontalArrangement = Arrangement.Center
            ) {

                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = NubBlu, contentColor = NubYel),
                    shape = ThorakShapes.large
                ) {
                    Text(
                        text = "Cancel",
                        color = NubYel,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        style = Typography.bodyLarge
                    )
                }

                Spacer(modifier = Modifier.fillMaxWidth(0.3F))

                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = NubBlu, contentColor = NubYel),
                    shape = ThorakShapes.large
                ) {
                    Text(
                        text = "Apply",
                        color = NubYel,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        style = Typography.bodyLarge
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SheetsPreview(){
    FontsBottomSheet()
}