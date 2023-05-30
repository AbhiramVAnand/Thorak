package com.abhiram.thorak.ui.screens.tour

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.abhiram.thorak.R
import com.abhiram.thorak.helpers.PrefernceRepository
import com.abhiram.thorak.ui.screens.Route
import com.abhiram.thorak.ui.theme.DarkDim
import com.abhiram.thorak.ui.theme.NubYel
import com.abhiram.thorak.ui.theme.Poppins
import com.abhiram.thorak.ui.theme.Typography
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition


@Composable
fun StartUp(navController: NavController) {
    val prefernceRepository = PrefernceRepository(LocalContext.current)
//    prefernceRepository.addShape()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = DarkDim
    ) {
        Greeting(navController)
    }
}


@Composable
fun Greeting(navController: NavController, modifier: Modifier = Modifier) {
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
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(250.dp))
        LottieAnimation(
            composition,
            progress,
            modifier = Modifier
                .size(180.dp)
                .align(Alignment.CenterHorizontally)
                .padding(0.dp)
        )
        Text(
            text = "Thorak",
            color = NubYel,
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold,
            style = Typography.headlineLarge,
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
        )
        Text(
            text = stringResource(R.string.version),
            fontFamily = Poppins,
            color = NubYel,
            style = Typography.bodySmall
        )
        Spacer(modifier = Modifier.height(80.dp))
        IconButton(onClick = {
            navController.navigate(Route.Customize.route)
        },Modifier.size(200.dp)) {
            Image(painter = painterResource(id = R.drawable.start_arrow),
                contentDescription = "Start")
        }
    }
}