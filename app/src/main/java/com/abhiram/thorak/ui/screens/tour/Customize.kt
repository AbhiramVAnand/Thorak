package com.abhiram.thorak.ui.screens.tour


import CustomizationViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhiram.thorak.R
import com.abhiram.thorak.ui.theme.DarkDim
import com.abhiram.thorak.ui.theme.IconShapes
import com.abhiram.thorak.ui.theme.JosefinSans
import com.abhiram.thorak.ui.theme.Jura
import com.abhiram.thorak.ui.theme.Lato
import com.abhiram.thorak.ui.theme.NubBlu
import com.abhiram.thorak.ui.theme.NubLY
import com.abhiram.thorak.ui.theme.NubYel
import com.abhiram.thorak.ui.theme.Papyrus
import com.abhiram.thorak.ui.theme.Phudu
import com.abhiram.thorak.ui.theme.Poppins
import com.abhiram.thorak.ui.theme.SquadaOne
import com.abhiram.thorak.ui.theme.ThorakShapes
import com.abhiram.thorak.ui.theme.Typography
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun Customize() {
    Column {
        Header()
        IconsFeel()
    }

}


@Composable
fun IconsFeel() {
    val customViewModel = CustomizationViewModel(LocalContext.current)
    val iSliderPos : Float by customViewModel.iseek.observeAsState(customViewModel.iseektemp)
    val height : Float by customViewModel.iseek.observeAsState(customViewModel.iseektemp)
    Box(
        modifier = Modifier
            .fillMaxWidth(1F)
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth(1F)
                .clip(ThorakShapes.large)
                .background(NubBlu)
        ){
            Text(
                text = "Icons",
                color = NubYel,
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                style = Typography.headlineMedium,
                modifier = Modifier.padding(top = 8.dp, start = 14.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(1F)
                    .padding(horizontal = 14.dp)
                    .padding(top = 5.dp, bottom = 8.dp)
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(1F)
                        .clip(ThorakShapes.large)
                        .background(NubYel)
                ) {
                    Text(
                        text = "Preview",
                        color = NubBlu,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Medium,
                        style = Typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp, start = 14.dp))
                    AppPreview(height,customViewModel)
                }
            }
            Text(
                text = "Icon Size",
                color = NubYel,
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                style = Typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp, start = 28.dp))
            IconSliderView(sliderPosition = iSliderPos, onValChange = { customViewModel.onISlide(it)})
            Text(
                text = "Icon Size",
                color = NubYel,
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                style = Typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp, start = 28.dp))
            IconShapeSelect(CustomViewModel = customViewModel)
            Text(
                text = "Font",
                color = NubYel,
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                style = Typography.headlineMedium,
                modifier = Modifier.padding(top = 8.dp, start = 14.dp))
            Font(customViewModel)
        }
    }
}


@Composable
fun IconSliderView(sliderPosition: Float, onValChange: (Float) -> Unit){
    Slider(
        modifier = Modifier
            .semantics { contentDescription = "Localized Description" }
            .padding(start = 28.dp, end = 28.dp),
        value = sliderPosition,
        onValueChange = onValChange,
        valueRange = 34f..43f,
        colors = sliderColors(),
        steps = 4
    )
}

@Composable
fun sliderColors() : SliderColors = SliderDefaults.colors(
    thumbColor = NubYel,
    activeTickColor = NubLY,
    inactiveTickColor = NubYel,
    activeTrackColor = NubYel,
    inactiveTrackColor = NubLY
)

@Composable
fun AppPreview(height: Float, CustomViewModel : CustomizationViewModel) {
    val shape by CustomViewModel.iconShape.collectAsState()
    val font : String by CustomViewModel.font.collectAsState()
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth(1F)
            .padding(bottom = 10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription ="Google",
                modifier = Modifier
                    .size(height.dp)
                    .clip(shape)
            )
            Text(
                text = "Google",
                color = NubBlu,
                fontFamily = getFont(font)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.thorak),
                contentDescription ="Thorak",
                modifier = Modifier
                    .size(height.toInt().dp)
                    .clip(shape)
            )
            Text(
                text = "Thorak",
                color = NubBlu,
                fontFamily = getFont(font)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.messages),
                contentDescription ="Messages",
                modifier = Modifier
                    .size(height.toInt().dp)
                    .clip(shape)
            )
            Text(
                text = "Messages",
                color = NubBlu,
                fontFamily = getFont(font)
            )
        }
    }
    
}

@Composable
fun Header() {
    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.logo)
    )
    val progress by animateLottieCompositionAsState(
        // pass the composition created above
        composition,

        // Iterates Forever

        // pass isPlaying we created above,
        // changing isPlaying will recompose
        // Lottie and pause/play
        isPlaying = true,

        // pass speed we created above,
        // changing speed will increase Lottie
        speed = 1f,

        // this makes animation to restart when paused and play
        // pass false to continue the animation at which it was paused
        restartOnPlay = true

    )
    Column(
        modifier = Modifier.background(DarkDim)
    ) {
        Spacer(modifier = Modifier
            .height(50.dp)
            .fillMaxWidth())
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth(1F)
        ) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .weight(1F)
            ) {
                Text(
                    text = "Thorak",
                    color = NubYel,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    style = Typography.headlineSmall,
                    modifier = Modifier.padding(0.dp)
                )
                Text(
                    text = stringResource(R.string.version),
                    fontFamily = Poppins,
                    color = NubYel,
                    style = Typography.bodySmall,
                    modifier = Modifier.padding(0.dp)
                )
            }
            LottieAnimation(
                composition,
                progress,
                modifier = Modifier
                    .size(150.dp)
                    .padding(end = 20.dp)
            )
        }
        Spacer(modifier = Modifier
            .height(20.dp)
            .fillMaxWidth())
    }
}



@Composable
fun IconShapeSelect(CustomViewModel: CustomizationViewModel) {
    Row (
        modifier = Modifier
            .fillMaxWidth(1F)
            .padding(start = 28.dp, end = 28.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Image(
            painter = painterResource(id = R.drawable.google),
            contentDescription = "Square",
            modifier = Modifier
                .clip(IconShapes.extraSmall)
                .clickable { CustomViewModel.changeShape(IconShapes.extraSmall, 1) }
        )
        Image(
            painter = painterResource(id = R.drawable.google),
            contentDescription = "Square",
            modifier = Modifier
                .clip(IconShapes.small)
                .clickable { CustomViewModel.changeShape(IconShapes.small, 2) }
        )
        Image(
            painter = painterResource(id = R.drawable.google),
            contentDescription = "Square",
            modifier = Modifier
                .clip(IconShapes.medium)
                .clickable { CustomViewModel.changeShape(IconShapes.medium, 3) }
        )
        Image(
            painter = painterResource(id = R.drawable.google),
            contentDescription = "Square",
            modifier = Modifier
                .clip(IconShapes.large)
                .clickable { CustomViewModel.changeShape(IconShapes.large, 4) }

        )
    }
}


@Composable
fun Font(CustomViewModel: CustomizationViewModel) {
    val font : String by CustomViewModel.font.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxWidth(1F)
            .padding(horizontal = 14.dp)
            .padding(top = 5.dp, bottom = 8.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(1F)
                .clip(ThorakShapes.large)
                .background(NubYel)
                .padding(top = 5.dp, bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = font,
                modifier = Modifier
                    .padding(start = 14.dp)
                    .weight(1f),
                color = NubBlu,
                style = Typography.bodyLarge,
                fontFamily = getFont(font),
                fontWeight = FontWeight.Medium
            )
            Image(
                painter = painterResource(id = R.drawable.dropblu),
                contentDescription = "drop",
                modifier = Modifier.padding(end=14.dp)
            )

        }
    }
}



@Preview(showBackground = true)
@Composable
fun CustomizePreview() {
    Customize()
}

fun getFont(font : String): androidx.compose.ui.text.font.FontFamily {
    when (font){
        "Poppins" -> return Poppins
        "JosefinSans" -> return JosefinSans
        "Jura" -> return Jura
        "Lato" -> return Lato
        "Papyrus" -> return Papyrus
        "Phudu" -> return Phudu
        "SquadaOne" -> return SquadaOne
    }
    return Poppins
}